package com.github.irimiadragos.tests;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public interface WordParser {
	
	String WORD_PATTERN = "[a-zA-Z\\'\\-]+";
	
	void printResult(String file);
	Map<String, Long> loadWords(InputStream stream) throws IOException ;
	Map<String, Long> sort(Map<String, Long> map);
	
}
