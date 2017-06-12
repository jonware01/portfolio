package com.jware.apriori.model;

import java.util.Collection;
import java.util.TreeSet;

/**
 * Model class for an Item Set.
 * 
 * @author jww25@njit.edu
 */
public class ItemSet extends TreeSet<Item> {
	private static final long serialVersionUID = 1L;
	private Double support;

	public ItemSet() {
		super();
	}

	public ItemSet(Item item) {
		super();
		this.add(item);
	}

	public ItemSet(Collection<? extends Item> collection) {
		super(collection);
	}

	public Double getSupport() {
		return support;
	}

	public void setSupport(Double support) {
		this.support = support;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("ItemSet: [");

		for (Item item : this) {
			if (sb.length() > 10) {
				sb.append(", ");
			}
			sb.append(item.getName());
		}

		sb.append("]");

		if (support != null) {
			sb.append(", Support: ");
			sb.append(support);
		}

		return sb.toString();
	}
}
