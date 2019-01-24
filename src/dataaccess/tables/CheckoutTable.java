package dataaccess.tables;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class CheckoutTable {
	
	private final SimpleIntegerProperty id;
	private final SimpleStringProperty fname;
	private final SimpleStringProperty lname;
	private final SimpleIntegerProperty entrie;
	private final SimpleDoubleProperty fines;
	private final SimpleStringProperty lastcheckout;
	
	public CheckoutTable(int id, String fname, String lname,
			int entrie, Double fines, String lastcheckout) {
		super();
		this.id = new SimpleIntegerProperty(id);
		this.fname = new SimpleStringProperty(fname);
		this.lname = new SimpleStringProperty(lname);
		this.entrie = new SimpleIntegerProperty(entrie);
		this.fines = new SimpleDoubleProperty(fines);
		this.lastcheckout = new SimpleStringProperty(lastcheckout);
	}

	public SimpleIntegerProperty getId() {
		return id;
	}

	public SimpleStringProperty getFname() {
		return fname;
	}

	public SimpleStringProperty getLname() {
		return lname;
	}

	public SimpleIntegerProperty getEntrie() {
		return entrie;
	}

	public SimpleDoubleProperty getFines() {
		return fines;
	}

	public SimpleStringProperty getLastcheckout() {
		return lastcheckout;
	}

}
