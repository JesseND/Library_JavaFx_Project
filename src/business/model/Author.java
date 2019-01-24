package business.model;

import java.io.Serializable;
import java.util.List;

public class Author extends Person implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4943938113363294457L;
	private String credentials;
	private String shortBio;
	
	
	public Author(int id, String firstName, String lastName, Account account, 
			List<Role> role, Address address, String phone) {
		super(id, firstName, lastName,  null, address, phone);
	}
	

	public String getCredentials() {
		return credentials;
	}
	public void setCredentials(String credentials) {
		this.credentials = credentials;
	}
	public String getShortBio() {
		return shortBio;
	}
	public void setShortBio(String shortBio) {
		this.shortBio = shortBio;
	}
}
