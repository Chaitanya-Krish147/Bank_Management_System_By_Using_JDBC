package com.bank.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class TransactionDetails {
	
	//Transaction_Id, Transaction_Type, Transaction_Date, Transaction_Time, Balance_Amount, Transaction_Amount, Account_Number
	private int transaction_id;
	private String transaction_type;
	private LocalDate transaction_date;
	private LocalTime transaction_time;
	private double balance_amount;
	private double transaction_amount;
	private int account_number;
	
	public TransactionDetails(int transaction_id, String transaction_type, LocalDate transaction_date,
			LocalTime transaction_time, double balance_amount, double transaction_amount, int account_number) {
		this.transaction_id = transaction_id;
		this.transaction_type = transaction_type;
		this.transaction_date = transaction_date;
		this.transaction_time = transaction_time;
		this.balance_amount = balance_amount;
		this.transaction_amount = transaction_amount;
		this.account_number = account_number;
	}

	public TransactionDetails() {}

	public int getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getTransaction_type() {
		return transaction_type;
	}

	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}

	public LocalDate getTransaction_date() {
		return transaction_date;
	}

	public void setTransaction_date(LocalDate transaction_date) {
		this.transaction_date = transaction_date;
	}

	public LocalTime getTransaction_time() {
		return transaction_time;
	}

	public void setTransaction_time(LocalTime transaction_time) {
		this.transaction_time = transaction_time;
	}

	public double getBalance_amount() {
		return balance_amount;
	}

	public void setBalance_amount(double balance_amount) {
		this.balance_amount = balance_amount;
	}

	public double getTransaction_amount() {
		return transaction_amount;
	}

	public void setTransaction_amount(double transaction_amount) {
		this.transaction_amount = transaction_amount;
	}

	public int getAccount_number() {
		return account_number;
	}

	public void setAccount_number(int account_number) {
		this.account_number = account_number;
	}

	@Override
	public String toString() {
		return "TransactionDetails [transaction_id=" + transaction_id + ", transaction_type=" + transaction_type
				+ ", transaction_date=" + transaction_date + ", transaction_time=" + transaction_time
				+ ", balance_amount=" + balance_amount + ", transaction_amount=" + transaction_amount
				+ ", account_number=" + account_number + "]";
	}	

}
