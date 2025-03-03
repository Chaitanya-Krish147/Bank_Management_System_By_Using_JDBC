package com.bank.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bank.model.BankCustomerDetails;

public class BankCustomerDAOimpl  implements BankCustomerDAO {

	private static final String insert_customer_details = "insert into bank_customer_details( Name, Emailid, Mobile_Number, Aadhar_Number, Pan_Number, Date_Of_Birth, Address, Amount, Age, Gender, Status) values (?,?,?,?,?,?,?,?,?,?,?)";
    private static final String validate_customer_details = "Select * from bank_customer_details";
    private static final String update_accountNumber_Pin = "Update bank_customer_details Set Account_Number = ?, Pin = ?, Status = ? Where Id= ?";
    private static final String delete_customer_details = "Delete from bank_customer_details where Id = ?";
    private static final String customer_login = "Select *from bank_customer_details where Emailid=? And Pin=?";
    private static final String update_debit_amount = "Update bank_customer_details Set Amount = ? WHERE Account_Number = ?";
    private static final String update_credit_amount = "Update bank_customer_details Set Amount = Amount + ? WHERE Account_Number = ?";
    private static final String update_new_pin = "Update bank_customer_details Set Pin = ? Where Emailid = ?";
    private static final String select_details = "Select *from bank_customer_details Where Mobile_Number = ?";
    
	private static final String url ="jdbc:mysql://localhost:3306/tecca_66_advance_java_project?user=root&password=6281406276";
	@Override
	public void insertBankCustomerDetails(BankCustomerDetails bankCustomerDetails) 
	{
		try {
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement preparedStatement = connection.prepareStatement(insert_customer_details);
			preparedStatement.setString(1, bankCustomerDetails.getName());
			preparedStatement.setString(2, bankCustomerDetails.getEmailId());
			preparedStatement.setLong(3, bankCustomerDetails.getMobilenumber());
			preparedStatement.setLong(4, bankCustomerDetails.getAadharnumber());
			preparedStatement.setString(5, bankCustomerDetails.getPannumber());
			preparedStatement.setDate(6, bankCustomerDetails.getDateofbirth());
			preparedStatement.setString(7, bankCustomerDetails.getAddress());
			preparedStatement.setDouble(8, bankCustomerDetails.getAmount());
			preparedStatement.setInt(9, bankCustomerDetails.getAge());
			preparedStatement.setString(10, bankCustomerDetails.getGender());
			preparedStatement.setString(11, "Pending");
			
			int result = preparedStatement.executeUpdate();
			if(result>0) 
			{
				System.out.println("Customer Registration is done Successfully...");
			}else 
			{
				System.err.println("Invalid Data");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public List<BankCustomerDetails> validateCustomerDetails() 
	{
		try {
			Connection  connection = DriverManager.getConnection(url);
			PreparedStatement preparedStatement = connection.prepareStatement(validate_customer_details);
						
			ResultSet resultSet = preparedStatement.executeQuery();
			List<BankCustomerDetails> l = new ArrayList<BankCustomerDetails>();
			
			if(resultSet.isBeforeFirst()) 
			{
				while(resultSet.next()) 
				{
					BankCustomerDetails bankCustomerDetails = new BankCustomerDetails();
					bankCustomerDetails.setId(resultSet.getInt("Id"));
					bankCustomerDetails.setName(resultSet.getString("Name"));
					bankCustomerDetails.setEmailId(resultSet.getString("Emailid"));
					bankCustomerDetails.setAadharnumber(resultSet.getLong("Aadhar_Number"));
					bankCustomerDetails.setMobilenumber(resultSet.getLong("Mobile_Number"));
					bankCustomerDetails.setPannumber(resultSet.getString("Pan_Number"));
					bankCustomerDetails.setStatus(resultSet.getString("Status"));
					l.add(bankCustomerDetails);
				}
				return l;
			}
			else 
			{
				return new ArrayList<>();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public void updateAccountNumberPinByUsingId(BankCustomerDetails bankCustomerDetails) 
	{
		try {
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement preparedStatement = connection.prepareStatement(update_accountNumber_Pin);
			preparedStatement.setInt(1, bankCustomerDetails.getAccountnumber());
			preparedStatement.setInt(2, bankCustomerDetails.getPin());
			preparedStatement.setString(3, "Accepted");
			preparedStatement.setInt(4, bankCustomerDetails.getId());

			int result = preparedStatement.executeUpdate();
			if (result>0) {
				System.out.println("Updated A/c & Pin Number");
			}
			else 
			{
				System.out.println("Not Updated...");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteBankCustomerDetails(BankCustomerDetails bankCustomerDetails) 
	{
		try {
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement preparedStatement = connection.prepareStatement(delete_customer_details);
			preparedStatement.setInt(1, bankCustomerDetails.getId());
			int result = preparedStatement.executeUpdate();
			if (result>0) {
				System.out.println("Deleted");
			}
			else 
			{
				System.out.println("Not Deleted...");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public BankCustomerDetails selectCustomerDetailsByUsingEmailIdAndPin(String emailId, int pin) {
		try {
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement preparedStatement = connection.prepareStatement(customer_login);
			preparedStatement.setString(1, emailId);
			preparedStatement.setInt(2, pin);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) 
			{
				 BankCustomerDetails bankCustomerDetails = new BankCustomerDetails();
				 bankCustomerDetails.setName(resultSet.getString("Name"));
				 bankCustomerDetails.setGender(resultSet.getString("Gender"));
				 bankCustomerDetails.setAmount(resultSet.getDouble("Amount"));
				 bankCustomerDetails.setAccountnumber(resultSet.getInt("Account_Number"));
		         return bankCustomerDetails;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
		
	}

	@Override
	public boolean updateBalanceAmountByusingAmountNumber(double Amount, int Account_Number) {
		
		try {
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement preparedStatement = connection.prepareStatement(update_debit_amount);
			preparedStatement.setDouble(1, Amount);
			preparedStatement.setInt(2, Account_Number);
			int result = preparedStatement.executeUpdate();
			if(result>0) 
			{
				return true;
			}else 
			{
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
	
	 @Override
	 public boolean updateCreditAmountByUsingAccountNumber(double amount, int accountNumber) {
		 try {
	            Connection connection = DriverManager.getConnection(url);
	            PreparedStatement preparedStatement = connection.prepareStatement(update_credit_amount);
	            preparedStatement.setDouble(1, amount);
	            preparedStatement.setInt(2, accountNumber);
	            int result = preparedStatement.executeUpdate();
	            return result > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	@Override
	public boolean updatingPassword(String emailId, int newPassword) {
		try {
			Connection connection = DriverManager.getConnection(url);
            PreparedStatement preparedStatement = connection.prepareStatement(update_new_pin);
            preparedStatement.setInt(1, newPassword);
            preparedStatement.setString(2, emailId);
            int result = preparedStatement.executeUpdate();
            return result > 0;
            } 
		catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
	}

	@Override
	public BankCustomerDetails selectCustomerDetailsByMobileNumberBankCustomerDetails(long mobilenumber) {
		
		try {
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement preparedStatement = connection.prepareStatement(select_details);
			preparedStatement.setLong(1, mobilenumber);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) 
			{
				 BankCustomerDetails bankCustomerDetails = new BankCustomerDetails();
				 bankCustomerDetails.setAccountnumber(resultSet.getInt("Account_Number"));
				 bankCustomerDetails.setName(resultSet.getString("Name"));
				 bankCustomerDetails.setMobilenumber(resultSet.getLong("Mobile_Number"));
				 bankCustomerDetails.setAmount(resultSet.getDouble("Amount"));
		         return bankCustomerDetails;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}
}



