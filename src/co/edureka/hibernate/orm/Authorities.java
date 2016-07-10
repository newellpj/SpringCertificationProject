package co.edureka.hibernate.orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.Table;

@Entity
@Table(name="authorities")
public class Authorities {
	
	@Id
	@Column(name="usersID")
	private int idauthorities;
	

	@Column(name="username")
	private String username;
	
	@Column(name="authority")
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
