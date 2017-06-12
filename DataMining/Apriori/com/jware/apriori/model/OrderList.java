package com.jware.apriori.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A model class for an Order List.s
 * 
 * @author jww25@njit.edu
 */
public class OrderList extends ArrayList<Order> {
	private static final long serialVersionUID = 1L;

	public OrderList() {

	}

	public OrderList(List<Order> orders) {
		this.addAll(orders);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (Order order : this) {
			sb.append(order.toString());
			sb.append(System.lineSeparator());
			sb.append(System.lineSeparator());
		}

		return sb.toString();
	}
}
