package co.edureka.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import co.edureka.hibernate.orm.BookReviews;
import co.edureka.hibernate.orm.Books;
import co.edureka.service.BooksAndReviewsService;
import co.edureka.viewmodel.BookReviewsModel;


@Controller
@EnableWebMvc
public class PaginationController {

	private final static Logger log = Logger.getLogger(PaginationController.class); 
	
	@RequestMapping(value = { "/retrieveNextReviewsSegment"}, method = RequestMethod.GET)
	public ModelAndView retrieveNextReviewsSegment(HttpServletRequest request, HttpServletResponse response) {

		
		if(request.getSession() == null){
			return null;
		}
		
		log.info("we getting in here retrieveNextSegment?");
		
		log.info("bookTitleFound retrieveNextSegment : "+request.getSession().getAttribute("bookTitleFound")); 
		log.info("bookAuthorFound  retrieveNextSegment : "+request.getSession().getAttribute("bookAuthorFound")); 
		
		BookReviewsModel bookReviewsModel = new BookReviewsModel();
		bookReviewsModel.setBookTitleReview(request.getSession().getAttribute("bookTitleFound").toString());
		bookReviewsModel.setBookAuthorReview(request.getSession().getAttribute("bookAuthorFound").toString());
		
		BooksAndReviewsService booksService = new BooksAndReviewsService();
		
		String currentOffsetInSession = request.getSession().getAttribute("currentPaginationOffset").toString();
		
		log.info("currentOffset : "+currentOffsetInSession);
		
		int latestOffset = Integer.parseInt(currentOffsetInSession)+20;
		
		HashMap<Books, List<BookReviews>> bookMap = booksService.searchBookReviewsByTitleAndAuthor(request.getSession().getAttribute("bookTitleFound").toString(), 
				request.getSession().getAttribute("bookAuthorFound").toString(), latestOffset, 20);

		request.getSession().setAttribute("currentPaginationOffset", latestOffset);
		
		
		ArrayList<String> list = new ArrayList<String>();
		
		for(Books book : bookMap.keySet()){	
			bookMap.get(book);
			
			for(BookReviews bookRev : bookMap.get(book)){
				list.add(bookRev.getReviewText()+" - reviewed by -  "+bookRev.getReviewersUsername());
			}
		}
		
		log.info("size of reviews list returned : "+list.size());
		ModelAndView model = new ModelAndView();	
		//model.addObject("bookReviewsModel", bookReviewsModel);
		model.addObject("reviewLists2", list);
		model.setViewName("reviewsPaginationPage"); //reviewsPaginationPage
		return model;
	}
	
	
	@RequestMapping(value = { "/retrieveNextSearchSegment"}, method = RequestMethod.GET)
	public ModelAndView retrieveNextSearchSegment(HttpServletRequest request, HttpServletResponse response) {

		if(request.getSession() == null){
			return null;
		}
		
		log.info("we getting in here retrieveNextSegment?");
		
		log.info("bookTitleFound retrieveNextSegment : "+request.getSession().getAttribute("bookTitleFound")); 
		log.info("bookAuthorFound  retrieveNextSegment : "+request.getSession().getAttribute("bookAuthorFound")); 
		
		BookReviewsModel bookReviewsModel = new BookReviewsModel();
		//bookReviewsModel.setBookTitleReview(request.getSession().getAttribute("bookTitleFound").toString());
		//bookReviewsModel.setBookAuthorReview(request.getSession().getAttribute("bookAuthorFound").toString());
		
		BooksAndReviewsService booksService = new BooksAndReviewsService();
		
		String currentOffsetInSession = request.getSession().getAttribute("currentPaginationOffset").toString();
		
		log.info("currentOffset : "+currentOffsetInSession);
		
		int latestOffset = Integer.parseInt(currentOffsetInSession)+20;
		
		String searchType = request.getSession().getAttribute("searchType").toString();
		log.info("search type : "+searchType);
		int paginationOffset = Integer.parseInt(request.getSession().getAttribute("currentPaginationOffset").toString());
		log.info("paginationOffset: "+paginationOffset);
		List<Books> booksList = new ArrayList<Books>();
		
		if("findBooksByPublisherLazyLoad".equalsIgnoreCase(searchType)){
			
			log.info("doing findBooksByPublisherLazyLoad");
			
			String publisherVal = request.getSession().getAttribute("publisherText").toString();			
			booksList = booksService.findBooksByPublisherLazyLoad(publisherVal, latestOffset, 20);
			request.getSession().setAttribute("currentPaginationOffset", (paginationOffset +20));
			
		}else if("findBooksByTagsLazyLoad".equalsIgnoreCase(searchType)){
			log.info("doing findBooksByTagsLazyLoad");
			HashMap<String, String> tagsAndValueMap = (HashMap<String, String>)request.getSession().getAttribute("tagsAndValueMap");
			booksList = booksService.findBooksByTagsLazyLoad(tagsAndValueMap, paginationOffset+20, 20);
			request.getSession().setAttribute("currentPaginationOffset", (paginationOffset +20));
		}else{
			log.info("just here");
		}
		log.info("size of booksList list returned : "+booksList.size());
		//log.info("size of booksList2 list returned : "+booksLists2.size());
		List<String> booksLists2 = new ArrayList<String>();
//		HashMap<Books, List<BookReviews>> bookMap = booksService.searchBookReviewsByTitleAndAuthor(request.getSession().getAttribute("bookTitleFound").toString(), 
//				request.getSession().getAttribute("bookAuthorFound").toString(), latestOffset, 20);

	//	request.getSession().setAttribute("currentPaginationOffset", (paginationOffset +20));
		ArrayList<String> list = new ArrayList<String>();
		
		for(Books book : booksList){	
			booksLists2.add(book.getTitle()+" - "+book.getAuthor());
		}
		
		log.info("22 size of booksList list returned : "+booksList.size());
		log.info("22 size of booksList2 list returned : "+booksLists2.size());
		ModelAndView model = new ModelAndView();	
		//model.addObject("bookReviewsModel", bookReviewsModel);
		model.addObject("booksLists2", booksLists2);
		model.setViewName("searchPaginationPage"); //reviewsPaginationPage
		return model;
	}
	
	
}


