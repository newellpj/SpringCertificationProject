package co.edureka.controller;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import co.edureka.hibernate.orm.BookReviews;
import co.edureka.hibernate.orm.Books;
import co.edureka.service.BooksAndReviewsService;
import co.edureka.service.SolrSearchService;
import co.edureka.solr.SolrSearchData;
import co.edureka.viewmodel.BookReviewsModel;

@Controller
public class ReviewController {

	private final static Logger log = Logger.getLogger(ReviewController.class); 
	
	@RequestMapping(value = { "/reviews"}, method = RequestMethod.GET)
	public ModelAndView welcomePage() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		log.info("we getting in here user logged in here - "+auth.getName());
		ModelAndView model = new ModelAndView();		
		model.setViewName("reviews");
		return model;
	}
	
	@RequestMapping(value = { "/reviewsAddBook"}, method = RequestMethod.GET)
	public ModelAndView addBookPage() {
		log.info("we getting in here reviewsAddBook?");
		ModelAndView model = new ModelAndView();		
		model.setViewName("reviewsAddBook");
		return model;
	}

	@RequestMapping(value = { "/reviewsSearchBook"}, method = RequestMethod.GET)
	public ModelAndView addSearchPage() {
		log.info("we getting in here reviewsSearchBook?");
		ModelAndView model = new ModelAndView();		
		model.setViewName("reviewsSearchBook");
		return model;
	}
	
	@RequestMapping(value = { "/reviewsSearchDocs"}, method = RequestMethod.GET)
	public ModelAndView addDocsSearchPage() {
		log.info("we getting in here addDocsSearchPage?");
		ModelAndView model = new ModelAndView();		
		model.setViewName("reviewsSearchDocs");
		return model;
	}
	
	@RequestMapping(value = { "/getBookReviewsList"}, method = RequestMethod.GET)
	public ModelAndView getBookReviewsList(HttpServletRequest request, HttpServletResponse response) {
		
		if(request.getSession() == null){
			return null;
		}
		
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

		
		if(request.getSession() == null){
			return null;
		}
		
		log.info("we getting in here reviewsReviewBook?");
		
		log.info("bookTitleFound : "+request.getSession().getAttribute("bookTitleFound")); 
		log.info("bookAuthorFound : "+request.getSession().getAttribute("bookAuthorFound")); 
		
		BookReviewsModel bookReviewsModel = new BookReviewsModel();
		
	
		
		BooksAndReviewsService booksService = new BooksAndReviewsService();
		ModelAndView model = new ModelAndView();	
		
		String searchSelectedBook = request.getParameter("titleAuthorText");
		log.info("searchSelectedBook : "+searchSelectedBook);
		
		if(searchSelectedBook != null && !"".equals(searchSelectedBook)){
			//the existence of the request parameter searchSelectedBook - means we are coming to the reviews page from the search book page AND NOT the add book page.
			String title = searchSelectedBook.substring(0, searchSelectedBook.lastIndexOf("-")).trim();
			String author = searchSelectedBook.substring(searchSelectedBook.lastIndexOf("-")+1).trim();
			
			title = title.replaceAll("-", " ");
			
			log.info("title : "+title);
			
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
					list.add(bookRev.getReviewText()+" - <b>reviewed by "+bookRev.getReviewersUsername()+"</b>");
				}
			}
			
			if(list.size() == 0){
				list.add("No Reviews found for title.");
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
		
		if(request.getSession() == null){
			return null;
		}
		
		
		log.info(" addBookReview request "+request.toString());
		log.info("request contain titleText ? : "+request.getParameter("titleText"));
		log.info("request contain authorText ? : "+request.getParameter("authorText"));
	
		
		log.info("request contain titleText ? : "+request.getParameter("titleText"));
		log.info("request contain authorText ? : "+request.getParameter("authorText"));
		log.info("request contain reviewText ? : "+request.getParameter("reviewText"));
		//log.info("author from map : "+bookReviewsModel.getAuthorText());
		
		BooksAndReviewsService booksService = new BooksAndReviewsService();
		int bookID = (request.getSession().getAttribute("bookID") != null) ? Integer.parseInt(request.getSession().getAttribute("bookID").toString()) : -1;
		
		log.info("bookID : "+bookID);
		
		if(bookID == -1){
			log.info("BOOK != -1");
			Books book = booksService.searchBooksByTitleAndOrAuthor(request.getParameter("titleText"), request.getParameter("authorText")).get(0);
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
		log.info("request "+request.toString());
		
		if(request.getSession() == null){
			return null;
		}
		
		log.info("request contain titleText ? : "+request.getParameter("titleText"));
		log.info("request contain authorText ? : "+request.getParameter("authorText"));
	
		
		log.info("request contain titleText ? : "+request.getParameter("titleText"));
		log.info("request contain authorText ? : "+request.getParameter("authorText"));
		//log.info("author from map : "+bookReviewsModel.getAuthorText());
		
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
		
		if(request.getSession() == null){
			return null;
		}
		
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
		request.getSession().setAttribute("solrSearchListReturned", null);
	}
	
	
	
	@RequestMapping(value = { "/searchForDocs"}, method = RequestMethod.GET)
	public @ResponseBody List<String> searchForDocs(HttpServletRequest request, HttpServletResponse response){
		log.info("searchForDocs keyword text : : "+request.getParameter("keywordText"));
		
		resetSearchSessionAttributes(request);
		SolrSearchData ssd = new SolrSearchData();
		SolrSearchService solrService = new SolrSearchService();	
		String keywords = request.getParameter("keywordText");
		keywords = keywords.replaceAll(",", " ");
		
		log.info("keywords : "+keywords);
		
		String titleText = request.getParameter("titleText");
		String authorText = request.getParameter("authorText");
		
		
		SolrDocumentList solrDocListAuthorsSearch = null;
		
		if(!"".equals(authorText)){
			solrDocListAuthorsSearch =  solrService.performQueryPaginated("author:"+authorText, 5, 0);
			log.info("list solrDocListAuthorsSearch is : "+solrDocListAuthorsSearch.size());
		}
		
		
		
		SolrDocumentList solrDocListTitleSearch = null;
		
		if(!"".equals(titleText)){
			solrDocListTitleSearch = solrService.performQuery("title:"+titleText);
			log.info("list solrDocListTitleSearch is : "+solrDocListTitleSearch.size());
		}
		
		
		
		SolrDocumentList filteredList = new SolrDocumentList();
		
		if(solrDocListTitleSearch != null && solrDocListTitleSearch.size() > 0 && 
				solrDocListAuthorsSearch != null && solrDocListAuthorsSearch.size() > 0){
			
			for(SolrDocument solrDoc : solrDocListTitleSearch){
				
				for(SolrDocument solrDocAuthors : solrDocListAuthorsSearch){
					if(solrDocAuthors.getFieldValue("id").toString().equals(solrDoc.getFieldValue("id").toString())){
						filteredList.add(solrDoc);
					}
				}
			}
			
		}else{
			
			if(solrDocListAuthorsSearch != null){
				filteredList.addAll(solrDocListAuthorsSearch);
			}
			
			if(solrDocListTitleSearch != null){
				filteredList.addAll(solrDocListTitleSearch);
			}
		}
		
		
		log.info("filteredList size "+filteredList.size());
		
		SolrDocumentList solrDocListKeywordsSearch = null;
		
		if(!"".equals(keywords)){
			solrDocListKeywordsSearch = solrService.performQuery(keywords);
			log.info("list solrDocListKeywordsSearch is : "+solrDocListKeywordsSearch.size());
		}

		for(SolrDocument solrDocument : filteredList){
			log.info("solrDocument filtered list ID : "+solrDocument.getFieldValue("id"));
		}
		
		SolrDocumentList finalisedFilteredList = new SolrDocumentList();
		
		if(solrDocListKeywordsSearch != null && solrDocListKeywordsSearch.size() > 0 && filteredList.size() > 0){
			for(SolrDocument solrDocument : solrDocListKeywordsSearch){
				
				log.info("keywords list ID : "+solrDocument.getFieldValue("id"));
				
				for(SolrDocument solrDocFiltered : filteredList){
					if(solrDocFiltered.getFieldValue("id").toString().equals(solrDocument.getFieldValue("id").toString())){
						finalisedFilteredList.add(solrDocument);
					}
				}
			}
		}else{
			finalisedFilteredList.addAll(filteredList);
			
			if(solrDocListKeywordsSearch != null){
				finalisedFilteredList.addAll(solrDocListKeywordsSearch);
			}
			
		}
		
		log.info("finalisedFilteredList size "+finalisedFilteredList.size());

		List<SolrSearchData> returnList = new ArrayList<SolrSearchData>();
		List<String> formattedList = new ArrayList<String>();

		for(SolrDocument solrD : finalisedFilteredList){
			
			ssd = new SolrSearchData();
			
			for(String field : solrService.getFieldsArray()){
				String fieldToSet = (solrD.getFieldValue(field) != null) ? solrD.getFieldValue(field).toString() : "";
				
				try{
					Method method = ssd.getClass().getDeclaredMethod("set"+field, String.class);
					method.invoke(ssd, fieldToSet);
				}catch(Exception e){
					e.printStackTrace();
					log.error(e.getMessage());
				}
			}
			
			log.info("author set : "+ssd.getauthor());
			log.info("title set : "+ssd.gettitle());
			log.info("id set : "+ssd.getid());
			
			returnList.add(ssd);
			
			String title = "";
			
			if(ssd.gettitle() == null || "".equals(ssd.gettitle().trim())){

				if(ssd.getid().lastIndexOf(File.separator) > -1){
					title = ssd.getid().substring(ssd.getid().lastIndexOf(File.separator)+1);
				}else{
					title = ssd.getid();
				}
			}else{
				title = ssd.gettitle();
			}
			
			log.info("title "+title);

			String author = ssd.getauthor().replaceAll("\\[", "").replaceAll("\\]","");
			log.info("author 2 : "+author);
			formattedList.add("<b>Title : </b>"+title+"<b> Author : </b> "+author+" link to doc <a href='file://///"+ssd.getid()+"'"+" target="+"'"+"_blank"+"'"+">"+title+"</a>");
			
		}
		
		if(formattedList.size() == 0){
			formattedList.add("No documents found..");
		}
		
//		Gson gson = new Gson();
//		
//		var jsonSolrDocList = gson.toJson(returnList);
//
//		 System.out.println("jsonCartList: " + jsonSolrDocList);
		
		request.getSession().setAttribute("solrSearchListReturned", returnList);
		
		log.info("list to return is : "+returnList.size());
		
		
		
		return formattedList;
	}
	
	@RequestMapping(value = { "/searchForBook"}, method = RequestMethod.GET)
	public @ResponseBody BookReviewsModel searchBook(HttpServletRequest request, HttpServletResponse response){
		
		log.info("request.getSession() : "+request.getSession());
		
		if(request.getSession() == null){
			return null;
		}
		
		resetSearchSessionAttributes(request);
		log.info("request contain titleText ? : "+request.getParameter("titleText"));
		log.info("request contain authorText ? : "+request.getParameter("authorText"));
		log.info("request contain publisherText ? : "+request.getParameter("publisherText"));
		//log.info("author from map : "+bookReviewsModel.getAuthorText());
		
		String titleText = request.getParameter("titleText");
		String authorText = request.getParameter("authorText");
		String publisherText = request.getParameter("publisherText");
		
		HashMap<String, String> tagsAndValueMap = new HashMap<String, String>();
		
		if(request.getParameter("genreText") != null && !"".equals(request.getParameter("genreText"))){
			tagsAndValueMap.put("genreText", request.getParameter("genreText"));
			log.info("genreText to search on : "+request.getParameter("genreText"));
		}
		
		if(request.getParameter("catText") != null && !"".equals(request.getParameter("catText"))){
			log.info("catText to search on : "+request.getParameter("catText"));
			
			tagsAndValueMap.put("catText", request.getParameter("catText"));
		}
		
		if(request.getParameter("langText") != null && !"".equals(request.getParameter("langText"))){
			log.info("lang text to search on : "+request.getParameter("langText"));
			tagsAndValueMap.put("langText", request.getParameter("langText"));
		}
		
		
		log.info("just before service instantiation !");
		
		BooksAndReviewsService booksService = new BooksAndReviewsService();
		
		List<Books> booksList = new ArrayList<Books>();
		log.info("just before test !");
		
		if(titleText != null && !"".equals(titleText) || (!"".equals(authorText) && authorText != null)){
			log.info("in here111");
			booksList.addAll(booksService.searchBooksByTitleAndOrAuthor(request.getParameter("titleText"), request.getParameter("authorText")));
			
		}else if(publisherText != null && !"".equals(publisherText)){
			log.info("in here222");
			booksList.addAll(booksService.findBooksByPublisherLazyLoad(publisherText, 0, 20));
			request.getSession().setAttribute("publisherText", publisherText);
			request.getSession().setAttribute("searchType", "findBooksByPublisherLazyLoad");
		}else{
			log.info("in here333");
			booksList.addAll(booksService.findBooksByTagsLazyLoad(tagsAndValueMap, 0, 20));
			request.getSession().setAttribute("searchType", "findBooksByTagsLazyLoad");
			request.getSession().setAttribute("tagsAndValueMap", tagsAndValueMap);
		}

		ModelAndView modelView = new ModelAndView();
		List<String> booksStringViewList = new ArrayList<String>();
		
		log.info("book list : "+booksList.size());
		
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
			
			booksStringViewList.add("No books found");
			
			//return booksStringViewList;
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
