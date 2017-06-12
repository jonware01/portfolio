package com.jware.apriori;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.jware.apriori.data.AssociationsUtil;
import com.jware.apriori.data.DataGenerator;
import com.jware.apriori.data.DataLoader;
import com.jware.apriori.model.Association;
import com.jware.apriori.model.FrequentItemSets;
import com.jware.apriori.model.Item;
import com.jware.apriori.model.Order;
import com.jware.apriori.model.OrderList;

/**
 * <b>Midterm Project</b>
 * <p>
 * This is the runner class. It contains an implementation of the Apriori
 * algorithm. To run the program, no arguments are required. The program will
 * ask for database, minimum support, and minimum confidence. Each database uses
 * a different set of 10 items to generate 20 transactions. All output will
 * display to system out.
 * 
 * @author jww25@njit.edu
 */
public class Apriori {
	private Integer database = -1; // Selected database
	private Double minSupport = -1d; // Entered minimum support
	private Double minConfidence = -1d; // Entered minimum confidence
	private boolean firstProgramLoop = true; // Is this the first program loop?

	/**
	 * Main program.
	 * 
	 * @param args
	 *            Program arguments
	 */
	public static void main(String[] args) {
		// Create an Apriori instance
		Apriori apriori = new Apriori();

		// Open a scanner for the duration of the program
		try (Scanner in = new Scanner(System.in)) {
			// Generate the data before the program loop
			apriori.generateDataLoop(in);

			// Determine if the user wants to continue (if after first loop)
			while (apriori.finishProgramLoop(in)) {
				// Set up the program
				apriori.setupProgramLoop(in);

				// Load the database
				OrderList orders = DataLoader.instance().getOrderList(apriori.database);

				// Print out the Transactions
				System.out.println("");
				System.out.println(singleLine());
				System.out.println(String.format("Transactions in Database %s", apriori.database + 1));
				System.out.println(singleLine());
				for (Order order : orders) {
					System.out.println(order);
				}

				// Print out the Apriori loop variables
				System.out.println("");
				System.out.println(String.format("Running Apriori with %.0f%% support and %.0f%% confidence",
						apriori.minSupport * 100, apriori.minConfidence * 100));

				// Get the set of items from the orders
				List<Item> possibleItems = new ArrayList<>();
				for (Order order : orders) {
					for (Item item : order.getItems()) {
						if (!possibleItems.contains(item))
							possibleItems.add(item);
					}
				}

				// Generate Frequent Item Sets
				FrequentItemSets frequentItemSets = AssociationsUtil.instance().getFrequentItemSets(possibleItems,
						orders, apriori.minSupport);

				// Print out the Frequent Item Sets
				System.out.println("");
				System.out.println(singleLine());
				System.out.println("Frequent Item Sets");
				System.out.println(String.format("(Minimum Support >= %.0f%%)", apriori.minSupport * 100));
				System.out.println(singleLine());
				System.out.println(frequentItemSets);

				// Generate the possible associations
				List<Association> associations = AssociationsUtil.instance().getAssociations(frequentItemSets, orders,
						apriori.minConfidence);

				// Print out the associations
				System.out.println("");
				System.out.println(singleLine());
				System.out.println(String.format("Matching Associations: %s", associations.size()));
				System.out.println(singleLine());
				for (Association association : associations) {
					System.out.println(association);
				}
			}
		} catch (final Exception e) {
			// If there is an exception, print it out
			e.printStackTrace();
		}
	}

	/**
	 * Perform the generate data loop.
	 * 
	 * @param in
	 *            System input stream
	 */
	private void generateDataLoop(final Scanner in) {
		// Run until return
		while (true) {
			System.out.print("Would you like to generate data (y or n)? ");
			System.out.flush();
			String line = in.nextLine();

			// If no, then return
			if (line.equals("n")) {
				return;
			}
			// If yes, then generate orders and return
			else if (line.equals("y")) {
				try {
					DataGenerator.instance().generateOrders(false);
				} catch (Exception e) {
					System.out.println("There was an issue creating data");
					System.out.println(e);
				}

				return;
			}
		}
	}

	/**
	 * Perform the setup program loop.
	 * 
	 * @param in
	 *            System input stream
	 */
	private void setupProgramLoop(final Scanner in) {
		// Get the database that the user would like to use
		while (database < 0 || database > 4) {
			try {
				System.out.print("Which database would you like to use (1-5)? ");
				System.out.flush();
				database = in.nextInt() - 1;
			} catch (InputMismatchException e) {
				// Eat the line break
				in.nextLine();
			}
		}

		// Get the minimum support
		while (minSupport < 0 || minSupport > 1) {
			try {
				System.out.print("Minimum Support (decimal format): ");
				System.out.flush();
				minSupport = in.nextDouble();
			} catch (InputMismatchException e) {
				// Eat the line break
				in.nextLine();
			}
		}

		// Get the minimum confidence
		while (minConfidence < 0 || minConfidence > 1) {
			try {
				System.out.print("Minimum Confidence (decimal format): ");
				System.out.flush();
				minConfidence = in.nextDouble();
			} catch (InputMismatchException e) {
				// Eat the line break
				in.nextLine();
			}
		}
	}

	/**
	 * Perform the finish program loop.
	 * 
	 * @param in
	 *            System input stream
	 * @return <code>true</code> if the user would like to continue. Otherwise
	 *         <code>false</code>
	 */
	private boolean finishProgramLoop(final Scanner in) {
		// If this is the first loop, just return true
		if (firstProgramLoop) {
			firstProgramLoop = false;
			return true;
		}

		// Write a separator in system out
		System.out.println("");
		System.out.println(doubleLine());
		in.nextLine();

		// Run until return
		while (true) {
			System.out.print("Would you like to continue (y or n)? ");
			System.out.flush();
			String line = in.nextLine();

			// If no, return false
			if (line.equals("n")) {
				return false;
			}
			// Otherwise, reset the program and return true
			else if (line.equals("y")) {
				// Reset variables
				database = -1;
				minSupport = -1d;
				minConfidence = -1d;

				return true;
			}
		}
	}

	/**
	 * Single Line.
	 * 
	 * @return Returns a single line for printing to system out.
	 */
	private static final String singleLine() {
		return String.format("%25s", "").replaceAll("\\s", "-");
	}

	/**
	 * Double Line.
	 * 
	 * @return Returns a double line for printing to system out.
	 */
	private static final String doubleLine() {
		return String.format("%25s", "").replaceAll("\\s", "=");
	}
}
