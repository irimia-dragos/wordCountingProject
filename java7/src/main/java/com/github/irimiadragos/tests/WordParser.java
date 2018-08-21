package com.github.irimiadragos.tests;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordParser {
	public WordParser() {

	}

	public void printResult(String file) {
		try (InputStream input = new FileInputStream(file)) {
			System.out.println(sort(loadWords(input)));
		} catch (FileNotFoundException fne) {
			fne.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Map<String, Long> loadWords(InputStream stream) throws IOException {
		Map<String, Long> words = new HashMap<String, Long>();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(stream))) {
			String line;
			while ((line = br.readLine()) != null) {

				Pattern pattern = Pattern.compile("[a-zA-Z\\'\\-]+");
				Matcher matcher = pattern.matcher(line);

				while (matcher.find()) {
					String nextToken = matcher.group().toLowerCase();
					if (words.containsKey(nextToken)) {
						words.put(nextToken, words.get(nextToken) + 1);
					} else {
						words.put(nextToken,new Long(1L));
					}
				}
			}
		}
		return words;
	}

	public Map<String, Long> sort(Map<String, Long> map) {
		TreeSet<Entry<String, Long>> sorted = new TreeSet<Entry<String, Long>>( new Comparator<Entry<String, Long>>() {
				@Override
				public int compare(Entry<String, Long> o1, Entry<String, Long> o2) {
					int result = (int)(o2.getValue() - o1.getValue());
					if (result == 0) {
						return o1.getKey().compareTo(o2.getKey());
					}
					return result;
				}
			});
		
		sorted.addAll(map.entrySet());		
		return sorted;
	}
	
	public Map<String, Long> top(Map<String, Long> sorted, int size) {
		LinkedHashMap<String, Long> ordered = new LinkedHashMap<String, Long>();
		int i = 0;
		for(Entry<String, Long> entry : sorted) {
			ordered.put(entry.getKey(), entry.getValue());
			i++;
			if (i >= 10) {
				break;
			}
		}
		return ordered;
	}

}
