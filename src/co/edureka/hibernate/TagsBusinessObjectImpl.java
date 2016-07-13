package co.edureka.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import co.edureka.hibernate.orm.BookTags;
import co.edureka.hibernate.orm.Books;

public class TagsBusinessObjectImpl extends HibernateDaoSupport implements TagsBusinessObject{
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
				sqlAppender.append(" or ");
			}
			
			sqlAppender.append(" tag_type="+"'"+key+"'");
			sqlAppender.append(" or tag_value="+"'"+tagsKeyValues.get(key)+"'");
		}

		List list = session.createQuery("select distinct(idbooks) from "+BookTags.class.getName()+" where "+" "+sqlAppender.toString()).list();
		
		List<Books> books = new ArrayList<Books>();
		
		for(Object obj : list){
			
			int idbooks = (Integer)obj;
			String sql = " from "+Books.class.getName()+" where idbooks = :booksid ";
			books.addAll(session.createQuery(sql).setParameter("booksid", idbooks).setFirstResult(offset).setMaxResults(numberOfRecords).list());
		}
		
		session.close();
		return books;

	}
}
