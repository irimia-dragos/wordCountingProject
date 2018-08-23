package com.github.irimiadragos.tests;

/**
 * Small loader service used to dynamically bootstrap current service.
 * In a real world implementation this will probably fall on spring injection service.
 *
 */
public class WordParserLoader {
	
	
	private static final String[] classes = {"com.github.irimiadragos.tests.WordParserCollections", "com.github.irimiadragos.tests.WordParserStreams"};
	
	/**
	 * Singleton instance of myself
	 */
	private static WordParserLoader myself ;

	/**
	 * Current instance of word parser
	 */
	private WordParser parser;

	/**
	 * Singleton building method. Be aware, it's not multithreaded.
	 * @return The myself instance
	 */
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
