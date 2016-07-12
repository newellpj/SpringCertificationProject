package co.edureka.hibernate.orm;

public class BookReviews implements java.io.Serializable {


	private static final long serialVersionUID = -3752091466955698753L;
	private Integer idbookReviews;
	private Integer idbooks;
	private String reviewText;
	private String reviewersUsername;

	public BookReviews() {
	}

	public BookReviews(Integer idbooks, String reviewText, String reviewersUsername) {
		this.idbooks = idbooks;
		this.reviewText = reviewText;
		this.reviewersUsername = reviewersUsername;
	}

	public Integer getIdbookReviews() {
		return this.idbookReviews;
	}

	public void setIdbookReviews(Integer idbookReviews) {
		this.idbookReviews = idbookReviews;
	}

	public Integer getIdbooks() {
		return this.idbooks;
	}

	public void setIdbooks(Integer idbooks) {
		this.idbooks = idbooks;
	}

	public String getReviewText() {
		return this.reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

	public String getReviewersUsername() {
		return this.reviewersUsername;
	}

	public void setReviewersUsername(String reviewersUsername) {
		this.reviewersUsername = reviewersUsername;
	}

}
