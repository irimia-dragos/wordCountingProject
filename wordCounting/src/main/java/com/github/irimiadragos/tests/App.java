package com.github.irimiadragos.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class App
{
	
    public static void main( String[] args )
    {
    	if(args.length == 0) {
    		System.out.println("Please provide a parameters as a file to be parsed");
    	} else {
    		try {
    			WordParser parser = WordParserLoader.instance().getParser();
				try (InputStream input = new FileInputStream(args[0])) {
					String response = parser.sort(parser.parseWords(input)).toString();
					System.out.println("Top 10 most frequent words are " + response);
				} catch (FileNotFoundException fne) {
					System.err.println("File " + args[0] + " was not found on system");
				} 
    		}catch(Exception wpe) {
    			System.err.println("Failed to parse file " + wpe.getMessage());    			
    		}
    	}
    }
}
