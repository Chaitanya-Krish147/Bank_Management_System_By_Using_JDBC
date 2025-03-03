package com.bank.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.bank.DAO.AdminDAO;
import com.bank.DAO.AdminDAOImpl;
import com.bank.DAO.BankCustomerDAO;
import com.bank.DAO.BankCustomerDAOimpl;
import com.bank.model.BankCustomerDetails;

public class AdminServiceImpl implements AdminService 
{

	Scanner scanner = new Scanner(System.in);
	BankCustomerDAO bankCustomerDAO = new BankCustomerDAOimpl();
	AdminDAO adminDAO = new AdminDAOImpl();
	private List<BankCustomerDetails> deletedAccounts = new ArrayList<>();
	
	@Override
	public void adminLogin() {
	
		System.out.println("Enter Admin Email Id: ");
		String emailId = scanner.next();
		
		System.out.println("Enter Admin Password: ");
		String password = scanner.next();
	
	    if(adminDAO.selectAdminDetailsbyUsingEmailIDAndPassword(emailId, password)) 
	    {
	    	while(true) 
	    	{
	    		System.out.println("Enter \n 1. To get All Accounts request Details \n 2. To get all User Details \n 3. To get all Account closing Request Details \n 4. Logout");
		    	
		    	switch (scanner.nextInt()) 
		    	{
				case 1:
					System.out.println("All Account Request Details");
					allPendingDetails();
//					viewDetailsBySNo();
					break;

				case 2:
					System.out.println("All User Details");
					allUserDetails();
					break;
					
				case 3:
					System.out.println("All Account Closing Request Details");
					accountclosingDetails();
					break;
					
				case 4:
					System.out.println("Logout");
					return;
					
				default:
					System.err.println("Invalid Choice, Please try once again...");
					break;
				}
	    	}
	    }
	    else 
	    {
	    	System.err.println("Invalid Admin Credentials...");
	    }
	}
	
	@Override
	public void allUserDetails() 
	{
		List<BankCustomerDetails> allCustomerDetails = bankCustomerDAO.validateCustomerDetails();
		int SerialNumber = 1;
		for(BankCustomerDetails customerDetails : allCustomerDetails) 
		{
			System.out.println("S.No: "+ SerialNumber++);
			System.out.println("Customer Name :"+ customerDetails.getName());
			System.out.println("Customer Email Id "+ customerDetails.getEmailId());
			System.out.println("Customer Mobile Number "+ mobileDetails(String.valueOf(customerDetails.getMobilenumber())));
			System.out.println("Customer Aadhar Number "+ aadharDetails(String.valueOf(customerDetails.getAadharnumber())));
			System.out.println("Customer Status "+ customerDetails.getStatus());
			System.out.println("------------------------------------------------");

		}
		
    }
	
	private String mobileDetails(String mobilenumber) 
	{
		if(mobilenumber.length()==10) 
		{
			return mobilenumber.substring(0,3)+ "****"+ mobilenumber.substring(7);
		}
		return mobilenumber;
	}
	
	private String aadharDetails(String aadharnumber) 
	{
		if(aadharnumber.length()==12) 
		{
			return aadharnumber.substring(0,4)+ "****"+ aadharnumber.substring(8);
		}
		return aadharnumber;
	}
	

    @Override
    public void allPendingDetails() {
        List<BankCustomerDetails> allBankCustomerDetails = bankCustomerDAO.validateCustomerDetails();
        List<BankCustomerDetails> allpendingDetailslist = new ArrayList<>();
        
        int serialNumber = 1;
        for (BankCustomerDetails bankcustomerDetails : allBankCustomerDetails) {
        	if("Pending".equalsIgnoreCase(bankcustomerDetails.getStatus())) 
        	{
        		allpendingDetailslist.add(bankcustomerDetails);
        		System.out.println("S.No: " + serialNumber++);
                System.out.println("Customer Name: " + bankcustomerDetails.getName());
                System.out.println("Customer Email Id: " + bankcustomerDetails.getEmailId());
                System.out.println("Customer Mobile Number: " + bankcustomerDetails.getMobilenumber());
                System.out.println("Customer Aadhar Number: " + bankcustomerDetails.getAadharnumber());
                System.out.println("Customer Status: " + bankcustomerDetails.getStatus());
                System.out.println("---------------------------------------------------");
        	}
        }
        System.out.println("Enter S.No to Select the Customer Details: ");
		BankCustomerDetails adminselectObject = allpendingDetailslist.get(scanner.nextInt()-1);
		System.out.println(adminselectObject);
		System.out.println("Enter \n 1. Accept \n 2. Delete");
		switch (scanner.nextInt()) {
		case 1:
			acceptPendingDetails(adminselectObject);
			break;
		case 2:
			deletePendingDetails(adminselectObject);
			break;

		default:
			break;
		}
    }

//	@Override
//	public void viewDetailsBySNo() 
//	{
//		List<BankCustomerDetails> allBankCustomerDetails = bankCustomerDAO.validateCustomerDetails();
//		
//		System.out.println("Enter S.No to Select the Customer Details: ");
//		
//		int SerialNumber = scanner.nextInt();
//		if(SerialNumber > 0 && SerialNumber <= allBankCustomerDetails.size())
//		{
//			BankCustomerDetails selectedCustomer = allBankCustomerDetails.get(SerialNumber-1);
//			System.out.println(selectedCustomer.toString());
//			acceptPendingDetails(selectedCustomer);
//			System.out.println(selectedCustomer.getAccountnumber());
//			System.out.println(selectedCustomer.getPin());
//
//	        System.out.println("Customer account has been approved and updated successfully!");
//		}
//		else 
//		{
//			System.err.println("Invalid Serial Number! Please try again");
//		}
//	}

	@Override
	public void acceptPendingDetails(BankCustomerDetails bankCustomerDetails) 
	{
		Random random = new Random();
		int accountNumber = random.nextInt(10000000);
		if(accountNumber<1000000) 
		{
			accountNumber+=1000000;
		}
		int pinNumber = random.nextInt(10000);
		if (pinNumber<1000) 
		{
			pinNumber+=1000;
		}
		        
		bankCustomerDetails.setAccountnumber(accountNumber);
		bankCustomerDetails.setPin(pinNumber);
		bankCustomerDetails.setStatus("Accepted");

		bankCustomerDAO.updateAccountNumberPinByUsingId(bankCustomerDetails);
		
		 System.out.println("Account Number: "+accountNumber);
		 System.out.println("Pin: "+pinNumber);
		 System.out.println("Customer Approved Successfully");

	}

	@Override
	public void deletePendingDetails(BankCustomerDetails bankCustomerDetails) {
				
		deletedAccounts.add(bankCustomerDetails);
		
		bankCustomerDAO.deleteBankCustomerDetails(bankCustomerDetails);
		
		System.out.println("Selected bank Customer Details are Deleted successfully");
		
		System.out.println("Do you want to Continue further (YES/NO)");
		String choice = scanner.next();
		if(choice.equalsIgnoreCase("Yes")) 
		{	
			return;
		}else 
		{
			System.out.println("Logging out...");
			System.exit(0);
		}
	}

	@Override
	public void accountclosingDetails() 
	{
		
		if(deletedAccounts.isEmpty()) 
		{
			System.out.println("No deleted account details are available");
			return;
		}
		else 
		{
			System.out.println("All Deleted Account details: ");
			for (BankCustomerDetails CustomerDetails : deletedAccounts) {
				System.out.println("Customer Name: "+ CustomerDetails.getName());
				System.out.println("Customer EmailId: "+CustomerDetails.getEmailId());
				System.out.println("Customer Mobile Number: "+CustomerDetails.getMobilenumber());
				System.out.println("Customer Aadhar Number: "+CustomerDetails.getAadharnumber());
				System.out.println("----------------------------------------");
			}
		}
	}
}
