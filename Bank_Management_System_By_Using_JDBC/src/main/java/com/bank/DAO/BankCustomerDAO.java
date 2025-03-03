package com.bank.DAO;

import java.util.List;

import com.bank.model.BankCustomerDetails;

public interface BankCustomerDAO {
	
	void insertBankCustomerDetails(BankCustomerDetails bankCustomerDetails);

	List<BankCustomerDetails> validateCustomerDetails();

	void updateAccountNumberPinByUsingId(BankCustomerDetails bankCustomerDetails);
	
	void deleteBankCustomerDetails(BankCustomerDetails bankCustomerDetails);
	
	BankCustomerDetails selectCustomerDetailsByUsingEmailIdAndPin(String emailId, int pin);
		
	boolean updateBalanceAmountByusingAmountNumber(double Amount, int Account_Number);
	
	boolean updateCreditAmountByUsingAccountNumber(double amount, int accountNumber);
	
	boolean updatingPassword(String emailId, int newPassword);
	
	BankCustomerDetails selectCustomerDetailsByMobileNumberBankCustomerDetails(long mobilenumber);
}
