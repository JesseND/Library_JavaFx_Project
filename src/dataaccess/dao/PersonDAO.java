package dataaccess.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import business.model.*;

public class PersonDAO {

	public static PersonDAO pDao = new PersonDAO();
	public static final String OUTPUT_DIR = System.getProperty("user.dir") 
			+File.pathSeparator+"dataaccess"+File.pathSeparator+"person.txt";

//	public static final String OUTPUT_DIR = System.getProperty("user.dir") 
//				+"\\src\\dataaccess\\person.txt";
//	
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

		public void add(List<Person> persons) {
			
			try {
				connectWrite();
			    output.writeObject(persons);
			}catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		public List<Person> getAll() throws ClassNotFoundException {
			List<Person> pList =new ArrayList<>();
			ArrayList<Person> list =new ArrayList<>();
			try {
				connectRead();
			     list=(ArrayList<Person>) input.readObject();
			}catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
				
			for(int i=0;i<list.size();i++){
	        	pList.add(list.get(i));
	        }
			return pList;
		}

		
		public List<Person> getAllLibMember(){
			List<Person> pList;
			try {
				pList = getAll();
				return pList.stream()
						.filter(p -> 
						(p.getAccount().getUsername().equals("") && 
								p.getAccount().getPassword().equals("")))
				     .collect(Collectors.toList());
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		public List<Person> getAdminOrLib(){
			List<Person> pList;
			try {
				pList = getAll();
				
				return pList.stream().filter(p -> 
								!(p.getAccount().getUsername().equals("") && p.getAccount().getUsername().equals("")))
					     .collect(Collectors.toList());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			return null;
		}
		
		public Person update(Person person) {
			int personId = person.getId();
			try {
				List<Person> list = getAll();
				Person existingPerson = get(String.valueOf(personId));
				list.add(list.indexOf(existingPerson), person);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return person;
		}


		public Person get(String id) {
			Optional<Person> person;
			try {
				List<Person> list = getAll();
				int idVal = Integer.parseInt(id);
				person = list.stream()
						.filter(p -> p.getId()==idVal)
						.findAny();
				if(person.isPresent()){
					return person.get();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return null;
		}


		public static void main(String[] args) throws ClassNotFoundException{
			List<Person> list = new ArrayList<>();
			
			LibraryMember m = new LibraryMember("1");
			Address a = new Address("1000 N 4th", "Fairfield", "Iowa", "IA, 52557");
			
			Admin admin = new Admin();
			Librarian lib = new Librarian();
			
			Account acc1 = new Account("admin", "admin");
			Account acc2 = new Account("lib", "lib");
			
			Person p1 = new Person(1, "Jesse", "Ndamutsa", m, a, "213434" );
			Person p2 = new Person(2, "Abdoul", "Fall", m, a, "345434" );
			Person p3 = new Person(3, "Hilina", "Beyene", m, a, "975643" );
			Person p4 = new Person(100, "Mr", "Admin",acc1, admin, a,  "342356");
			Person p5 = new Person(102, "Mr", "lib",acc2, lib, a,  "456786");
			
			list.add(p4);
			list.add(p5);
			list.add(p1);
			list.add(p2);
			list.add(p3);
			
			pDao.add(list);
			List<Person> readList;
			readList = pDao.getAdminOrLib();
			if(!readList.isEmpty()){
				for(Person person:readList){
					System.out.println(person);
				}
			}

		}
}


