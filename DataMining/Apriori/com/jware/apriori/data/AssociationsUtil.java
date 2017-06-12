package com.jware.apriori.data;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.jware.apriori.model.Association;
import com.jware.apriori.model.FrequentItemSets;
import com.jware.apriori.model.Item;
import com.jware.apriori.model.ItemSet;
import com.jware.apriori.model.Order;

/**
 * This utility class contains methods for retrieving the frequent items and
 * associations for a given set of orders.
 * 
 * @author jww25@njit.edu
 */
public class AssociationsUtil {
	private static AssociationsUtil instance;

	/**
	 * Private constructor.
	 */
	private AssociationsUtil() {
		// Do nothing
	}

	/**
	 * Singleton instance method.
	 * 
	 * @return <code>AssociationsUtil</code>
	 */
	public static synchronized AssociationsUtil instance() {
		if (instance == null) {
			instance = new AssociationsUtil();
		}

		return instance;
	}

	/**
	 * Get the frequent item sets that meet the minimum support given a list of
	 * items and list of orders.
	 * 
	 * @param items
	 *            List of items to use
	 * @param orders
	 *            List of orders to use
	 * @param minSupport
	 *            Minimum support to allow
	 * @return <code>FrequentItemSets</code> corresponding to a set of frequent
	 *         items.
	 */
	public FrequentItemSets getFrequentItemSets(final List<Item> items, final List<Order> orders,
			final double minSupport) {
		FrequentItemSets frequentItemSet = new FrequentItemSets();

		// For the base items, determine if they should be a part of the
		// frequent item set.
		for (Item item : items) {
			// Make an item set out of the single item
			ItemSet combination = new ItemSet(item);

			// Get the support for the "combination"
			combination.setSupport(calculateSupport(orders, combination));

			// If the combination meets the minimum support, add it to the
			// frequent item set
			if (combination.getSupport() >= minSupport) {
				frequentItemSet.add(combination);
			}
		}

		// Determine all other frequent item sets
		for (int associationSize = 2; associationSize <= items.size(); associationSize++) {
			// Generate the n-combinations from the given
			List<ItemSet> combinations = generateCombinations(items, associationSize);

			// Determine if each combination is a frequent item set
			outer: for (ItemSet combination : combinations) {

				// For each combination, generate sub-combinations to determine
				// if the
				// combination meets the subset rule.
				for (int j = associationSize - 1; j > 0; j--) {
					List<ItemSet> subCombinations = generateCombinations(new ArrayList<Item>(combination), j);

					for (ItemSet subCombination : subCombinations) {
						if (!frequentItemSet.contains(subCombination))
							continue outer;
					}
				}

				// If the subset rule passed, calculate the support
				combination.setSupport(calculateSupport(orders, combination));

				// If the combination meets the minimum support, add it to the
				// frequent item set
				if (combination.getSupport() >= minSupport) {
					frequentItemSet.add(combination);
				}
			}
		}

		return frequentItemSet;
	}

	/**
	 * Get the associations that meet the minimum confidence, given the frequent
	 * item sets and a set of orders.
	 * 
	 * @param frequentItemSets
	 *            Frequent item sets to use
	 * @param orders
	 *            Orders to use
	 * @param minConfidence
	 *            Minimum confidence to use
	 * @return <code>List&lt;Association&gt;</code> - list of associations
	 */
	public List<Association> getAssociations(final FrequentItemSets frequentItemSets, final List<Order> orders,
			final double minConfidence) {
		Set<Association> associations = new LinkedHashSet<>();
		int associationsProcessed = 0;

		// For each frequent item set...
		for (ItemSet frequentItemSet : frequentItemSets) {
			// If there is only one item in the set, continue
			if (frequentItemSet.size() <= 1)
				continue;

			// Generate the permutations of the items in the frequent item set
			List<List<Item>> permutations = generatePermutations(new ItemSet(frequentItemSet));
			// For each permutation...
			for (List<Item> permutation : permutations) {
				List<Item> right = new ArrayList<Item>(permutation);
				List<Item> left = new ArrayList<Item>();

				// Build an association with n-items on the left
				for (int sizeOfLeft = 1; sizeOfLeft < permutation.size(); sizeOfLeft++) {
					// Build the left
					while (left.size() < sizeOfLeft) {
						left.add(right.remove(0));
					}

					// Create the association
					Association association = new Association(new ItemSet(left), new ItemSet(right));
					association.setSupport(frequentItemSet.getSupport());
					association.setConfidence(calculateConfidence(orders, association));

					// If the association is not already in the matching
					// associations list...
					if (!associations.contains(association)) {
						associationsProcessed++;

						// and the association meets the confidence, add it to
						// the matching associations
						if (association.getConfidence() >= minConfidence)
							associations.add(association);
					}
				}
			}
		}

		// Print out the number of associations processed
		System.out.println("Associations processed: " + associationsProcessed);

		// Return a List of Associations
		return new ArrayList<Association>(associations);
	}

	/**
	 * Generates the n-combinations of the items given.
	 * 
	 * @param items
	 *            Items to use
	 * @param size
	 *            Size of resultant combination
	 * @return List of item sets for each combination or <code>null</code> if
	 *         the size is greater than the item set.
	 */
	private List<ItemSet> generateCombinations(List<Item> items, Integer size) {
		int[] indexes = new int[size];
		List<ItemSet> combinations = new ArrayList<>();

		// If the current size is not greater than the item size, process the
		// list
		if (size <= items.size()) {
			// Initialize the indexes
			for (int i = 0; i < size; i++) {
				indexes[i] = i;
			}

			// Generate first combination
			combinations.add(generateItemSet(items, indexes));

			// Process until return
			while (true) {
				int toIncrement = size - 1;
				// Find position of item that can be incremented
				while (toIncrement >= 0 && indexes[toIncrement] == items.size() - size + toIncrement) {
					toIncrement--;
				}

				// If no indexes can be incremented, return the combinations
				if (toIncrement < 0) {
					return combinations;
				}
				// Otherwise...
				else {
					// Increment the index
					indexes[toIncrement]++;

					// Increment subsequent indexes
					for (++toIncrement; toIncrement < size; toIncrement++) {
						indexes[toIncrement] = indexes[toIncrement - 1] + 1;
					}

					// Generate the combination
					combinations.add(generateItemSet(items, indexes));
				}
			}
		}

		// size > item set
		return null;
	}

	/**
	 * Generate the item set from the given indexes
	 * 
	 * @param items
	 *            Items to use
	 * @param indexes
	 *            Indexes to use
	 * @return An <code>ItemSet</code> with the items at the given indexes
	 */
	private ItemSet generateItemSet(List<Item> items, int[] indexes) {
		ItemSet result = new ItemSet();

		for (int i = 0; i < indexes.length; i++) {
			result.add(items.get(indexes[i]));
		}

		return result;
	}

	/**
	 * Generate the permutations of the given item set.
	 * 
	 * @param itemSet
	 *            Item set to use
	 * @return List of permutations
	 */
	private List<List<Item>> generatePermutations(final ItemSet itemSet) {
		List<List<Item>> result = new ArrayList<List<Item>>();

		// If the item set is zero, return an empty list
		if (itemSet.size() == 0) {
			result.add(new ArrayList<Item>());
			return result;
		}

		// Get and Remove the first item from the set
		Item firstElement = itemSet.first();
		itemSet.remove(firstElement);

		// Generate the permutations of the smaller item set
		List<List<Item>> permutations = generatePermutations(itemSet);

		// For each permutation...
		for (List<Item> permutation : permutations) {
			// Add the first element to the i-th position
			for (int index = 0; index <= permutation.size(); index++) {
				List<Item> temp = new ArrayList<Item>(permutation);
				temp.add(index, firstElement);
				result.add(new ArrayList<Item>(temp));
			}
		}

		return result;
	}

	/**
	 * Calculate the support for the item set given the orders.
	 * 
	 * @param orders
	 *            Orders to use
	 * @param itemSet
	 *            Item set to use
	 * @return the support of the item set
	 */
	private Double calculateSupport(final List<Order> orders, final ItemSet itemSet) {
		double support = 0;

		for (Order order : orders) {
			if (order.getItems().containsAll(itemSet)) {
				support++;
			}
		}

		return support / orders.size();
	}

	/**
	 * Calculate the confidence of the association given the orders.
	 * 
	 * @param orders
	 *            Orders to use
	 * @param association
	 *            Association to use
	 * @return the confidence of the association
	 */
	private Double calculateConfidence(final List<Order> orders, final Association association) {
		double leftSupport = 0;

		for (Order order : orders) {
			if (order.getItems().containsAll(association.getLeft())) {
				leftSupport++;
			}
		}

		if (leftSupport > 0) {
			return association.getSupport() / (leftSupport / orders.size());
		}

		return 0d;
	}
}