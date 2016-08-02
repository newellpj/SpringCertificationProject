package co.edureka.webservices.books;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import co.edureka.webservices.books.model.BooksReviewsWSModel;

@WebService
@SOAPBinding(style = Style.RPC)
public interface QueryBooks {

	@WebMethod public BooksReviewsWSModel[] queryAuthor(String authorName);
	@WebMethod public BooksReviewsWSModel[] queryTitle(String title);
	@WebMethod public BooksReviewsWSModel[] queryTitleAndAuthor(String title, String author);
}
