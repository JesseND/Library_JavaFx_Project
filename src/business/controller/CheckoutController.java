package business.controller;

import java.io.IOException;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
	
	@FXML
	public void close(ActionEvent event) {
		
		((Node) event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/ui/MenuUI.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		scene.setFill(Color.TRANSPARENT);

		primaryStage.setScene(scene);
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		primaryStage.show();
		
	}

}
