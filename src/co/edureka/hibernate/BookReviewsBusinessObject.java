package co.edureka.hibernate;

import java.util.HashMap;
import java.util.List;

import co.edureka.hibernate.orm.BookReviews;
import co.edureka.hibernate.orm.Books;


public interface BookReviewsBusinessObject {
	public void save(BookReviews bookReviews);
	public void update(BookReviews bookReviews);
	public void delete(List<BookReviews> bookReviews);
	
	/**
	 * gets book by title and/or author. If author is null it will just search on title.
	 * @param title
	 * @param author
	 * @return Books ORM object
	 */
	public HashMap<Books, List<BookReviews>> findBooksReviewByTitleAndAuthor(String title, String author);
	
	public List<BookReviews> findBooksReviewByReviewer(String username);
	
	public List<Books> findBooksByPublishersLazyLoad(String publisher, int offset, int numberOfRecords);
	
	public List<BookReviews> findBooksReviewByReviewerLazyLoad(String username, int offset, int numberOfRecords);
	
	public HashMap<Books, List<BookReviews>> findBooksReviewByTitleAndAuthorLazyLoad(String title, String author, int offset, int numberOfRecords);
	
}
