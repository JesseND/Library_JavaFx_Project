package business.model;

import java.io.Serializable;

public class BookCopy implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3969315852691755681L;
	private Book book;
	private int copynumber;
	private boolean isBorrowed;
	
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getCopynumber() {
		return copynumber;
	}
	public void setCopynumber(int copynumber) {
		this.copynumber = copynumber;
	}
	public boolean isBorrowed() {
		return isBorrowed;
	}
	public void setBorrowed(boolean isBorrowed) {
		this.isBorrowed = isBorrowed;
	}
	
	

}
