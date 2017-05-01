package com.prefengine.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.prefengine.domain.NonFunctionalAttributes;

public class NonFunctionalParser {
	private final char[] tokens = {'&','◊','v'};
	private final String[] tokensToSQL = {"LEAST","AVERAGE","GREATEST"};
	private char[] allOperands;
	private String userInput = "P&DvM&S◊PvP◊S";
	private Map<Integer, Node<Character>> userInputMap;
	private Node<Character> rootNode;
	private static String format = "\t-%s%n";
	private String output = "";
	private String opr="";
	
	public NonFunctionalParser(){
		rootNode = new Node<Character>('r');
		allOperands = new char[NonFunctionalAttributes.values().length ];
		mapOperands();
	}
	public void mapOperands(){
		int count = 0;
		for(NonFunctionalAttributes att: NonFunctionalAttributes.values()){
			//System.out.println(att);
			allOperands[count] = att.toString().charAt(0);
			count++;
		}
	}
	public char[] splitInput(){
		char[] cList = null;
		if(userInput.length() > 1)
			cList =  userInput.toCharArray();
		return cList;
	}
	
	public void sliceForTree(){
		userInputMap = new HashMap<>();

		//Create parent node equal to the token
		Node<Character> parentNode = null; 
		if(splitInput() != null){
			char[] splitInputArray = splitInput();
			String splitInputString = new String(splitInputArray);
			
			//place each character with a null value. Null value is replaced with individual nodes later
			int a = 0;
			for(char c : splitInputArray){
				userInputMap.put(a, new Node<Character>(c));
				a++;
			}
		
			for(char token: tokens){
				// find all occurrences of the token
				int splitStringLen = splitInputString.split(Character.toString(token)).length;
				int[] occurrences = new int[splitStringLen-1];
				//Find the occurrences of an operator within the input string
				int count = 0;
				for (int i = -1; (i = splitInputString.indexOf(token, i + 1)) != -1; ) {
				    System.out.println("Location " + (count + 1 ) + " of occurence : " + (i + 1));
					occurrences[count] = i;
					count ++;
				}
				
				//Use the occurrences to populate the nodes and tree structure starting with the root
				for(int o = occurrences.length; o > 0; o--){
					parentNode = new Node<Character>(token);
					Node<Character> operand1 = userInputMap.get(occurrences[o-1]-1);
					Node<Character> operand2 = userInputMap.get(occurrences[o-1]+1);

					//Swap each node with the operator based on operator precedence
					try{
						//Left hand of the operator
						if(!userInputMap.get(occurrences[o-1]+1).getChildren().isEmpty()){
							int h = 0;
							for(Node<Character> n : userInputMap.values()) {
								if (n.equals(userInputMap.get(occurrences[o-1]+1))){
									userInputMap.replace(h, parentNode);
								}
								h++;
							}
							
						}
						userInputMap.replace(occurrences[o-1]+1, parentNode);
						
						
						//Right hand of the operator
						if(!userInputMap.get(occurrences[o-1]-1).getChildren().isEmpty()){
							int h = 0;
							for(Node<Character> n : userInputMap.values()) {
								if (n.equals(userInputMap.get(occurrences[o-1]-1))){
									userInputMap.replace(h, parentNode);
								}
								h++;
							}
						}
						userInputMap.replace(occurrences[o-1]-1, parentNode);
						
					}catch(ArrayIndexOutOfBoundsException e){
						System.out.println(e.getMessage());
					}
					
					parentNode.addChild(operand1);
					parentNode.addChild(operand2);

					userInputMap.replace(occurrences[o-1], parentNode);
		
				}
				splitInputString = "";
				for(Node<Character> c:userInputMap.values())
					splitInputString += c;
				System.out.println("String after using the " + token + " operator: " + splitInputString);
				System.out.println();

			}
		}else
			System.out.println("String too short");

		rootNode.addChild(parentNode);
		
	
	}
	
	public String generateOutputString(){
		//List<Node<Character>> rootOperations = rootNode.getChildren();
		if(rootNode==null)
			System.out.println("Root Node is null. You must instantiate the class");
		else{
			output = "";
			//getNodeList(rootNode);
		}
			//output = tokensToSQL[tokenCount] + "(";
	
			//output += userInputMap.get(occurrences[o-1]+1);
			
	
			//output += "," + userInputMap.get(occurrences[o-1]-1) + ")";
		
		return null;
	}
	public void showEntireStructure(){
		if(rootNode.isLeaf())
			System.out.println(rootNode.getData());
		else
			showAllNodes();
	}
	
	public void showAllNodes() {
		if(rootNode!=null){
			System.out.println(rootNode);
			//get directory content
			
			output += showNodeContent(rootNode, format);
			
		}
		else{
			System.out.println("File directory is empty");
		}		
	}
	public String showNodeContent(Node<Character> parent, String format){
		Iterator<Node<Character>> it = parent.getChildren().iterator();
		
		for (int i=0; i<tokens.length;i++)
			if(tokens[i]==(Character)parent.getData())
				opr += tokensToSQL[i] + "(";
		if(parent.isLeaf()){
			System.out.printf(format, parent);
			opr+=parent;
		}else{
			while(it.hasNext()){
				Node<Character> n = it.next();
				if(n.isLeaf()){
					System.out.printf(format, n);
					for(NonFunctionalAttributes s: NonFunctionalAttributes.values())
						if(s.toString().toCharArray()[0] ==n.getData())
							opr += s.sqlName();
					
				}
				else{
					System.out.printf(format, n + " -> ");
					System.out.print("");
					System.out.print("");
					showNodeContent(n, "\t" + format);
				}
				if(it.hasNext())
					opr += ",";
			}
			opr += ")";
		}
		return opr;
	}
	/*public List<Node<Character>> getChildren(Node<Character> parentNode){
		return parentNode.getChildren();
	}*/
	public static void main (String[] args){
		NonFunctionalParser np = new NonFunctionalParser();
		System.out.println("User input:'" + np.userInput + "'");
		np.sliceForTree();
		np.showEntireStructure();
		System.out.println(np.output);
		
	}
	/**
	 * @return the userInput
	 */
	public String getUserInput() {
		return userInput;
	}
	/**
	 * @param userInput the userInput to set
	 */
	public void setUserInput(String userInput) {
		this.userInput = userInput;
	}
	
	//Class to create tree with parent and children structure
	public class Node<T> {
	    private List<Node<T>> children = new ArrayList<Node<T>>();
	    private Node<T> parent = null;
	    private T data = null;

	    public Node(T data) {
	        this.data = data;
	    }

	    public Node(T data, Node<T> parent) {
	        this.data = data;
	        this.parent = parent;
	    }

	    public List<Node<T>> getChildren() {
	        return children;
	    }

	    public void setParent(Node<T> parent) {
	        //parent.addChild(this);
	        this.parent = parent;
	    }
	    public Node<T> getParent(){
	    	return parent;
	    }

	    public void addChild(T data) {
	        Node<T> child = new Node<T>(data);
	        child.setParent(this);
	        this.children.add(child);
	    }

	    public void addChild(Node<T> child) {
	        child.setParent(this);
	        this.children.add(child);
	    }

	    public T getData() {
	        return this.data;
	    }

	    public void setData(T data) {
	        this.data = data;
	    }

	    public boolean isRoot() {
	        return (this.parent == null);
	    }

	    public boolean isLeaf() {
	        if(this.children.size() == 0) 
	            return true;
	        else 
	            return false;
	    }

	    public void removeParent() {
	        this.parent = null;
	    }
	    @Override
	    public String toString(){
	    	return this.getData().toString();
	    }

	}
}
