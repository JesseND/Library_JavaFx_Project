package business.model;

import java.io.Serializable;
import java.util.List;

public class Author extends Person implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4943938113363294457L;
	private String phoneNumber;
	private String credentials;
	private String shortBio;
	
	
	public Author(int id, String firstName, String lastName, Account account, 
			List<Role> role, Address address) {
		super(id, firstName, lastName, null,  null, address);
		// TODO Auto-generated constructor stub
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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
