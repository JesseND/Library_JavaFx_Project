package dataaccess.dao;

import java.io.Serializable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import business.model.Book;
import business.model.BookCopy;
import business.model.Author;

public class BookData implements Serializable{
	
	private static  File bookDataFile; // = new File("BookDataCollection.txt");
	private static  FileInputStream fis;
	private static  ObjectInputStream ois;
	private static  FileOutputStream fos;
	private static  ObjectOutputStream oos;
	
	public BookData() throws IOException {
		bookDataFile = File.createTempFile("BookDataCollection", ".txt");
	}
	
	public void addBookData(Book book) throws IOException {
		fos = new FileOutputStream(bookDataFile);
		oos = new ObjectOutputStream(fos);
		oos.writeObject(book);
		fos.close();
		oos.close();
	}
	
	public void updateBookData(BookCopy bookCopy) throws IOException, ClassNotFoundException {
		List<Book> updateBook;
		List<Book> books = getFileInList();
		
		updateBook = searchAndUpdate(bookCopy, books);
		
		for(Book x: updateBook) {
			addBookData(x);
		}
	}
	
	private List<Book> searchAndUpdate(BookCopy bookCopy, List<Book> books) {
		List<BookCopy> bookCopies = null;
		
		outer: for(int i=0; i<books.size(); i++) {
				bookCopies = books.get(i).getCopies();
				for(int j=0; j<bookCopies.size(); j++) {
					if(bookCopies.get(j).getUniqueId().equals(bookCopy.getUniqueId())) {
						bookCopies.remove(j);
						bookCopies.add(bookCopy);
						books.get(i).setCopies(bookCopies);
						break outer;
					}
				}
		}
		
		return books;
	}
	
	public List<BookCopy> search(String search) throws IOException, ClassNotFoundException {
		List<BookCopy> bookCopies = null;
		List<BookCopy> searchValues = new ArrayList<>();
		
		List<Book> books = getFileInList();
		
		for(Book x: books) {
			bookCopies = x.getCopies();
			
			for(BookCopy y: bookCopies) {
				if(y.getBook().getTitle().equals(search)) {
					searchValues.add(y);
				}
				
				else if(y.getBook().getISBNnumber().equals(search)) {
					searchValues.add(y);
				}
				
				else {
					for(Author z: y.getBook().getAuthors()) {
						if(z.getFirstName().equals(search) || z.getLastName().equals(search)) {
							searchValues.add(y);
						}
					}
				}
			}
		}
		
		return searchValues;
	}
	
	public BookCopy editBookData(String bookCopyId, String objId) throws IOException, ClassNotFoundException {
		
		List<Book> books = getFileInList();
		List<BookCopy> bookCopies = null;
		Book bookObject = null;
		BookCopy copy = null;
		
		for(Book bookObj: books) {
			if(bookObj.getObjectID().equals(objId)) {
				bookObject = bookObj;
			}	
		}
		
		for(BookCopy x: bookObject.getCopies()) {
			if(x.getUniqueId().equals(bookCopyId)) {
				copy = x;
				break;
			}
		}
		
		return copy;
	} 
	
	public List<Book> lookupBookData() throws IOException, ClassNotFoundException{
		fis = new FileInputStream(bookDataFile);
		ois = new ObjectInputStream(fis);
		List<Book> books = getFileInList();
		return books;
	}
	
	private List<Book> getFileInList() throws IOException, ClassNotFoundException{
		fis = new FileInputStream(bookDataFile);
		ois = new ObjectInputStream(fis);
		Book bookObject = null;
		List<Book> books = new ArrayList<>();
		
		boolean nextFile = true;
		
		while(nextFile) {
			
			try {
				bookObject = (Book)ois.readObject();
				books.add(bookObject);
			}catch (EOFException ex) {
				nextFile = false;
			}
				
		}
		
		fis.close();
		ois.close();
		return books;
	}
}
