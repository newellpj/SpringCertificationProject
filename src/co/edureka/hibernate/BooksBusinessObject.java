package co.edureka.hibernate;

import java.util.List;

import co.edureka.hibernate.orm.BookReviews;
import co.edureka.hibernate.orm.Books;

public interface BooksBusinessObject {
	public void save(Books books);
	public void update(Books books);
	public void delete(Books books, List<BookReviews> bookReviews);
	
	/**
	 * gets book by title and/or author. If author is null it will just search on title.
	 * @param title
	 * @param author
	 * @return Books ORM object
	 */
	public Books findBooksByTitleAndAuthor(String title, String author);
}
