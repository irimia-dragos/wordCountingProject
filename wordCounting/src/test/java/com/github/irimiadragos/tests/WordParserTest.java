package com.github.irimiadragos.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayInputStream;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;

public class WordParserTest{
	
	@Test
	public void testLoadMethod() throws WordParserException {
		WordParser parser = WordParserLoader.instance().getParser();
		Map<String, Long> result = parser.parseWords(new ByteArrayInputStream("a aa aaa aaaa aa-aa aa\na aa aaa aaaa aa-aa aa".getBytes()));
		assertEquals(5, result.size());
	}

	@Test
	public void testLoadEmptyMethod() throws WordParserException {
		WordParser parser = WordParserLoader.instance().getParser();
		Map<String, Long> result = parser.parseWords(new ByteArrayInputStream("     ".getBytes()));
		assertEquals(0, result.size());
	}

	@Test
	public void testLoadSingleWord() throws WordParserException {
		WordParser parser = WordParserLoader.instance().getParser();
		Map<String, Long> result = parser.parseWords(new ByteArrayInputStream("  aaa   ".getBytes()));
		assertEquals(1, result.size());
		assertEquals("aaa", result.keySet().iterator().next());
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


	@Test
	@Ignore
	public void testBig() throws WordParserException{
		WordParser parser = WordParserLoader.instance().getParser();
		Map<String, Long> result = parser.parseWords(new InputStream(){
			long i = 0;
			@Override
			public int read() throws IOException {
				i++;
				if (i > 9e10){
					return -1;
				} else if (i % 10 == 0){
					return '\n';
				} else if (i % 3 == 0){
					return (int)'a';
				} else if (i % 3 == 1){
					return (int)'b';
				} else {
					return (int)' ';
				}
			}
		});
		assertNotNull(result);
		System.out.println(result);
		System.out.println(parser.getClass());

	}
	
}
