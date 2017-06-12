package com.jware.apriori.model;

/**
 * Model class for an Item.
 * 
 * @author jww25@njit.edu
 */
public class Item implements Comparable<Item> {
	private String name;

	public Item() {
		super();
	}

	public Item(String name) {
		super();
		this.name = name;
	}

	/**
	 * String array to Item Set.
	 * 
	 * @param items
	 *            Items to use
	 * @return <code>ItemSet</code>
	 */
	public static ItemSet toItems(String[] items) {
		ItemSet listOfItems = new ItemSet();

		for (String item : items) {
			listOfItems.add(new Item(item.trim()));
		}

		return listOfItems;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public String toString() {
		return name;
	}

	@Override
	public int compareTo(Item o) {
		return this.name.compareTo(((Item) o).name);
	}
}
