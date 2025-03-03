package com.bank.DAO;

import java.util.List;

import com.bank.model.TransactionDetails;

public interface TransactionDAO {
	
	void InsertTransactionDetails(TransactionDetails transactionDetails);

	List<TransactionDetails> selectTransactionDetailsByAccountNo(int accountNumber);
}
