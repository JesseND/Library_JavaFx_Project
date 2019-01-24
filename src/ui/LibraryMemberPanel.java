package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.List;

import business.controller.LibraryMemberController;
import business.model.Address;
import business.model.LibraryMember;
import business.model.Person;
import business.model.Role;

public class LibraryMemberPanel extends Stage {

	Stage primaryStage;

	private TableView<Person> table = new TableView<>();

	private ObservableList<Person> data = FXCollections.observableArrayList();
	private TextField memberIdTextField = new TextField();
	private TextField firstNameTextField = new TextField();
	private TextField lastNameTextField = new TextField();
	private TextField phoneNumberTextField = new TextField();
	private TextField streetTextField = new TextField();
	private TextField cityTextField = new TextField();
	private TextField stateTextField = new TextField();
	private TextField zipTextField = new TextField();

	private TextField queryTextField = new TextField();

	private Button createBtn = new Button("Create");
	LibraryMemberController controller = LibraryMemberController.getInstance();

	public LibraryMemberPanel(Stage ps) throws ClassNotFoundException {
		primaryStage = ps;
		primaryStage.setTitle("Member Form");

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);

		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Label memberIdLabel = new Label("Member ID");
		grid.add(memberIdLabel, 0, 0);
		grid.add(memberIdTextField, 1, 0);

		Label firstNameLabel = new Label("First Name");
		grid.add(firstNameLabel, 0, 1);
		grid.add(firstNameTextField, 1, 1);

		Label lastNameLabel = new Label("Last Name");
		grid.add(lastNameLabel, 0, 2);
		grid.add(lastNameTextField, 1, 2);

		Label phoneNumberLabel = new Label("Phone");
		grid.add(phoneNumberLabel, 0, 3);
		grid.add(phoneNumberTextField, 1, 3);

		Label streetLabel = new Label("Street");
		grid.add(streetLabel, 0, 4);
		grid.add(streetTextField, 1, 4);

		Label cityLabel = new Label("City");
		grid.add(cityLabel, 0, 5);
		grid.add(cityTextField, 1, 5);

		Label stateLabel = new Label("State");
		grid.add(stateLabel, 0, 6);
		grid.add(stateTextField, 1, 6);

		Label zipLabel = new Label("Zip");
		grid.add(zipLabel, 0, 7);
		grid.add(zipTextField, 1, 7);

		Button updateBtn = new Button("Update");
		Button clearBtn = new Button("Clear");
		Button back = new Button("Back to menu");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BASELINE_CENTER);
		hbBtn.getChildren().add(createBtn);
		hbBtn.getChildren().add(updateBtn);
		hbBtn.getChildren().add(clearBtn);
		hbBtn.getChildren().add(back);
		grid.add(hbBtn, 1, 8);

		HBox hboxSearch = new HBox(10);
		Button searchBtn = new Button("Search");
		hboxSearch.getChildren().add(queryTextField);
		hboxSearch.getChildren().add(searchBtn);
		grid.add(hboxSearch, 1, 9);

		TableColumn<Person, String> memberIdCol = new TableColumn<>("Member ID");
		memberIdCol.setCellValueFactory(new PropertyValueFactory<Person, String>("id"));

		TableColumn<Person, String> firstNameCol = new TableColumn<>("First Name");
		firstNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));

		TableColumn<Person, String> lastNameCol = new TableColumn<>("Last Name");
		lastNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));

		table.setItems(data);
		table.getColumns().add(memberIdCol);
		table.getColumns().add(firstNameCol);
		table.getColumns().add(lastNameCol);

		HBox hbTable = new HBox(10);
		hbTable.getChildren().add(table);
		grid.add(hbTable, 1, 10);

		createBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					createMember();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		updateBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Person person = getMemberInfo();

				controller.updateMember(person);

				try {
					setTableData(controller.getAllMembers());
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}

				clearForm();
				showDialog("Member has been updated.");
			}
		});

		clearBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				clearForm();
			}
		});

		back.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				((Node) event.getSource()).getScene().getWindow().hide();
				primaryStage = new Stage();
				Parent root = null;
				try {
					root = FXMLLoader.load(getClass().getResource("/ui/AdminUI.fxml"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Scene scene = new Scene(root);
				scene.setFill(Color.TRANSPARENT);

				primaryStage.setScene(scene);
				primaryStage.initStyle(StageStyle.TRANSPARENT);
				primaryStage.show();
			}
		});
		searchBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					searchMembers();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		});

		table.setRowFactory(tv -> {
			TableRow<Person> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {

					Person clickedLibMember = row.getItem();

					LibraryMemberController controller = LibraryMemberController.getInstance();
					Person member = controller.getMemberById(String.valueOf(clickedLibMember.getId()));
					System.out.println(member);
					displayMemberInfo(member);
				}
			});
			return row;
		});

		loadMemberList();

		Scene scene = new Scene(grid);
		primaryStage.setScene(scene);
		setScene(scene);
	}

	private Person getMemberInfo() {
		Address address = new Address(streetTextField.getText(), cityTextField.getText(), stateTextField.getText(),
				zipTextField.getText());

		LibraryMember libMember = new LibraryMember(memberIdTextField.getText());

		Person person = new Person(Integer.parseInt(memberIdTextField.getText()), firstNameTextField.getText(),
				lastNameTextField.getText(), libMember, address, phoneNumberTextField.getText());

		return person;
	}

	private void createMember() throws ClassNotFoundException {
		Person p = getMemberInfo();

		LibraryMemberController controller = LibraryMemberController.getInstance();
		controller.addMember(p);
		data.add(p);

		clearForm();
		showDialog("Member has been created.");
	}

	private void searchMembers() throws ClassNotFoundException {
		String query = queryTextField.getText();
		LibraryMemberController controller = LibraryMemberController.getInstance();
		List<Person> persons = controller.search(query);
		setTableData(persons);
	}

	private void setTableData(List<Person> persons) {
		data.clear();

		for (Person person : persons) {
			data.add(person);
		}
	}

	private void displayMemberInfo(Person person) {
		createBtn.setDisable(true);
		memberIdTextField.setDisable(true);
		memberIdTextField.setText(String.valueOf(person.getId()));
		firstNameTextField.setText(person.getFirstName());
		lastNameTextField.setText(person.getLastName());
		phoneNumberTextField.setText(person.getPhoneNumber());
		streetTextField.setText(person.getAddress().getStreet());
		cityTextField.setText(person.getAddress().getCity());
		stateTextField.setText(person.getAddress().getState());
		zipTextField.setText(person.getAddress().getZip());
	}

	private void clearForm() {
		createBtn.setDisable(false);
		memberIdTextField.setDisable(false);
		memberIdTextField.clear();
		firstNameTextField.clear();
		lastNameTextField.clear();
		phoneNumberTextField.clear();
		streetTextField.clear();
		cityTextField.clear();
		stateTextField.clear();
		zipTextField.clear();
	}

	private void loadMemberList() throws ClassNotFoundException {
		LibraryMemberController controller = LibraryMemberController.getInstance();
		setTableData(controller.getAllMembers());
	}

	private void showDialog(String msg) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText(msg);

		alert.showAndWait();
	}
}