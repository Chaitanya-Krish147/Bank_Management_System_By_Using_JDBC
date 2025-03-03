package com.bank.Exception;

public class BankCustomerException extends RuntimeException{
	
	private String exceptionmessage;
	
	public BankCustomerException() {}

	public BankCustomerException(String exceptionmessage) {
		super();
		this.exceptionmessage = exceptionmessage;
	}

	public String getExceptionmessage() {
		return exceptionmessage;
	}

	public void setExceptionmessage(String exceptionmessage) {
		this.exceptionmessage = exceptionmessage;
	}
}
