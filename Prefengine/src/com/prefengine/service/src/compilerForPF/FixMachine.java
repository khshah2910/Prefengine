package compilerForPF;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *  An interface of prefix and suffix classes' methods
 */
interface Fix
{	
	/** 
	 * Get image of suffix or prefix
	 * 
	 * @return image in String type
	 * */
	public String getImage();
	
	/** 
	 * Get suffix and prefix's meaning 
	 * 
	 * @return meaning in FixFunction type
	 * */
	public FixFunction getfixFunction();
	
	/** 
	 * set up suffix and prefix's meaning 
	 * 
	 * @param multipleFunction
	 * 			multiple meaning is contained in float type, 
	 * 			which is the multiple-operation result of fix's meaning and weight of Core-word
	 * */
	public void setMultipleFunction(float multipleFunction);	
	
	/** 
	 * get suffix and prefix's meaning 
	 * 
	 * @return multiple meaning in float type
	 * */
	public float getMultipleFunction();
}

/** 
 * Contain all prefixes with image and weight
 * */
enum PrefixList implements Fix
{
	ANTI("anti",FixFunction.REVERSE),DE("de",FixFunction.REVERSE),DIS("dis",FixFunction.REVERSE),
	EN("en",FixFunction.ENHANCE),EM("em",FixFunction.ENHANCE),FORE("fore",FixFunction.ENHANCE),
	IN("in",FixFunction.FURTHURFIX),IM("im",FixFunction.FURTHURFIX),IL("il",FixFunction.REVERSE),
	IR("ir",FixFunction.REVERSE),INTER("inter",FixFunction.BETWEEN),MID("mid",FixFunction.LACK),
	MIS("mis",FixFunction.REVERSE),NON("non",FixFunction.REVERSE),OVER("over",FixFunction.ENHANCE),
	PRE("pre",FixFunction.ENHANCE),RE("re",FixFunction.AGAIN),SEMI("semi",FixFunction.LACK),
	SUB("sub",FixFunction.UNDER),SUPER("super",FixFunction.ENHANCE),TRANS("trans",FixFunction.ENHANCE),
	PARA("para",FixFunction.LACK),
	UN("un",FixFunction.REVERSE),UNDER("under",FixFunction.UNDER);
	
	/**  image of prefix*/
	private String image;
	
	/**  weight of prefix, but regulated in FixFunction range */
	private FixFunction prefixFunction;
	
	/**  weight of prefix, but regulated in FixFunction range */
	private float multipleFunction = 0f;

	 /**  
	  * constructor for PrefixList
	  * 
	  * @param image
	  * @param fixFunction
	  * 			define multiple weight as basic weight as default.
	  */
	private PrefixList(String image,FixFunction fixFunction)
	{
		this.image = image;
		this.prefixFunction = fixFunction;
		this.multipleFunction =this.prefixFunction.getWeight();
	}

	 /**  
	  * get prefix image
	  * 
	  * @return image in String type
	  */
	public String getImage()
	{
		return this.image;
	}

	 /**  
	  * get prefix function type
	  * 
	  * @return fix function in FixFunction type
	  */
	public FixFunction getfixFunction()
	{
		return this.prefixFunction;
	}

	 /**  
	  * set up multiple function
	  * 
	  * @param multipleFunction
	  * 			multiple function in float
	  */
	public void setMultipleFunction(float multipleFunction)
	{
		this.multipleFunction =multipleFunction;
	}

	 /**  
	  * get multiple function
	  * 
	  * @return multiple function in float
	  */
	public float getMultipleFunction()
	{
		return this.multipleFunction;
	}
}
enum SuffixList implements Fix
{
	ABLE("able",FixFunction.STILL,Properties.ADJV),IBLE("ible",FixFunction.STILL,Properties.ADJV),
	AL("al",FixFunction.STILL,Properties.ADJV),IAL("ial",FixFunction.STILL,Properties.ADJV),
	ED("ed",FixFunction.STILL,Properties.ADJV),EN("en",FixFunction.STILL,Properties.ADJV),
	ER("er",FixFunction.FURTHURFIX,Properties.FURTHERFIX),EST("est",FixFunction.STRONGENHANCE,Properties.ADJV),
	FUL("ful",FixFunction.ENHANCE,Properties.ADJV),IC("ic",FixFunction.STILL,Properties.ADJV),
	ISM("ism",FixFunction.STILL,Properties.NOUN),HOOD("hood",FixFunction.STILL,Properties.NOUN),
	SHIP("ship",FixFunction.STILL,Properties.NOUN),IS("'s",FixFunction.STILL,Properties.VERB),
	ARE("'re",FixFunction.STILL,Properties.VERB),AM("'m",FixFunction.STILL,Properties.VERB),
	WILL("'ll",FixFunction.STILL,Properties.NOUN),WOULD("'d",FixFunction.STILL,Properties.VERB),
	NOT("n't",FixFunction.REVERSE,Properties.VERB),
	ING("ing",FixFunction.STILL,Properties.NOUN),ION("ion",FixFunction.STILL,Properties.NOUN),
	TION("tion",FixFunction.STILL,Properties.NOUN),ATION("ation",FixFunction.STILL,Properties.NOUN),
	ITION("ition",FixFunction.STILL,Properties.NOUN),ITY("ity",FixFunction.STILL,Properties.NOUN),
	IVE("ive",FixFunction.STILL,Properties.ADJV),
	ATIVE("ative",FixFunction.STILL,Properties.ADJV),ITIVE("itive",FixFunction.STILL,Properties.ADJV),
	LESS("less",FixFunction.REVERSE,Properties.ADJV),LY("ly",FixFunction.STILL,Properties.ADJV),
	MENT("ment",FixFunction.STILL,Properties.NOUN),NESS("ness",FixFunction.STILL,Properties.NOUN),
	OUS("ous",FixFunction.STILL,Properties.ADJV),EOUS("eous",FixFunction.STILL,Properties.ADJV),
	IOUS("ious",FixFunction.STILL,Properties.ADJV),S("s",FixFunction.STILL,Properties.NOUN),
	ES("es",FixFunction.STILL,Properties.NOUN),Y("y",FixFunction.STILL,Properties.ADJV),
	IST("ist",FixFunction.STILL,Properties.NOUN),MAN("man",FixFunction.STILL,Properties.NOUN),
	WOMAN("woman",FixFunction.STILL,Properties.NOUN),MEN("men",FixFunction.STILL,Properties.NOUN),
	WOMEN("women",FixFunction.STILL,Properties.NOUN),IZE("ize",FixFunction.STILL,Properties.VERB);
	
	/**  image of suffix*/
	private String image;
	
	/**  weight of suffix, but regulated in FixFunction range */
	private FixFunction suffixFunction;	
	
	/**  property of prefix, property of suffix will change the property of whole word */
	private Properties suffixProperty;
	
	/**  multiple function of suffix */
	private float multipleFunction ;

	 /**  
	  * constructor for SuffixList
	  * 	  
	  * @param image
	  * @param fixFunction
	  * 			define multiple weight as basic weight as default.
	  */
	private SuffixList(String image,FixFunction fixFunction,Properties suffixProperty)
	{
		this.image = image;
		this.suffixFunction = fixFunction;
		this.suffixProperty = suffixProperty;
		multipleFunction = this.suffixFunction.getWeight();
	}

	 /**  
	  * set up multiple function
	  * 
	  * @param multipleFunction
	  * 			multiple function in float type
	  */
	public void setMultipleFunction(float multipleFunction)
	{
		this.multipleFunction =multipleFunction;
	}

	 /**  
	  * get multiple function
	  * 
	  * @return multiple function in float type
	  */
	public float getMultipleFunction()
	{
		return this.multipleFunction;
	}
	
	 /**  
	  * get suffix image
	  * 
	  * @return image in String type
	  */
	public String getImage()
	{
		return this.image;
	}
	
	 /**  
	  * get suffix suffix function
	  * 
	  * @return suffix function in FixFunction type
	  */
	public FixFunction getfixFunction()
	{
		return this.suffixFunction;
	}
	

	 /**  
	  * set up suffix suffix function
	  * 
	  * @param suffixFunction
	  * 			suffix function in FixFunction type
	  */
	public void setPrefixFunction(FixFunction suffixFunction)
	{
		this.suffixFunction= suffixFunction;
	}
	
	 /**  
	  * get suffix suffix property
	  * 
	  * @return suffix property in Properties type
	  */
	public Properties getSuffixProperty()
	{
		return this.suffixProperty;
	}
	
	 /**  
	  * set up suffix suffix property
	  * 
	  * @param suffixProperty
	  * 			suffix property in Properties type
	  */
	public void setSuffixProperty(Properties suffixProperty)
	{
		 this.suffixProperty = suffixProperty;
	}
}
enum FixFunction
{
	REVERSE (-1f),ENHANCE(1.5f),BETWEEN(1f),AGAIN(1f),UNDER(0.5f),LACK(0.5f),FURTHURFIX(1f),STILL(1f),STRONGENHANCE(2f);	
	
	/**  weight of suffix, but regulated in FixFunction range */
	private float weight;
	
	 /**  
	  * constructor for FixFunction
	  * 	  * 
	  * @param weight
	  */
	private FixFunction(float weight)
	{
		this.weight = weight;
	}
	
	 /**  
	  * get weight of fix function
	  * 
	  * @return weight in float type
	  */
	public float getWeight()
	{
		return this.weight;
	}
}

/**  
 * analyze token and find prefix, suffix and core word of all words. 
 */
public class FixMachine {
	
	/**  store core word maps in local to decrease the call to CoreMeaning static function */
	private Map<String,Map<String,CoreMeaning>> maps = new HashMap<String,Map<String,CoreMeaning>> ();
	
	 /**  
	  * constructor for FixMachine
	  */
	public FixMachine()
	{	maps = TokenKind.getCoreWholeVacabulary();}
	
	 /**  
	  * find and return prefix of a word
	  * 
	  * @param word
	  * 		target word in String type
	  * @return prefix of this word in PrefixList type
	  */
	public PrefixList findPrefix(String word)
	{	if(word.length()> 5)
		{	String targetFirstLetter = word.substring(0, 5);			
			for(int i=1;i<5;i++)
			{	for(PrefixList prefix : PrefixList.values())
					{if(targetFirstLetter.equals(prefix.getImage()))
						{	return prefix;		}				
					}
				targetFirstLetter= word.substring(0, 5-i);
			}			
		}	
		return null;
	}
	
	 /**  
	  * correct users' mistaken spell and organize word type to what stored in CoreMeaning
	  * 
	  * @param tokenElement
	  * 		target element without prefix and suffix
	  * @return core-word in CoreMeaning type
	  */
	public CoreMeaning patchToCoreMeaning(String tokenElement)
	{	ArrayList<String> possibleCoreMeaning = new ArrayList<String>();
		if(tokenElement.endsWith("e"))
		{	possibleCoreMeaning.add(tokenElement.substring(0, tokenElement.length()-1));
		}
		else if(tokenElement.endsWith("i"))			
		{	String midResult = tokenElement.substring(0, tokenElement.length()-1) + "y";
			possibleCoreMeaning.add(midResult);
		}
		if(tokenElement.charAt(tokenElement.length()-1) == tokenElement.charAt(tokenElement.length()-2))			
		{	possibleCoreMeaning.add(tokenElement.substring(0, tokenElement.length()-1));
		}
		if(tokenElement.contains("ou")&& !tokenElement.contains("our"))			
		{	possibleCoreMeaning.add(tokenElement.replace("ou", "ow"));
			possibleCoreMeaning.add(tokenElement.replace("ou", "au"));
			possibleCoreMeaning.add(tokenElement.replace("ou", "our"));
			possibleCoreMeaning.add(tokenElement.replace("ou", "o"));
			possibleCoreMeaning.add(tokenElement.replace("ou", "or"));
		}
		if(tokenElement.contains("ow"))			
		{	possibleCoreMeaning.add(tokenElement.replace("ow", "ou"));
			possibleCoreMeaning.add(tokenElement.replace("ow", "au"));
			possibleCoreMeaning.add(tokenElement.replace("ow", "our"));
			possibleCoreMeaning.add(tokenElement.replace("ow", "o"));
			possibleCoreMeaning.add(tokenElement.replace("ow", "or"));
		}
		if(tokenElement.contains("au"))			
		{	possibleCoreMeaning.add(tokenElement.replace("au", "ow"));
			possibleCoreMeaning.add(tokenElement.replace("au", "ou"));
			possibleCoreMeaning.add(tokenElement.replace("au", "our"));
			possibleCoreMeaning.add(tokenElement.replace("au", "o"));
			possibleCoreMeaning.add(tokenElement.replace("au", "or"));
		}
		if(tokenElement.contains("our"))			
		{	possibleCoreMeaning.add(tokenElement.replace("our", "ow"));
			possibleCoreMeaning.add(tokenElement.replace("our", "au"));
			possibleCoreMeaning.add(tokenElement.replace("our", "ou"));
			possibleCoreMeaning.add(tokenElement.replace("our", "o"));
			possibleCoreMeaning.add(tokenElement.replace("our", "or"));
		}
		if(tokenElement.contains("o") && !tokenElement.contains("or")
				&& !tokenElement.contains("ow")&& !tokenElement.contains("ou")&& !tokenElement.contains("oo"))			
		{	possibleCoreMeaning.add(tokenElement.replace("o", "ow"));
			possibleCoreMeaning.add(tokenElement.replace("o", "au"));
			possibleCoreMeaning.add(tokenElement.replace("o", "ou"));
			possibleCoreMeaning.add(tokenElement.replace("o", "our"));
			possibleCoreMeaning.add(tokenElement.replace("o", "or"));
		}
		else if(tokenElement.contains("or") )			
		{	possibleCoreMeaning.add(tokenElement.replace("or", "ow"));
			possibleCoreMeaning.add(tokenElement.replace("or", "au"));
			possibleCoreMeaning.add(tokenElement.replace("or", "ou"));
			possibleCoreMeaning.add(tokenElement.replace("or", "our"));
			possibleCoreMeaning.add(tokenElement.replace("or", "o"));
		}
		if(tokenElement.contains("ea"))			
		{	possibleCoreMeaning.add(tokenElement.replace("ea", "ee"));
			possibleCoreMeaning.add(tokenElement.replace("ea", "ae"));
			possibleCoreMeaning.add(tokenElement.replace("ea", "i"));
		}
		if(tokenElement.contains("ee"))			
		{	possibleCoreMeaning.add(tokenElement.replace("ee", "ea"));
			possibleCoreMeaning.add(tokenElement.replace("ee", "ae"));
			possibleCoreMeaning.add(tokenElement.replace("ee", "i"));
		}
		if(tokenElement.contains("ae"))			
		{	possibleCoreMeaning.add(tokenElement.replace("ae", "ea"));
			possibleCoreMeaning.add(tokenElement.replace("ae", "ee"));
			possibleCoreMeaning.add(tokenElement.replace("ae", "i"));
		}
		if(tokenElement.contains("i") && !tokenElement.contains("ie")
				&& !tokenElement.contains("ia")&& !tokenElement.contains("ir")&& !tokenElement.contains("ei"))						
		{	possibleCoreMeaning.add(tokenElement.replace("i", "ea"));
			possibleCoreMeaning.add(tokenElement.replace("i", "ee"));
			possibleCoreMeaning.add(tokenElement.replace("i", "ae"));
			possibleCoreMeaning.add(tokenElement.replace("i", "ie"));
			possibleCoreMeaning.add(tokenElement.replace("i", "ia"));
			possibleCoreMeaning.add(tokenElement.replace("i", "ir"));
		}
		if(tokenElement.contains("ia") )						
		{	possibleCoreMeaning.add(tokenElement.replace("i", "ea"));
			possibleCoreMeaning.add(tokenElement.replace("i", "ee"));
			possibleCoreMeaning.add(tokenElement.replace("i", "ae"));
			possibleCoreMeaning.add(tokenElement.replace("i", "ie"));
			possibleCoreMeaning.add(tokenElement.replace("i", "ia"));
			possibleCoreMeaning.add(tokenElement.replace("i", "ir"));
		}
		
		if(tokenElement.contains("er"))			
		{	possibleCoreMeaning.add(tokenElement.replace("er", "ur"));
			possibleCoreMeaning.add(tokenElement.replace("er", "ir"));
			possibleCoreMeaning.add(tokenElement.replace("er", "ar"));
			
		}
		if(tokenElement.contains("ur"))			
		{	possibleCoreMeaning.add(tokenElement.replace("ur", "er"));
			possibleCoreMeaning.add(tokenElement.replace("ur", "ir"));
			possibleCoreMeaning.add(tokenElement.replace("ur", "ar"));
		}
		if(tokenElement.contains("ei"))			
		{	possibleCoreMeaning.add(tokenElement.replace("ei", "ie"));
			possibleCoreMeaning.add(tokenElement.replace("ei", "i"));
		}
		if(tokenElement.contains("ie"))			
		{	possibleCoreMeaning.add(tokenElement.replace("ie", "ei"));
			possibleCoreMeaning.add(tokenElement.replace("ie", "i"));
		}
		if(tokenElement.contains("at"))			
		{	possibleCoreMeaning.add(tokenElement.replace("at", "it"));
			possibleCoreMeaning.add(tokenElement.replace("at", "et"));
		}
		if(tokenElement.contains("it"))			
		{	possibleCoreMeaning.add(tokenElement.replace("it", "at"));
			possibleCoreMeaning.add(tokenElement.replace("it", "et"));
		}
		if(tokenElement.contains("et"))			
		{	possibleCoreMeaning.add(tokenElement.replace("et", "at"));
			possibleCoreMeaning.add(tokenElement.replace("et", "et"));
		}
		if(tokenElement.contains("en"))			
		{	possibleCoreMeaning.add(tokenElement.replace("en", "an"));
		}
		if(tokenElement.contains("an"))			
		{	possibleCoreMeaning.add(tokenElement.replace("an", "en"));
		}
		if(tokenElement.contains("ar"))			
		{	possibleCoreMeaning.add(tokenElement.replace("ar", "er"));
		}
		
		if(tokenElement.contains("p") && !tokenElement.contains("pp"))			
		{	possibleCoreMeaning.add(tokenElement.replace("p", "pp"));
		}
		if(tokenElement.contains("g")&& !tokenElement.contains("gg"))			
		{	possibleCoreMeaning.add(tokenElement.replace("g", "gg"));
		}
		if(tokenElement.contains("r")&& !tokenElement.contains("rr"))			
		{	possibleCoreMeaning.add(tokenElement.replace("r", "rr"));
		}
		if(tokenElement.contains("l")&& !tokenElement.contains("ll"))			
		{	possibleCoreMeaning.add(tokenElement.replace("l", "ll"));
		}
		if(tokenElement.contains("c")&& !tokenElement.contains("sc"))			
		{	possibleCoreMeaning.add(tokenElement.replace("c", "sc"));
			possibleCoreMeaning.add(tokenElement.replace("c", "s"));
		}
		if(tokenElement.contains("s")&& !tokenElement.contains("sc"))			
		{	possibleCoreMeaning.add(tokenElement.replace("s", "c"));
		}
		if(tokenElement.contains("sc"))			
		{	possibleCoreMeaning.add(tokenElement.replace("sc", "c"));
			possibleCoreMeaning.add(tokenElement.replace("sc", "s"));
		}
		for (int i = 0;i < tokenElement.length()-1;i++)
		{	if(tokenElement.charAt(i)== tokenElement.charAt(i+1))
			{	String newTarget = tokenElement.substring(0, i);
				if(i+2 < tokenElement.length())
					newTarget += tokenElement.substring(i+2,  tokenElement.length());
				possibleCoreMeaning.add(newTarget);
			}
		}		
		for(int i = 0;i< possibleCoreMeaning.size();i++)
		{	if(maps.get(String.valueOf(possibleCoreMeaning.get(i).charAt(0))).containsKey(possibleCoreMeaning.get(i)))
					return maps.get(String.valueOf(possibleCoreMeaning.get(i).charAt(0))).get(possibleCoreMeaning.get(i));
		}
		return null;
	}
	
	 /**  
	  * find and return suffix of a word
	  * 
	  * @param word
	  * 		target word in String type
	  * @return suffix of this word in SuffixList type
	  */
	public SuffixList findSuffix(String word)	
	{	if(word.length()> 5)
		{	String targetFirstLetter = word.substring(word.length()-5, word.length());
			for(int i=5;i>0;i--)
			{
				for(SuffixList suffix : SuffixList.values())
				{	
				if(targetFirstLetter.equals(suffix.getImage()))
						{
						return suffix;
						}
					else if(targetFirstLetter.length() == 1 && targetFirstLetter.charAt(0) == 's')
						{	return SuffixList.S;						
						}
					else if(targetFirstLetter.length() == 1 && targetFirstLetter.charAt(0) == 'y')
						return SuffixList.Y;
				}
				targetFirstLetter= targetFirstLetter.substring(1, targetFirstLetter.length());				
			}			
		}	
		else if(word.length()>2)
		{	String targetFirstLetter = word.substring(1, word.length());
			for(int i=word.length()-1;i>0;i--)
			{	for(SuffixList suffix : SuffixList.values())
					{if(targetFirstLetter.equals(suffix.getImage()))
						{return suffix;
						}
					}
				targetFirstLetter= targetFirstLetter.substring(1, targetFirstLetter.length());
			}	
		}
		return null;
	}
	
	 /**  
	  * analyze prefix and suffix by the type of core-word
	  * 
	  * @param tokens
	  * 		prefix, core-word and suffix(s)in an ArrayList
	  * @return prefix, core-word and suffix(s)in an ArrayList after fix all prefix and suffix(s).
	  */
	public ArrayList <Object > furtherFixToken(ArrayList <Object > tokens)
	{	if(tokens.get(tokens.size()-1) instanceof SuffixList && (SuffixList)tokens.get(tokens.size()-1) == SuffixList.ER)
		{	SuffixList suffixTarget = (SuffixList)tokens.get(tokens.size()-1);
			for(int i = 0;i<tokens.size();i++)
			{	if(tokens.get(i) instanceof CoreMeaning)		
				{	if(((CoreMeaning)tokens.get(i)).getBasicProperty() == Properties.ADJV)
					{	suffixTarget.setMultipleFunction(((CoreMeaning)tokens.get(i)).getWeight().getWeight()
							*((CoreMeaning)tokens.get(i)).getWeight().getWeight());
						tokens.remove(tokens.size()-1);
						tokens.add(suffixTarget);
					}
				}
			}
		}	
		else if(tokens.get(tokens.size()-1) instanceof SuffixList && (SuffixList)tokens.get(tokens.size()-1) == SuffixList.EST)
		{	SuffixList suffixTarget = (SuffixList)tokens.get(tokens.size()-1);
			for(int i = 0;i<tokens.size();i++)
			{	if(tokens.get(i) instanceof CoreMeaning)		
				{	if(((CoreMeaning)tokens.get(i)).getBasicProperty() == Properties.ADJV)
					{	suffixTarget.setMultipleFunction(((CoreMeaning)tokens.get(i)).getWeight().getWeight()
							*((CoreMeaning)tokens.get(i)).getWeight().getWeight()*((CoreMeaning)tokens.get(i)).getWeight().getWeight());
					tokens.remove(tokens.size()-1);
					tokens.add(suffixTarget);
					}
				}
			}
		}
	//System.out.println(((PrefixList)tokens.get(0)).getImage()+ "+++++++++++++++++++++");
		if(tokens.get(0) instanceof PrefixList && ((PrefixList)tokens.get(0) == PrefixList.IN || (PrefixList)tokens.get(0) == PrefixList.IM))
		{	PrefixList prefixTarget = (PrefixList)tokens.get(0);
			for(int i = 1;i<tokens.size();i++)
			{	if(tokens.get(i) instanceof CoreMeaning)		
				{	if(((CoreMeaning)tokens.get(i)).getBasicProperty() == Properties.ADJV)
					{	prefixTarget.setMultipleFunction(((CoreMeaning)tokens.get(i)).getWeight().getWeight()
							*-1f);
						tokens.remove(0);
						tokens.add(0,prefixTarget);
					}
				}
			}
		}
		return tokens;
	}
	
	 /**  
	  * recognize prefix and suffix from a word and return them with possible core-word(String)
	  * 
	  * @param word
	  * 		target word in String type
	  * @return prefix, core-word in String and suffix(s)in an ArrayList .
	  */
	public ArrayList <Object > analyzeToken(String word)
	{	ArrayList <Object > tokenAnalyzeResult = new ArrayList <Object >();
		String copyWord = word;
		String analyzeToolString = word;
		String otheranalyzeToolString = word;
		String targetHere = word;
		PrefixList prefix = findPrefix(word);
		SuffixList suffix = findSuffix(word);
		CoreMeaning coreWord = null;
		if(maps.get(String.valueOf(word.charAt(0))).containsKey(word))
		{	tokenAnalyzeResult.add(maps.get(String.valueOf(word.charAt(0))).get(word));
			return tokenAnalyzeResult;
		}
		if(prefix != null)
		{	copyWord = reomoveFix(copyWord,prefix);
			otheranalyzeToolString = reomoveFix(otheranalyzeToolString,prefix);			
		}
		if(suffix != null)
		{	copyWord = reomoveFix(copyWord,suffix);
			analyzeToolString = reomoveFix(analyzeToolString,suffix);
			targetHere = reomoveFix(targetHere,suffix);
			if(maps.get(String.valueOf(analyzeToolString.charAt(0))).containsKey(analyzeToolString))
			{	coreWord = maps.get(String.valueOf(analyzeToolString.charAt(0))).get(analyzeToolString);
				tokenAnalyzeResult.add(coreWord);
				tokenAnalyzeResult.add(suffix);
				return tokenAnalyzeResult;
			}
			else if(findSuffix(analyzeToolString)!=null)
			{	analyzeToolString = reomoveFix(analyzeToolString,suffix);
				ArrayList<Object> deeperFix1 = analyzeToken(copyWord);
				ArrayList<Object> deeperFix2 = analyzeToken(analyzeToolString);
				if(deeperFix2.get(0) instanceof CoreMeaning )
				{coreWord = (CoreMeaning)deeperFix2.get(0);
					float mulWeight = ((SuffixList)deeperFix2.get(1)).getfixFunction().getWeight();
					float thisWeight = suffix.getfixFunction().getWeight();
					 mulWeight = mulWeight * thisWeight;
					suffix.setMultipleFunction(mulWeight);						
					tokenAnalyzeResult.add(coreWord);
					tokenAnalyzeResult.add(suffix);
					return tokenAnalyzeResult;
				}				
				for(int i = 0;i<deeperFix1.size();i++)
				{	if(deeperFix1.get(i) instanceof CoreMeaning)
					{coreWord = (CoreMeaning)deeperFix1.get(i);
						if(i+1 <deeperFix1.size())
						suffix.setMultipleFunction(((SuffixList)deeperFix1.get(i+1)).getfixFunction().getWeight()
								*suffix.getfixFunction().getWeight());
						if(i !=0)
							prefix.setMultipleFunction(((PrefixList)deeperFix1.get(i-1)).getfixFunction().getWeight()
									*prefix.getfixFunction().getWeight());
						if(prefix != null)
							tokenAnalyzeResult.add(prefix);					
						tokenAnalyzeResult.add(coreWord);
						tokenAnalyzeResult.add(suffix);
						return tokenAnalyzeResult;
					}
				}
			}		
			else if(prefix != null)
			{	analyzeToolString = reomoveFix(analyzeToolString,prefix);			
				if(maps.get(String.valueOf(analyzeToolString.charAt(0))).containsKey(analyzeToolString))
				{	coreWord = maps.get(String.valueOf(analyzeToolString.charAt(0))).get(analyzeToolString);
					tokenAnalyzeResult.add(prefix);
					tokenAnalyzeResult.add(coreWord);
					tokenAnalyzeResult.add(suffix);
					return tokenAnalyzeResult;
				}
				
			}
		}
		else if(prefix != null)
		{	if( maps.get(String.valueOf(otheranalyzeToolString.charAt(0))).containsKey(otheranalyzeToolString))
			{	tokenAnalyzeResult.add(prefix);
				coreWord = maps.get(String.valueOf(otheranalyzeToolString.charAt(0))).get(otheranalyzeToolString);
				tokenAnalyzeResult.add(coreWord);
				return tokenAnalyzeResult;
			}
		}
		CoreMeaning coreMeaningAfterPatch = null;
		coreMeaningAfterPatch = patchToCoreMeaning(word);
		if(coreMeaningAfterPatch != null)
		{	tokenAnalyzeResult.add(coreMeaningAfterPatch);
			return tokenAnalyzeResult;			
		}			
		coreMeaningAfterPatch = patchToCoreMeaning(otheranalyzeToolString);
		if(coreMeaningAfterPatch != null)
		{	tokenAnalyzeResult.add(prefix);
			tokenAnalyzeResult.add(coreMeaningAfterPatch);
			return tokenAnalyzeResult;			
		}
		coreMeaningAfterPatch = patchToCoreMeaning(copyWord);	
		if(coreMeaningAfterPatch != null)
		{	tokenAnalyzeResult.add(prefix);
			tokenAnalyzeResult.add(coreMeaningAfterPatch);
			tokenAnalyzeResult.add(suffix);
			return tokenAnalyzeResult;			
		}
		coreMeaningAfterPatch = patchToCoreMeaning(targetHere);
		if(coreMeaningAfterPatch != null)
		{	tokenAnalyzeResult.add(coreMeaningAfterPatch);
			tokenAnalyzeResult.add(suffix);
			return tokenAnalyzeResult;			
		}
		tokenAnalyzeResult.add(new UnrecognizeToken(word));
		return tokenAnalyzeResult;
	}
	
	 /**  
	  * remove the prefix or suffix of a word 
	  * 
	  * @param word
	  * 		target word in String type
	  * @param fix
	  * 		prefix or suffix of this word
	  * @return cleaner word with certain prefix or suffix
	  */
	public String reomoveFix(String word,Object fix)
	{	String newword = null;
		if(fix instanceof PrefixList)
			 newword = word.substring(((PrefixList)fix).getImage().length(),word.length());
		else if(fix instanceof SuffixList)
			 newword = word.substring(0,word.length()-((SuffixList)fix).getImage().length());			
		else 
			System.out.println("Please put in PrefixList or Suffix as second param in removeFix method!");			
		return newword;
	}

}
