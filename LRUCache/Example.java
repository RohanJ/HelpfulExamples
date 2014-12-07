//Notes: To compile, first go into Cache folder and javac LRUCache.java. then javac this one

import Cache.LRUCache;

public class Example {

	public static void main(String[] args) {
		int capacity = 5;
		LRUCache<String, Integer> lruCache = new LRUCache<String, Integer>(capacity);

		//now lets test the cache
		lruCache.put("one", 1);
		lruCache.put("two", 2);
		lruCache.put("three", 3);
		lruCache.put("four", 4);
		lruCache.put("five", 5);

		lruCache.printCurrentCache();

		SOP("Adding 'six'. This should remove 'one' and replace with 'six'");
		lruCache.put("six", 6);
		lruCache.printCurrentCache();		

		//this should now be null
		SOP("If we now try to get 'one', we should get back null");
		SOP(lruCache.get("one"));
	}


	private static void SOP(Object arg) {
		System.out.println(arg);
	}
}