package com.prefengine.service.compilerForPF;

import java.io.FileNotFoundException;
import java.io.IOException;





public class Test {
	public static void main(String [] args) throws FileNotFoundException, IOException 
	{
		//String word =  "(price with round trip) I want short duration  and cheap price and come back to atlanta at 3/5/14 arrive at 3/6/14 . trip and better servic with no stop and serv banana and orange.";
		String word = "mileage of 600 and short duration, price from 50 to 600 trips 500 miles from boston at march 24 to newyork at 3/3/14 and non stop";
		Scanner scanner = new Scanner(word);
		scanner.scannerEngine();
		scanner.printMessage();
		Parser parser = new Parser(scanner);
		parser.parserEngine();
		parser.printMessage();
			
	}
}

