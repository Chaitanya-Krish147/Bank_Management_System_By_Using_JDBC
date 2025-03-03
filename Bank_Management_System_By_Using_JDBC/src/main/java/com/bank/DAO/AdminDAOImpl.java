package com.bank.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAOImpl implements AdminDAO {

    private static final String select_admin_details = "Select * from admin_details where Email_Id=? and Password = ?";
	private static final String url ="jdbc:mysql://Localhost:3306/tecca_66_advance_java_project?user=root&password=6281406276";

	@Override
	public boolean selectAdminDetailsbyUsingEmailIDAndPassword(String emailId, String password) {
		Connection connection;
		try {
			connection = DriverManager.getConnection(url);
			PreparedStatement preparedStatement = connection.prepareStatement(select_admin_details);
			preparedStatement.setString(1, emailId);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) 
			{
				
				//System.out.println("Login Successfully...");
				return true;
			}
			else 
			{
				//System.out.println("Invalid Login Email Id or password");
				return false;
			}
			
		} catch (SQLException e) 
		{
			e.printStackTrace();
			return false;
		}
	}
}
