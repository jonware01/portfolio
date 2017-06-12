package com.jware.apriori.model;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 * Model class for frequent item sets.
 * 
 * @author jww25@njit.edu
 *
 */
public class FrequentItemSets extends LinkedHashSet<ItemSet>{
	private static final long serialVersionUID = 1L;

	public FrequentItemSets() {
		super();
	}
	
	public FrequentItemSets(Collection<? extends ItemSet> collection) {
		super(collection);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder("FrequentItemSets: {");
		
		Iterator<ItemSet> it = this.iterator();
		while(it.hasNext()) {
			ItemSet itemSet = it.next();
			
			if(sb.length() > 19) {
				sb.append(", ");
			}
			
			sb.append(System.lineSeparator());
			sb.append("     Set: [");
			boolean first = true;
			for(Item item : itemSet) {
				if(!first) {
					sb.append(", ");
				}
				
				sb.append(item);
				first = false;
			}
			sb.append("], Support: ");
			sb.append(Math.round(itemSet.getSupport() * 100));
			sb.append("%");
		}
		
		sb.append(System.lineSeparator());
		sb.append("}");
		
		return sb.toString();
	}
}
