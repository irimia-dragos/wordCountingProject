package com.github.irimiadragos.tests;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public interface WordParser {
	
	String WORD_PATTERN = "[a-zA-Z\\'\\-]+";
	
	String parse(String file) throws WordParserException;
	Map<String, Long> loadWords(InputStream stream) throws WordParserException ;
	Map<String, Long> sort(Map<String, Long> map);
	
}
