package co.edureka.hibernate.orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.Table;


public class Authorities {

	private int idauthorities;
	private String username;
	private String authority;

	public int getIdauthorities() {
		return idauthorities;
	}

	public void setIdauthorities(int idauthorities) {
		this.idauthorities = idauthorities;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	
	
}
