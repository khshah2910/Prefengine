package com.prefengine.service.compilerForPF;

import java.io.FileNotFoundException;
import java.io.IOException;





public class Test {
	public static void main(String [] args) throws FileNotFoundException, IOException 
	{
		String word =  "from boston at 5/17 to newyork with price from 50 to 100";
				//String word  short duration, price from 50 to 600 trips 500 miles from boston at may 24 to newyork at 5/3/14 and non stop";
		Scanner scanner = new Scanner(word);
		scanner.scannerEngine();
		scanner.printMessage();
		Parser parser = new Parser(scanner);
		parser.parserEngine();
		parser.printMessage();
			
	}
}

