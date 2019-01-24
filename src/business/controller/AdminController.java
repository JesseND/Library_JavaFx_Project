package business.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ui.LibraryMemberPanel;
import business.model.ROLETYPE;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AdminController implements Initializable {

	@FXML
	BorderPane borderPane;
	@FXML
	private Button books, members, checkouts;
	private double x, y;
	@FXML
	private Label loggedUserName;

	@FXML
	public void loadBooks(ActionEvent event) throws IOException {
		if (Main.loggedUser.getRoles().get(0).getRoleValue().equals(ROLETYPE.ADMIN)) {
			new Alert(AlertType.INFORMATION, "No Access.", ButtonType.OK).showAndWait();
		} else {

			Parent root = FXMLLoader.load(getClass().getResource("/ui/Books.fxml"));
			Scene scene = new Scene(root);
			scene.setFill(Color.TRANSPARENT);
			((Node) event.getSource()).getScene().getWindow().hide();

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
		}
		books.getStyleClass().add("clicked-button"); // active button
		members.getStyleClass().add("unclicked-button");
		checkouts.getStyleClass().add("unclicked-button");
	}

	@FXML
	public void loadMembers(ActionEvent event) {

		if (Main.loggedUser.getRoles().get(0).getRoleValue().equals(ROLETYPE.LIBRARIAN)) {
			new Alert(AlertType.INFORMATION, "No Access.", ButtonType.OK).showAndWait();
		} else {
			Stage primaryStage = new Stage();
			primaryStage.initStyle(StageStyle.TRANSPARENT);
			LibraryMemberPanel p;
			try {
				p = new LibraryMemberPanel(primaryStage);
				p.show();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@FXML
	public void loadCheckouts(ActionEvent event) throws IOException {

		if (Main.loggedUser.getRoles().get(0).getRoleValue().equals(ROLETYPE.ADMIN)) {
			new Alert(AlertType.INFORMATION, "No Access.", ButtonType.OK).showAndWait();
		} else {

			Parent root = FXMLLoader.load(getClass().getResource("/ui/Checkouts.fxml"));
			Scene scene = new Scene(root);
			scene.setFill(Color.TRANSPARENT);
			((Node) event.getSource()).getScene().getWindow().hide();

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
		}
	}

	@FXML
	public void logout() throws IOException {
		Stage stage = (Stage) borderPane.getScene().getWindow();
		stage.close();

		stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/ui/Login.fxml"));
		Scene scene = new Scene(root);
		scene.setFill(Color.TRANSPARENT);

		stage.setScene(scene);
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.show();
	}

	public void loadUi(String ui) {
		HBox rooParent = null;
		try {
			rooParent = FXMLLoader.load(getClass().getResource(ui));
		} catch (IOException e) {
			e.printStackTrace();
		}
		borderPane.setCenter(rooParent);

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		if (Main.loggedUser.getRoles().get(0).getRoleValue().equals(ROLETYPE.ADMIN)) {
			checkouts.setDisable(false);
		}

		if (Main.loggedUser.getRoles().get(0).getRoleValue().equals(ROLETYPE.LIBRARIAN)) {
			members.setDisable(false);
		}

		loggedUserName
.setText("Welcome " + Main.loggedUser.getFirstName() + ". " + Main.loggedUser.getLastName());
	}

}
