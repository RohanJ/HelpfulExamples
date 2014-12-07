package Cache;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

/*
 This will implemente a LRU cache with O(1) put, get, and remove

 In an LRU cache, once the cache is full we replace the least recently used elements

 Essentially we need to have a hashmap keep track of the nodes in a doubly-linked list.
 But wait! That's basically just a LinkedHashMap! Therefore, we simply extend a LinkedHashMap
 and Override the removeEldestEntry function
*/

public class LRUCache<K, V> extends LinkedHashMap<K, V> {

	private int capacity;

	public LRUCache(int capacity) {
		//LinkedHashMap(int initialCapacity, float loadFactor, boolean accessOrder)
		super(capacity+1, 1.0f, true); // Pass 'true' for accessOrder.
		this.capacity = capacity;
	}

	@Override
	public boolean removeEldestEntry(Entry eldest) {
		//this function should return true if we should remove the eldest entry
		return (size() > this.capacity); //return true if this LinkedHashMap's size is more than the capacity
	}


	public void printCurrentCache() {
		SOP("=====CurrentCache=====");
		for (Entry<K,V> entry : entrySet()) {
			SOP(String.format("Key: %s, Value: %s", String.valueOf(entry.getKey()), String.valueOf(entry.getValue())));
		}

		SOP("======================");
	}

	private void SOP(Object arg) {
		System.out.println(arg);
	}
}
