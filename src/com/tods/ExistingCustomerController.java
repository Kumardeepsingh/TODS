package com.tods;

import java.io.IOException;
import java.util.HashMap;

import com.tods.model.CustomerModel;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class ExistingCustomerController extends Controller {
	private int customerId;

	private MainApp mainApp;
	@FXML
	private TextField pnumbersearch;
	@FXML
	private TextField fname;
	@FXML
	private TextField lname;
	@FXML
	private TextField pnumber;
	@FXML
	private TextField email;
	@FXML
	private TextField dependantname;
	@FXML
	private TextField schoolname;
	@FXML
	private Button backbtn;

	public ExistingCustomerController() {
		super();
	}

	@FXML
	private void initialize() {
		addTextLimiter(pnumbersearch, 10);
	}

	@FXML
	private void processSignOut(ActionEvent event) throws IOException {
		displayScene("view/EmployeeLogin.fxml");
	}

	@FXML
	private void searchCustomer() {
		if (pnumbersearch.getText().isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Please enter a valid customer number in the search bar");
			alert.showAndWait();
			
		} else {
			search();
		}

	}
	
	private void search() {
		CustomerModel customerModel = new CustomerModel();
		HashMap<String, String> customer = customerModel.searchCustomer(Long.parseLong(pnumbersearch.getText()));
		if (customer.isEmpty()) {
			displayAlert("The number you entered does not exist.");

		} else {
			this.customerId = Integer.parseInt(customer.get("customerId"));
			String dependantName = customerModel.getdependentNameFromCustomerId(customerId);
			int dependantId = customerModel.getdependentIdFromCustomerId(customerId);
			String schoolName = customerModel.getSchoolFromDependantId(dependantId);
			fname.setText(customer.get("firstName"));
			lname.setText(customer.get("lastName"));
			pnumber.setText(customer.get("phoneNumber"));
			email.setText(customer.get("email"));
			dependantname.setText(dependantName);
			schoolname.setText(schoolName);
			OrderController.customerId = this.customerId;
		}
	
	}

	@FXML
	private void processBack(ActionEvent event) throws IOException {
		displayScene("view/ChooseCustomer.fxml");
	}

	@FXML
	private void processOrder(ActionEvent event) throws IOException {
		if (pnumber.getText().isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Please enter a valid customer number in the search bar");
			alert.showAndWait();
		} else {
			displayScene("view/Order.fxml");

		}
	}

}
