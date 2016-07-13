package co.edureka.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import co.edureka.hibernate.BookReviewsBusinessObject;
import co.edureka.hibernate.BooksBusinessObject;
import co.edureka.hibernate.TagsBusinessObject;
import co.edureka.hibernate.orm.BookReviews;
import co.edureka.hibernate.orm.BookTags;
import co.edureka.hibernate.orm.Books;
import co.edureka.viewmodel.BookReviewsModel;

public class BooksAndReviewsService {

	private ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	private BooksBusinessObject booksBO = (BooksBusinessObject) ctx.getBean("booksBusinessObject");
	private BookReviewsBusinessObject booksReviewsBO = (BookReviewsBusinessObject) ctx.getBean("booksReviewsBusinessObject");
	private TagsBusinessObject tagsBO = (TagsBusinessObject) ctx.getBean("tagsBusinessObject");
	
	/**
	 * @param title
	 * @param author
	 * @return
	 */
	public Books searchBooksByTitleAndOrAuthor(String title, String author){
		return booksBO.findBooksByTitleAndAuthor(title, author);
	}
	
	public HashMap<Books, List<BookReviews>> searchBookReviewsByTitleAndAuthor(String title, String author){
		return booksReviewsBO.findBooksReviewByTitleAndAuthor(title, author);
	}
	
	public List<Books> findBooksByTagsLazyLoad(HashMap<String, String> tagsKeyValues, int offset, int numberOfRecords){
		return tagsBO.findBooksByTagsLazyLoad(tagsKeyValues, offset, numberOfRecords);
	}
	
	public List<Books> findBooksByublisherLazyLoad(String publisher, int offset, int numberOfRecords){
		return booksReviewsBO.findBooksByPublishersLazyLoad(publisher, offset, numberOfRecords);
	}
	
	public HashMap<Books, List<BookReviews>> searchBookReviewsByTitleAndAuthor(String title, String author, int offset, int numberOfRecords){
		return booksReviewsBO.findBooksReviewByTitleAndAuthorLazyLoad(title, author, offset, numberOfRecords);
	}
	
	public void addBook(BookReviewsModel bookReviewsModel, HashMap<String, String> tagsAndValueMap){
		Books books = new Books();
		books.setTitle(bookReviewsModel.getTitleText());
		books.setAuthor(bookReviewsModel.getAuthorText());
		books.setPublisher(bookReviewsModel.getPublisherText());
		
		BookTags bookTags = null;

		try{
			
			booksBO.save(books);
			
			for(String key : tagsAndValueMap.keySet()){
				 bookTags = new BookTags();
				 bookTags.setTagType(key);
				 bookTags.setTagValue(tagsAndValueMap.get(key));
				 bookTags.setIdbooks(books.getIdbooks());
				 tagsBO.save(bookTags);
			}
			
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public void addReview(int bookID, String username, String reviewText){
		System.out.println("add review : "+bookID+" : "+username+" : "+reviewText);
		BookReviews bookReview = new BookReviews();
		bookReview.setIdbooks(bookID);
		bookReview.setReviewersUsername(username);
		bookReview.setReviewText(reviewText);
		booksReviewsBO.save(bookReview);
	}
}
