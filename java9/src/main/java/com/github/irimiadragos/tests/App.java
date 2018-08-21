package com.github.irimiadragos.tests;


public class App
{
    public static void main( String[] args )
    {
    	if(args.length == 0) {
    		System.out.println("Please provide a parameters as a file to be parsed");
    	} else {
            WordParser parser = new WordParser();
            parser.printResult(args[0]);
    	}
    }
}
