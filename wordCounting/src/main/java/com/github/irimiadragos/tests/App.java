package com.github.irimiadragos.tests;

public class App
{
	
    public static void main( String[] args )
    {
    	if(args.length == 0) {
    		System.out.println("Please provide a parameters as a file to be parsed");
    	} else {
    		try {
	            WordParser parser = WordParserLoader.instance().getParser();
	            String result = parser.parse(args[0]);
	            System.out.println("Top 10 most frequent words are " + result);
    		}catch(WordParserException wpe) {
    			System.err.println("Failed to parse file " + wpe.getMessage());    			
    		}
    	}
    }
}
