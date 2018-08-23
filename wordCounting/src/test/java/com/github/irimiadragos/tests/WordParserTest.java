package com.github.irimiadragos.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayInputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class WordParserTest{
	
	@Test
	public void testLoadMethod() throws WordParserException {
		WordParser parser = WordParserLoader.instance().getParser();
		Map<String, Long> result = parser.parseWords(new ByteArrayInputStream("a aa aaa aaaa aa-aa aa\na aa aaa aaaa aa-aa aa".getBytes()));
		assertEquals(5, result.size());
	}

	@Test
	public void testSortdMethod() throws WordParserException {
		WordParser parser = WordParserLoader.instance().getParser();
		Map<String, Long> words = new HashMap<String, Long>();
		words.put("1a", 10L);
		words.put("2b", 20L);
		words.put("3c", 15L);
		words.put("4c", 13L);
		words.put("5d", 17L);
		words.put("6e", 11L);
		words.put("7f", 19L);
		words.put("8g", 22L);
		words.put("9h", 14L);
		words.put("10i", 12L);
		words.put("11j", 16L);
		Map<String, Long> sorted = parser.sort(words, 5);
		assertEquals(5, sorted.size());
	}
	
	@Test
	public void testLoadSingleMethod() throws WordParserException {
		WordParser parser = WordParserLoader.instance().getParser();
		Map<String, Long> result = parser.parseWords(new ByteArrayInputStream("a a a a a a a a a".getBytes()));
		assertEquals(1, result.size());
	}
	
	@Test
	public void testParseNullMethod() throws WordParserException {
		WordParser parser = WordParserLoader.instance().getParser();
		Map<String, Long> result = parser.parseWords(null);
		assertNotNull(result);
	}
	
	@Test
	public void testSortNullMethod() throws WordParserException {
		WordParser parser = WordParserLoader.instance().getParser();
		Map<String, Long> result = parser.sort(null, 10);
		assertNotNull(result);
	}
	
	@Test
	public void testSortNegativeMethod() throws WordParserException {
		WordParser parser = WordParserLoader.instance().getParser();
		Map<String, Long> result = parser.sort(Collections.<String, Long>emptyMap(), -5);
		assertNotNull(result);
	}
	
}
