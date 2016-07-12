package co.edureka.hibernate.orm;

import java.io.Serializable;

public class Users implements Serializable{

	private static final long serialVersionUID = -6310399697126817475L;

	private int usersId;
	
	private String username;
	
	private String password;
	
	private String enabled;
	
	
	
	public Users(){
		
	}
	
	public int getUsersId() {
		return usersId;
	}

	public void setUsersId(int usersId) {
		this.usersId = usersId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	
	
	
	

}
