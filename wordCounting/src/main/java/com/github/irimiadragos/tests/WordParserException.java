package com.github.irimiadragos.tests;

/**
 * Generic business exception used to signal something wrong in the application.
 *
 */
public class WordParserException extends Exception{
	
	public WordParserException(String message) {
		super(message);
	}

}
