package co.edureka.controller;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import co.edureka.hibernate.orm.BookReviews;
import co.edureka.hibernate.orm.Books;
import co.edureka.service.BooksAndReviewsService;
import co.edureka.service.SolrSearchService;
import co.edureka.solr.SolrSearchData;
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
	
	
	@RequestMapping(value = { "/retrieveNextSearchDocsSegment"}, method = RequestMethod.GET)
	public ModelAndView retrieveNextSearchDocsSegment(HttpServletRequest request, HttpServletResponse response) {
		log.info("searchForDocs keyword text : : "+request.getParameter("keywordText"));
		
		if(request.getSession() == null){
			return null;
		}
		
		SolrSearchData ssd = new SolrSearchData();
		SolrSearchService solrService = new SolrSearchService();	
		
		String keywordsQuery = request.getSession().getAttribute("solrKeywordsQuery").toString();
	
		
		log.info("keywords : "+keywordsQuery);
		
		String titleQueryText = request.getSession().getAttribute("solrTitleQuery").toString();
		String authorQueryText = request.getSession().getAttribute("solrAuthorQuery").toString();
		
		String offset = request.getSession().getAttribute("solrPaginationOffset").toString();
		
		log.info("OFFSET : "+offset);
		
		SolrDocumentList solrDocListAuthorsSearch = null;
		
		if(!"".equals(authorQueryText)){
			solrDocListAuthorsSearch =  solrService.performQueryPaginated(authorQueryText, 5, Integer.parseInt(offset)+5);
			log.info("list solrDocListAuthorsSearch is : "+solrDocListAuthorsSearch.size());
		}
		
		request.getSession().setAttribute("solrPaginationOffset", Integer.parseInt(offset)+5);
		
		
		
		SolrDocumentList solrDocListTitleSearch = null;
		
		if(!"".equals(titleQueryText)){
			solrDocListTitleSearch = solrService.performQueryPaginated(titleQueryText, 5, Integer.parseInt(offset)+5);
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
		
		if(!"".equals(keywordsQuery)){
			solrDocListKeywordsSearch = solrService.performQueryPaginated(keywordsQuery, 5, Integer.parseInt(offset)+5);
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
			
			if(ssd.gettitle() == null || "".equals(ssd.gettitle().trim()) || "Unknown".equalsIgnoreCase(ssd.gettitle()) || "en".equalsIgnoreCase(ssd.gettitle())){

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
			formattedList.add("<b>Title : </b>"+title+"<b> Author : </b> "+author+" &nbsp; <b> link to doc </b> <a href='file://///"+ssd.getid()+"'"+
					" target="+"'"+"_blank"+"'"+">"+title+"</a><p>"+solrService.extractSpecifiedDocumentContent(ssd.getid(), 430)+
					"<i> <a href='reviewsSearchBook' class='html5lightbox' data-width='960' data-height='600'> ...see more</a></i></p>"+
					"<div style='display:none'>"+solrService.extractAllDocumentContent(ssd.getid())+"</div>");
			
		}

		
		request.getSession().setAttribute("solrSearchListReturned", returnList);
		
		log.info("list to return is : "+returnList.size());
		
		request.getSession().setAttribute("solrPaginationOffset", Integer.parseInt(offset)+5);
		
		// formattedList;
		
		ModelAndView model = new ModelAndView();	
		model.addObject("booksLists2", formattedList);
		model.setViewName("searchDocsPaginationPage"); 
		
		return model;
		
	}
	
}


