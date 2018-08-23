package com.github.irimiadragos.tests;

public class WordParserLoader {
	
	private static final String[] classes = {"com.github.irimiadragos.tests.WordParserCollections", "com.github.irimiadragos.tests.WordParserStreams"};
	
	private static WordParserLoader myself ;
	private WordParser parser;
	
	public static WordParserLoader instance() {
		if (myself == null) {
			myself = new WordParserLoader();
		}
		return myself;
	}

	public WordParser getParser() {
		if (parser == null) {
			forceInit();
		}
		return parser;
	}

	public void setParser(WordParser parser) {
		this.parser = parser;
	}
	
	private void forceInit() {
		for(String clazz : classes) {
			try {
				Class<?> class1 = Class.forName(clazz);
			}catch (ClassNotFoundException e) {
			}
		}
	}
}
