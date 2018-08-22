package com.github.irimiadragos.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Map;

import org.junit.Test;

public class WordParserTest {

	@Test
	public void test() throws WordParserException{
		WordParser parser = WordParserLoader.instance().getParser();
		Map<String, Long> words = parser.loadWords(WordParserTest.class.getResourceAsStream("/tempest.txt"));
		assertNotNull(words);
		
		
		assertEquals("counted i", new Long(446), words.get("i"));
		assertEquals("counted the", new Long(513), words.get("the"));
		assertEquals("counted and", new Long(514), words.get("and"));
		assertEquals("{and=514, the=513, i=446, to=324, a=310, of=295, my=288, you=211, that=188, this=185}", parser.sort(words).toString());
	}
	
}
