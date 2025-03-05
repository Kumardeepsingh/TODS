package com.tods;

import java.io.IOException;
import java.util.HashMap;

import com.tods.model.DependantModel;
import com.tods.model.OrderModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class EditOrderController extends Controller {

	int customerId;
	@FXML
	private ComboBox<String> salad;
	@FXML
	private ComboBox<String> snack;
	@FXML
	private ComboBox<String> dish;
	@FXML
	private ComboBox<String> drink;
	@FXML
	private TextField orderFor;
	OrderModel odrModel;
	
	ObservableList<String> saladList = FXCollections.observableArrayList("Caesar Salad", "Green Salad", "Cobbl Salad",
			"Chicken Salad");
	ObservableList<String> snackList = FXCollections.observableArrayList("Rasgulla", "Apple Pie",
			"Strawberry Cheesecake");
	ObservableList<String> dishList = FXCollections.observableArrayList("Macaroni", "Dhokla", "Masala Dosa",
			"Hyderabadi biriyani", "Masala Dosa", "Sushi", "Teriyaki", "Spring Rolls", "Kung Pao Chicken", "Spicy Tofu", "Burgers", "Soup", "Curry", "Pasta");
	ObservableList<String> drinkList = FXCollections.observableArrayList("Juice", "Energy drink", "Pepsi", "Coca-Cola");

	public EditOrderController() {
		super();
	}

	@FXML
	private void initialize() {
		odrModel = new OrderModel();
		this.customerId = OrderController.customerId;
		DependantModel dependantModel = new DependantModel();
		orderFor.setText(dependantModel.getdependentNameFromCustomerId(customerId));
		int dependantId = dependantModel.getdependentIdFromCustomerId(customerId);
		HashMap<String, String> customerOrder = new HashMap<>();
		customerOrder = odrModel.getCustomerOrderFromDependantId(dependantId);
		
		salad.setValue(customerOrder.get("salad").toString());
		salad.setItems(saladList);

		snack.setValue(customerOrder.get("snack").toString());
		snack.setItems(snackList);

		dish.setValue(customerOrder.get("dish").toString());
		dish.setItems(dishList);

		drink.setValue(customerOrder.get("drink").toString());
		drink.setItems(drinkList);
		
	}
	@FXML
	private void confirmOrder() {
		
		HashMap<String, String> newOdr = new HashMap<>();
		newOdr.put("salad", salad.getValue().toString());
		newOdr.put("snack", snack.getValue().toString());
		newOdr.put("dish", dish.getValue().toString());
		newOdr.put("drink", drink.getValue().toString());
		HashMap<String, Double> orderDetails = odrModel.updateOrder(newOdr, customerId);
		displayScene("view/ConfirmOrder.fxml", orderDetails);

	}

	@FXML
	private void processBack(ActionEvent event) throws IOException {
		displayScene("view/ExistingCustomer.fxml");
	}
}
