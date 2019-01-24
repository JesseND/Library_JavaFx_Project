package business.controller;

import java.lang.reflect.Member;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import business.model.Book;
import business.model.Person;
import dataaccess.dao.PersonDAO;
import dataaccess.tables.CheckoutTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CheckoutController implements Initializable {

	@FXML
	private ComboBox<String> membrsCbx;
	@FXML
	private ComboBox<String> booksCbx;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		List<Person> members = new ArrayList<Person>();
		try {
			members = PersonDAO.pDao.getAll();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		for (Person p : members) {
			membrsCbx.getItems().add(p.toString());
		}
	}

}
