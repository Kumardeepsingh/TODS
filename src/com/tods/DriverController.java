package com.tods;

import java.io.IOException;

import com.tods.model.OrderModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DriverController extends Controller {

	@FXML
	private Label label;
	@FXML
	private ListView<Integer> list;
	@FXML
	private ListView<Integer> numberlist;
	@FXML
	private TextField totalorders;
	@FXML
	private ChoiceBox schoolname;
	@FXML
	private Button confirm;

	@FXML
	private TextArea textarea;

	public DriverController() {
		super();
		label = new Label();
		totalorders = new TextField();
	}

	@FXML
	public void initialize() {
		ObservableList<String> schoollist = FXCollections.observableArrayList("North Ridge Elementary",
				"Khalsa School Newton", "Princess Margaret Secondary School", "Hyland Elementary",
				"Frank Hurt Secondary School");
		schoolname.setValue("North Ridge Elementary");
		schoolname.setItems(schoollist);
	}

	@FXML
	private void processSignOut(ActionEvent event) throws IOException {
		displayScene("view/EmployeeLogin.fxml");
	}
	
	@FXML
	private void processConfirmPickup(ActionEvent event) throws IOException  {
		label.setText("Pickup is Confirmed.");
	}

	@FXML
	private void processgetOrders(ActionEvent event) throws IOException {
		OrderModel ordermodel = new OrderModel();
		ObservableList<Integer> order = FXCollections.observableArrayList();
		ObservableList<Integer> templist = FXCollections.observableArrayList();
		order.addAll(ordermodel.getOrderIdFromSchoolName((String) schoolname.getValue()));
		list.setItems(order);
		for (int i = 1; i <= order.size(); i++) {
			templist.add(i - 1, i);
		}
		int total = (templist.size());
		totalorders.setText(String.valueOf(total));
		numberlist.setItems(templist);
		confirm.setDisable(false);
	}
}
