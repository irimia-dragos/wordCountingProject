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
	
	Map<String, Long> parseWords(InputStream stream) throws WordParserException ;
	Map<String, Long> sort(Map<String, Long> map, int noOfWords);
	
}
