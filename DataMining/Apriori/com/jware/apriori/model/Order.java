package com.jware.apriori.model;

import java.util.Set;

/**
 * A model class for an Order.
 * 
 * @author jww25@njit.edu
 */
public class Order {
	private ItemSet items = new ItemSet();

	public Order() {

	}

	public Order(ItemSet items) {
		this.items.addAll(items);
	}

	public void addItem(Item item) {
		items.add(item);
	}

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(ItemSet items) {
		this.items = items;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(items.toString());

		return sb.toString();
	}
}
