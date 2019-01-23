package business.controller;

import java.awt.Desktop.Action;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
	public void login(ActionEvent event) throws IOException {

		root = FXMLLoader.load(getClass().getResource("/ui/AdminUI.fxml"));
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
		
	}

	@FXML
	public void close(ActionEvent event) {
		Stage stage = (Stage) root.getScene().getWindow();
		stage.close();
	}

}
