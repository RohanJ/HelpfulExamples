import java.util.*;

public class Anagrams{
	public static void main(String[] args){
		testAnagrams();
	}

	public static void testAnagrams() {
		String[] firstWords = {"cinema", "host", "aba", "train"};
		String[] secondWords = {"iceman", "shot", "bab", "rain"};
		anagrams(firstWords, secondWords);
	}

	private static void anagrams(String[] firstWords, String[] secondWords) {
		if (firstWords.length != secondWords.length) {
			return;
		}

		for (int i = 0; i < firstWords.length; i++) {
			if (isAnagram(firstWords[i], secondWords[i])) {
				SOP(firstWords[i] + " and " + secondWords[i] + " are anagrams --> 1");
			} else {
				SOP(firstWords[i] + " and " + secondWords[i] + " are NOT anagrams --> 0");
			}
		}
	}

	private static boolean isAnagram(String firstWord, String secondWord) {
		if (firstWord.length() != secondWord.length()) {
			return false;
		}

		HashMap<String, Integer> fMap = generateMap(firstWord);
		HashMap<String, Integer> sMap = generateMap(secondWord);

		if (fMap == null || sMap == null) {
			return false;
		}

		for (Map.Entry<String, Integer> entry : fMap.entrySet()) {
			if (entry.getValue() != sMap.get(entry.getKey())) {
				return false;
			}
		}
		return true;
	}

	private static HashMap<String, Integer> generateMap(String word) {
		if(word.length() == 0) {
			return null;
		}

		HashMap<String, Integer> mMap = new HashMap<String, Integer>();
		for(int i = 0 ; i<word.length(); i++) {
			String tChar = word.charAt(i) + "";
			if (mMap.get(tChar) == null) {
				mMap.put(tChar, 1);
			} else {
				mMap.put(tChar, mMap.get(tChar) + 1);
			}
		}

		return mMap;
	}

	private static void SOP(Object arg) {
		System.out.println(arg);
	}
}
