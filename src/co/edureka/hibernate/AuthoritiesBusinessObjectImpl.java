package co.edureka.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import co.edureka.hibernate.orm.Authorities;

public class AuthoritiesBusinessObjectImpl extends HibernateDaoSupport implements AuthoritiesBusinessObject {

	@Override
	public void save(Authorities authorities) {
		
		Session session = this.getSessionFactory().openSession();
		
		session.save(authorities);
		
		session.flush();
		session.close();
	}

	@Override
	public void update(Authorities authorities) {
		getHibernateTemplate().update(authorities);

	}

	@Override
	public void delete(Authorities authorities) {
		getHibernateTemplate().delete(authorities);

	}

	@Override
	public List<Authorities> findAuthoritiesByUserName(String username) {
		// TODO Auto-generated method stub
		List list = getHibernateTemplate().find(" from "+Authorities.class.getName()+" where username = ? ", username);

		//Object obj = list.get(0);
		
		//UsersDBObj user = (AuthoritiesDBObj)obj;

		return list;
	}

}
