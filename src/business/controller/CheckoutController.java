package business.controller;

import java.net.URL;
import java.util.ResourceBundle;

import dataaccess.tables.CheckoutTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CheckoutController implements Initializable {
	
	@FXML private TableView<CheckoutTable> table;
	@FXML private TableColumn<CheckoutTable, Integer> id;
	@FXML private TableColumn<CheckoutTable, String> fname;
	@FXML private TableColumn<CheckoutTable, String> lname;
	@FXML private TableColumn<CheckoutTable, Integer> entries;
	@FXML private TableColumn<CheckoutTable, Double> fines;
	@FXML private TableColumn<CheckoutTable, String> lastCheckout;

	public ObservableList<CheckoutTable> list = FXCollections.observableArrayList(
			new CheckoutTable(123, "Jesse", "NDAM", 23, 0.0, ""),
			new CheckoutTable(123, "Abdu", "FALL", 23, 0.0, "")
			);
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
			
		id.setCellValueFactory(new PropertyValueFactory<CheckoutTable, Integer>("id"));
		fname.setCellValueFactory(new PropertyValueFactory<CheckoutTable, String>("fname"));
		lname.setCellValueFactory(new PropertyValueFactory<CheckoutTable, String>("lname"));
		entries.setCellValueFactory(new PropertyValueFactory<CheckoutTable, Integer>("entries"));
		fines.setCellValueFactory(new PropertyValueFactory<CheckoutTable, Double>("fines"));
		lastCheckout.setCellValueFactory(new PropertyValueFactory<CheckoutTable, String>("lastCheckout"));
		table.setItems(list);
	}

}
