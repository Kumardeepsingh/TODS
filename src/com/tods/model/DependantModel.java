package com.tods.model;

import java.sql.SQLException;
import java.util.HashMap;

public class DependantModel extends Model {

	public DependantModel() {
		super();
	}

	public int addNewDependant(String name, int customerId, String schoolName) {
		int dependantId = 0;
		String firstName = "";
		String lastName = "";
		String[] nameArr = name.split(" ");

		if (nameArr.length == 1) {
			firstName = nameArr[0];
		} else if (nameArr.length >= 2) {
			firstName = nameArr[0];
			for (int i = 1; i < nameArr.length; i++) {
				lastName += nameArr[i];
			}
		}
		int schoolId = getSchoolIdFromSchoolName(schoolName);
		try {
			preparedStatement = connect.prepareStatement("insert into  dependant values (default, ?, ?, ?, ?)");

			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);
			preparedStatement.setInt(3, customerId);
			preparedStatement.setInt(4, schoolId);
			preparedStatement.executeUpdate();
			preparedStatement = connect.prepareStatement("select dependantId from dependant where firstName ='"
					+ firstName + "' And lastName = '" + lastName + "'");
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			dependantId = resultSet.getInt("dependantId");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dependantId;

	}
}
