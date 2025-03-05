package com.tods;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class OrderConfirmationController extends Controller {
	@FXML
	TextArea orderDetails;
	@FXML
	TextField confirmBox;
	public static Boolean confirmation = false;

	public OrderConfirmationController() {
		super();
	}

	@FXML
	private void initialize() {
		if (confirmation) {
			confirmBox.setText("Order Confirmed");
		}
	}

	@Override
	protected void passDataToScene(HashMap<String, Double> order) {
		setOrderDetails(order);
	}

	public void setOrderDetails(HashMap<String, Double> order) {
		String str = "";
		Iterator iterator = order.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry mapElement = (Map.Entry) iterator.next();
			if ((String) mapElement.getKey() == "totalPrice") {
				continue;
			} else {
				String productName = (String) mapElement.getKey();
				Double price = (Double) mapElement.getValue();
				str += productName + ":		" + price + "\n";
			}

		}
		str += "-x-x-x-x-x-x-x-x-x-x-x-x-x-";
		str += "\nTotal Price:		" + order.get("totalPrice");
		orderDetails.setText(str);

	}

	@FXML
	private void home() {
		displayScene("view/ChooseCustomer.fxml");

	}

	@FXML
	private void editOrder() {
		displayScene("view/EditOrder.fxml");
	}

}
