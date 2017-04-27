package com.prefengine.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.prefengine.domain.NonFunctionalAttributes;

public class NonFunctionalParser {
	private final char[] tokens = {'&','◊','v'};
	private char[] allOperands;
	private String userInput = "P&DvM&S◊P";
	private List<Node<Character>> nodeList;
	private static Node<Character> rootNode;
	private static String format = "\t-%s%n";
	
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
		nodeList = new ArrayList<Node<Character>>();
		if(splitInput() != null){
			char[] splitInputArray = splitInput();
			String splitInputString = new String(splitInputArray);
			 //= new Node<Character>(token);
			
			for(char token: tokens){
				//Create parent node equal to the token
				Node<Character> parentNode = null; 
				// find all occurrences of the token
				int splitStringLen = splitInputString.split(Character.toString(token)).length;
				int[] occurrences = new int[splitStringLen-1];
				//System.out.println(splitStringLen);
				//Find the occurrences within the 
				int count = 0;
				for (int i = -1; (i = splitInputString.indexOf(token, i + 1)) != -1; ) {
				    System.out.println("Location of occurence " + (count + 1 ) + ": " + (i + 1));
					occurrences[count] = i;
					count ++;
					
					parentNode = new Node<Character>(token);
					Node<Character> operand1 = new Node<Character>(splitInputArray[i-1]);
					operand1.setOldData(userInput.toCharArray()[i-1]);
					
					Node<Character> operand2 = new Node<Character>(splitInputArray[i+1]);
					operand2.setOldData(userInput.toCharArray()[i+1]);
					
					parentNode.addChild(operand1);
					parentNode.addChild(operand2);
					//replace the operands and operator set with the parent.
					//splitInputArray[i-1] = '#';
					StringBuilder sb = new StringBuilder(splitInputString);
					sb.deleteCharAt(i+1);
					sb.deleteCharAt(i-1);
					System.out.println(sb);
					
					splitInputString = new String(sb);
					//splitInputArray[i+1] = '#';
					//splitInputString = new String(splitInputArray);
					//System.out.println(splitInputString);
					rootNode.addChild(parentNode);
				
				//for(int occur:occurrences)
					System.out.println("Amount of times " + token + " found:" +occurrences.length);
				/*for (int location = 0; location < occurrences.length; location++) {
					parentNode = new Node<Character>(token);
					Node<Character> operand1 = new Node<Character>(splitInputArray[occurrences[location]-1]);
					operand1.setOldData(userInput.toCharArray()[occurrences[location]-1]);
					
					Node<Character> operand2 = new Node<Character>(splitInputArray[occurrences[location]+1]);
					operand2.setOldData(userInput.toCharArray()[occurrences[location]+1]);
					
					parentNode.addChild(operand1);
					parentNode.addChild(operand2);
					//replace the operands and operator set with the parent.
					splitInputArray[occurrences[location]-1] = '#';
					splitInputArray[occurrences[location]+1] = '#';
					splitInputString = new String(splitInputArray);
					//System.out.println(splitInputString);
					rootNode.addChild(parentNode);*/
					
					//System.out.println(operand1.getParent().getData());
					/*if(operand1.getData() == '#')
						while(operand1.getData()=='#'){
							operand1 = operand1.getParent();
							System.out.println(operand1.getParent().getData());
						}
					//nodeList.add(parentNode);
				*/

				System.out.println(splitInputArray);
				//System.out.println(new String(splitInput()).indexOf(token));
				//String[] splitInput = userInput.split(Character.toString(token));
				/*for(String split : splitInput){
					for(char c : split.toCharArray()){
						Node<Character> aNode = new Node<Character>(c);
						parentNode.addChild(aNode);
					}
					*/
				}

			}
		}else
			System.out.println("String to short");
		//nodeList.forEach(Node<Character> n);
		//Iterator<Node<Character>> i = nodeList.iterator();
		if(rootNode.isLeaf()){
			System.out.println(rootNode.getData());
		}else{
			showAllNodes();
		}
	
	}
	
	public static void showAllNodes() {
		if(rootNode!=null){
			System.out.println(rootNode);
			//get directory content
			getNodeContent(rootNode, format);
		}
		else{
			System.out.println("File directory is empty");
		}		
	}
	public static void getNodeContent(Node<Character> parent, String format){
		Iterator<Node<Character>> it = parent.getChildren().iterator();
		//for(Node<Character> n: parent.getChildren()){
			if(parent.isLeaf())
				if(parent.getOldData()!=null)
					System.out.printf(format, parent.getOldData());
				else
					System.out.printf(format, parent);
			else{
				while(it.hasNext()){
					Node<Character> n = it.next();
					if(n.isLeaf())
						System.out.printf(format, n);
					else{
						System.out.printf(format, n + " -> ");
						//format = "\t" + format;
						getNodeContent(n, "\t" + format);
						/*System.out
						System.out.print(nextNode + "->");
						for(Node<Character> child: nextNode.getChildren())
							if(child.getData() == '#'){
								System.out.print(child.getChildren().toString());
							}else
								System.out.print(child.getData().toString());
						System.out.println();*/
					}
				}
			}
		}
	public List<Node<Character>> getChildren(Node<Character> parentNode){
		return parentNode.getChildren();
	}
	public static void main (String[] args){
		NonFunctionalParser np = new NonFunctionalParser();
		System.out.println("User input:'" + np.userInput + "'");
		np.sliceForTree();
		
		System.out.println(np.rootNode.getChildren().get(1).getChildren());
		
		
		/*Node<String> parentNode = np.new Node<String>("Parent"); 
		Node<String> childNode1 = np.new Node<String>("Child 1", parentNode);
		Node<String> childNode2 = np.new Node<String>("Child 2");     

		childNode2.setParent(parentNode); 
		parentNode.addChild(childNode1);

		parentNode.addChild(childNode2);

		Node<String> grandchildNode = np.new Node<String>("Grandchild of parentNode. Child of childNode1", childNode1); 
		childNode1.addChild(grandchildNode);
		List<Node<String>> childrenNodes = parentNode.getChildren();
		
		System.out.println(childrenNodes.get(0).getChildren());
		System.out.println(np.userInput.split("&")[2].toString());*/
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
	    private T oldData = null;

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

		/**
		 * @return the oldData
		 */
		public T getOldData() {
			return oldData;
		}

		/**
		 * @param oldData the oldData to set
		 */
		public void setOldData(T oldData) {
			this.oldData = oldData;
		}
	}
}
