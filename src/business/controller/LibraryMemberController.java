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
	
	public Person addNewMembers(Person person) {
		List<Person> persons = getAllMembers();
		persons.add(person);
		return pDao.add(persons);
	}
	
	public Person getMemberById(String id) {
		return pDao.get(id);
	}
	
	public Person updateMember(Person person) {
		return pDao.update(person);
	}
	
	public List<Person> search(String query) {
		String q = query.toLowerCase();
		
		return getAllMembers()
				.stream()
				.filter(member -> 
					member.getFirstName().toLowerCase().startsWith(q) ||
					member.getLastName().toLowerCase().startsWith(q)
				)
				.collect(Collectors.toList());
	}

	public List<Person> getAllMembers() {
		return pDao.getAll();
	}
}

