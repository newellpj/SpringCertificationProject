package co.edureka.viewmodel;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UsersModel {

	@NotNull
	@Size(min=5,max=50)
	private String username;
	
	@NotNull
	@Size(min=5,max=30)
	private String password;

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
	
	
	public UsersModel(){}

	public UsersModel(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	
	
	
}
