package compilerForPF;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException; 
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Data {
	
	/** stores all airline data*/
	private ArrayList<String> airlineContent = new ArrayList<String>();
	
	/** stores all rank data*/
	private ArrayList<String> rankContent = new ArrayList<String>();
	
	/** stores all city data*/
	private ArrayList<String> cityContent = new ArrayList<String>();
	
	/**
	 * get data from txt files into String
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void setupData() throws FileNotFoundException, IOException
	{
		FileInputStream file = new FileInputStream("airlines.txt");
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
		 file = new FileInputStream("citys.txt");
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
	
			 file = new FileInputStream("rank.txt");
				try(BufferedReader br = new BufferedReader(new InputStreamReader(file)))
				{
					String line;
					while((line = br.readLine()) != null)
					{
						String[] mid = line.split("(\t)|( )");
						System.out.println("====" + mid[0] );
						for(int i = 0; i< mid.length; i++)
							rankContent.add(mid[i]);
					}
					br.close();
				}
				for(String str : this.airlineContent)
				{
					if(str != null)
					System.out.println(str);
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
	public String matchToAirLineName(UnrecognizeToken token)
	{
		String before = "";
		if(this.airlineContent.contains(token.getImage()))
		{
			int index = this.airlineContent.indexOf(token.getImage());
			if(index != 0)
			{
				before = this.airlineContent.get(index -1);
			}
			else
			{
				return token.getImage();
			}
			if(before== null)
			{
				return  token.getImage();
			}
			else
			{
				return  before;
			}
			
		}
		else
			return null;
	}

	/**
	 * match a unrecognized-token to a city or country name
	 * 
	 * @param  token
	 * 			unrecognized-token sent from parser
	 * 
	 * @return city code in String type
	 */
	public String matchToCityName(UnrecognizeToken token)
	{
		String before = "";
		String extraBefore = "";
		if(this.cityContent.contains(token.getImage()))
		{
			int index = this.cityContent.indexOf(token.getImage());
			if(index<=2)
			{
				return this.cityContent.get(0);
			}
			else
			{
				before = this.cityContent.get(index -1);
				extraBefore = this.cityContent.get(index -2);
				if( before == null)
					return token.getImage();
				else if(extraBefore == null)
					return before;
				else 
					return extraBefore;
			}						
		}
		else
			return null;
	}
	
	/**
	 * get a list of airport name based on user's requirement of airline reputation
	 * 
	 * @param  token
	 * 			unrecognized-token sent from parser
	 * 
	 * @return city code in String type
	 */
	public ArrayList<String> getirlineListByRank(int[] range)
	{
		int min = range[0];
		int max = range[1];
		int minindex = 3* min- 1;
		int maxindex = 3* max- 1;
		ArrayList<String> result = new ArrayList<String>();
		if(min == max)
		{
			result.add(this.rankContent.get(minindex));			
			return result;
		}
		else
		{
			for(int i =minindex; i<= maxindex;)
			{
				result.add(this.rankContent.get(i));
				i += 3; 
			}
			return result;
		}
	
	}	
	
}
