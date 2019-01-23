package business.model;

import java.io.Serializable;
import java.util.List;

public class Book implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3349822635229618363L;
	private String ISBNnumber;
	private String title;
	private boolean isAvailable;
	private List<Author> authors;
	private List<BookCopy> copies;
	
	
	
	public Book(String iSBNnumber, String title, boolean isAvailable, List<Author> authors, List<BookCopy> copies) {
		this.ISBNnumber = iSBNnumber;
		this.title = title;
		this.isAvailable = isAvailable;
		this.authors = authors;
		this.copies = copies;
	}
	
	public String getISBNnumber() {
		return ISBNnumber;
	}
	public void setISBNnumber(String iSBNnumber) {
		ISBNnumber = iSBNnumber;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean isAvailable() {
		return isAvailable;
	}
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	public List<Author> getAuthors() {
		return authors;
	}
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	public List<BookCopy> getCopies() {
		return copies;
	}
	public void setCopies(List<BookCopy> copies) {
		this.copies = copies;
	}
}
