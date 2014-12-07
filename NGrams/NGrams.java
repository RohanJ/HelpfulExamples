import java.util.*;

public class NGrams {
	public static void main(String[] args){
		testNGrams();
	}

	public static void testNGrams() {
		String text = "aaaab a0a baaab caab";
		ngram(text, /* ngramLength = */ 3);
	}

	private static void ngram(String text, int ngramLength) {
		String[] words = text.split(" ");
		SortedMap<String, Integer> mMap = new TreeMap<String, Integer>();

		for (String word : words) {
			int wLegth = word.length();
			if (wLegth >= ngramLength) {
				for(int i=0; i<wLegth; i++) {
					if(i+ngramLength <= wLegth) {
						String tNgram = word.substring(i, i+ngramLength);
						putIntoMap(mMap, tNgram);
					}
				}
			}
		}

		SOP(mMap);
		String mostFrequentNgram = getMostFrequentNgram(mMap);
		SOP("The most frequent ngram is: " + mostFrequentNgram);
	}

	private static void putIntoMap(SortedMap<String, Integer> tMap, String string) {
		if (tMap == null) {
			return;
		}

		if (tMap.get(string) == null) {
			tMap.put(string, 1);
		} else {
			tMap.put(string, tMap.get(string) + 1);
		}
	}

	private static String getMostFrequentNgram(SortedMap<String, Integer> mMap) {
		int maxFreq = 0;
		String retNgram = "";
		for (Map.Entry<String, Integer> entry : mMap.entrySet()) {
			if (entry.getValue() > maxFreq ) {
				maxFreq = entry.getValue();
				retNgram = entry.getKey();
			}
		}
		return retNgram;

	}


	private static void SOP(Object arg) {
		System.out.println(arg);
	}
}
