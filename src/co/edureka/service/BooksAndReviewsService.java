package co.edureka.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import co.edureka.hibernate.BookReviewsBusinessObject;
import co.edureka.hibernate.BooksBusinessObject;
import co.edureka.hibernate.orm.BookReviews;
import co.edureka.hibernate.orm.Books;

public class BooksAndReviewsService {

	private ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	private BooksBusinessObject booksBO = (BooksBusinessObject) ctx.getBean("booksBusinessObject");
	private BookReviewsBusinessObject booksReviewsBO = (BookReviewsBusinessObject) ctx.getBean("booksReviewsBusinessObject");
	
	
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
	
	public HashMap<Books, List<BookReviews>> searchBookReviewsByTitleAndAuthor(String title, String author, int offset, int numberOfRecords){
		return booksReviewsBO.findBooksReviewByTitleAndAuthorLazyLoad(title, author, offset, numberOfRecords);
	}
	
	public void addBook(String title, String author){
		Books books = new Books();
		books.setTitle(title);
		books.setAuthor(author);
		booksBO.save(books);
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
