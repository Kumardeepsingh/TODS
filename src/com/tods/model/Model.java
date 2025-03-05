package com.tods.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.tods.ConnectionUtil;

public class Model {
	protected Connection connect = null;
	protected Statement statement = null;
	protected PreparedStatement preparedStatement = null;
	protected ResultSet resultSet = null;

	public Model() {
		this.connect = ConnectionUtil.getConn();
	}

	public int getdependentIdFromCustomerId(int customerId) {
		int dependantId = 0;
		try {
			preparedStatement = connect
					.prepareStatement("select dependantId from dependant where customerID = " + customerId);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			dependantId = resultSet.getInt("dependantID");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dependantId;
	}

	public String getdependentNameFromCustomerId(int customerId) {
		String dependantName = "";
		try {
			preparedStatement = connect
					.prepareStatement("select firstName,lastName from dependant where customerID = " + customerId);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			String firstName = resultSet.getString("firstName");
			String lastName = resultSet.getString("lastName");
			dependantName = firstName + " " + lastName;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dependantName;
	}

	public String getSchoolFromDependantId(int dependantId) {
		String schoolName = "";
		try {
			preparedStatement = connect.prepareStatement("select schoolName from school where schoolID ="
					+ "(select schoolID from dependant where dependantId = " + dependantId + ")");
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			schoolName = resultSet.getString("schoolName");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return schoolName;
	}

	public ArrayList<Integer> getOrderIdFromSchoolName(String schoolName) {
		ArrayList<Integer> orderid = new ArrayList<>();
		try {
			preparedStatement = connect.prepareStatement("select orderID from MyOrder where dependantID in "
					+ "(select dependantID from Dependant where schoolID in "
					+ "(select schoolID from School where SchoolName = '" + schoolName + "'))");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				orderid.add(resultSet.getInt("orderid"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderid;
	}

	public int getSchoolIdFromSchoolName(String schoolName) {
		int schoolId = 0;
		try {
			preparedStatement = connect
					.prepareStatement("select schoolId from school where schoolName ='" + schoolName + "'");
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			schoolId = resultSet.getInt("schoolId");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return schoolId;
	}

	public int getEmployeeTypeIDFromUsername(String username) {
		int typeid = 0;
		try {
			preparedStatement = connect
					.prepareStatement("select EmployeeTypeID from Employee where Username = '" + username + "'");
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			typeid = resultSet.getInt("employeetypeid");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return typeid;
	}

	public int getEmployeeTypeIDFromEmployeeType() {
		int typeid = 0;
		String typename = "staff";
		try {
			preparedStatement = connect
					.prepareStatement("select EmployeeTypeID from EmployeeType where typename = '" + typename + "'");
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			typeid = resultSet.getInt("employeetypeid");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return typeid;
	}

}
