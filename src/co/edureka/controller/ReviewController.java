package co.edureka.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import co.edureka.hibernate.orm.BookReviews;
import co.edureka.hibernate.orm.Books;
import co.edureka.service.BooksAndReviewsService;
import co.edureka.viewmodel.BookReviewsModel;

@Controller
@EnableWebMvc
public class ReviewController {

	@RequestMapping(value = { "/reviews"}, method = RequestMethod.GET)
	public ModelAndView welcomePage() {
		System.out.println("we getting in here reviews ");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("we getting in here user logged in here - "+auth.getName());
		ModelAndView model = new ModelAndView();		
		model.setViewName("reviews");
		return model;
	}
	
	@RequestMapping(value = { "/reviewsAddBook"}, method = RequestMethod.GET)
	public ModelAndView addBookPage() {
		System.out.println("we getting in here reviewsAddBook?");
		ModelAndView model = new ModelAndView();		
		model.setViewName("reviewsAddBook");
		return model;
	}

	@RequestMapping(value = { "/reviewsSearchBook"}, method = RequestMethod.GET)
	public ModelAndView addSearchPage() {
		System.out.println("we getting in here reviewsSearchBook?");
		ModelAndView model = new ModelAndView();		
		model.setViewName("reviewsSearchBook");
		return model;
	}
	
	@RequestMapping(value = { "/getBookReviewsList"}, method = RequestMethod.GET)
	public ModelAndView getBookReviewsList(HttpServletRequest request, HttpServletResponse response) {
		
		//TODO add book review then bring back all back reviews with this one added - paginated!
		
		BooksAndReviewsService booksService = new BooksAndReviewsService();
		HashMap<Books, List<BookReviews>> bookMap = booksService.searchBookReviewsByTitleAndAuthor(request.getSession().getAttribute("bookTitleFound").toString(), 
				request.getSession().getAttribute("bookAuthorFound").toString());
		
		ModelAndView modelView = new ModelAndView("reviewsReviewBook");
		modelView.addObject("reviewsList", bookMap.values());
		return modelView;
	}
	
	@RequestMapping(value = { "/reviewsReviewBook"}, method = RequestMethod.GET)
	public ModelAndView addReviewsPage(HttpServletRequest request, HttpServletResponse response) {

		System.out.println("we getting in here reviewsReviewBook?");
		
		System.out.println("bookTitleFound : "+request.getSession().getAttribute("bookTitleFound")); 
		System.out.println("bookAuthorFound : "+request.getSession().getAttribute("bookAuthorFound")); 
		
		BookReviewsModel bookReviewsModel = new BookReviewsModel();
		
	
		
		BooksAndReviewsService booksService = new BooksAndReviewsService();
		ModelAndView model = new ModelAndView();	
		
		String searchSelectedBook = request.getParameter("titleAuthorText");
		System.out.println("searchSelectedBook : "+searchSelectedBook);
		
		if(searchSelectedBook != null && !"".equals(searchSelectedBook)){
			//the existence of the request parameter searchSelectedBook - means we are coming to the reviews page from the search book page AND NOT the add book page.
			String title = searchSelectedBook.substring(0, searchSelectedBook.lastIndexOf("-")).trim();
			String author = searchSelectedBook.substring(searchSelectedBook.lastIndexOf("-")+1).trim();
			
			title = title.replaceAll("-", " ");
			
			System.out.println("title : "+title);
			
			request.getSession().setAttribute("bookTitleFound", title);
			request.getSession().setAttribute("bookAuthorFound", author);
		}
		
		String bookTitleFound = (request.getSession().getAttribute("bookTitleFound") != null) ? request.getSession().getAttribute("bookTitleFound").toString() : ""; 
		String bookAuthorFound = (request.getSession().getAttribute("bookAuthorFound") != null) ? request.getSession().getAttribute("bookAuthorFound").toString() : ""; 
		
		bookReviewsModel.setBookTitleReview(bookTitleFound);
		bookReviewsModel.setBookAuthorReview(bookAuthorFound);
		
		
		if(!"".equals(bookTitleFound)){
		
			HashMap<Books, List<BookReviews>> bookMap = booksService.searchBookReviewsByTitleAndAuthor(request.getSession().getAttribute("bookTitleFound").toString(), 
					request.getSession().getAttribute("bookAuthorFound").toString(),0,20);
			request.getSession().setAttribute("currentPaginationOffset", 0);
			ArrayList<String> list = new ArrayList<String>();
			
			for(Books book : bookMap.keySet()){	
				bookMap.get(book);
				
				for(BookReviews bookRev : bookMap.get(book)){
					list.add(bookRev.getReviewText()+" - reviewed by -  "+bookRev.getReviewersUsername());
				}
			}
			
			model.addObject("reviewLists", list);
		}else{
			request.getSession().setAttribute("bookAuthorFound", "");
			request.getSession().setAttribute("bookTitleFound", "");
			request.getSession().setAttribute("currentPaginationOffset", 0);
		}
		
		model.addObject("bookReviewsModel", bookReviewsModel);	
		model.setViewName("reviewsReviewBook");
		return model;
	}
	
	
	
	@RequestMapping(value = { "/addBookReview"}, method = RequestMethod.GET)
	public @ResponseBody BookReviewsModel addBookReview(HttpServletRequest request, HttpServletResponse response){
		System.out.println(" addBookReview request "+request.toString());
		System.out.println("request contain titleText ? : "+request.getParameter("titleText"));
		System.out.println("request contain authorText ? : "+request.getParameter("authorText"));
	
		
		System.out.println("request contain titleText ? : "+request.getParameter("titleText"));
		System.out.println("request contain authorText ? : "+request.getParameter("authorText"));
		System.out.println("request contain reviewText ? : "+request.getParameter("reviewText"));
		//System.out.println("author from map : "+bookReviewsModel.getAuthorText());
		
		BooksAndReviewsService booksService = new BooksAndReviewsService();
		int bookID = (request.getSession().getAttribute("bookID") != null) ? Integer.parseInt(request.getSession().getAttribute("bookID").toString()) : -1;
		
		System.out.println("bookID : "+bookID);
		
		if(bookID == -1){
			System.out.println("BOOK != -1");
			Books book = booksService.searchBooksByTitleAndOrAuthor(request.getParameter("titleText"), request.getParameter("authorText"));
			bookID = book.getIdbooks();
		}
		
		booksService.addReview(bookID, SecurityContextHolder.getContext().getAuthentication().getName(), request.getParameter("reviewText").toString());
		//addReview(request.getParameter("titleText"), request.getParameter("authorText"));
	
		ModelAndView modelAndView = new ModelAndView();
		
		BookReviewsModel bookReviewsModel = new BookReviewsModel();
		bookReviewsModel.setTitleText(request.getParameter("titleText"));
		bookReviewsModel.setAuthorText(request.getParameter("authorText"));
		bookReviewsModel.setReviewText(request.getParameter("reviewText"));
		
		//store returned values in session
		
		request.getSession().setAttribute("bookTitleFound", request.getParameter("titleText"));
		request.getSession().setAttribute("bookAuthorFound", request.getParameter("authorText"));
		//request.getSession().setAttribute("bookReviewText", request.getParameter("reviewText"));
		
	
		//modelAndView.setViewName("reviews");
	//	modelAndView.addObject("bookReviewsModel", bookReviewsModel);
		return bookReviewsModel;
	}
	
	
	@RequestMapping(value = { "/addNewBook"}, method = RequestMethod.GET)
	public @ResponseBody BookReviewsModel addNewBook(HttpServletRequest request, HttpServletResponse response){
		System.out.println("request "+request.toString());
		System.out.println("request contain titleText ? : "+request.getParameter("titleText"));
		System.out.println("request contain authorText ? : "+request.getParameter("authorText"));
	
		
		System.out.println("request contain titleText ? : "+request.getParameter("titleText"));
		System.out.println("request contain authorText ? : "+request.getParameter("authorText"));
		//System.out.println("author from map : "+bookReviewsModel.getAuthorText());
		
		BooksAndReviewsService booksService = new BooksAndReviewsService();
		
		BookReviewsModel bookReviewsModel = new BookReviewsModel();
		bookReviewsModel.setTitleText(request.getParameter("titleText"));
		bookReviewsModel.setAuthorText(request.getParameter("authorText"));
		bookReviewsModel.setPublisherText(request.getParameter("publisherText"));
		
		HashMap<String, String> tagsAndValueMap = new HashMap<String, String>();
		tagsAndValueMap.put("genreText", request.getParameter("genreText"));
		tagsAndValueMap.put("catText", request.getParameter("catText"));
		tagsAndValueMap.put("langText", request.getParameter("langText"));
		
		booksService.addBook(bookReviewsModel, tagsAndValueMap);
	
		ModelAndView modelAndView = new ModelAndView();
		
		
		//store returned values in session
		
		request.getSession().setAttribute("bookTitleFound", request.getParameter("titleText"));
		request.getSession().setAttribute("bookAuthorFound", request.getParameter("authorText"));
		request.getSession().setAttribute("bookPublisherFound", request.getParameter("publisherText"));
		
		modelAndView.setViewName("reviews");
		return bookReviewsModel;
	}
	
	@RequestMapping(value = { "/searchForBook2"}, method = RequestMethod.GET)
	public @ResponseBody BookReviewsModel searchBook2(HttpServletRequest request, HttpServletResponse response){
		
		request.getSession().setAttribute("langText", request.getParameter("langText"));
		request.getSession().setAttribute("publisherText", request.getParameter("publisherText"));
		return new BookReviewsModel();
	}
	
	
	@RequestMapping(value = { "/resetSearch"}, method = RequestMethod.GET)
	public @ResponseBody BookReviewsModel resetSearch(HttpServletRequest request, HttpServletResponse response){
		request.getSession().removeAttribute("bookAuthorFound");
		request.getSession().removeAttribute("bookTitleFound");
		request.getSession().removeAttribute("currentPaginationOffset");
		request.getSession().removeAttribute("searchType");
		request.getSession().removeAttribute("tagsAndValueMap");
		request.getSession().removeAttribute("publisherText");
		
		return new BookReviewsModel();
	}
	
	private void resetSearchSessionAttributes(HttpServletRequest request){
		request.getSession().setAttribute("publisherText", "");
		request.getSession().setAttribute("titleText", "");
		request.getSession().setAttribute("authorText", "");
		request.getSession().setAttribute("genreText", "");
		request.getSession().setAttribute("catText", "");
		request.getSession().setAttribute("langText", "");
		request.getSession().setAttribute("searchType", "");
		request.getSession().setAttribute("tagsAndValueMap", null);
		request.getSession().setAttribute("currentPaginationOffset", 0);
	}
	
	@RequestMapping(value = { "/searchForBook"}, method = RequestMethod.GET)
	public @ResponseBody BookReviewsModel searchBook(HttpServletRequest request, HttpServletResponse response){
		
		resetSearchSessionAttributes(request);
		System.out.println("request contain titleText ? : "+request.getParameter("titleText"));
		System.out.println("request contain authorText ? : "+request.getParameter("authorText"));
		System.out.println("request contain publisherText ? : "+request.getParameter("publisherText"));
		//System.out.println("author from map : "+bookReviewsModel.getAuthorText());
		
		String titleText = request.getParameter("titleText");
		String authorText = request.getParameter("authorText");
		String publisherText = request.getParameter("publisherText");
		
		HashMap<String, String> tagsAndValueMap = new HashMap<String, String>();
		
		if(request.getParameter("genreText") != null && !"".equals(request.getParameter("genreText"))){
			tagsAndValueMap.put("genreText", request.getParameter("genreText"));
		}
		
		if(request.getParameter("catText") != null && !"".equals(request.getParameter("catText"))){
			tagsAndValueMap.put("catText", request.getParameter("catText"));
		}
		
		if(request.getParameter("langText") != null && !"".equals(request.getParameter("langText"))){
			tagsAndValueMap.put("langText", request.getParameter("langText"));
		}
		
		BooksAndReviewsService booksService = new BooksAndReviewsService();
		
		List<Books> booksList = new ArrayList<Books>();
		
		if(titleText != null && !"".equals(titleText) && !"".equals(authorText) && authorText != null){
			System.out.println("in here111");
			Books book = booksService.searchBooksByTitleAndOrAuthor(request.getParameter("titleText"), request.getParameter("authorText"));
			booksList.add(book);
			
		}else if(publisherText != null && !"".equals(publisherText)){
			System.out.println("in here222");
			booksList.addAll(booksService.findBooksByPublisherLazyLoad(publisherText, 0, 20));
			request.getSession().setAttribute("publisherText", publisherText);
			request.getSession().setAttribute("searchType", "findBooksByPublisherLazyLoad");
		}else{
			System.out.println("in here333");
			booksList.addAll(booksService.findBooksByTagsLazyLoad(tagsAndValueMap, 0, 20));
			request.getSession().setAttribute("searchType", "findBooksByTagsLazyLoad");
			request.getSession().setAttribute("tagsAndValueMap", tagsAndValueMap);
		}

		ModelAndView modelView = new ModelAndView();
		List<String> booksStringViewList = new ArrayList<String>();
		
		System.out.println("book list : "+booksList.size());
		
		if(booksList != null && booksList.size() > 0){
			
			for(Books books : booksList){
				booksStringViewList.add(books.getTitle()+" - "+books.getAuthor());
			}
			
			modelView.addObject("booksList", booksStringViewList);
			request.getSession().setAttribute("currentPaginationOffset", 0);
			
		}else{
			request.getSession().setAttribute("bookAuthorFound", "");
			request.getSession().setAttribute("bookTitleFound", "");
			request.getSession().setAttribute("currentPaginationOffset", 0);
			return null;
		}
		
		BookReviewsModel bookReviewsModel = new BookReviewsModel();
		bookReviewsModel.setTitleText(request.getParameter("titleText"));
		bookReviewsModel.setAuthorText(request.getParameter("authorText"));
		bookReviewsModel.setPublisherText(request.getParameter("publisherText"));
		
		bookReviewsModel.setBooksList(booksStringViewList);
		modelView.setViewName("reviewsSearchBook");
		return bookReviewsModel;

	}


}
