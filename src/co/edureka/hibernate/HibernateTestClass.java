package co.edureka.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import co.edureka.hibernate.orm.Authorities;
import co.edureka.hibernate.orm.BookReviews;
import co.edureka.hibernate.orm.Books;
import co.edureka.hibernate.orm.Users;


public class HibernateTestClass {

	public static void main(String[] args) {
		
		//testBooksAndReviews();
		//testUsersAuthorities();
		//testSelectSubsets();
		testTagsSearch();
	}
	
	private static void testTagsSearch(){
		//findBooksByTagsLazyLoad
		
		ApplicationContext ctx = new FileSystemXmlApplicationContext("C:/Users/newelly/SpringCertificationProject/SpringCertificationProject/resources/applicationContext.xml");
		
		//BooksBusinessObject booksBO = (BooksBusinessObject) ctx.getBean("booksBusinessObject");
		TagsBusinessObject tagsBO = (TagsBusinessObject) ctx.getBean("tagsBusinessObject");
		
		Books books = new Books();
		books.setTitle("Fear and Loathing in Las Vegas");
		books.setAuthor("Hunter S Thompson");
	
	//	booksBO.save(books);
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("langText", "English");	
		map.put("catText", "Non-fiction");	
		map.put("genreText", "Philosophy");	
		
		List<Books> booksFound = tagsBO.findBooksByTagsLazyLoad(map, 0, 20);
		
		for(Books book : booksFound){
			System.out.println("title :::: "+book.getTitle());
		}
		
		
	
		
	}
	
	private static void testBooksSearchPublisher(){
		ApplicationContext ctx = new FileSystemXmlApplicationContext("C:/Users/newelly/SpringCertificationProject/SpringCertificationProject/resources/applicationContext.xml");
		
		BooksBusinessObject booksBO = (BooksBusinessObject) ctx.getBean("booksBusinessObject");
		BookReviewsBusinessObject booksReviewsBO = (BookReviewsBusinessObject) ctx.getBean("booksReviewsBusinessObject");
		
		Books books = new Books();
		books.setTitle("Fear and Loathing in Las Vegas");
		books.setAuthor("Hunter S Thompson");
		books.setPublisher("Harper");
	//	booksBO.save(books);
		
		List<Books> booksFound = booksReviewsBO.findBooksByPublishersLazyLoad(books.getPublisher(), 0, 20);

		for(Books book : booksFound){
			System.out.println("title :::: "+book.getTitle());
		}

	}
	
	
	private static void testBooksAndReviews(){
		ApplicationContext ctx = new FileSystemXmlApplicationContext("C:/Users/newelly/SpringCertificationProject/SpringCertificationProject/resources/applicationContext.xml");
		
		BooksBusinessObject booksBO = (BooksBusinessObject) ctx.getBean("booksBusinessObject");
		BookReviewsBusinessObject booksReviewsBO = (BookReviewsBusinessObject) ctx.getBean("booksReviewsBusinessObject");
		
		Books books = new Books();
		books.setTitle("Fear and Loathing in Las Vegas");
		books.setAuthor("Hunter S Thompson");
	
	//	booksBO.save(books);
		
		Books bookFound = booksBO.findBooksByTitleAndAuthor(books.getTitle(), books.getAuthor());
		
		System.out.println("book found : "+bookFound.getTitle()+" author : "+bookFound.getAuthor());
		
		int count = 0;
		
		while(count < 200){	
			BookReviews bookReviews = new BookReviews();
			bookReviews.setIdbooks(15);
			bookReviews.setReviewersUsername("edureka");
			bookReviews.setReviewText("Absolutely loved it. RECOMMENDED! : "+count);
			booksReviewsBO.save(bookReviews);
			count++;
		}

	}
	
	private static void testSelectSubsets(){
		ApplicationContext ctx = new FileSystemXmlApplicationContext("C:/Users/newelly/SpringCertificationProject/SpringCertificationProject/resources/applicationContext.xml");
		
		BooksBusinessObject booksBO = (BooksBusinessObject) ctx.getBean("booksBusinessObject");
		BookReviewsBusinessObject booksReviewsBO = (BookReviewsBusinessObject) ctx.getBean("booksReviewsBusinessObject");
		
		List<BookReviews> reviews = booksReviewsBO.findBooksReviewByReviewerLazyLoad("edureka", 10, 37);
		
		for(BookReviews br : reviews){
			System.out.println("reviews book id : "+br.getIdbooks());
			System.out.println("reviews text : "+br.getReviewText());
		}
		
		HashMap<Books, List<BookReviews>> booksAndReviews = booksReviewsBO.findBooksReviewByTitleAndAuthorLazyLoad("Fear and Loathing in Las Vegas","Hunter S Thompson", 20, 59);
		
		Set<Books> books = booksAndReviews.keySet();
		
		for(Books book :  books){
		
			System.out.println("book with reviews is : "+book.getTitle() +" by "+book.getAuthor());
			
			
			for(BookReviews br : booksAndReviews.get(book)){
				System.out.println("reviews book id 2 : "+br.getIdbooks());
				System.out.println("reviews text 2 : "+br.getReviewText());
			}
		}
		
	}
	
	private static void testUsersAuthorities(){
		ApplicationContext ctx = new FileSystemXmlApplicationContext("C:/Users/newelly/SpringCertificationProject/SpringCertificationProject/resources/applicationContext.xml");
		
		UsersBusinessObject userBO = (UsersBusinessObject) ctx.getBean("usersBusinessObject");
		AuthoritiesBusinessObject authBO = (AuthoritiesBusinessObject) ctx.getBean("authoritiesBusinessObject");
		
		Users users = new Users();

		users.setUsername("taylorpt");
		users.setPassword("password1");
		users.setEnabled("Y");
		
	//	userBO.save(users);
		
		Authorities auth = new Authorities();
		auth.setAuthority("ROLE_USER");
		auth.setUsername(users.getUsername());
		
		userBO.save(users, auth);
		
		Users usersRet = userBO.findUsersByUsername(users.getUsername());
		List<Authorities> authRetList = authBO.findAuthoritiesByUserName(users.getUsername());
		
		System.out.println("user password returned is : "+usersRet.getPassword());
		
		for(Authorities authority : authRetList){
			System.out.println("auth authoritiy : "+authority.getAuthority());
			System.out.println("auth username : "+authority.getUsername());
		}
	}

}
