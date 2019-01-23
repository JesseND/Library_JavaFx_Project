package business.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AdminController {

	@FXML
	BorderPane borderPane;
	@FXML
	private Button books, members, checkouts;
	private double x, y;

	@FXML
	public void loadBooks(ActionEvent event) {
		loadUi("/ui/Books.fxml");
		/*members.getStyleClass().clear();
		books.getStyleClass().clear();
		checkouts.getStyleClass().clear();*/
		books.getStyleClass().add("clicked-button"); // active button
		members.getStyleClass().add("unclicked-button");
		checkouts.getStyleClass().add("unclicked-button");
	}

	@FXML
	public void loadMembers(ActionEvent event) {
		loadUi("/ui/Members.fxml");
		/*members.getStyleClass().clear();
		books.getStyleClass().clear();
		checkouts.getStyleClass().clear();*/
		members.getStyleClass().add("clicked-button"); // active button
		books.getStyleClass().add("unclicked-button");
		checkouts.getStyleClass().add("unclicked-button");
	}
	
	@FXML
	public void loadCheckouts(ActionEvent event) {
		loadUi("/ui/Checkouts.fxml");
		/*members.getStyleClass().clear();
		books.getStyleClass().clear();
		checkouts.getStyleClass().clear();*/
		checkouts.getStyleClass().add("clicked-button"); // active button
		books.getStyleClass().add("unclicked-button");
		members.getStyleClass().add("unclicked-button");
	}

	@FXML
	public void logout() throws IOException {
		Stage stage = (Stage) borderPane.getScene().getWindow();
		stage.close();
		
		stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/ui/Login.fxml"));
		Scene scene = new Scene(root);
		scene.setFill(Color.TRANSPARENT);

		/*borderPane.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				x = event.getSceneX();
				y = event.getSceneY();
			}
		});
		borderPane.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				stage.setX(event.getSceneX() - x);
				stage.setY(event.getSceneY() - y);
			}
		});*/

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

}
