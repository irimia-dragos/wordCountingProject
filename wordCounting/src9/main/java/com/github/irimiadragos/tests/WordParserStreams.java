package com.github.irimiadragos.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


/**
 * Parser implementation compatible with java9.
 * This class makes full use of lambdas.
 *
 */
public class WordParserStreams implements WordParser {
	
	static{
		//add itself to the parser loader service
		WordParserLoader.instance().setParser(new WordParserStreams());
	}
	
	/**
	 * The java 9 scanner class has a findAll method which will parse for all available words in the stream.
	 * The stream will then be mapped to lowerCase it will be added in a map.
	 * 
	 * The key value pair correspons to the words and their frequency.
	 * 
	 * @param stream The input stream UTF-8 which will be parsed
	 * @return A map with the words and their frequency in the stream
	 */
	public Map<String, Long> parseWords(InputStream stream) {
		if (stream == null) {
			return Collections.<String, Long>emptyMap();
		}
		try (Scanner input = new Scanner(stream, ENCODING)) {
			return input.findAll(Pattern.compile(WORD_PATTERN)).
					map(MatchResult::group).map(String::toLowerCase).
					collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		}
	}


	/**
	 * 
	 * Sort the map entries and get the top noOfWords
	 * 
	 * @param map A map of words and their frequency
	 * @param noOfWords The number of words which should be trimmed.
	 * @return A ordered map of words
	 */
	public Map<String, Long> sort(Map<String, Long> map, int noOfWords) {
		if (map == null || noOfWords <= 0) {
			return Collections.<String, Long>emptyMap();
		}		
		return map.entrySet().stream()
				.sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).limit(noOfWords)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	}

}
