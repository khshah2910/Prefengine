package com.prefengine.service.compilerForPF;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * A scanner take sentences as a String instance, recognize each mark, letter
 * number, Arabic number and word. Then put tokens in sentence clauses
 * (ArrayList<TokenGeneralKind>), and generate connective operators for those
 * clauses.
 */
public class Scanner {

	/** sentences got from user */
	private String inputSentence;

	/** words and marks separated from sentences */
	private String[] words;

	/** a set of sentence clauses as an output of scanner */
	private ArrayList<ArrayList<TokenGeneralKind>> clauseArray = new ArrayList<ArrayList<TokenGeneralKind>>();

	/** a set of connective operators as an output of scanner */
	private ArrayList<ConnectiveOperator> operatorBetweenClauses = new ArrayList<ConnectiveOperator>();

	/**
	 * Construct a scanner.
	 * 
	 * @param inputSentence
	 *            sentences got from user
	 */
	public Scanner(String inputSentence) {
		this.inputSentence = inputSentence;
	}

	/**
	 * Start the whole scanner function as an interface.
	 * 
	 * @return sentence clauses in ArrayList
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<ArrayList<TokenGeneralKind>> scannerEngine() {
		this.words = getArrayFromStringInput(this.inputSentence);
		ArrayList<Object> here = preAnalyzeSentence(this.words);// getNumberUnits(this.inputSentence);//

		ArrayList<TokenGeneralKind> here2 = analyzeSentence(here);
		ArrayList<TokenGeneralKind> here3 = preFixDateNumber(here2);
		ArrayList<Object> result = generateClause(here3);
		this.clauseArray = (ArrayList<ArrayList<TokenGeneralKind>>) result.get(0);
		this.operatorBetweenClauses = (ArrayList<ConnectiveOperator>) result.get(1);
		return this.clauseArray;
	}

	/**
	 * get one of scanner output: sentence clauses .
	 * 
	 * @return sentence clauses in ArrayList
	 */
	public ArrayList<ArrayList<TokenGeneralKind>> getClauseArray() {
		return this.clauseArray;
	}

	/**
	 * Get one of scanner output: connective operators .
	 * 
	 * @return connective operators in ArrayList
	 */
	public ArrayList<ConnectiveOperator> getConnectiveOperatorArray() {
		return this.operatorBetweenClauses;
	}

	/**
	 * Split sentence-string into token-array.
	 * 
	 * @param sentence
	 *            sentences get from user
	 * 
	 * @return an Array of tokens in String type
	 */
	private String[] getArrayFromStringInput(String sentence) {
		StringBuilder mySentence = new StringBuilder(sentence);
		while (hasIllegalSymbol(mySentence) != -1) {
			mySentence.setCharAt(hasIllegalSymbol(mySentence), ' ');
		}
		sentence = String.valueOf(mySentence);
		words = sentence.split("( )|(\t)|(\n)|(\f)");
		return this.words;
	}

	/**
	 * Organize each token: change upper case to lower case; escape meaningless
	 * mark; recognize Arabic number in general number types; split Arabic
	 * number from letters.
	 * 
	 * @param sentenceArray
	 *            an Array of tokens in String type
	 * 
	 * @return an ArrayList of tokens in types that mixed by String and
	 *         SpecialToken
	 */
	private ArrayList<Object> preAnalyzeSentence(String[] sentenceArray) {
		ArrayList<Object> sentenceContent = new ArrayList<Object>();
		for (int i = 0; i < sentenceArray.length; i++) {
			// analyze of every word start here
			if (hasNumber(sentenceArray[i]) != -1) {
				ArrayList<Object> middleArraylistForNumber = new ArrayList<Object>();
				middleArraylistForNumber = getNumberUnits(sentenceArray[i]);
				for (Object o : middleArraylistForNumber) {
					if (o instanceof String) {
						String midString = (String) o;
						midString = midString.toLowerCase();
						boolean tokenGotfixed = false;
						for (int j = 0; j < midString.length(); j++) {
							if (isMeaningfulSpecicalToken(midString.charAt(j))) {
								if (j != 0) {
									sentenceContent.add(midString.substring(0, j));
								}
								tokenGotfixed = true;
								sentenceContent.add(generateMeaningfulSpecicalToken(midString.charAt(j)));
								sentenceContent.add(midString.substring(j + 1, midString.length()));
							} else if (midString.charAt(j) == ':' || midString.charAt(j) == '#'
									|| midString.charAt(j) == '%' || midString.charAt(j) == '/') {
								sentenceContent.add(midString.substring(0, j));
								sentenceContent.add(midString.substring(j + 1, midString.length()));
								tokenGotfixed = true;
							}
						}

						if (tokenGotfixed == false)
							sentenceContent.add(midString);

					} else
						sentenceContent.add(o);
				}
			} else {
				String midString = sentenceArray[i];
				midString = midString.toLowerCase();
				boolean tokenGotfixed = false;
				for (int j = 0; j < midString.length(); j++) {
					if (isMeaningfulSpecicalToken(midString.charAt(j))) {
						if (j != 0)
							sentenceContent.add(midString.substring(0, j));
						tokenGotfixed = true;
						sentenceContent.add(generateMeaningfulSpecicalToken(midString.charAt(j)));
						sentenceContent.add(midString.substring(j + 1, midString.length()));
						tokenGotfixed = true;

					} else if (midString.charAt(j) == ':' || midString.charAt(j) == '#' || midString.charAt(j) == '%'
							|| midString.charAt(j) == '/') {
						sentenceContent.add(midString.substring(0, j));
						sentenceContent.add(midString.substring(j + 1, midString.length()));
						tokenGotfixed = true;
					}
				}
				if (tokenGotfixed == false)
					sentenceContent.add(midString);
			}
		}
		ArrayList<Object> organizedReault = new ArrayList<Object>();
		for (int index = 0; index < sentenceContent.size(); index++) {
			if (sentenceContent.get(index) instanceof String) {
				String str = (String) sentenceContent.get(index);

				if (!str.equals(""))
					organizedReault.add(sentenceContent.get(index));

			} else
				organizedReault.add(sentenceContent.get(index));
		}
		return organizedReault;
	}

	/**
	 * Recognize word in String-type left in the ArrayList by using FixMachine
	 * methods, calculate multiple weight of each word regarding core-word,
	 * prefix and suffix, and find out money-number in the list then change the
	 * property-type into NumberProperties.MONEYNUMBER.
	 * 
	 * @param sentenceArray
	 *            an Array of tokens in Object type
	 * 
	 * @return an ArrayList of tokens in type of TokenGeneralKind
	 */
	private ArrayList<TokenGeneralKind> analyzeSentence(ArrayList<Object> sentenceArray) {
		FixMachine fixmachine = new FixMachine();
		TokenKind.putInMapByFirstLetter();
		ArrayList<TokenGeneralKind> result = new ArrayList<TokenGeneralKind>();
		for (int i = 0; i < sentenceArray.size(); i++) {
			if (sentenceArray.get(i) instanceof String) {
				String instanceofString = (String) sentenceArray.get(i);
				ArrayList<Object> beforeResult = fixmachine.analyzeToken(instanceofString);
				ArrayList<Object> middleResult = fixmachine.furtherFixToken(beforeResult);
				boolean unrecognizedTokenOccur = false;
				CoreMeaning coreMeaning = null;
				Properties property = null;
				float weight = 1f;
				BasicFunctionType functionType = null;
				for (Object elements : middleResult) {
					if (elements instanceof CoreMeaning) {
						coreMeaning = (CoreMeaning) elements;
						property = ((CoreMeaning) elements).getBasicProperty();
						functionType = ((CoreMeaning) elements).getBasicFunctionType();
						weight = weight * ((CoreMeaning) elements).getMultipleWeight();
						coreMeaning.setMultipleWeight(weight);
					} else if (elements instanceof PrefixList) {
						weight = weight * ((PrefixList) elements).getMultipleFunction();
					} else if (elements instanceof SuffixList) {
						weight = weight * ((SuffixList) elements).getMultipleFunction();
						property = ((SuffixList) elements).getSuffixProperty();
					} else if (elements instanceof UnrecognizeToken) {
						result.add((UnrecognizeToken) elements);
						unrecognizedTokenOccur = true;
					}
				}
				if (unrecognizedTokenOccur == false)
					result.add(new Token((String) sentenceArray.get(i), coreMeaning, property, weight, functionType));
			} else {
				result.add((Token) sentenceArray.get(i));
			}
		}
		ArrayList<TokenGeneralKind> newResult = changeNumberTypeToNumber(result);
		for (int index = 0; index < newResult.size(); index++) {
			String tokenImage = "";
			if (newResult.get(index) instanceof Token)
				tokenImage = ((Token) newResult.get(index)).getImage();
			if ((tokenImage.equals("to") || tokenImage.equals("from")) && index + 1 < newResult.size()
					&& newResult.get(index + 1) instanceof Token && ((CoreMeaning) ((Token) newResult.get(index + 1))
							.getCoreMeaning()) == CoreMeaningOne.NUMBERTYPE)
			{
				((Token) newResult.get(index)).setFunctionType(ServiceProperty.GENERALPROPERTY);
				((Token) newResult.get(index)).setCoreMeaning(CoreMeaningOne.PRICETO);
			}
		}

		for (int i = 0; i < newResult.size(); i++) {
			if (newResult.get(i) instanceof Token && ((Token) newResult.get(i)).getImage().equals("$")) {
				if (newResult.get(i + 1) instanceof Token
						&& ((Token) newResult.get(i + 1)).getCoreMeaning() == CoreMeaningOne.NUMBERTYPE) {
					((Token) newResult.get(i + 1)).setProperty(NumberProperties.MONEYNUMBER);
				}
			} else if (newResult.get(i) instanceof Token
					&& ((Token) newResult.get(i)).getFunctionType() == ServiceProperty.COST
					&& ((Token) newResult.get(i)).getWeight() == 0F
					&& ((Token) newResult.get(i)).getProperty() == Properties.NOUN) {
				if (i - 1 >= 0 && newResult.get(i - 1) instanceof Token
						&& ((Token) newResult.get(i - 1)).getCoreMeaning() == CoreMeaningOne.NUMBERTYPE) {
					((Token) newResult.get(i - 1)).setProperty(NumberProperties.MONEYNUMBER);
				}
			} else if (i > 1 && i + 1 < newResult.size() && newResult.get(i) instanceof Token
					&& ((Token) newResult.get(i)).getImage().equals("to")
					&& (newResult.get(i - 1) instanceof Token
							&& ((Token) newResult.get(i - 1)).getCoreMeaning() == CoreMeaningOne.NUMBERTYPE)
					&& ((newResult.get(i + 1) instanceof Token
							&& ((Token) newResult.get(i + 1)).getCoreMeaning() == CoreMeaningOne.NUMBERTYPE)
							|| (newResult.get(i + 1) instanceof Token
									&& ((Token) newResult.get(i + 1)).getFunctionType() == ServiceProperty.COST
									&& ((Token) newResult.get(i + 1)).getWeight() == 0F
									&& ((Token) newResult.get(i + 1)).getProperty() == Properties.NOUN))) {

			}
		}

		return newResult;
	}

	/**
	 * Generate tokens into clause by conjunction marks or words, and put the
	 * connective operator between clauses adjective to each other.
	 * 
	 * @param tokens
	 *            all tokens in ArrayList
	 * 
	 * @return an ArrayList of tokens in type of TokenGeneralKind
	 */
	private ArrayList<Object> generateClause(ArrayList<TokenGeneralKind> tokens) {
		int clauseStartIndex = 0;
		ArrayList<ArrayList<TokenGeneralKind>> resultRequestClause = new ArrayList<ArrayList<TokenGeneralKind>>();
		ArrayList<ConnectiveOperator> resultOperatorClause = new ArrayList<ConnectiveOperator>();
		ArrayList<Object> result = new ArrayList<Object>();
		for (int i = 0; i < tokens.size(); i++) {
			if (tokens.get(i) instanceof Token
					&& (((Token) tokens.get(i)).getProperty() == Properties.CCONJ
							|| ((Token) tokens.get(i)).getProperty() == Properties.RPREP)
					&& !((Token) tokens.get(i)).getImage().endsWith("&")) {
				Token targetToken = (Token) tokens.get(i);
				ArrayList<TokenGeneralKind> copy = (ArrayList<TokenGeneralKind>) tokens;
				ArrayList<TokenGeneralKind> newClause = new ArrayList<TokenGeneralKind>(
						copy.subList(clauseStartIndex, i));
				clauseStartIndex = i + 1;
				resultRequestClause.add(newClause);
				resultOperatorClause.add(calculateConnectiveOperator(targetToken));

			} else if (tokens.get(i) instanceof Token && (((Token) tokens.get(i)).getImage().equals("and")
					|| ((Token) tokens.get(i)).getImage().equals("&"))) {
				if (i + 1 < tokens.size() && tokens.get(i - 1) instanceof Token && tokens.get(i + 1) instanceof Token) {
					Token tokenBeforeAnd = (Token) tokens.get(i - 1);
					Token tokenAfterAnd = (Token) tokens.get(i + 1);
					// when the two token besides "and" have same property and
					// function type then and will not work as a conjunctive.
					if (tokenBeforeAnd.getProperty() != tokenAfterAnd.getProperty()
							|| tokenBeforeAnd.getFunctionType() != tokenAfterAnd.getFunctionType()) {
						Token targetToken = (Token) tokens.get(i);
						ArrayList<TokenGeneralKind> copy = (ArrayList<TokenGeneralKind>) tokens;
						ArrayList<TokenGeneralKind> newClause = new ArrayList<TokenGeneralKind>(
								copy.subList(clauseStartIndex, i));
						clauseStartIndex = i + 1;
						resultRequestClause.add(newClause);
						resultOperatorClause.add(calculateConnectiveOperator(targetToken));
					}
				}
			} else if (tokens.get(i) instanceof Token && ((Token) tokens.get(i)).getProperty() == null) {
				Token targetToken = (Token) tokens.get(i);
				ConnectiveOperator connectiveOperator = calculateConnectiveOperator(targetToken);
				if (connectiveOperator != null) {
					ArrayList<TokenGeneralKind> newClause = new ArrayList<TokenGeneralKind>(
							tokens.subList(clauseStartIndex, i));
					clauseStartIndex = i + 1;
					resultRequestClause.add(newClause);
					resultOperatorClause.add(connectiveOperator);
				}
			}

		}
		// put the rest of tokens without any conjunction with it inside
		// clauseArray as a clause
		if (clauseStartIndex < tokens.size()) {
			ArrayList<TokenGeneralKind> newClause = new ArrayList<TokenGeneralKind>(
					tokens.subList(clauseStartIndex, tokens.size()));
			clauseStartIndex = tokens.size() - 1;
			resultRequestClause.add(newClause);
		}
		// remove the last period recognized as conjunction out of
		// operatorBetweenClauses
		if (resultOperatorClause.size() == resultRequestClause.size()) {
			if (resultOperatorClause.size() - 1 >= 0)
				resultOperatorClause.remove(resultOperatorClause.size() - 1);
		}
		result.add(resultRequestClause);
		result.add(resultOperatorClause);
		return result;
	}

	private ArrayList<TokenGeneralKind> preFixDateNumber(ArrayList<TokenGeneralKind> clause) {
		ArrayList<TokenGeneralKind> newClause = new ArrayList<TokenGeneralKind>();
		for (int index = 0; index < clause.size(); index++) {
			String date = "";
			if (clause.get(index) instanceof Token
					&& ((Token) clause.get(index)).getCoreMeaning() instanceof CoreMeaningOne
					&& ((CoreMeaningOne) ((Token) clause.get(index)).getCoreMeaning())
							.getBasicProperty() == Properties.MONTH
					&& ((CoreMeaningOne) ((Token) clause.get(index)).getCoreMeaning())
							.getBasicFunctionType() == ServiceProperty.LANDA) {
				int dateInInt = (int) (((CoreMeaningOne) ((Token) clause.get(index)).getCoreMeaning())
						.getMultipleWeight());
				date = String.valueOf(dateInInt) + "/";
				if (index + 1 < clause.size()
						&& ((Token) clause.get(index + 1)).getCoreMeaning() instanceof CoreMeaningOne
						&& ((CoreMeaningOne) ((Token) clause.get(index + 1))
								.getCoreMeaning()) == CoreMeaningOne.NUMBERTYPE) {
					int dateNumber = Integer.parseInt(((Token) clause.get(index + 1)).getImage());

					if (dateNumber > 31 && ((date.equals("1") || date.equals("3") || date.equals("5")
							|| date.equals("7") || date.equals("8") || date.equals("10") || date.equals("12"))))
						dateNumber = 31;
					else if (dateNumber > 30
							&& ((date.equals("4") || date.equals("6") || date.equals("9") || date.equals("11"))))
						dateNumber = 30;
					else if (dateNumber > 28 && ((date.equals("2"))))
						dateNumber = 28;
					date = date + String.valueOf(dateNumber) + "/";
					if (index + 2 < clause.size()
							&& ((Token) clause.get(index + 2)).getCoreMeaning() instanceof CoreMeaningOne
							&& ((CoreMeaningOne) ((Token) clause.get(index + 2))
									.getCoreMeaning()) == CoreMeaningOne.NUMBERTYPE) {
						int yearNumber = Integer.parseInt(((Token) clause.get(index + 2)).getImage());
						if (yearNumber < 2017)
							yearNumber = 2017;
						date += String.valueOf(yearNumber);
						index += 2;
					} else {
						date += "2017";
						index += 1;
					}

				} else {
					String dateNumber = "";
					if ((date.equals("1") || date.equals("3") || date.equals("5") || date.equals("7")
							|| date.equals("8") || date.equals("10") || date.equals("12")))
						dateNumber = "31";
					else if ((date.equals("4") || date.equals("6") || date.equals("9") || date.equals("11")))
						dateNumber = "30";
					else if ((date.equals("2")))
						dateNumber = "28";
					date = date + dateNumber + "/2017";
				}
				newClause.add(new Token(date, CoreMeaningOne.NUMBERTYPE, NumberProperties.DATENUMBER));
			} else
				newClause.add(clause.get(index));
		}

		return newClause;
	}

	/**
	 * Calculate connective Operators into ConnectiveOperator class from
	 * TokenGeneralKind(conjunctive tokens).
	 * 
	 * @param conjunction
	 *            one conjunctive mark or word
	 * 
	 * @return a ConnectiveOperator instance
	 */
	private ConnectiveOperator calculateConnectiveOperator(TokenGeneralKind conjunction) {
		CoreMeaning coreMeaning;
		if (((Token) conjunction).getFunctionType() != null) {
			coreMeaning = (CoreMeaning) (((Token) conjunction).getCoreMeaning());
			if (coreMeaning.getWeight() == WeightOriginalRange.POSITIVESTABLE)
				return ConnectiveOperator.AND;
			else if (coreMeaning.getWeight() == WeightOriginalRange.POSITIVESLACK)
				return ConnectiveOperator.OR;
			else if (coreMeaning.getWeight() == WeightOriginalRange.POSITIVEENHANCE)
				return ConnectiveOperator.COMPROMISE;
			else
				return ConnectiveOperator.FURTHERFIX;
		} else {
			String image = ((Token) conjunction).getImage();
			if (image.equals("."))
				return ConnectiveOperator.AND;
			else if (image.equals(";"))
				return ConnectiveOperator.AND;
			else if (image.equals(","))
				return ConnectiveOperator.AND;
			else if (image.equals("&"))
				return ConnectiveOperator.AND;
			else if (image.equals(">"))
				return ConnectiveOperator.OR;
			else if (image.equals("<"))
				return ConnectiveOperator.OR;
			else if (image.equals("|"))
				return ConnectiveOperator.OR;
			else if (image.equals("("))
				return ConnectiveOperator.AND;
			else if (image.equals(")"))
				return ConnectiveOperator.AND;
			else
				return ConnectiveOperator.FURTHERFIX;
		}
	}

	/**
	 * Analyze a char is meaningful mark or not.
	 * 
	 * @param c
	 *            one char
	 * 
	 * @return boolean type
	 */
	private boolean isMeaningfulSpecicalToken(char c) {
		if (c == '.' || c == ';' || c == ',' || c == '|' || c == '&' || c == '(' || c == ')' || c == '$' || c == '>'
				|| c == '<')
			return true;
		else
			return false;
	}

	/**
	 * Analyze a char is number or not.
	 * 
	 * @param c
	 *            one char
	 * 
	 * @return boolean type
	 */
	private boolean isNumber(char c) {
		if (c >= '0' && c <= '9')
			return true;
		else
			return false;
	}

	/**
	 * Analyze a char is letter or not.
	 * 
	 * @param c
	 *            one char
	 * 
	 * @return boolean type
	 */
	private boolean isLetter(char c) {
		if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))
			return true;
		else
			return false;
	}

	/**
	 * Find out if there is number shows in a token-String.
	 * 
	 * @param token
	 *            one token in String type
	 * 
	 * @return the index where number shows up
	 */
	private int hasNumber(String token) {
		int findNumber = -1;
		for (int i = 0; i < token.length(); i++)
			if (isNumber(token.charAt(i))) {
				findNumber = i;
				break;
			}
		return findNumber;
	}

	/**
	 * Find out if there is meaningless mark shows in a token-String.
	 * 
	 * @param token
	 *            one token in StringBuilder type
	 * 
	 * @return the index where meaningless mark shows up
	 */
	private int hasIllegalSymbol(StringBuilder token) {
		int indexOFIllegalSymbol = -1;
		for (int i = 0; i < token.length(); i++) {
			if (token.charAt(i) == '-')
				token.deleteCharAt(i);
			else if (token.charAt(i) == '!' || token.charAt(i) == '?')
				token.replace(i, i + 1, ".");
			else if (token.charAt(i) == '~')
				token.replace(i, i + 1, " numberto ");
		}
		for (int i = 0; i < token.length(); i++) {
			indexOFIllegalSymbol = i;
			char targetChar = token.charAt(i);
			if (isNumber(targetChar) || isLetter(targetChar) || targetChar == ' ')
				;
			else {
				SpecialToken[] specialTokenList = SpecialToken.values();
				boolean findItAsSpecialToken = false;
				for (int j = 0; j < specialTokenList.length; j++) {
					if (String.valueOf(targetChar).equals(specialTokenList[j].getImage()))
						findItAsSpecialToken = true;
				}
				if (findItAsSpecialToken == false)
					return indexOFIllegalSymbol;
			}
		}
		return -1;
	}

	public String[] getCurrentDateInformation()
	{
		DateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd");
		Date currentDate = new Date();
		String date = dateFormat.format(currentDate);
		String [] result = date.split("-");
		return result;
	}

	/**
	 * Split number unit from words or mark.
	 * 
	 * @param token
	 *            one token in String type
	 * 
	 * @return an ArrayList of number-type and words or marks.
	 */
	private ArrayList<Object> getNumberUnits(String token) {
		int indexOfNumberOccer = hasNumber(token);
		ArrayList<Object> seperateNumberOutputArray = new ArrayList<Object>();
		String wordBeforeNumber = token.substring(0, indexOfNumberOccer);
		seperateNumberOutputArray.add(wordBeforeNumber);
		String numberWord = String.valueOf(token.charAt(indexOfNumberOccer));
		if (token.length() == 1) {
			seperateNumberOutputArray.add(new Token(token, CoreMeaningOne.NUMBERTYPE, NumberProperties.REGULARNUMBER));
			return seperateNumberOutputArray;
		}
		for (int i = indexOfNumberOccer + 1; i < token.length(); i++) {
			int indexAtHere = -1;
			if (isNumber(token.charAt(i))) {
				numberWord += String.valueOf(token.charAt(i));
				if (i == token.length() - 1) {
					seperateNumberOutputArray
							.add(new Token(numberWord, CoreMeaningOne.NUMBERTYPE, NumberProperties.REGULARNUMBER));
					return seperateNumberOutputArray;
				}
			}
			// when '.' works as a radix point
			else if (token.charAt(i) == '.' && (i + 1 < token.length() && isNumber(token.charAt(i + 1)))) {
				indexAtHere = i + 1;
				numberWord += ".";
				while (indexAtHere < token.length() && (isNumber(token.charAt(indexAtHere))
						|| token.charAt(indexAtHere) == '%' || isMeaningfulSpecicalToken(token.charAt(indexAtHere)))) {
					numberWord += String.valueOf(token.charAt(indexAtHere));
					if (token.charAt(indexAtHere) == '%' || indexAtHere == token.length() - 1) {
						seperateNumberOutputArray
								.add(new Token(numberWord, CoreMeaningOne.NUMBERTYPE, NumberProperties.REGULARNUMBER));
						numberWord = "";
						seperateNumberOutputArray.add(token.substring(indexAtHere + 1, token.length()));
						return seperateNumberOutputArray;
					} else if (isMeaningfulSpecicalToken(token.charAt(indexAtHere))) {
						seperateNumberOutputArray.add(new Token(numberWord.substring(0, numberWord.length() - 1),
								CoreMeaningOne.NUMBERTYPE, NumberProperties.REGULARNUMBER));
						seperateNumberOutputArray.add(generateMeaningfulSpecicalToken(token.charAt(indexAtHere)));
						seperateNumberOutputArray.add(token.substring(indexAtHere + 1, token.length()));
						return seperateNumberOutputArray;
					} else
						indexAtHere += 1;
				}
				seperateNumberOutputArray
						.add(new Token(numberWord, CoreMeaningOne.NUMBERTYPE, NumberProperties.REGULARNUMBER));
				if (indexAtHere == token.length() - 2) {
					return seperateNumberOutputArray;
				} else {
					seperateNumberOutputArray.add(token.substring(indexAtHere, token.length()));
					return seperateNumberOutputArray;
				}
			}
			// when '.' works as a period
			else if (token.charAt(i) == '.') {
				seperateNumberOutputArray
						.add(new Token(numberWord, CoreMeaningOne.NUMBERTYPE, NumberProperties.REGULARNUMBER));
				seperateNumberOutputArray.add(generateMeaningfulSpecicalToken(token.charAt(i)));
				if (i + 1 < token.length() - 1) {
					seperateNumberOutputArray.add(token.substring(i + 1, token.length()));
				}
				return seperateNumberOutputArray;
			}
			// other meaningful special token shows after number.
			else if (isMeaningfulSpecicalToken(token.charAt(i))) {
				seperateNumberOutputArray
						.add(new Token(numberWord, CoreMeaningOne.NUMBERTYPE, NumberProperties.REGULARNUMBER));
				seperateNumberOutputArray.add(generateMeaningfulSpecicalToken(token.charAt(i)));
				seperateNumberOutputArray.add(token.substring(i + 1, token.length()));
			}
			// put it in array when when ':' use as time number separator
			else if (token.charAt(i) == ':' && i + 1 < token.length() && isNumber(token.charAt(i + 1))) {
				if (i + 2 < token.length() && isNumber(token.charAt(i + 2))) {
					numberWord = numberWord + ":" + String.valueOf(token.charAt(i + 1));
					numberWord += String.valueOf(token.charAt(i + 2));
					seperateNumberOutputArray
							.add(new Token(numberWord, CoreMeaningOne.NUMBERTYPE, NumberProperties.TIMENUMBER));
					if (i + 3 < token.length())
						seperateNumberOutputArray.add(token.substring(i + 3, token.length()));
				} else {
					numberWord = numberWord + ":" + String.valueOf(token.charAt(i + 1));
					seperateNumberOutputArray
							.add(new Token(numberWord, CoreMeaningOne.NUMBERTYPE, NumberProperties.REGULARNUMBER));
				}
				return seperateNumberOutputArray;
			}
			// ignore it when ':' use as divide operator
			else if (token.charAt(i) == ':') {
				seperateNumberOutputArray
						.add(new Token(numberWord, CoreMeaningOne.NUMBERTYPE, NumberProperties.REGULARNUMBER));
				if (i + 1 < token.length())
					seperateNumberOutputArray.add(token.substring(i + 1, token.length()));
				return seperateNumberOutputArray;
			}
			// put it in array when when '/' use as date separator
			else if (token.charAt(i) == '/') {
				if ((i + 2 < token.length() && isNumber(token.charAt(i + 1)) && isNumber(token.charAt(i + 2)))
						|| (i + 1 < token.length() && isNumber(token.charAt(i + 1)))) {
					if (i + 2 < token.length() && isNumber(token.charAt(i + 1)) && isNumber(token.charAt(i + 2))) {
						numberWord = numberWord + "/" + token.substring(i + 1, i + 3);
						i += 2;
					} else {
						numberWord = numberWord + "/" + token.substring(i + 1, i + 2);
						i += 1;
					}
					if (i + 1 < token.length() && token.charAt(i + 1) == '/') {
						if (i + 5 < token.length() && isNumber(token.charAt(i + 2)) && isNumber(token.charAt(i + 3))
								&& isNumber(token.charAt(i + 4)) && isNumber(token.charAt(i + 5))) {
							numberWord = numberWord + "/" + token.substring(i + 2, i + 6);
							seperateNumberOutputArray
									.add(new Token(numberWord, CoreMeaningOne.NUMBERTYPE, NumberProperties.DATENUMBER));
							if (i + 6 < token.length())
								seperateNumberOutputArray.add(token.substring(i + 6, token.length()));
						} else if (i + 3 < token.length() && isNumber(token.charAt(i + 2))
								&& isNumber(token.charAt(i + 3))) {
							numberWord = numberWord + "/" + token.substring(i + 2, i + 4);
							seperateNumberOutputArray
									.add(new Token(numberWord, CoreMeaningOne.NUMBERTYPE, NumberProperties.DATENUMBER));
							if (i + 4 < token.length())
								seperateNumberOutputArray.add(token.substring(i + 4, token.length()));
						} else {
							seperateNumberOutputArray
									.add(new Token(numberWord, CoreMeaningOne.NUMBERTYPE, NumberProperties.DATENUMBER));
							if (i + 2 < token.length())
								seperateNumberOutputArray.add(token.substring(i + 2, token.length()));
						}
						return seperateNumberOutputArray;
					} 
					else if (i + 1 < token.length() && (!isNumber(token.charAt(i + 1))))
					{
						String currentyear = getCurrentDateInformation()[0];
						numberWord = numberWord + "/" + currentyear;
						seperateNumberOutputArray
								.add(new Token(numberWord, CoreMeaningOne.NUMBERTYPE, NumberProperties.DATENUMBER));

						seperateNumberOutputArray.add(token.substring(i + 1, token.length()));
					}
					
					else if (i + 3 < token.length())
					{
						String currentyear = getCurrentDateInformation()[0];
						numberWord = numberWord + "/" + currentyear;						
						seperateNumberOutputArray
								.add(new Token(numberWord, CoreMeaningOne.NUMBERTYPE, NumberProperties.DATENUMBER));
						seperateNumberOutputArray.add(token.substring(i + 3, token.length()));
					}
					else if(i == token.length()-1)
					{
						String currentyear = getCurrentDateInformation()[0];
						numberWord = numberWord + "/" + currentyear;
						seperateNumberOutputArray
						.add(new Token(numberWord, CoreMeaningOne.NUMBERTYPE, NumberProperties.DATENUMBER));
				
					}
				}
				else {
					seperateNumberOutputArray
							.add(new Token(numberWord, CoreMeaningOne.NUMBERTYPE, NumberProperties.REGULARNUMBER));
					if (i + 1 < token.length())
						seperateNumberOutputArray.add(token.substring(i + 1, token.length()));
				}
				return seperateNumberOutputArray;
			} else {
				if (i + 1 < token.length()) {
					if ((token.charAt(i) == 't' && token.charAt(i + 1) == 'h')
							|| (token.charAt(i - 1) == '1' && token.charAt(i) == 's' && token.charAt(i + 1) == 't')
							|| (token.charAt(i - 1) == '2' && token.charAt(i) == 'n' && token.charAt(i + 1) == 'd')) {
						seperateNumberOutputArray
								.add(new Token(numberWord, CoreMeaningOne.NUMBERTYPE, NumberProperties.DATENUMBER));
						if (i + 2 < token.length())
							seperateNumberOutputArray.add(token.substring(i + 2, token.length()));
						return seperateNumberOutputArray;
					} else {
						seperateNumberOutputArray
								.add(new Token(numberWord, CoreMeaningOne.NUMBERTYPE, NumberProperties.REGULARNUMBER));
						seperateNumberOutputArray.add(token.substring(i, token.length()));
						return seperateNumberOutputArray;
					}
				} else {
					seperateNumberOutputArray
							.add(new Token(numberWord, CoreMeaningOne.NUMBERTYPE, NumberProperties.REGULARNUMBER));
					seperateNumberOutputArray.add(token.substring(i, token.length()));
					return seperateNumberOutputArray;
				}
			}
		}
		return seperateNumberOutputArray;
	}

	/**
	 * Change letter number to Arabic number.
	 * 
	 * @param tokens
	 *            all tokens in ArrayList
	 * 
	 * @return an ArrayList without letter number.
	 */
	private ArrayList<TokenGeneralKind> changeNumberTypeToNumber(ArrayList<TokenGeneralKind> tokens) {
		ArrayList<TokenGeneralKind> newTokenObject = new ArrayList<TokenGeneralKind>();
		int startIndex = 0;
		for (int i = 0; i < tokens.size(); i++) {
			// analyze money number
			if (i < tokens.size() && tokens.get(i) instanceof Token
					&& ((Token) tokens.get(i)).getCoreMeaning() instanceof CoreMeaning
					&& (((Token) tokens.get(i)).getProperty() == Properties.NUMBER)) {
				int j = 1;
				for (int ii = startIndex; ii < i; ii++)
					newTokenObject.add(tokens.get(ii));
				float numberAmount = 0;
				CoreMeaning coreMeaning = (CoreMeaning) ((Token) tokens.get(i)).getCoreMeaning();
				float midAmount = Float.valueOf(coreMeaning.getMultipleWeight());
				while (i + j < tokens.size() && tokens.get(i + j) instanceof Token
						&& ((Token) tokens.get(i + j)).getCoreMeaning() instanceof CoreMeaning
						&& (((CoreMeaning) ((Token) tokens.get(i + j)).getCoreMeaning())
								.getBasicProperty() == Properties.NUMBER
								|| (((CoreMeaning) ((Token) tokens.get(i + j)).getCoreMeaning())
										.getImage() == "and"))) {
					String targetImage = ((CoreMeaning) ((Token) tokens.get(i + j)).getCoreMeaning()).getImage();
					if (targetImage.equals("hundred") || targetImage.equals("thousand") || targetImage.equals("million")
							|| targetImage.equals("billion")) {
						midAmount *= ((CoreMeaning) ((Token) tokens.get(i + j)).getCoreMeaning()).getMultipleWeight();
					} else if (targetImage.equals("and")) {
						numberAmount += midAmount;
						midAmount = 0;
					} else
						midAmount += ((CoreMeaning) ((Token) tokens.get(i + j)).getCoreMeaning()).getMultipleWeight();
					j += 1;
				}
				i = i + j;
				startIndex = i;
				numberAmount += midAmount;
				newTokenObject.add(new Token(String.valueOf(numberAmount), CoreMeaningOne.NUMBERTYPE,
						NumberProperties.REGULARNUMBER));
			}

			// analyze date number
			// else if(i< tokens.size() && tokens.get(i) instanceof Token
			// && ((Token)tokens.get(i)).getCoreMeaning() instanceof CoreMeaning
			// && (((Token)tokens.get(i)).getProperty() == Properties.MONTH)
			// && (((Token)tokens.get(i)).getFunctionType() ==
			// ServiceProperty.LANDA))
			// {
			// String StringOfDate =
			// String.valueOf(((Token)tokens.get(i)).getWeight());
			// if(i+1 < tokens.size()
			// && ((Token)tokens.get(i+1)).getCoreMeaning() instanceof
			// CoreMeaning
			// && (((Token)tokens.get(i)).getProperty() == Properties.NOUN)
			// && (((Token)tokens.get(i)).getFunctionType() ==
			// ServiceProperty.LANDA)
			// &&(((Token)tokens.get(i)).getWeight() !=
			// WeightOriginalRange.STABLE.getWeight()) )
			// {
			//// StringOfDate = StringOfDate + "/" +
			// String.valueOf(((Token)tokens.get(i)).getWeight());
			//// if(((Token)tokens.get(i)).getWeight() >= 20f && )
			//// {
			////
			//// }
			// }
			// }
		}
		if (startIndex < tokens.size() - 1) {
			for (int i = startIndex; i < tokens.size(); i++)
				newTokenObject.add(tokens.get(i));
		}
		return newTokenObject;
	}

	/**
	 * Generate meaningful mark into Token type.
	 * 
	 * @param c
	 *            one mark in char type
	 * 
	 * @return a Token instance
	 */
	private Token generateMeaningfulSpecicalToken(char c) {
		if (c == '.')
			return new Token(".", SpecialToken.PERIOD, Properties.CCONJ);
		else if (c == ',')
			return new Token(",", SpecialToken.SEMI, Properties.CCONJ);
		else if (c == ';')
			return new Token(";", SpecialToken.COMMA, Properties.CCONJ);
		else if (c == '|')
			return new Token("|", SpecialToken.OR, Properties.CCONJ);
		else if (c == '&')
			return new Token("&", SpecialToken.AND, Properties.CCONJ);
		else if (c == '(')
			return new Token("(", SpecialToken.PARAMETER, Properties.CCONJ);
		else if (c == ')')
			return new Token(")", SpecialToken.PARAMETERCLOSE, Properties.CCONJ);
		else if (c == '$')
			return new Token("$", SpecialToken.DOLLAR, Properties.NUMBER);
		else if (c == '>')
			return new Token(">", SpecialToken.GREATERTHAN, Properties.CCONJ);
		else if (c == '<')
			return new Token("<", SpecialToken.SMALLERTHAN, Properties.CCONJ);
		else if (c == '=')
			return new Token("=", SpecialToken.EQUAL, Properties.CCONJ);
		else if (isNumber(c) || isLetter(c))
			return new Token("", CoreMeaningOne.NUMBERTYPE, NumberProperties.REGULARNUMBER);
		else
			return null;
	}

	/**
	 * Print the detail of result for testing.
	 */
	public void printMessage() {
		this.words = getArrayFromStringInput(this.inputSentence);
		for (int i = 0; i < clauseArray.size(); i++) {
			for (TokenGeneralKind oo : clauseArray.get(i)) {
				if (oo instanceof Token)
					System.out.print(((Token) oo).getImage() + "(" + ((Token) oo).getFunctionType() + ")("
							+ ((Token) oo).getWeight() + ")     ");

				else
					System.out.print(((UnrecognizeToken) oo).getImage() + "(--)");
			}
			System.out.println();
			if (i < operatorBetweenClauses.size())
				System.out.println(operatorBetweenClauses.get(i).getImage());
		}
	}
}
