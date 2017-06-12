package com.jware.apriori.common;

/**
 * Contains all of the program constants.
 * 
 * @author jww25@njit.edu
 */
public interface Constants {
	// Database file constants
	public static final String DB_FILE_COMMENTS = "CS634" + System.lineSeparator() + "Fall 2016"
			+ System.lineSeparator() + "Jonathan Ware";
	public static final String DB_FILE_FORMAT = "db.%1$s.Transaction.txt";
	public static final String TRANSACTION_FORMAT = "transaction.%1$s.items";

	// Other constants
	public static final Integer NUM_OF_TRANSACTIONS = 20;
	public static final String COMMA = ",";
}
