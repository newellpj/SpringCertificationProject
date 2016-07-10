package co.edureka.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import co.edureka.hibernate.orm.Authorities;
import co.edureka.hibernate.orm.Users;

public class UsersBusinessObjectImpl extends HibernateDaoSupport implements UsersBusinessObject{

	
	@Transactional
	public void save(Users users, Authorities authorities) {
		Session session = this.getSessionFactory().openSession();
		session.save(authorities);
		session.save(users);
		session.flush();
		session.close();
	}

	@Transactional
	@Override
	public void update(Users users) {
		
		Session session = this.getSessionFactory().openSession();
		session.update(users);
		session.flush();
		session.close();
	}

	@Transactional
	@Override
	public void delete(Users users, Authorities authorities) {
		Session session = this.getSessionFactory().openSession();
		session.delete(authorities);
		session.delete(users);
		session.flush();
		session.close();
	}

	@Override
	public Users findUsersByUsername(String username) {
		
		System.out.println("username : "+username);
		
		List list = getHibernateTemplate().find(" from "+Users.class.getName()+" where username = ? ", username);

		if(list != null && list.size() > 0){
			Object obj = list.get(0);
			
			Users user = (Users)obj;
			
			return user;
		}else{
			return null;
		}

	}
	

}
