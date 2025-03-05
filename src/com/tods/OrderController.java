package com.tods;

import java.io.IOException;
import java.util.HashMap;

import com.tods.model.CustomerModel;
import com.tods.model.DependantModel;
import com.tods.model.OrderModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class OrderController extends Controller {

	static int customerId;

	@FXML
	private ComboBox salad;
	@FXML
	private ComboBox snack;
	@FXML
	private ComboBox dish;
	@FXML
	private ComboBox drink;
	@FXML
	private TextField orderFor;

	ObservableList<String> saladList = FXCollections.observableArrayList("Caesar Salad", "Green Salad", "Cobbl Salad",
			"Chicken Salad");
	ObservableList<String> snackList = FXCollections.observableArrayList("Rasgulla", "Apple Pie",
			"Strawberry Cheesecake");
	ObservableList<String> dishList = FXCollections.observableArrayList("Macaroni", "Dhokla", "Masala Dosa",
			"Hyderabadi biriyani", "Masala Dosa", "Sushi", "Teriyaki", "Spring Rolls", "Kung Pao Chicken", "Spicy Tofu", "Burgers", "Soup", "Curry", "Pasta");
	ObservableList<String> drinkList = FXCollections.observableArrayList("Juice", "Energy drink", "Pepsi", "Coca-Cola");

	public OrderController() {
		super();
	}

	@FXML
	private void initialize() {
		salad.setValue("Caesar Salad");
		salad.setItems(saladList);

		snack.setValue("Apple Pie");
		snack.setItems(snackList);

		dish.setValue("Sushi");
		dish.setItems(dishList);

		drink.setValue("Energy drink");
		drink.setItems(drinkList);
		DependantModel dependantModel = new DependantModel();
		orderFor.setText(dependantModel.getdependentNameFromCustomerId(customerId));
	}

	@FXML
	private void confirmOrder() {
		OrderModel odrModel = new OrderModel();
		HashMap<String, String> newOdr = new HashMap<>();
		newOdr.put("salad", salad.getValue().toString());
		newOdr.put("snack", snack.getValue().toString());
		newOdr.put("dish", dish.getValue().toString());
		newOdr.put("drink", drink.getValue().toString());
		HashMap<String, Double> orderDetails = odrModel.placeOrder(newOdr, customerId);
		displayScene("view/ConfirmOrder.fxml", orderDetails);

	}

	@FXML
	private void processBack(ActionEvent event) throws IOException {
		displayScene("view/ExistingCustomer.fxml");
	}

}
