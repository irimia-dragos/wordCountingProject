package com.github.irimiadragos.tests;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * WordPArser implementtion using only java 7 api's.
 * The implementation is based on collections. Sorting is done throught a set.
 * 
 */
public class WordParserCollections implements WordParser {
	
	static{
		WordParserLoader.instance().setParser(new WordParserCollections());
	}
	
	
	/**
	 * The method will parse words from a stream. The words are then added in a map with word value and their frequency.
	 * 
	 * @param stream The input stream UTF-8 which will be parsed
	 * @return A map with the words and their frequency in the stream
	 * @throws WordParserException
	 */
	@Override
	public Map<String, Long> parseWords(InputStream stream) throws WordParserException {
		if (stream == null) {
			return Collections.<String, Long>emptyMap();
		}
		Map<String, Long> words = new HashMap<String, Long>();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(stream))) {
			String line;
			while ((line = br.readLine()) != null) {

				Pattern pattern = Pattern.compile(WORD_PATTERN);
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
		}catch(IOException e) {
			//TODO properly log error stack
			e.printStackTrace();
			throw new WordParserException("There was error reading file " + e.getMessage());
		}
		return words;
	}
	
	/**
	 * Helper method to sort and get the top most frequent words.
	 * 
	 * @param map A map of words and their frequency.
	 * @param noOfWords The number of words needed to be left in the list.
	 * @return Sorted most freqent words
	 */
	public Map<String, Long> sort(Map<String, Long> map, int noOfWords) {
		return top(sortToSet(map), noOfWords);
	}

	/**
	 * Helper method used to sort map based on the values.
	 * 
	 * @param map A map of words and their frequency.
	 * @return Sorted set of the given words.
	 */
	public Set<Entry<String,Long>> sortToSet(Map<String, Long> map) {
		if (map == null ){
			return Collections.<Entry<String,Long>>emptyNavigableSet();
		}
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
	
	/** 
	 * Helper method used to trimm down a sorted set size.
	 * 
	 * @param sorted The set which needs to be trimmed
	 * @param size The size required of the trimmed set
	 * @return A map with the trimmed entries from the set.
	 */
	public Map<String, Long> top(Set<Entry<String,Long>> sorted, int size) {
		if (sorted == null || size <= 0 ){
			return Collections.<String, Long>emptyMap();
		}
		LinkedHashMap<String, Long> ordered = new LinkedHashMap<String, Long>();
		int i = 0;
		for(Entry<String, Long> entry : sorted) {
			ordered.put(entry.getKey(), entry.getValue());
			i++;
			if (i >= size) {
				break;
			}
		}
		return ordered;
	}

}
