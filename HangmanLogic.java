
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Random;

public class HangmanLogic implements IHangman {
		
	//~ INSTANCE VARIABLES.........................
	
	private int wordLength;
	private int attemptsMax;
	private int attemptsMade;
	private int attemptsLeft; 
	private int initialListSize = 5;
	
	/** Represents contents of game board. */
	private char[] boardDisplay;
	
	/** Represents most recent letter guess. */
	private char currentGuess; 
	
	/** Represents history of guesses. */
	private ArrayList<String> previousGuesses;
	
	/** Represents current game status. */
	private Status currentStatus;
	
	/** Represents remaining word list. */
	private ArrayList<String> candidates; 
	
	
	//~ CONSTRUCTOR ...........................................
	
	public HangmanLogic () {
		currentStatus = Status.ACTIVE;
		currentGuess = '_';
		candidates = new ArrayList<String>();
		previousGuesses = new ArrayList<String>();
	}
	
	//~ GETTER METHODS.........................................
	
	@Override
	public int getWordLength() {
		return wordLength;
	}

	@Override
	public int getAttemptsMax() {
		return attemptsMax;
	}

	@Override
	public int getAttemptsMade() {
		return attemptsMade;
	}
	
	@Override
	public int getAttemptsLeft() {
		return attemptsLeft;
	}

	@Override
	public char[] getBoardDisplay() {
		return boardDisplay;
	}
	
	@Override
	public char getGuess() {
		return currentGuess;
	}

	@Override
	public ArrayList<String> getPrevious() {
		return previousGuesses;
	}

	@Override
	public Status getStatus() {
		return currentStatus;
	}
	
	public ArrayList<String> getCandidates() {
		return candidates;
	}
	
	//~ SETTER METHODS .....................................
	
	@Override
	public void setWordLength (String length) {
		
		try {
			int a = Integer.parseInt(length);
			
			// Word list must consist of five words, and there are fewer than
			// five words available for 26-, 27-, 28-, and 29-letter words
			// in dictionary
			if (a<=1 | a>= 26) {
				throw new IllegalArgumentException ("Unfortunately,"
						+ " this is an invalid word length. "
						+ " Restart the game and try again.");
			}
			
			wordLength = a;
		}
		catch (NumberFormatException e){
				throw new IllegalArgumentException ("This is invalid."
						+ " Only integers are allowed."
					+ " Restart the game and try again.");
		}
		
		finally {
			System.out.println("Sorry this didn't work out.");
		}
	}
	
	@Override
	public void setAttemptsMax (String attempts) {
		try {
			int a = Integer.parseInt(attempts);
			
			if (a<=0 || a>26 ) {
				throw new IllegalArgumentException ("Unfortunately,"
						+ " this is an invalid number for total attempts."
						+ " The minimum allowed is 1, and the maximum allowed"
						+ " is 26 (for you can only guess a letter once.) "
						+ " Restart game and try again.");
			}
			
			attemptsMax = a;
			attemptsLeft = a;
		}
		
		catch (NumberFormatException e) {
			System.out.println("Unfortunately, this is not a valid "
					+ " number. Restart game and try again.");
		}
	}
	
	public void setAttemptsMade (int attempts) {
		attemptsMade = attempts;
		attemptsLeft = attemptsMax - attemptsMade;
	}
	
	@Override
	public void setAttemptsMade () {
		attemptsMade++;
		attemptsLeft--;
		
		if (attemptsLeft==0) {
			currentStatus = Status.LOST;
		}
	}
	
	@Override
	public void setAttemptsLeft() {
		attemptsLeft = attemptsMax - attemptsMade;
		if (attemptsLeft ==0 && missingLetter()==true) {
			currentStatus = Status.LOST;
		}
	}
	
	public void setBoardDisplay(int length) {
		char[] arr = new char[length];
		for (int i = 0; i < length; i++) {
			arr[i] = '_';
		}
		boardDisplay = arr;
	}

	public void setBoardDisplay(char[] a) {
		boardDisplay = a;
	}
	
	public void setBoardDisplay() {
		String a = candidates.get(0);
		for (int i = 0; i < boardDisplay.length; i ++) {
			if (a.charAt(i)==currentGuess) {
				boardDisplay[i] = currentGuess;
			}
		}
		return;
	}
	
	@Override
	public void setGuess(char c) {
		currentGuess = c;
	}
	
	@Override
	public void addToPrevious(String a) {
		previousGuesses.add(a);
	}

	@Override
	public void setStatus() {
		if (missingLetter()==false) {
			currentStatus = Status.WON;
		}
		
		if (attemptsLeft==0 && missingLetter()==true) {
			currentStatus = Status.LOST;
		}
		
		return;
	}
	
	@Override
	public void setStatus (Status a) {
		currentStatus = a;
	}
	
	/**
	 * Checks if current board display has blank spaces.
	 * @return true if any blank space exists. False otherwise.
	 */
	private boolean missingLetter() {
		for (int i = 0; i < boardDisplay.length; i++) {
			if (boardDisplay[i]=='_') {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void setCandidates() {
		Words dict_obj = new Words();
		String[] dict_full = dict_obj.getWords();
		ArrayList<String> dict_copy = new ArrayList<String>();
	
		for (int i = 0; i < dict_full.length; i++) {
			if (dict_full[i].length()==wordLength) {
				dict_copy.add(dict_full[i]);
			}
		}
		
		ArrayList<String> listSoFar = new ArrayList<String>();
		
		int max = dict_copy.size();

		if (max<50) {
			int i = 0;
			while (i < initialListSize) {
				listSoFar.add(dict_copy.get(i));
				i++;
			}
		}
			
		else { 
		
			Random rand = new Random();
		
			int i = rand.nextInt(max);
			
			while (listSoFar.size()<initialListSize) {
				if (listSoFar.size()==0) {
					listSoFar.add(dict_copy.get(i));
				}
				else {
					
					int levenshtein = editDistance(dict_copy.get(i),listSoFar.get(listSoFar.size()-1));
	
					if ((levenshtein > ((int) (wordLength/2)) )) {
						listSoFar.add(dict_copy.get(i));
					}
					
					i = rand.nextInt(max);
				}
				
				i = rand.nextInt(max);
			}
		}
		
		candidates = listSoFar;
	}
	

	//~ OTHER METHODS............................	

	/**
	 * Finds the minimum edit operations necessary to convert one
	 * string to another.
	 * @param string 1
	 * @param index for string 1
	 * @param string 2
	 * @param index for string 2
	 * @return minimum operations
	 */
	private int editDistance (String a, int i, String b, int j) {
		
		int cost=1;

		if (i==a.length()-1 ) {
			if (a.charAt(i)==b.charAt(j)) {
				cost = 0;
			}
			return cost + b.length()-1-j;
		}

		else if (j==b.length()-1 ) {
			if (a.charAt(i)==b.charAt(j)) {
				cost = 0;
			}
			return cost + a.length()-1-i;
		}

		else if (a.length()==0) {
			return b.length();
		}

		else if (b.length()==0) {
			return a.length();
		}
		
		else if (a.charAt(i)==b.charAt(j)) {
			return editDistance(a,i+1,b,j+1);
		}

		else {
			return Math.min(Math.min(1 + editDistance(a,i,b,j+1),1 + 
					editDistance(a,i+1,b,j+1)),1 + editDistance(a,i+1,b,j));
		}
	}

	@Override
	public int editDistance (String a, String b) {
		return editDistance (a,0,b,0);	
	}
	
	@Override
	public boolean charInList (char c, ArrayList<String> list) {
		
		String a = Character.toString(c);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).contains(a)) {
				return true;
			}
		}
		return false; 
	}
	
	@Override
	public boolean inPrevious (String a) {
		if (previousGuesses.contains(a.toLowerCase())) {
				return true;
		}
		
		return false; 
	}
	
	@Override
	public void setNewList (char c) {
		HashMap<ArrayList<Integer>,ArrayList<String>> hmap = createFamilies(candidates, c);
		ArrayList<String> new_candidates = findLargestFamily(hmap);
		candidates = new_candidates;
	}
	
	@Override
	public void setNewList(ArrayList<String> arr) {
		candidates = arr;
	}
	
	/**
	 * Lists index position(s) at which a given letter in
	 * is found in a word. 
	 * @param word
	 * @param letter 
	 * @return list of index position(s)
	 */
	private ArrayList<Integer> encodeChar(String a, char c) {
		ArrayList<Integer> encoding = new ArrayList<Integer>();
		
		for (int i = 0; i < a.length(); i++) {
			if (a.charAt(i)==c) {
				encoding.add(i);
			}
		}
		
		return encoding;
	
	}
	
	/**
	 * Creates hash table of key-value pairs where the key is equal to
	 * the encoding of a word according to a given letter (see encodeChar), and 
	 * the value is equal to the list of words with the corresponding encoding.
	 * @param current word list
	 * @param letter 
	 * @return table of key-value pairs for codes and associated words
	 */
	private HashMap<ArrayList<Integer>,ArrayList<String>> createFamilies (ArrayList<String> list, char c) {
		
		HashMap<ArrayList<Integer>,ArrayList<String>> hmap = 
				new HashMap<ArrayList<Integer>,ArrayList<String>>();
		
		for (int i = 0; i < list.size(); i++) {
			
			String item = list.get(i);
			ArrayList<Integer> encoding = encodeChar(item, c);
			
			// if empty, continue
			if (encoding.size()==0) {
				encoding.add(-99);
			}
			
			// if key exists in hashmap, add word to list 
			if (hmap.get(encoding)!=null) {
				ArrayList<String> new_list = hmap.get(encoding);
				new_list.add(list.get(i));
				hmap.replace(encoding,new_list);
			}
			
			// else, create new key-value pair
			else {
				ArrayList<String> first = new ArrayList<String>();
				first.add(list.get(i));
				hmap.put(encoding,first);
			}
		}
		return hmap;
	}
	
	/**
	 * Finds the key containing the largest list of words in a hash map,
	 * and returns the list.
	 * @param hashmap
	 * @return list of words
	 */
	private ArrayList<String> findLargestFamily (HashMap<ArrayList<Integer>,ArrayList<String>> hash) {
		ArrayList<String> largestSoFar = new ArrayList<String>();
		
		//iterating over keys in hashmap to find largest list
		for (ArrayList<Integer> k : hash.keySet()) {
			if (hash.get(k).size() > largestSoFar.size()) {
				largestSoFar = hash.get(k);
			}
		}
		
		return largestSoFar;
	}

}

