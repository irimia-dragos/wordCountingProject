package com.github.irimiadragos.tests;

public class WordParserLoader {
	
	private static  WordParserLoader myself ;
	private WordParser parser;
	
	public static WordParserLoader instance() {
		if (myself == null) {
			myself = new WordParserLoader();
		}
		return myself;
	}

	public WordParser getParser() {
		return parser;
	}

	public void setParser(WordParser parser) {
		this.parser = parser;
	}
}
