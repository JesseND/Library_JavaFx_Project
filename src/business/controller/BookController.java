package business.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

import business.model.Book;
import business.model.BookCopy;
import business.model.Author;
import business.model.Address;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class BookController implements Initializable {

	@FXML
	private static TableView<BookCopy> table = new TableView<BookCopy>();
	@FXML
	private TextField titleField;
	@FXML
	private TextField isbnField;
	@FXML
	private TextField priceField;
	@FXML
	private TextField limitField;
	@FXML
	private TextField searchField;
	@FXML
	private TextField firstNameField;
	@FXML
	private TextField lastNameField;
	@FXML
	private TextField stateField;
	@FXML
	private TextField cityField;
	@FXML
	private TextField zipField;
	@FXML
	private TextField streetField;
	@FXML
	private TextField phoneNumField;
	@FXML
	private TextField emailField;

	/*
	 * @FXML private Button addAuthorButton;
	 * 
	 * @FXML private Button editBookButton;
	 * 
	 * @FXML private Button clearButton;
	 */
	private static Book rootBook;
	private static Book book;
	private static BookCopy bookCopy;
	private static List<Author> authors;
	private static Address address;
	private boolean clearAuthorFields;
	private boolean searchAuthorField;
	private boolean bookFieldField;
	private boolean aduthorAdded;
	private boolean editIsOn;
	private static String title;
	private static String isbn;
	private static double price;
	private static int limit;
	private static String firstName;
	private static String lastName;
	private static String state;
	private static String city;
	private static String zip;
	private static String street;
	private static String phoneNum;
	private static String email;

	public BookController() {
		/*
		 * titleField.setDisable(true); isbnField.setDisable(true);
		 * priceField.setDisable(true); limitField.setDisable(true);
		 * /*addAuthorButton.setDisable(true); editBookButton.setDisable(true);
		 * clearButton.setDisable(true);
		 */
		// restrictTextFieldsToNumeric();
		authors = new ArrayList<>();
	}

	private void saveAuthorPrivate() {
		address = new Address(stateField.getText(), cityField.getText(), streetField.getText(), zipField.getText());

		Author author = new Author(firstNameField.getText(), lastNameField.getText(), address);
		authors.add(author);
		aduthorAdded = true;
	}

	private void compulsoryFields(String value) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(value);
		alert.showAndWait();
	}

	private void showTableDataForSearch(List<BookCopy> boockCopies) throws ClassNotFoundException, IOException {
		ObservableList<BookCopy> bookCopies = (ObservableList<BookCopy>) boockCopies;
		table.setItems(bookCopies);
	}

	private void showTableData() throws ClassNotFoundException, IOException {
		List<Book> books = rootBook.lookupBookData();

		for (Book x : books) {
			ObservableList<BookCopy> bookCopies = (ObservableList<BookCopy>) x.getCopies();
			table.setItems(bookCopies);
		}
	}

	/*
	 * private void restrictTextFieldsToNumeric() {
	 * zipField.textProperty().addListener(new ChangeListener<String>() {
	 * 
	 * @Override public void changed(ObservableValue<? extends String>
	 * observable, String oldValue, String newValue) { if
	 * (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
	 * zipField.setText(oldValue); } } }); }
	 */

	private boolean isAnyBookTextFieldEmpty() {
		if (titleField.getText().equals("") || isbnField.getText().equals("") || priceField.getText().equals("")
				|| limitField.getText().equals("")) {
			return true;
		} else
			return false;
	}

	private boolean isAnyAuthorTextFieldEmpty() {

		if (firstNameField.getText().equals("") || lastNameField.getText().equals("")
				|| stateField.getText().equals("") || cityField.getText().equals("") || zipField.getText().equals("")
				|| streetField.getText().equals("") || phoneNumField.getText().equals("")
				|| emailField.getText().equals("")) {
			return true;
		} else
			return false;
	}

	@FXML
	public void newBookButton() throws IOException {
		rootBook = new Book();
		clearAuthorFields = false;
		searchAuthorField = false;
		aduthorAdded = false;
		editIsOn = false;
		bookFieldField = true;
		title = "";
		isbn = "";
		price = 0.0;
		limit = 0;
		firstName = "";
		lastName = "";
		state = "";
		city = "";
		zip = "";
		street = "";
		phoneNum = "";
		email = "";
		titleField.setDisable(false);
		isbnField.setDisable(false);
		priceField.setDisable(false);
		limitField.setDisable(false);
		titleField.requestFocus();
	}

	@FXML
	public void addAuthorButton(ActionEvent event) throws IOException {

		if (rootBook == null) {
			compulsoryFields("Please Create a new book by clicking \"New Book\"!");
			return;
		}

		if (isAnyBookTextFieldEmpty()) {
			compulsoryFields("All fields are compulsory!");
			return;
		}

		clearAuthorFields = true;
		searchAuthorField = true;
		bookFieldField = false;
		title = titleField.getText();
		isbn = isbnField.getText();
		try {
			price = Double.parseDouble(priceField.getText());
			limit = Integer.parseInt(limitField.getText());
		} catch (NumberFormatException e) {
			compulsoryFields("Price and Limit can only accept number!");
			return;
		}

		Parent pane = FXMLLoader.load(getClass().getClassLoader().getResource("ui/AuthorFXML.fxml"));
		Scene scene = new Scene(pane);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();

		if (editIsOn) {
			firstNameField.setText(firstName);
			lastNameField.setText(lastName);
			stateField.setText(state);
			cityField.setText(city);
			zipField.setText(zip);
			streetField.setText(street);
			phoneNumField.setText(phoneNum);
			emailField.setText(email);
		}

	}

	@FXML
	public void editBookButton(ActionEvent event) throws IOException {

		if (table.getSelectionModel().getSelectedItem() != null) {
			bookInfoButton(event);
			String priceString = "";
			String limitString = "";
			BookCopy selectedPerson = table.getSelectionModel().getSelectedItem();
			titleField.setText(selectedPerson.getBook().getTitle());
			isbnField.setText(selectedPerson.getBook().getISBNnumber());
			priceField.setText(priceString + selectedPerson.getBook().getPrice());
			limitField.setText(limitString + selectedPerson.getBook().getLimit());

			firstName = selectedPerson.getBook().getAuthors().get(0).getFirstName();
			lastName = selectedPerson.getBook().getAuthors().get(0).getLastName();
			state = selectedPerson.getBook().getAuthors().get(0).getAddress().getState();
			city = selectedPerson.getBook().getAuthors().get(0).getAddress().getCity();
			zip = selectedPerson.getBook().getAuthors().get(0).getAddress().getZip();
			street = selectedPerson.getBook().getAuthors().get(0).getAddress().getStreet();
			//phoneNum = selectedPerson.getBook().getAuthors().get(0).getAddress().getPhoneNumber();
			//email = selectedPerson.getBook().getAuthors().get(0).getAddress().getEmail();
			editIsOn = true;

		}
	}

	@FXML
	public void clearButton() {
		if (clearAuthorFields) {
			firstNameField.setText("");
			lastNameField.setText("");
			stateField.setText("");
			cityField.setText("");
			zipField.setText("");
			streetField.setText("");
			phoneNumField.setText("");
			emailField.setText("");
		} else if (bookFieldField) {
			titleField.setText("");
			isbnField.setText("");
			priceField.setText("");
			limitField.setText("");
			searchField.setText("");
		}
	}

	@FXML
	public void searchButton() throws ClassNotFoundException, IOException {
		// if(searchAuthorField) {}
		// else {}
		String value = searchField.getText();
		if (!value.equals("") || value != null) {
			List<BookCopy> bookCopies = rootBook.search(value);
			showTableDataForSearch(bookCopies);
		}

	}

	@FXML
	public void bookInfoButton(ActionEvent event) throws IOException {
		clearAuthorFields = false;
		searchAuthorField = false;
		bookFieldField = true;

		Parent pane = FXMLLoader.load(getClass().getClassLoader().getResource("ui/BookFXML.fxml"));
		Scene scene = new Scene(pane);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	public void saveBookButton() throws IOException, ClassNotFoundException {
		if (isAnyAuthorTextFieldEmpty()) {
			compulsoryFields("All fields are compulsory!");

			return;
		}
		if (aduthorAdded) {
			String uniqueID = UUID.randomUUID().toString();
			book = new Book(title, isbn, price, limit, authors);
			bookCopy = new BookCopy(uniqueID, book);

			rootBook.addBookCopy(bookCopy);
			rootBook.addBookData();
			aduthorAdded = false;
		} else {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setHeaderText("Warring");
			alert.setContentText("Please add author information first!");

			alert.showAndWait();
		}
		// clearButton();
		// showTableData();
	}

	@FXML
	public void saveAuthorButton() {
		/*
		 * if(isAnyAuthorTextFieldEmpty()) {
		 * compulsoryFields("All fields are compulsory!"); return; }
		 */

		saveAuthorPrivate();
		// clearButton();
	}

	@FXML
	public void updateBookButton() throws ClassNotFoundException, IOException {
		if (editIsOn) {

			if (aduthorAdded) {
				String uniqueID = UUID.randomUUID().toString();
				book = new Book(title, isbn, price, limit, authors);
				bookCopy = new BookCopy(uniqueID, book);

				rootBook.updateBookData(bookCopy);
				aduthorAdded = false;
			} else {
				if (isAnyAuthorTextFieldEmpty()) {
					compulsoryFields("All fields are compulsory!");
				} else
					saveAuthorPrivate();
			}
			showTableData();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

}
