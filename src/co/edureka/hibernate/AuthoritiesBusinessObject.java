package co.edureka.hibernate;

import java.util.List;

import co.edureka.hibernate.orm.Authorities;

public interface AuthoritiesBusinessObject {
	public void save(Authorities authorities);
	public void update(Authorities authorities);
	public void delete(Authorities authorities);
	public List<Authorities> findAuthoritiesByUserName(String username);
}
