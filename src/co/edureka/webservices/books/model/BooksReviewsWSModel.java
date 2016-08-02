package co.edureka.webservices.books.model;

/**
 * books and reviews model for web services
 * 
 * Doesn't inlude view specific attribute such as the formated book details string list.
 * 
 * @author newelly
 *
 */
public class BooksReviewsWSModel {

	private int booksID;
	
	private int booksReviewsID;
	
	private String username;
	
	private String titleText;

	private String authorText;
	
	private String titleTextAdd;
	
	private String authorTextAdd;
	
	private String reviewText;
	
	private String bookTitleReview;

	private String bookAuthorReview;

	private String publisherText;

	public BooksReviewsWSModel(){
		
	}
	
	public int getBooksID() {
		return booksID;
	}

	public void setBooksID(int booksID) {
		this.booksID = booksID;
	}

	public int getBooksReviewsID() {
		return booksReviewsID;
	}

	public void setBooksReviewsID(int booksReviewsID) {
		this.booksReviewsID = booksReviewsID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTitleText() {
		return titleText;
	}

	public void setTitleText(String titleText) {
		this.titleText = titleText;
	}

	public String getAuthorText() {
		return authorText;
	}

	public void setAuthorText(String authorText) {
		this.authorText = authorText;
	}

	public String getTitleTextAdd() {
		return titleTextAdd;
	}

	public void setTitleTextAdd(String titleTextAdd) {
		this.titleTextAdd = titleTextAdd;
	}

	public String getAuthorTextAdd() {
		return authorTextAdd;
	}

	public void setAuthorTextAdd(String authorTextAdd) {
		this.authorTextAdd = authorTextAdd;
	}

	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

	public String getBookTitleReview() {
		return bookTitleReview;
	}

	public void setBookTitleReview(String bookTitleReview) {
		this.bookTitleReview = bookTitleReview;
	}

	public String getBookAuthorReview() {
		return bookAuthorReview;
	}

	public void setBookAuthorReview(String bookAuthorReview) {
		this.bookAuthorReview = bookAuthorReview;
	}

	public String getPublisherText() {
		return publisherText;
	}

	public void setPublisherText(String publisherText) {
		this.publisherText = publisherText;
	}
	
	
}
