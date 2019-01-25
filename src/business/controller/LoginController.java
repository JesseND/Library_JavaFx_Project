package business.controller;

import java.awt.Desktop.Action;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import business.model.Account;
import business.model.Address;
import business.model.Admin;
import business.model.Librarian;
import business.model.Person;
import business.model.ROLETYPE;
import business.model.Role;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginController {

	private double x, y;
	@FXML
	private Parent root;
	@FXML
	private TextField username;
	@FXML
	private TextField password;
	@FXML
	private Label message;

	@FXML
	public void login(ActionEvent event) throws IOException {
		
		String login = username.getText().toString().trim();
		String pass = password.getText().toString().trim();
		
//		Account acc = new Account();
//		acc.setUsername(login);
//		acc.setPassword(pass);
		List<Person> persons = new ArrayList<Person>();
		
		Person p1= new Person(1, "Jesse", "NDAM", new Admin(), new Address("", "", "", ""), "7394939983");
//		p1.setAccount(new Account());
//		p1.getAccount().setUsername("admin");
//		p1.getAccount().setPassword("admin");
		
		Person p2= new Person(1, "Abdu", "FALL", new Librarian(), new Address("", "", "", ""), "7394939983");
//		p2.setAccount(new Account());
//		p2.getAccount().setUsername("lib");
//		p2.getAccount().setPassword("lib");
		
		persons.add(p1);
		persons.add(p2);
		
		
//		for (Person person : persons) {
//			if (person.getAccount() != null) {
//				if (person.getAccount().getUsername().equals(acc.getUsername()) 
//						&& person.getAccount().getPassword().equals(acc.getPassword())) {
//					Main.loggedUser = person;
//				}
//			}
//		}

		if (Main.loggedUser != null) {
			root = FXMLLoader.load(getClass().getResource("/ui/MenuUI.fxml"));
			Scene scene = new Scene(root);
			scene.setFill(Color.TRANSPARENT);
			((Node)event.getSource()).getScene().getWindow().hide();

			Stage stage = new Stage();
			root.setOnMousePressed(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					x = event.getSceneX();
					y = event.getSceneY();
				}
			});

			root.setOnMouseDragged(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					stage.setX(event.getSceneX() - x);
					stage.setY(event.getSceneY() - y);
				}
			});

			scene.getStylesheets().add(getClass().getResource("/ui/application.css").toExternalForm());
			stage.setScene(scene);
			stage.initStyle(StageStyle.TRANSPARENT);
			stage.show();
		} else {
			message.setText("Username or Password incorrect. Please try again !!!");
		}
		
	}

	@FXML
	public void close(ActionEvent event) {
		Stage stage = (Stage) root.getScene().getWindow();
		stage.close();
	}

}
