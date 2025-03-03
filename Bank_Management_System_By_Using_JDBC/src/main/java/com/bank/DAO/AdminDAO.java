package com.bank.DAO;

public interface AdminDAO 
{
	boolean selectAdminDetailsbyUsingEmailIDAndPassword(String emailId, String password);
}

