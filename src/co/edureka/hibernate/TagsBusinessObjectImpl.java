package co.edureka.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import co.edureka.controller.PaginationController;
import co.edureka.hibernate.orm.BookTags;
import co.edureka.hibernate.orm.Books;



public class TagsBusinessObjectImpl extends HibernateDaoSupport implements TagsBusinessObject{

	private final static Logger log = Logger.getLogger(TagsBusinessObjectImpl.class); 
	
	@Override
	@Transactional
	public void save(BookTags bookTags) {
		Session session = this.getSessionFactory().openSession();
		session.save(bookTags);
		session.flush();
		session.close();
	}

	@Override
	@Transactional
	public void update(BookTags bookTags) {
		Session session = this.getSessionFactory().openSession();
		session.update(bookTags);
		session.flush();
		session.close();
	}

	@Override
	@Transactional
	public void delete(BookTags bookTags) {
		Session session = this.getSessionFactory().openSession();
		session.delete(bookTags);
		session.flush();
		session.close();
	}
	
	public List<Books> findBooksByTagsLazyLoad(HashMap<String, String> tagsKeyValues, int offset, int numberOfRecords){
		Session session = this.getSessionFactory().openSession();
		
		StringBuffer sqlAppender = new StringBuffer();
		
		int count = 0;
		
		for(String key : tagsKeyValues.keySet()){
			
			count++;
			
			if(count > 1){
				sqlAppender.append(" and idbooks in (");
			}
			
			sqlAppender.append(" select idbooks from book_tags where ");
			sqlAppender.append("  tag_type="+"'"+key+"'");
			sqlAppender.append(" and tag_value="+"'"+tagsKeyValues.get(key)+"'");
			
			if(count > 1){
				sqlAppender.append(")");
			}
			
		}

		//entityManager.getEntityManagerFactory();
		
		log.info("sql appender value ::: "+sqlAppender.toString());
		
		//Query query = entityManager.getEntityManagerFactory().createEntityManager().createNativeQuery(sqlAppender.toString());
		//List list = query.getResultList();
		
		List list = session.createSQLQuery(sqlAppender.toString()).list(); //allows you to create native sql query
		
		List<Books> books = new ArrayList<Books>();
		
		for(Object obj : list){
			
			int idbooks = (Integer)obj;
			
			log.info("id books to search on : "+idbooks);
			
			String sql = " from "+Books.class.getName()+" where idbooks = :booksid ";
			//EntityManager em = EntityManagerFactory
			
		
			books.addAll(session.createQuery(sql).setParameter("booksid", idbooks).setFirstResult(offset).setMaxResults(numberOfRecords).list());
			
			log.info("id books returned : "+idbooks);
			
		}
		
		session.close();
		return books;
	}
}
