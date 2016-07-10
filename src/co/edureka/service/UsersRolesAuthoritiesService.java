package co.edureka.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import co.edureka.hibernate.UsersBusinessObject;
import co.edureka.hibernate.orm.Authorities;
import co.edureka.hibernate.orm.Users;
import co.edureka.viewmodel.UsersModel;

public class UsersRolesAuthoritiesService {

	private ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	private UsersBusinessObject userBO = (UsersBusinessObject) ctx.getBean("usersBusinessObject");
	
	public boolean isUsernameAvailable(String username){
		Users user = userBO.findUsersByUsername(username);
		System.out.println("is user null : "+user);
		return user == null;
		
	}
	
	/**
	 * @param userModel
	 */
	public void addUser(UsersModel userModel){
		Users user = new Users();
		user.setUsername(userModel.getUsername());
		user.setPassword(userModel.getPassword());
		user.setEnabled("Y");
		
		Authorities authorities = new Authorities();
		authorities.setUsername(userModel.getUsername());
		authorities.setAuthority("ROLE_USER");
		
		userBO.save(user, authorities);
	}
}
