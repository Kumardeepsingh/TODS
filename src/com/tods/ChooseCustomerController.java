package com.tods;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class ChooseCustomerController extends Controller {

	@FXML
	private Button newcustomerbtn;
	@FXML
	private Button existingcustomerbtn;
	@FXML
	private Button signoutbtn;

	public ChooseCustomerController() {
		super();
	}

	@FXML
	private void processNewCustomer(ActionEvent event) throws IOException {
		displayScene("view/NewCustomer.fxml");
	}

	@FXML
	private void processExistingCustomer(ActionEvent event) throws IOException {
		displayScene("view/ExistingCustomer.fxml");
	}

	@FXML
	private void processSignOut(ActionEvent event) throws IOException {
		displayScene("view/EmployeeLogin.fxml");
	}

}
