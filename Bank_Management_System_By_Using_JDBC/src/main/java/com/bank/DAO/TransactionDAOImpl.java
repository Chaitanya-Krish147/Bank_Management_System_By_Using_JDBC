package com.bank.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.bank.model.TransactionDetails;

public class TransactionDAOImpl implements TransactionDAO {

	private static final String insert_transaction_details = "Insert into transaction_details (Transaction_Type, Transaction_Date, Transaction_Time, Balance_Amount, Transaction_Amount, Account_Number) values(?,?,?,?,?,?)";
	private static final String select_transaction_details = "Select *from transaction_details where Account_Number = ?";
	private static final String url ="jdbc:mysql://localhost:3306/tecca_66_advance_java_project?user=root&password=6281406276";

	@Override
	public void InsertTransactionDetails(TransactionDetails transactionDetails) {
		
		try {
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement preparedStatement = connection.prepareStatement(insert_transaction_details);
			preparedStatement.setString(1, transactionDetails.getTransaction_type());
			preparedStatement.setDate(2, Date.valueOf(transactionDetails.getTransaction_date()));
			preparedStatement.setTime(3, Time.valueOf(transactionDetails.getTransaction_time()));
			preparedStatement.setDouble(4, transactionDetails.getBalance_amount());
			preparedStatement.setDouble(5, transactionDetails.getTransaction_amount());
			preparedStatement.setInt(6, transactionDetails.getAccount_number());
			int result = preparedStatement.executeUpdate();
			if(result>0) 
			{
				System.out.println(transactionDetails);
			}else 
			{
				System.out.println("Not Inserted");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<TransactionDetails> selectTransactionDetailsByAccountNo(int accountNumber) {
	    List<TransactionDetails> transactions = new ArrayList<>();

	    try (Connection connection = DriverManager.getConnection(url);
	         PreparedStatement preparedStatement = connection.prepareStatement(select_transaction_details)) {
	        
	        preparedStatement.setInt(1, accountNumber); 
	        ResultSet resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	            TransactionDetails transactionDetails = new TransactionDetails();
	            transactionDetails.setTransaction_id(resultSet.getInt("Transaction_Id"));
	            transactionDetails.setTransaction_type(resultSet.getString("Transaction_Type"));
	            transactionDetails.setTransaction_date(resultSet.getDate("Transaction_Date").toLocalDate());
	            transactionDetails.setTransaction_time(resultSet.getTime("Transaction_Time").toLocalTime());
	            transactionDetails.setBalance_amount(resultSet.getDouble("Balance_Amount"));
	            transactionDetails.setTransaction_amount(resultSet.getDouble("Transaction_Amount"));
	            transactionDetails.setAccount_number(resultSet.getInt("Account_Number"));
	            transactions.add(transactionDetails);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return transactions;
	}
}
