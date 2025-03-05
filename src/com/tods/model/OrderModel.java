package com.tods.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.IntStream;

import com.tods.OrderConfirmationController;

public class OrderModel extends Model {

	public OrderModel() {
		super();
	}

	public HashMap<String, Double> placeOrder(HashMap<String, String> order, int customerId) {
		int orderId = 0;
		HashMap<String, Double> orderDetails = new HashMap<String, Double>();
		HashMap<Integer, Double> productList = getProducts(order, orderDetails);
		double totalPrice = calculateTotalPrice(productList);
		orderDetails.put("totalPrice", totalPrice);
		int dependantId = getdependentIdFromCustomerId(customerId);
		try {

			preparedStatement = connect.prepareStatement(
					"insert into  myorder(totalPrice, dependantId) values (" + totalPrice + "," + dependantId + ")");
			int check = preparedStatement.executeUpdate();
			if (check != 0) {
				OrderConfirmationController.confirmation = true;
				preparedStatement = connect.prepareStatement("select orderId from myorder where totalPrice ="
						+ totalPrice + "And dependantId = " + dependantId + " Order By OrderDate Desc Limit 1");
				resultSet = preparedStatement.executeQuery();
				resultSet.next();
				orderId = resultSet.getInt("orderId");
				Iterator iterator = productList.entrySet().iterator();
				while (iterator.hasNext()) {
					Map.Entry mapElement = (Map.Entry) iterator.next();

					int productId = (int) mapElement.getKey();
					recordTransaction(orderId, productId);
				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderDetails;
	}

	public HashMap<String, Double> updateOrder(HashMap<String, String> order, int customerId) {
		int orderId = 0;
		HashMap<String, Double> orderDetails = new HashMap<String, Double>();
		HashMap<Integer, Double> productList = getProducts(order, orderDetails);
		double totalPrice = calculateTotalPrice(productList);
		orderDetails.put("totalPrice", totalPrice);
		int dependantId = getdependentIdFromCustomerId(customerId);
		ArrayList<Integer> newOrder = getProductIds(order);
		ArrayList<Integer> previousOrder = getOrderedProductFromDependantId(dependantId);
		try {

			preparedStatement = connect.prepareStatement("update  myorder set totalPrice= " + totalPrice
					+ "where dependantId = " + dependantId + " Order By OrderDate Desc Limit 1");
			int check = preparedStatement.executeUpdate();
			if (check != 0) {
				OrderConfirmationController.confirmation = true;
				preparedStatement = connect.prepareStatement("select orderId from myorder where totalPrice ="
						+ totalPrice + "And dependantId = " + dependantId + " Order By OrderDate Desc Limit 1");
				resultSet = preparedStatement.executeQuery();
				resultSet.next();
				orderId = resultSet.getInt("orderId");
				for (int i = 0; i < newOrder.size(); i++) {
					int newProductId = newOrder.get(i);
					int oldProductId = previousOrder.get(i);

					if (previousOrder.contains(newProductId)) {
						continue;
					} else {
						updateTransaction(orderId, oldProductId, newProductId);

					}
				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderDetails;
	}


	private void recordTransaction(int orderId, int productId) {
		try {
			preparedStatement = connect
					.prepareStatement("insert into  order_product(orderId, productId,Quantity) values (" + orderId + ","
							+ productId + ",1)");

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void updateTransaction(int orderId, int oldProductId, int newProductId) {
		try {
			preparedStatement = connect.prepareStatement("update  order_product set productId = " + newProductId
					+ " where orderID = " + orderId + " And productId = " + oldProductId);

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private HashMap<Integer, Double> getProducts(HashMap<String, String> order, HashMap<String, Double> orderDetails) {
		HashMap<Integer, Double> products = new HashMap<Integer, Double>();
		try {
			Iterator iterator = order.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry mapElement = (Map.Entry) iterator.next();
				String productName = (String) mapElement.getValue();
				preparedStatement = connect.prepareStatement(
						"select productID,price,Name from product where Name = '" + productName + "'");
				resultSet = preparedStatement.executeQuery();
				resultSet.next();
				Integer productId = resultSet.getInt("productID");
				Double productPrice = resultSet.getDouble("price");
				products.put(productId, productPrice);
				orderDetails.put(productName, productPrice);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
	}

	private ArrayList<Integer> getProductIds(HashMap<String, String> order) {
		ArrayList<Integer> products = new ArrayList<Integer>();
		try {
			Iterator iterator = order.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry mapElement = (Map.Entry) iterator.next();
				String productName = (String) mapElement.getValue();
				preparedStatement = connect
						.prepareStatement("select productID from product where Name = '" + productName + "'");
				resultSet = preparedStatement.executeQuery();
				resultSet.next();
				Integer productId = resultSet.getInt("productID");
				products.add(productId);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
	}

	private double calculateTotalPrice(HashMap<Integer, Double> productsPrice) {
		double totalPrice = 0.0;
		Iterator iterator = productsPrice.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry mapElement = (Map.Entry) iterator.next();
			totalPrice += (double) mapElement.getValue();
		}
		return totalPrice;

	}

	public HashMap<String, String> getCustomerOrderFromDependantId(int dependantId) {
		HashMap<String, String> order = new HashMap<String, String>();
		try {

			preparedStatement = connect.prepareStatement("select name,categoryID from product where productID in "
					+ "(select productID from order_product where orderID = "
					+ "(select orderID from myorder where dependantID = " + dependantId
					+ " Order By OrderDate Desc Limit 1))");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				switch (resultSet.getInt("categoryID")) {
				case 123:
					order.put("salad", resultSet.getString("name"));
					break;
				case 124:
					order.put("drink", resultSet.getString("name"));
					break;
				case 125:
					order.put("snack", resultSet.getString("name"));
					break;
				case 126:
					order.put("dish", resultSet.getString("name"));
					break;
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return order;
	}

	public ArrayList<Integer> getOrderedProductFromDependantId(int dependantId) {
		ArrayList<Integer> order = new ArrayList<Integer>();
		try {

			preparedStatement = connect.prepareStatement("select productID,name from product where productID in "
					+ "(select productID from order_product where orderID = "
					+ "(select orderID from myorder where dependantID = " + dependantId
					+ " Order By OrderDate Desc Limit 1))");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				order.add(resultSet.getInt("productID"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return order;
	}
	

}
