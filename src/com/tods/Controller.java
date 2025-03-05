package com.tods;

import java.io.IOException;
import java.util.HashMap;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class Controller {

	MainApp mainApp;
	Controller controller;

	public Controller() {

	}

	public Controller(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	protected void passDataToScene(HashMap<String, Double> data) {

	}

	protected void displayScene(String location) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource(location));
			AnchorPane NewCustomer = (AnchorPane) loader.load();
			this.mainApp.getRootLayout().setCenter(NewCustomer);

			controller = loader.getController();
			controller.setMainApp(this.mainApp);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void displayScene(String location, HashMap<String, Double> data) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource(location));
			AnchorPane NewCustomer = (AnchorPane) loader.load();
			this.mainApp.getRootLayout().setCenter(NewCustomer);

			controller = loader.getController();
			controller.passDataToScene(data);
			controller.setMainApp(this.mainApp);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void addTextLimiter(final TextField tf, final int maxLength, final Text spec, final boolean isNumber) {
		tf.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(final ObservableValue<? extends String> ov, final String oldValue,
					final String newValue) {

				if (tf.getText().length() > maxLength) {
					String s = tf.getText().substring(0, maxLength);
					tf.setText(s);
				}
				if (isNumber) {
					if (!newValue.matches("\\d*")) {
						tf.setText(newValue.replaceAll("[^\\d]", ""));
					}
				}
			}
		});
		if (spec.toString() != "") {
			tf.focusedProperty().addListener(new ChangeListener<Boolean>() {

				@Override
				public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue,
						Boolean newPropertyValue) {
					if (newPropertyValue) {
						spec.setVisible(true);
					} else {
						spec.setVisible(false);
					}
				}
			});
		}
	}

	protected void addTextLimiter(final TextField tf, final int maxLength) {
		tf.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(final ObservableValue<? extends String> ov, final String oldValue,
					final String newValue) {

				if (tf.getText().length() > maxLength) {
					String s = tf.getText().substring(0, maxLength);
					tf.setText(s);
				}
				if (!newValue.matches("\\d*")) {
					tf.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
	}

	protected void displayAlert(String msg) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(msg);
		alert.setContentText("Please try again");
		alert.showAndWait();
	}

	private void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

}
