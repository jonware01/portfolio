package com.jware.apriori.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import com.jware.apriori.common.Constants;
import com.jware.apriori.model.Item;

/**
 * This is the utility class for generating data randomly. The pre-requisite for
 * this to function properly is that there must be a "generator.properties" file
 * in the classpath with each database item list defined.
 * <p>
 * The output of this class is a number of properties files consisting of 10
 * random transactions, given the pre-defined list of items for each database.
 * <p>
 * If the verbose flag is set, the program will output the list of items in each
 * database along with the items for each of the 10 transactions.
 * 
 * @author jww25@njit.edu
 */
public class DataGenerator {
	// Generator properties constants
	private static final String GENERATOR_PROPS = "generator.properties";
	private static final String GENERATOR_ITEM_FORMAT = "db.%1$s.items";

	// Verbose option constants
	private static final String VERBOSE_DB_HEADER = "Database %1$s Items: %2$s";
	private static final String VERBOSE_DB_TRANSACTION = "Transactions: ";

	private static DataGenerator instance;
	private static Properties generatorProperties;

	/**
	 * Constructor.
	 * <p>
	 * Initializes the generator properties file.
	 */
	private DataGenerator() {
		try {
			generatorProperties = new Properties();
			generatorProperties.load(DataGenerator.class.getClassLoader().getResourceAsStream(GENERATOR_PROPS));
		} catch (final Exception e) {
			System.out.println("There was a problem opening the generator properties");
			e.printStackTrace(System.out);
		}
	}

	/**
	 * Singleton instance method.
	 * 
	 * @return an instance of the <code>DataGenerator</code>s
	 */
	public static synchronized DataGenerator instance() {
		if (instance == null) {
			instance = new DataGenerator();
		}

		return instance;
	}

	/**
	 * Generate 10 orders for the databases configured in the generator
	 * properties file.
	 * 
	 * @param verbose
	 *            If this flag is set, the database items and transactions will
	 *            be printed to the screen
	 * @throws FileNotFoundException
	 *             Thrown if there is a problem opening/generating files
	 * @throws IOException
	 *             Thrown if there is a problem opening/generating files
	 */
	public void generateOrders(final boolean verbose) throws FileNotFoundException, IOException {
		// If the properties file is defined, parse it and generate transactions
		if (generatorProperties != null && generatorProperties.size() > 0) {
			int databaseNum = 0;
			String itemKey = String.format(GENERATOR_ITEM_FORMAT, databaseNum);

			while (generatorProperties.containsKey(itemKey)) {
				// Create a Rapid Miner file
				try (PrintWriter rmWriter = new PrintWriter("db" + databaseNum + "_rapidminer.txt", "UTF-8")) {
					Properties dbProperties = new Properties();
					Set<Item> items = Item.toItems(generatorProperties.get(itemKey).toString().split(Constants.COMMA));
					
					// Generate header for RapidMiner
					rmWriter.println(items.toString().replace("ItemSet: [", "").replace("]", ""));
	
					if (verbose) {
						System.out.println(String.format(VERBOSE_DB_HEADER, databaseNum, items));
						System.out.println(VERBOSE_DB_TRANSACTION);
					}
	
					// Generate the transactions
					for (int transactionNum = 0; transactionNum < Constants.NUM_OF_TRANSACTIONS; transactionNum++) {
						StringBuilder rmLine = new StringBuilder();
						List<Item> itemsInOrder = new ArrayList<>();
	
						// Determine if each item will be a part of this order
						for (Item item : items) {
							if(rmLine.length() > 0) {
								rmLine.append(",");
							}
							
							if (Math.random() > .5) {
								itemsInOrder.add(item);
								rmLine.append("1");
							}
							else {
								rmLine.append("0");
							}
						}
	
						if (verbose) {
							System.out.println(itemsInOrder);
						}
						
						// Put the transaction into the rapid miner file
						rmWriter.println(rmLine.toString());
	
						// Put the transaction into the properties file
						dbProperties.put(String.format(Constants.TRANSACTION_FORMAT, transactionNum),
								itemsInOrder.toString().replace("[", "").replace("]", ""));
					}
	
					if (verbose) {
						System.out.println(System.lineSeparator());
					}
	
					// Store the file
					dbProperties.store(new FileOutputStream(new File(String.format(Constants.DB_FILE_FORMAT, databaseNum))),
							Constants.DB_FILE_COMMENTS);
	
					// Increment the key
					itemKey = String.format(GENERATOR_ITEM_FORMAT, ++databaseNum);
				}
			}
		} else {
			throw new IOException("The generator properties file is not set up correctly");
		}
	}
}
