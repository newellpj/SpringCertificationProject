package co.edureka.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import co.edureka.controller.PaginationController;
import co.edureka.hibernate.orm.BookReviews;
import co.edureka.hibernate.orm.Books;

@Configuration
@EnableAspectJAutoProxy
public class BookReviewsBusinessObjectImpl extends HibernateDaoSupport implements BookReviewsBusinessObject {

	private final static Logger log = Logger.getLogger(BookReviewsBusinessObjectImpl.class); 
	
	@Override
	@Transactional
	public void save(BookReviews bookReviews) {
		Session session = this.getSessionFactory().openSession();
		session.save(bookReviews);
		System.out.println("saved book");
		session.flush();
		session.close();
	}

	@Override
	@Transactional
	public void update(BookReviews bookReviews) {
		Session session = this.getSessionFactory().openSession();
		session.update(bookReviews);
		session.flush();
		session.close();
	}

	@Override
	@Transactional
	public void delete(List<BookReviews> bookReviews) {
		Session session = this.getSessionFactory().openSession();
		this.getHibernateTemplate().deleteAll(bookReviews);
		session.flush();
		session.close();
	}
	
	public HashMap<Books, List<BookReviews>> findBooksReviewByTitleAndAuthorLazyLoad(String title, String author, int offset, int numberOfRecords ){
		
		log.info("BookReviews title to search : "+title);
		Session session = this.getSessionFactory().openSession();
		Map hashMap = new HashMap();
		hashMap.put("title", title);
		hashMap.put("author", author);
		
		List list = session.createQuery(" from "+Books.class.getName()+" where UPPER(title) = UPPER(:title) and UPPER(author) = UPPER(:author) ").setProperties(hashMap).list();
		
		if(list != null && list.size() > 0){
			Object obj = list.get(0);
			Books books = (Books)obj;
//					session.flush();
			HashMap<Books, List<BookReviews>>  bookAndReviews = new HashMap<Books, List<BookReviews>> ();
			
			String sql = " from "+BookReviews.class.getName()+" where idbooks = :booksid ";
			
			List<BookReviews> reviewsList = session.createQuery(sql).setParameter("booksid", 
					books.getIdbooks()).setFirstResult(offset).setMaxResults(numberOfRecords).list();
			
			bookAndReviews.put(books, reviewsList);
			session.close();
			return bookAndReviews;
			
		}else{
			session.close();
			return null;
		}
	}

	@Override
	public HashMap<Books, List<BookReviews>> findBooksReviewByTitleAndAuthor(String title, String author) {
		
		log.info("BookReviews title to search : "+title);
		Session session = this.getSessionFactory().openSession();
		Map hashMap = new HashMap();
		hashMap.put("title", title);
		hashMap.put("author", author);
		
		List list = session.createQuery(" from "+Books.class.getName()+" where UPPER(title) = UPPER(:title) and UPPER(author) = UPPER(:author) ").setProperties(hashMap).list();
		
		if(list != null && list.size() > 0){
			Object obj = list.get(0);
			Books books = (Books)obj;
//			session.flush();
			HashMap<Books, List<BookReviews>>  bookAndReviews = new HashMap<Books, List<BookReviews>> ();
			
			List<BookReviews> reviewsList = session.createQuery(" from "+BookReviews.class.getName()+" where idbooks = :booksid ").setParameter("booksid", books.getIdbooks()).list();
			
			bookAndReviews.put(books, reviewsList);
			session.close();
			return bookAndReviews;
			
		}else{
			session.close();
			return null;
		}
	}

	@Override
	public List<BookReviews> findBooksReviewByReviewer(String username) {
		// TODO Auto-generated method stub
		Session session = this.getSessionFactory().openSession();		
		List<BookReviews> list = session.createQuery(" from "+BookReviews.class.getName()+" where UPPER(reviewers_username) = UPPER(:username) ").setParameter("username", username).list();
		session.close();
		return list;
	}
	
	public List<Books> findBooksByPublishersLazyLoad(String publisher, int offset, int numberOfRecords){
		Session session = this.getSessionFactory().openSession();		
		List<Books> list = session.createQuery(" from "+Books.class.getName()+" where UPPER(publisher) = UPPER(:publisher) ").setParameter("publisher", publisher).
				setFirstResult(offset).setMaxResults(numberOfRecords).list();
		session.close();
		return list;
	}

	public List<BookReviews> findBooksReviewByReviewerLazyLoad(String username, int offset, int numberOfRecords){
		Session session = this.getSessionFactory().openSession();
		
		String sql =  " where reviewers_username = :username ";
		
		List<BookReviews> list = session.createQuery(" from "+BookReviews.class.getName()+sql).setParameter("username", username)
								.setFirstResult(offset).setMaxResults(numberOfRecords).list();
		session.close();
		return list;
		
	}
	
}
