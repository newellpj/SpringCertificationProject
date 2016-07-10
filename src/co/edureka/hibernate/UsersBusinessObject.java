package co.edureka.hibernate;

import co.edureka.hibernate.orm.Authorities;
import co.edureka.hibernate.orm.Users;

public interface UsersBusinessObject {
	public void save(Users users, Authorities authorities);
	public void update(Users users);
	public void delete(Users users, Authorities authorities);
	public Users findUsersByUsername(String username);
}
