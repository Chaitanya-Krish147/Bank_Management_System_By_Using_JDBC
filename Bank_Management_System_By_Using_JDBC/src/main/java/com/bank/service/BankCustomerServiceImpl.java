	package com.bank.service;
		
	import java.sql.Date;
	import java.time.LocalDate;
	import java.time.LocalTime;
	import java.util.List;
	import java.util.Random;
	import java.util.Scanner;
	
	import com.bank.DAO.BankCustomerDAO;
	import com.bank.DAO.BankCustomerDAOimpl;
	import com.bank.DAO.TransactionDAO;
	import com.bank.DAO.TransactionDAOImpl;
	import com.bank.Exception.BankCustomerException;
	import com.bank.model.BankCustomerDetails;
	import com.bank.model.TransactionDetails;
		
		public class BankCustomerServiceImpl implements BankCustomerService {
			
			Scanner scanner = new Scanner(System.in);
			BankCustomerDAO bankCustomerDAO = new BankCustomerDAOimpl();
			private BankCustomerDetails bankCustomerDetails;
			
			TransactionDAO transactionDAO = new TransactionDAOImpl();
			
			@Override
		    public void bankCustomerDetails()
			{
				
				List<BankCustomerDetails> l = bankCustomerDAO.validateCustomerDetails();
				BankCustomerDetails bankCustomerDetails = new BankCustomerDetails();
				
				System.out.println("Enter Customer Name: ");
				String name = scanner.next();
				bankCustomerDetails.setName(name);
				
				boolean emailStatus = true;
				while(emailStatus)
				{
				System.out.println("Enter Customer EmailID: ");
				String emailId = scanner.next();
				
				int emailIdcount = 0;
				try {
				for(BankCustomerDetails bankCustomerDetails2 : l) 
				{
					if (bankCustomerDetails2.getEmailId().equals(emailId)) {
						emailIdcount++;
					}
				}
				if (emailIdcount > 0)
				{
					throw new BankCustomerException("Sorry, Current Customer Email Id is already Existed in our Database..."); 
				} 
				else 
				{
					bankCustomerDetails.setEmailId(emailId);
					emailStatus=false;
				}
				}
				catch(BankCustomerException bankCustomerException) 
				{
					System.out.println(bankCustomerException.getExceptionmessage());
				}
				}
				
		
				boolean mbnoStatus = true;
				while(mbnoStatus) 
				{
				System.out.println("Enter Customer Mobile Number: ");
				Long mbno = scanner.nextLong();
				int mbnoCount = 0;
				try {
				for(BankCustomerDetails bankCustomerDetails2 : l) 
				{
					if(bankCustomerDetails2.getMobilenumber()==mbno) 
					{
						mbnoCount++;
					}
				}
				if(mbnoCount>0) 
				{
					throw new BankCustomerException("Sorry, Current Mobile Number is already exist in our Database...");
				}
				else {
					bankCustomerDetails.setMobilenumber(mbno);
					mbnoStatus=false;
				}
				}
				catch(BankCustomerException bankCustomerException) 
				{
					System.out.println(bankCustomerException.getExceptionmessage());
				}
			    }
				
				boolean aadharStatus = true;
				while(aadharStatus) 
				{
					System.out.println("Enter Customer Aadhar Number: ");
					Long aadhar = scanner.nextLong();
					int aadharCount = 0;
					try 
					{
						for(BankCustomerDetails bankCustomerDetails2 : l) 
						{
							if(bankCustomerDetails2.getAadharnumber()==aadhar) 
							{
								aadharCount++;
							}
						}
						if(aadharCount > 0) 
						{
							throw new BankCustomerException("Sorry, Current Aadhar Number is already exist in our Database...");
						}
						else 
						{
							bankCustomerDetails.setAadharnumber(aadhar);
							aadharStatus=false;
						}
					}
					catch(BankCustomerException bankCustomerException)
					{
						System.out.println(bankCustomerException.getExceptionmessage());
					} 
				}
				
				
				boolean panStatus = true;
				while(panStatus) 
				{
					System.out.println("Enter Customer Pan Card (ABCDE1234X): ");
					String pan = scanner.next();
					int panCount = 0;
					try 
					{
						for(BankCustomerDetails bankCustomerDetails2 : l) 
						{
							if(bankCustomerDetails2.getPannumber().equalsIgnoreCase(pan)) 
							{
								panCount++;
							}
						}
						if(panCount > 0) 
						{
							throw new BankCustomerException("Sorry, Current Pan Number is already exist in our Database...");
						}
						else 
						{
							bankCustomerDetails.setPannumber(pan);
							panStatus=false;
						}
					}
					catch(BankCustomerException bankCustomerException)
					{
						System.out.println(bankCustomerException.getExceptionmessage());
					} 
				}
			
						
				System.out.println("Enter Customer DOB (YYYY-MM-DD): ");
				String dob = scanner.next();
				bankCustomerDetails.setDateofbirth(Date.valueOf(dob));
					
				System.out.println("Enter Customer Address: ");
				String address = scanner.next();
				bankCustomerDetails.setAddress(address);
				
				System.out.println("Enter Customer Age: ");
				int age = scanner.nextInt();
				bankCustomerDetails.setAge(age);
				
				System.out.println("Enter Customer Gender: ");
				String gender = scanner.next();
				bankCustomerDetails.setGender(gender);
				
				System.out.println("Enter Customer Amount: ");
				double amount = scanner.nextDouble();
				bankCustomerDetails.setAmount(amount);
				
				
				
				bankCustomerDAO.insertBankCustomerDetails(bankCustomerDetails);
			 }
	
			@Override
			public void customerLogin() 
			{
				System.out.println("Enter the Customer Email Id: ");
				String emailId = scanner.next();
				
				System.out.println("Enter the pin: ");
				int pin = scanner.nextInt();
				bankCustomerDetails = bankCustomerDAO.selectCustomerDetailsByUsingEmailIdAndPin(emailId, pin);
				if(bankCustomerDetails!=null) 
				{
					Boolean status = true;
					while (status) 
					{
						Random random = new Random();
						String alpha = "";
						String a[] = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z",
								      "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",
								      "1","2","3","4","5","6","7","8","9"}; 
						
						for(int i=0;i<5;i++) 
						{
							int index = random.nextInt(a.length);
							String num = a[index];
							alpha = alpha + num;
						}
						System.out.println("Your Captcha: "+ alpha);
						System.out.println("Enter the Captcha: ");
						String usercaptcha = scanner.next();
						if(alpha.equals(usercaptcha)) 
						{
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							status = false;
							if (bankCustomerDetails.getGender().equalsIgnoreCase("male")) {
								System.out.println("Hello Mr. "+bankCustomerDetails.getName());
								bankCustomerOperations();
								
							} else {
								System.out.println("Hello Mrs. "+bankCustomerDetails.getName());
								bankCustomerOperations();
							}
						}
						else 
						{
							System.err.println("Invalid Captcha");
						}
					}
				}
				else 
				{
					System.err.println("Invalid Email Id or Pin, Please try again");
				}
			}
	
			@Override
			public void bankCustomerOperations() 
			{
				boolean operation = true;
				while(operation) 
				{
					System.out.println("Enter"
							+ " \n 1.For Credit"
							+ " \n 2.For Debit "
							+ " \n 3.For Check-Balance"
							+ " \n 4.For Check-Statement"
							+ " \n 5.For Update-Password"
							+ " \n 6.For Mobile-To-Mobile Transaction"
							+ " \n 7.Logout");
					switch (scanner.nextInt()) {
					case 1:
						System.out.println("Credit");
						credit();
						break;
					case 2:
						System.out.println("Debit");
						debit();
						break;
					case 3:
						System.out.println("Check Balance");
						checkbalance();
						break;
					case 4:
						System.out.println("Check Statement");
						checkstatement();
						break;
					case 5:
						System.out.println("Update Password");
						updatepassword();
						break;
					case 6:
						System.out.println("Mobile-To-Mobile Transaction");
						mobiletransaction();
						break;
					case 7:
						System.out.println("Logging off");
						operation = false;
						break;
					default:
						System.err.println("Invalid Choice!, please try again..");
						break;
					}
				}
			}
	
			@Override
			public void debit() {
				System.out.println("Enter Amount :");
				double useramount=scanner.nextDouble();
				if(useramount>=0) {
					double databaseamount=bankCustomerDetails.getAmount();
					int accountnumber=bankCustomerDetails.getAccountnumber();
					
					if(useramount<=databaseamount) 
					{
						double balanceamount=databaseamount-useramount;
						System.out.println(balanceamount);
				    	boolean res = bankCustomerDAO.updateBalanceAmountByusingAmountNumber(balanceamount, accountnumber);
						if(res) 
						{
							TransactionDetails transactionDetails = new TransactionDetails();
							transactionDetails.setTransaction_type("Debit");
							transactionDetails.setTransaction_date(LocalDate.now());
							transactionDetails.setTransaction_time(LocalTime.now());
							transactionDetails.setAccount_number(accountnumber);
							transactionDetails.setBalance_amount(balanceamount);
							transactionDetails.setTransaction_amount(useramount);
							System.out.println(transactionDetails);
							
							transactionDAO.InsertTransactionDetails(transactionDetails);
							
							System.out.println("Amount Debited Successfully");
						}
						else {
							System.err.println("Amount not Debited");
						}
					}
					else {
						System.err.println("Insufficient Balance"); 
					}
				}
				else {
					System.err.println("Invalid Amount");
				}	
			}
		@Override
		public void credit() 
		{
			System.out.println("Enter the Amount :");
			double useramount=scanner.nextDouble();
			if(useramount>=0) {
				double databaseamount=bankCustomerDetails.getAmount();
				int accountnumber=bankCustomerDetails.getAccountnumber();
				
				if(useramount<=databaseamount) 
				{
					double balanceamount=databaseamount+useramount;
					System.out.println(balanceamount);
			    	boolean res = bankCustomerDAO.updateCreditAmountByUsingAccountNumber(balanceamount, accountnumber);
					if(res) 
					{
						TransactionDetails transactionDetails = new TransactionDetails();
						transactionDetails.setTransaction_type("Credit");
						transactionDetails.setTransaction_date(LocalDate.now());
						transactionDetails.setTransaction_time(LocalTime.now());
						transactionDetails.setAccount_number(accountnumber);
						transactionDetails.setBalance_amount(balanceamount);
						transactionDetails.setTransaction_amount(useramount);
						System.out.println(transactionDetails);
						
						transactionDAO.InsertTransactionDetails(transactionDetails);
						
						System.out.println("Amount Credited Successfully");
					}
					else {
						System.err.println("Amount not Credited");
					}
				}
				else {
					System.err.println("Insufficient Balance"); 
				}
			}
			else {
				System.err.println("Invalid Amount");
			}	
		}
	
		@Override
		public void checkstatement() {
			
			System.out.println("Enter the Account Number: ");
			int accountnumber = scanner.nextInt();
		    List<TransactionDetails> transaction = transactionDAO.selectTransactionDetailsByAccountNo(accountnumber);
		    
		    if(transaction.isEmpty()) 
		    {
		    	System.err.println("No Statement found for this Ac/No");
		    }
		    else 
		    {
		    	System.out.println("Here' s the Transaction Statement: "+ accountnumber);
		    	transaction.forEach((transactionDetails)->{
		    		System.out.println("---------------------------------------------------");
					System.out.println("Transaction Type: "+ transactionDetails.getTransaction_type());
					System.out.println("Transaction Date: "+ transactionDetails.getTransaction_date());
					System.out.println("Transaction Time: "+ transactionDetails.getTransaction_time());
					System.out.println("Balance Amount: "+ transactionDetails.getBalance_amount());
					System.out.println("Transaction Amount: "+ transactionDetails.getTransaction_amount());
					System.out.println("Account Number: "+ transactionDetails.getAccount_number());
		    	});
	    		System.out.println("---------------------------------------------------");
		    }	  
		}

		@Override
		public void checkbalance() 
		{
			System.out.println("Enter the Account Number: ");
			int accountnumber = scanner.nextInt();
			
			if(bankCustomerDetails.getAccountnumber()==accountnumber) 
			{
				double balanceamount = bankCustomerDetails.getAmount();
				System.out.println("Current Balance: "+ balanceamount);
			}
			else 
			{
				System.err.println("Invalid account number");
			}
		}

		@Override
		public void updatepassword() {
			System.out.println("Enter the Email Id: ");
			String emailId = scanner.next();
			
			System.out.println("Enter your Current Pin: ");
			int currentpin = scanner.nextInt();
			
			BankCustomerDetails customerDetails = bankCustomerDAO.selectCustomerDetailsByUsingEmailIdAndPin(emailId, currentpin);
			
			if(customerDetails!=null) 
			{
				System.out.println("Enter your new pin: ");
				int newpin = scanner.nextInt();
				
				System.out.println("Confirm your pin: ");
				int confirmpin = scanner.nextInt();
				
				if(newpin == confirmpin) 
				{
					boolean isUpdated = bankCustomerDAO.updatingPassword(emailId, newpin);
					if(isUpdated) 
					{
						System.out.println("Pin updated Successfully");
					}
					else 
					{
						System.err.println("Pin not updated, please check again");
					}
				}
				else 
				{
					System.err.println("Password does not matched");
				}
			}
			else 
			{
				System.err.println("invalid emailId or pin");
			}
			
		}

		@Override
		public void mobiletransaction() 
		{
			System.out.println("Enter your Mobile Number: ");
			long sendermobile = scanner.nextLong();
			
			System.out.println("Enter sender Mobile Number: ");
			long receivermobile = scanner.nextLong();
			
			BankCustomerDetails sender = bankCustomerDAO.selectCustomerDetailsByMobileNumberBankCustomerDetails(sendermobile);
			BankCustomerDetails receiver = bankCustomerDAO.selectCustomerDetailsByMobileNumberBankCustomerDetails(receivermobile);
			
			if(sender!= null && receiver!=null) 
			{
				System.out.println("Enter Amount to transfer: ");
				double transfer = scanner.nextDouble();
				
				if(transfer > 0 && sender.getAmount() >= transfer) 
				{
					double SenderNew = sender.getAmount() - transfer;
					double ReceiverNew = receiver.getAmount() - transfer;
					
					boolean UpdateSender = bankCustomerDAO.updateBalanceAmountByusingAmountNumber(SenderNew, sender.getAccountnumber());
					
					boolean UpdateReceiver = bankCustomerDAO.updateCreditAmountByUsingAccountNumber(ReceiverNew, receiver.getAccountnumber());
					
					if(UpdateSender && UpdateReceiver) 
					{
						TransactionDetails senderTransaction = new TransactionDetails();
						senderTransaction.setTransaction_type("Debit");
		                senderTransaction.setTransaction_date(LocalDate.now());
		                senderTransaction.setTransaction_time(LocalTime.now());
		                senderTransaction.setAccount_number(sender.getAccountnumber());
		                senderTransaction.setBalance_amount(SenderNew);
		                senderTransaction.setTransaction_amount(transfer);
		                transactionDAO.InsertTransactionDetails(senderTransaction);
		                
		                TransactionDetails receiverTransaction = new TransactionDetails();
		                receiverTransaction.setTransaction_type("Credit");
		                receiverTransaction.setTransaction_date(LocalDate.now());
		                receiverTransaction.setTransaction_time(LocalTime.now());
		                receiverTransaction.setAccount_number(receiver.getAccountnumber());
		                receiverTransaction.setBalance_amount(ReceiverNew);
		                receiverTransaction.setTransaction_amount(transfer);
		                transactionDAO.InsertTransactionDetails(receiverTransaction);
		                
						System.out.println("Transferred of "+ "₹"+transfer +" Successfully ");

					}
					else {
						System.err.println("Transaction Failed! Please try again");
					}
					
				}
				else {
					System.err.println("Insufficient Balance or Invalid Amount");
				}
				
			}else 
			{
				System.err.println("Invalid Mobile Number, Please check it again");
			}
		}
	}
