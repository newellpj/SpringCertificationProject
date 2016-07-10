package co.edureka.viewmodel;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BookReviewsModel {
	
	private int booksID;
	
	private int booksReviewsID;
	
	@Size(min=5,max=40)
	private String username;
	
	@NotNull
	@Size(min=5,max=40)
	private String titleText;

	@NotNull
	@Size(min=5,max=40)
	private String authorText;
	
	@Size(min=5,max=40)
	private String titleTextAdd;
	
	@Size(min=5,max=40)
	private String authorTextAdd;
	
	@Size(min=5,max=300)
	private String reviewText;
	
	@Size(min=5,max=40)
	private String bookTitleReview;
	
	@Size(min=5,max=40)
	private String bookAuthorReview;
	
	
	
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

	public BookReviewsModel(){}

	
}
