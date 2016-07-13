package co.edureka.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	@RequestMapping(value = { "/retrieveNextReviewsSegment"}, method = RequestMethod.GET)
	public ModelAndView retrieveNextReviewsSegment(HttpServletRequest request, HttpServletResponse response) {

		System.out.println("we getting in here retrieveNextSegment?");
		
		System.out.println("bookTitleFound retrieveNextSegment : "+request.getSession().getAttribute("bookTitleFound")); 
		System.out.println("bookAuthorFound  retrieveNextSegment : "+request.getSession().getAttribute("bookAuthorFound")); 
		
		BookReviewsModel bookReviewsModel = new BookReviewsModel();
		bookReviewsModel.setBookTitleReview(request.getSession().getAttribute("bookTitleFound").toString());
		bookReviewsModel.setBookAuthorReview(request.getSession().getAttribute("bookAuthorFound").toString());
		
		BooksAndReviewsService booksService = new BooksAndReviewsService();
		
		String currentOffsetInSession = request.getSession().getAttribute("currentPaginationOffset").toString();
		
		System.out.println("currentOffset : "+currentOffsetInSession);
		
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
		
		System.out.println("size of reviews list returned : "+list.size());
		ModelAndView model = new ModelAndView();	
		//model.addObject("bookReviewsModel", bookReviewsModel);
		model.addObject("reviewLists2", list);
		model.setViewName("reviewsPaginationPage"); //reviewsPaginationPage
		return model;
	}
	
	
	@RequestMapping(value = { "/retrieveNextSearchSegment"}, method = RequestMethod.GET)
	public ModelAndView retrieveNextSearchSegment(HttpServletRequest request, HttpServletResponse response) {

		System.out.println("we getting in here retrieveNextSegment?");
		
		System.out.println("bookTitleFound retrieveNextSegment : "+request.getSession().getAttribute("bookTitleFound")); 
		System.out.println("bookAuthorFound  retrieveNextSegment : "+request.getSession().getAttribute("bookAuthorFound")); 
		
		BookReviewsModel bookReviewsModel = new BookReviewsModel();
		bookReviewsModel.setBookTitleReview(request.getSession().getAttribute("bookTitleFound").toString());
		bookReviewsModel.setBookAuthorReview(request.getSession().getAttribute("bookAuthorFound").toString());
		
		BooksAndReviewsService booksService = new BooksAndReviewsService();
		
		String currentOffsetInSession = request.getSession().getAttribute("currentPaginationOffset").toString();
		
		System.out.println("currentOffset : "+currentOffsetInSession);
		
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
		
		System.out.println("size of reviews list returned : "+list.size());
		ModelAndView model = new ModelAndView();	
		//model.addObject("bookReviewsModel", bookReviewsModel);
		model.addObject("reviewLists2", list);
		model.setViewName("reviewsPaginationPage"); //reviewsPaginationPage
		return model;
	}
	
	
}


