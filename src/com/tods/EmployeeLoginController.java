package com.tods;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.tods.model.EmployeeModel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class EmployeeLoginController extends Controller {

	@FXML
	private Button loginbtn;
	@FXML
	private TextField uname;
	@FXML
	private PasswordField pwd;
	@FXML
	private Label label;
	private EmployeeModel employeeModel;

	public EmployeeLoginController() {
		super();
		label = new Label();
	}

	@FXML
	private void processLogin(ActionEvent event) throws IOException {
		String email = uname.getText().toString();
		String password = pwd.getText().toString();
		employeeModel = new EmployeeModel();
		boolean validate = employeeModel.login(email, password);

		if (validate == true) {

			if (employeeModel.getEmployeeTypeIDFromUsername(email) == employeeModel.getEmployeeTypeIDFromEmployeeType())
				displayScene("view/ChooseCustomer.fxml");
			else
				displayScene("view/Driver.fxml");
		} else {
			label.setText("Incorrect Username or Password");
		}
	}

	@FXML
	private void forgotPassword() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText("Please talk to your manager regarding changing the password.");
		alert.showAndWait();
	}
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

}
