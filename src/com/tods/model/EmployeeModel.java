package com.tods.model;

public class EmployeeModel extends Model {

	public EmployeeModel() {
		super();
	}

	public boolean login(String email, String password) {
		String sql = "SELECT * FROM Employee WHERE username = ? and password = ?";
		boolean login = false;
		try {
			preparedStatement = connect.prepareStatement(sql);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();
			
			if (!resultSet.next()) {
			} else {
				login = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return login;
	}
}
