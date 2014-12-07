/* Notes:
To Compile: javac -cp guava-18.0.jar CommonNames.java
To Run: java -cp guava-18.0.jar:. CommonNames names.txt 25
*/

import java.util.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import com.google.common.base.Functions;
import com.google.common.collect.Ordering;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.base.Strings;


public class CommonNames {
	private static HashMap<String, Integer> lastNames = new HashMap<String, Integer>();
	private static HashMap<String, Integer> firstNames = new HashMap<String, Integer>();
	private static HashMap<String, Integer> fullNames = new HashMap<String, Integer>();

	private static class FullName {
		public String first, last;
		FullName(String first, String last) {
			this.first = first;
			this.last = last;
		}

		@Override
		public String toString() {
			return (this.last + ", " + this.first);
		}
	}
	private static ArrayList<FullName> modifiedNames = new ArrayList<FullName>();

	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			SOP("ERROR: must specify filename and N (in that order)");
			return;
		}

		String filename = args[0];
		int N = Integer.parseInt(args[1]);
		processFile(filename, N);
	}

	private static void processFile(String filename, int N) throws FileNotFoundException, IOException {
		FileInputStream fileInputStream = null;
		BufferedReader bufferedReader = null;

		fileInputStream = new FileInputStream(filename);
		bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

		String line = bufferedReader.readLine();
		String name = "";
		while(line != null) {
			name = line.trim();
			processName(name, N);
			line = bufferedReader.readLine();
		}

		SOP("=====Ten Most Common Last Names=====");
		printTenMostCommon(lastNames);
		SOP("=====Ten Most Common First Names=====");
		printTenMostCommon(firstNames);

		SOP("=====Unique Counts=====");
		SOP("Last Names: " + lastNames.size());
		SOP("First Names: " + firstNames.size());
		SOP("Full Names: " + fullNames.size());

		bufferedReader.close();
		fileInputStream.close();
	}

	private static void processName(String name, int N) {
		putIntoMap(fullNames, name);

		String fullName[] = name.split(", ");
		String last = fullName[0];
		String first = fullName[1];

		//Have I aready seen this first or last name? if not add name to list
		if (modifiedNames.size() < N) {
			if (lastNames.get(last) == null && firstNames.get(first) == null) {
				modifiedNames.add(new FullName(first, last));
			}
		}

		putIntoMap(lastNames, last);
		putIntoMap(firstNames, first);
	}

	private static void putIntoMap(HashMap<String, Integer> tMap, String string) {
		if (tMap == null) {
			return;
		}

		if (tMap.get(string) == null) {
			tMap.put(string, 1);
		} else {
			tMap.put(string, tMap.get(string) + 1);
		}
	}

	private static void printTenMostCommon(HashMap<String, Integer> nameMap) {
		final Ordering<String> naturalReverseValueOrdering = Ordering.natural().reverse().nullsLast().onResultOf(Functions.forMap(nameMap, null)).compound(Ordering.natural());
		ImmutableSortedMap<String, Integer> sortedMap = ImmutableSortedMap.copyOf(nameMap, naturalReverseValueOrdering);
		int count = 0, max = 10;
		for(Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
			if (count < max) {
				SOP(entry);
			} else {
				break;
			}
			count++;
		}
	}


	private static void SOP(Object arg) {
		System.out.println(arg);
	}
}
