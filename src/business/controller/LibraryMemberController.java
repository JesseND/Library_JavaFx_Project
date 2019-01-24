package business.controller;

import java.util.List;
import java.util.stream.Collectors;
import business.model.Person;
import dataaccess.dao.PersonDAO;

public class LibraryMemberController {
	private static LibraryMemberController instance = new LibraryMemberController();
	
	private static PersonDAO pDao = new PersonDAO();
	
	private LibraryMemberController() {}
	
	public static LibraryMemberController getInstance() { return instance; }
	
	public void addNewMembers(List<Person> persons) {
		pDao.add(persons);
	}
	
	public void addMember(Person person) throws ClassNotFoundException{
		List<Person> p = getAllMembers();
		p.add(person);
		addNewMembers(p);
	}
	public Person getMemberById(String id) {
		return pDao.get(id);
	}
	
	public Person updateMember(Person person) {
		return pDao.update(person);
	}
	
	public List<Person> search(String query) throws ClassNotFoundException {
		String q = query.toLowerCase();
		
		return getAllMembers()
				.stream()
				.filter(member -> 
					member.getFirstName().toLowerCase().startsWith(q) ||
					member.getLastName().toLowerCase().startsWith(q)
				)
				.collect(Collectors.toList());
	}

	public List<Person> getAllMembers() throws ClassNotFoundException {
		return pDao.getAll();
	}
}

