package dataaccess.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import business.model.*;

public class PersonDAO {

	public static final String OUTPUT_DIR = System.getProperty("user.dir") 
				+ File.pathSeparator+"dataaccess"+ File.pathSeparator+"person.txt";
	ObjectOutputStream output;
	ObjectInputStream input;
	
	public void connectWrite(){
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(
					OUTPUT_DIR);
			output = new ObjectOutputStream(fileOutputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	
	
	public void connectRead() throws ClassNotFoundException{
     		try {
		   FileInputStream fileInputStream = new FileInputStream(new File(
						OUTPUT_DIR));
			input = new ObjectInputStream(fileInputStream);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


		public List<Person> getAll() {
			// TODO Auto-generated method stub
			return null;
		}


		public Person update(Person person) {
			// TODO Auto-generated method stub
			return null;
		}


		public Person get(String id) {
			// TODO Auto-generated method stub
			return null;
		}


		public Person add(List<Person> persons) {
//           output.writeObject(user);
//			
//			output.close();
			return null;
		}
}


