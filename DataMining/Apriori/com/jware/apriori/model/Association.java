package com.jware.apriori.model;

import java.io.Serializable;

/**
 * Model class for an association.
 * 
 * @author jww25@njit.edu
 */
public class Association implements Serializable {
	private static final long serialVersionUID = 1L;
	private ItemSet left = new ItemSet();
	private ItemSet right = new ItemSet();
	private Double support = 0d;
	private Double confidence = 0d;
	
	public Association() {
		super();
	}
	
	public Association(final ItemSet left, final ItemSet right) {
		this.left = left;
		this.right = right;
		this.support = left.getSupport();
	}
	
	public void addLeft(final Item item) {
		left.add(item);
	}
	
	public void addRight(final Item item) {
		right.add(item);
	}
	
	public ItemSet getLeft() {
		return left;
	}
	
	public ItemSet getRight() {
		return right;
	}

	public Double getSupport() {
		return support;
	}

	public void setSupport(Double support) {
		this.support = support;
	}

	public Double getConfidence() {
		return confidence;
	}

	public void setConfidence(Double confidence) {
		this.confidence = confidence;
	}	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;		
		result = prime * result + ((left == null) ? 0 : left.hashCode());
		result = prime * result + ((right == null) ? 0 : right.hashCode());
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
		Association other = (Association) obj;
		if (left == null) {
			if (other.left != null)
				return false;
		} else if (!left.equals(other.left))
			return false;
		if (right == null) {
			if (other.right != null)
				return false;
		} else if (!right.equals(other.right))
			return false;
		return true;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("Association: [");
		
		boolean firstOfList = true;
		for(Item item : left) {
			if(!firstOfList) {
				sb.append(", ");
			}
			
			sb.append(item);
			firstOfList = false;
		}
		
		sb.append(" -> ");
		
		firstOfList = true;
		for(Item item : right) {
			if(!firstOfList) {
				sb.append(", ");
			}
			
			sb.append(item);
			firstOfList = false;
		}
		
		sb.append("], support: ");
		sb.append(Math.round(support * 100));
		sb.append("%, confidence: ");
		sb.append(Math.round(confidence * 100));
		sb.append("%");
		
		return sb.toString();
	}
}
