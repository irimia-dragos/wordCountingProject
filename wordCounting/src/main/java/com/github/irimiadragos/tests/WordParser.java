package com.github.irimiadragos.tests;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * This word parser will parse words in a stream of text
 * There is a helper method to get first top 10 most freqvent words
 */
public interface WordParser {
	
	String WORD_PATTERN = "[a-zA-Z\\'\\-]+";
	
	/**
	 * Method use to parse the stream in a map of words.
	 * The keys are the words found and the values are their frequency.
	 * 
	 * @param stream Input stream which will be parsed for all the words
	 * @return 
	 * @throws WordParserException
	 */
	Map<String, Long> parseWords(InputStream stream) throws WordParserException ;
	
	/**
	 * Helper methos used to order a map of object based on the value.
	 * This method will trim down the map to a given specific size.
	 * 
	 * @param map A map of words and their frequency
	 * @param noOfWords The number of required words 
	 * @return The top noOfWords values from the input map
	 */
	Map<String, Long> sort(Map<String, Long> map, int noOfWords);
	
}
