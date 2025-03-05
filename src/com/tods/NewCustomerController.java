package com.tods;

import java.io.IOException;
import java.util.HashMap;

import com.tods.MainApp;
import com.tods.model.CustomerModel;
import com.tods.model.DependantModel;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class NewCustomerController extends Controller {
	private int customerId;
	private Text text = new Text("");
	@FXML
	private Button exCusBtn;
	@FXML
	private Button nwCusBtn;
	@FXML
	private TextField fname;
	@FXML
	private TextField lname;
	@FXML
	private TextField pnumber;
	@FXML
	private TextField email;
	@FXML
	private Text emailSpec;
	@FXML
	private TextField address;
	@FXML
	private TextField city;
	@FXML
	private TextField province;
	@FXML
	private TextField pcode;
	@FXML
	private TextField dependant;
	@FXML
	private Text dependantSpec;

	@FXML
	private ComboBox school;
	ObservableList<String> schoolList = FXCollections.observableArrayList("North Ridge Elementary",
			"Khalsa School Newton", "Princess Margaret Secondary School", "Hyland Elementary",
			"Frank Hurt Secondary School");

	public NewCustomerController() {
		super();
	}

	@FXML
	private void addAndOrder() {
		if (verifyInput()) {
			addNewCustomer();
			displayScene("view/Order.fxml");
		}
	}

	@FXML
	private void initialize() {
		school.setValue("Frank Hurt Secondary School");
		school.setItems(schoolList);
		addTextLimiter(pnumber, 10, text, true);
		addTextLimiter(email, 50, emailSpec, false);
		addTextLimiter(dependant, 50, dependantSpec, false);
		addTextLimiter(pcode, 6, text, false);
	}

	@FXML
	private void processBack(ActionEvent event) throws IOException {
		displayScene("view/ChooseCustomer.fxml");
	}

	private void addNewCustomer() {

		CustomerModel cusModel = new CustomerModel();
		DependantModel dependantModel = new DependantModel();
		HashMap<String, String> newCus = new HashMap<>();
		newCus.put("firstName", fname.getText());
		newCus.put("lastName", lname.getText());
		newCus.put("phoneNumber", pnumber.getText());
		newCus.put("email", email.getText());
		newCus.put("address", address.getText());
		newCus.put("city", city.getText());
		newCus.put("province", province.getText());
		newCus.put("postalCode", pcode.getText());
		customerId = cusModel.AddNewCustomer(newCus);
		OrderController.customerId = this.customerId;
		dependantModel.addNewDependant(dependant.getText(), customerId, school.getValue().toString());

	}

	private boolean verifyInput() {
		boolean check = false;
		if (fname.getText().isEmpty()) {
			displayAlert("Please enter a First Name");
			return check;
		} else if (lname.getText().isEmpty()) {
			displayAlert("Please enter a Last Name");
			return check;
		} else if (pnumber.getText().isEmpty()) {
			displayAlert("Please enter a Phone Number");
			return check;
		} else if (email.getText().isEmpty()) {
			displayAlert("Please enter an appropriate email address");
			return check;
		} else if (address.getText().isEmpty()) {
			displayAlert("Missing Street Address");
			return check;
		} else if (city.getText().isEmpty()) {
			displayAlert("Please specify a city");
			return check;
		} else if (pcode.getText().isEmpty()) {
			displayAlert("Please put your postal code");
			return check;
		} else if (dependant.getText().isEmpty()) {
			displayAlert("Please put the dependant name");
			return check;
		} else {
			check = true;
		}
		return check;
	}

}
