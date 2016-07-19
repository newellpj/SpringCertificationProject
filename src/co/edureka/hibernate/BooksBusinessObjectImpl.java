package co.edureka.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import co.edureka.hibernate.orm.BookReviews;
import co.edureka.hibernate.orm.Books;


@Configuration
@EnableAspectJAutoProxy
public class BooksBusinessObjectImpl extends HibernateDaoSupport implements BooksBusinessObject{

	@Override
	@Transactional
	public void save(Books books) {
		Session session = this.getSessionFactory().openSession();
		session.save(books);
		session.flush();
		session.close();
	}

	@Override
	@Transactional
	public void update(Books books) {
		Session session = this.getSessionFactory().openSession();
		session.update(books);
		session.flush();
		session.close();
	}

	@Override
	@Transactional
	public void delete(Books books, List<BookReviews> bookReviews) {
		Session session = this.getSessionFactory().openSession();
		
		for(BookReviews bookReview : bookReviews){
			session.delete(bookReview);
		}
		
		session.delete(books);
		session.flush();
		session.close();
	}

	@Override
	public List<Books> findBooksByTitleAndAuthor(String title, String author) {
		// TODO Auto-generated method stub
		System.out.println("book title to search : "+title);
		Session session = this.getSessionFactory().openSession();
		List<Books> list = null;
		
		
		if(author == null || "".equals(author)){
			list = session.createQuery(" from "+Books.class.getName()+" where UPPER(title) = UPPER(:title) ").setString("title", title).list();
		}else if(title == null || "".equals(title)){
			list = session.createQuery(" from "+Books.class.getName()+" where UPPER(author) = UPPER(:author) ").setString("author", author).list();
		}else{
			Map hashMap = new HashMap();
			hashMap.put("title", title);
			hashMap.put("author", author);
			
			list = session.createQuery(" from "+Books.class.getName()+" where UPPER(title) = UPPER(:title) and UPPER(author) = UPPER(:author) ").setProperties(hashMap).list();
		}
		
		return list;


	}

}
