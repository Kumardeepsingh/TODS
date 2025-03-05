package com.tods.model;

import java.sql.SQLException;
import java.util.HashMap;


public class CustomerModel extends Model{
	
    
    public CustomerModel() {
    	super();
    }
    
    public int AddNewCustomer(HashMap<String, String> cusInfo) {
    	int customerId = 0;
    	try {
			
			preparedStatement = connect
                    .prepareStatement("insert into  customer values (default, ?, ?, ?, ? , ?, ?,?,?)");

            preparedStatement.setString(1, cusInfo.get("firstName"));
            preparedStatement.setString(2, cusInfo.get("lastName"));
            preparedStatement.setLong(3, Long.parseLong(cusInfo.get("phoneNumber")));
            preparedStatement.setString(4, cusInfo.get("email"));
            preparedStatement.setString(5, cusInfo.get("address"));
            preparedStatement.setString(6, cusInfo.get("city"));
            preparedStatement.setString(7, cusInfo.get("province"));
            preparedStatement.setString(8, cusInfo.get("postalCode"));
            preparedStatement.executeUpdate();
            preparedStatement = connect
                    .prepareStatement("select customerId from customer where firstName ='"+cusInfo.get("firstName")+"' And phoneNumber = "+Long.parseLong(cusInfo.get("phoneNumber")));
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            customerId = resultSet.getInt("customerId");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return customerId;
    	
    }
    public HashMap<String, String> searchCustomer(Long pNumber) {
        HashMap<String, String> customer = new HashMap<String, String>();

    	try {
			preparedStatement = connect
                    .prepareStatement("select * from customer where phoneNumber = "+pNumber);
			resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
            	customer.put("customerId",resultSet.getString("customerId"));
                customer.put("firstName",resultSet.getString("firstName"));
                customer.put("lastName",resultSet.getString("lastName"));
                customer.put("phoneNumber",resultSet.getString("phoneNumber"));
                customer.put("email",resultSet.getString("email"));
            }
            return customer;
            
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return customer;
		}
    	
    }
}
