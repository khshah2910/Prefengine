package com.prefengine.service.compilerForPF;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException; 
import java.io.InputStreamReader;
import java.util.ArrayList;
public class Data {
	
	/** stores all airline data*/
	private static ArrayList<String> airlineContent = new ArrayList<String>();
	
	/** stores all rank data*/
	private static ArrayList<String> rankContent = new ArrayList<String>();
	
	/** stores all city data*/
	private static ArrayList<String> cityContent = new ArrayList<String>();
	
	/**
	 * get data from text files into String
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void setupData() throws FileNotFoundException, IOException
	{
		FileInputStream file = new FileInputStream("src/com/prefengine/service/compilerForPF/airlines.txt");		
		//FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "/airlines.txt");
		try(BufferedReader br = new BufferedReader(new InputStreamReader(file)))
		{
			String line;			
			while((line = br.readLine()) != null)
			{
				String[] mid = line.split("\t");
				airlineContent.add(mid[0]);
				airlineContent.add(mid[1]);
				airlineContent.add(null);
			}
			br.close();
		}
		 file = new FileInputStream("src/com/prefengine/service/compilerForPF/citys.txt");
		 //file = new FileInputStream(System.getProperty("user.dir") + "/citys.txt");
			try(BufferedReader br = new BufferedReader(new InputStreamReader(file)))
			{
				String line;
				while((line = br.readLine()) != null)
				{
					String[] mid = line.split("\t");
					cityContent.add(mid[0]);
					cityContent.add(mid[1]);
					cityContent.add(mid[2]);
					cityContent.add(null);
				}
				br.close();
			}
	
			 file = new FileInputStream("src/com/prefengine/service/compilerForPF/rank.txt");
			 //file = new FileInputStream(System.getProperty("user.dir") + "/rank.txt");
				try(BufferedReader br = new BufferedReader(new InputStreamReader(file)))
				{
					String line;
					while((line = br.readLine()) != null)
					{
						String[] mid = line.split("(\t)|( )");
						for(int i = 0; i< mid.length; i++)
							rankContent.add(mid[i]);
					}
					br.close();
				}	
	}

	/**
	 * match a unrecognized-token to a airline name
	 * 
	 * @param  token
	 * 			unrecognized-token sent from parser
	 * 
	 * @return airline code in String type
	 */
	public String matchToAirLineName(String token)
	{
		String before = "";
		String result = null;
		if(airlineContent.contains(token))
		{
			int index = airlineContent.indexOf(token);
			if(index != 0)
			{
				before = airlineContent.get(index -1);
			}
			else
			{
				result =  token;
			}
			if(before== null)
			{
				result =  token;
			}
			else
			{
				result =   before;
			}
			
		}
		else 
		{
			for(int index = 0; index< airlineContent.size(); index ++)
			{ 
				
				if(airlineContent.get(index) != null && airlineContent.get(index).contains(" "))
				{
					String target = airlineContent.get(index).replaceAll(" ", "");
					if(target.equals(token))
					{
						if(index != 0)
						{
							before = airlineContent.get(index -1);
						}
						else
						{
							result =  airlineContent.get(index);
						}
						if(before== null)
						{
							result =  airlineContent.get(index);
						}
						else
						{
							result =  before;
						}
						
					}
				}
			}
		}
			return result;
	}

	/**
	 * match a unrecognized-token to a city or country name
	 * 
	 * @param  token
	 * 			unrecognized-token sent from parser
	 * @param possibleCountryName 
	 * 
	 * @return city code in String type
	 */
	public String matchToCityName(String token, String possibleCountryName)
	{
		String before = "";
		String extraBefore = "";
		String result = null;
		if(cityContent.contains(token))
		{
			if(possibleCountryName == null 
					|| (possibleCountryName != null &&cityContent.get(cityContent.indexOf(token)+1).equals(possibleCountryName)))
			{	int index = cityContent.indexOf(token);
				if(index<=2)
				{
					result = cityContent.get(0);
				}
				else
				{
					before = cityContent.get(index -1);
					extraBefore =  cityContent.get(index -2);
					if( before == null)
						result = token;
					else if(extraBefore == null)
						result = before;
					else 
						result = extraBefore;
				}
			}
		}
		else
		{
			for(int index = 0; index< cityContent.size(); index ++)
			{ 
				if(cityContent.get(index) != null &&cityContent.get(index).contains(" "))
				{
					
					String target = cityContent.get(index).replaceAll(" ", "");
					if(possibleCountryName == null ||(possibleCountryName != null 
							&&( target.equals(token) && possibleCountryName.equals(cityContent.get(index+1))
							|| (index + 2 < cityContent.size() && possibleCountryName.equals(cityContent.get(index+2))))))
					{	
						if(index<=2)
						{
							result = cityContent.get(0);
						}
						else
						{
							before = cityContent.get(index -1);
							extraBefore =  cityContent.get(index -2);
							if( before == null)
								result = cityContent.get(index);
							else if(extraBefore == null)
								result = before;
							else 
								result = extraBefore;
						}
					}
				}
			}
		}
			return result;
	}
	
	/**
	 * get a list of airport name based on user's requirement of airline reputation
	 * 
	 * @param  token
	 * 			unrecognized-token sent from parser
	 * 
	 * @return city code in String type
	 */
	public String getAirlineRankNumberByName(String airlineName)
	{
//		int min = range[0];
//		int max = range[1];
//		int minindex = 3* min- 1;
//		int maxindex = 3* max- 1;
//		ArrayList<String> result = new ArrayList<String>();
//		if(min == max)
//		{
		String	result = null;
		if(rankContent.contains(airlineName))
			result= rankContent.get(rankContent.indexOf(airlineName)-1);	
		else
		{
			for(int index = 0; index< rankContent.size(); index ++)
			{ 
				if(rankContent.get(index) != null &&rankContent.get(index).contains(" "))
				{
					String target = rankContent.get(index).replaceAll(" ", "");
					if(target.equals(airlineName) )
						result= rankContent.get(index-1);
				}
			}
					
		}
		return result;
//		}
//		else
//		{
//			for(int i =minindex; i<= maxindex;)
//			{
//				result.add( rankContent.get(i));
//				i += 3; 
//			}
//			return result;
//		}
	
	}	
	
}
