package business.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class BookController implements Initializable {
	
	@FXML private TextField titleField;
	@FXML private TextField isbnField;
	@FXML private TextField priceField;
	@FXML private TextField limitField;
	@FXML private TextField searchField;
	@FXML private TextField firstNameField;
	@FXML private TextField lastNameField;
	@FXML private TextField stateField;
	@FXML private TextField cityField;
	@FXML private TextField zipField;
	@FXML private TextField streetField;
	@FXML private TextField phoneNumField;
	@FXML private TextField emailField;
	@FXML private Button newBookButton;
	@FXML private Button addAuthorButton;
	@FXML private Button editBookButton;
	@FXML private Button clearButton;
	@FXML private Button searchButton;
	@FXML private Button bookInfoButton;
	@FXML private Button saveBookButton;
	@FXML private Button saveAuthorButton;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

}
