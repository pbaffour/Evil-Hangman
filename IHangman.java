import java.util.ArrayList;

public interface IHangman {

	//~ GETTER METHODS............................
	
	/**
	 * Gets word length selected for game.
	 * @return word length.
	 */
	public int getWordLength();
	
	/** 
	 * Gets maximum guess attempts selected for game.
	 * @return maximum attempts.
	 */
	public int getAttemptsMax();
	
	/**
	 * Gets attempts made so far in game.
	 * @return made attempts. 
	 */
	public int getAttemptsMade();
	
	/**
	 * Gets number of attempts remaining for user.
	 * @return attempts left. 
	 */
	public int getAttemptsLeft();
	
	/**
	 * Gets contents of current game board.
	 * @return board.
	 */
	public char[] getBoardDisplay();
	
	/** 
	 * Gets most recent letter guessed in game.
	 * @return recent guess.
	 */
	public char getGuess ();
	
	/**
	 * Gets list of previous guesses in game. 
	 * @return list of previous guesses.
	 */
	public ArrayList<String> getPrevious();
	
	/**
	 * Gets current status of game.
	 * @return current status.
	 */
	public Status getStatus();
	
	/**
	 * Gets list of remaining word candidates.
	 * @return word list.
	 */
	public ArrayList<String> getCandidates();
	
	//~ SETTER METHODS............................	
	
	/**
	 * Sets word length for game.
	 * @throws IllegalArgumentException if input is negative, 
	 * greater than 29, or equal to 26 or 27.
	 * @param word length. 
	 */
	public void setWordLength (String length) throws IllegalArgumentException;
	
	/**
	 * Sets maximum attempts for game.
	 * @throws IllegalArgumentException if input is 
	 * a non-positive number. 
	 * @param maximum attempts. 
	 */
	public void setAttemptsMax (String attempts) throws IllegalArgumentException;

	/**
	 * Updates attempts made for game with parameter.
	 * @param attempts
	 */
	public void setAttemptsMade (int attempts);

	/**
	 * Updates attempts made for game with internal increment.
	 */
	public void setAttemptsMade();
	
	/**
	 * Updates attempts left in game.
	 */
	public void setAttemptsLeft();
	
	/**
	 * Initializes board display based on word length.
	 * @param word length.
	 */
	public void setBoardDisplay(int length);
	
	/**
	 * Updates contents of board based on recent guess.
	 */
	public void setBoardDisplay();
	
	/**
	 * Hard-codes contents of board.
	 * @param current board state.
	 */
	public void setBoardDisplay(char[] board);
	
	/**
	 * Sets latest guess for game.
	 * @param letter guessed
	 */
	public void setGuess(char c);
	
	/**
	 * Adds current guess to list of historical guesses.
	 * @param current guess.
	 */
	public void addToPrevious(String a);
	
	/**
	 * Sets game status to inputted parameter.
	 * @param game status.
	 */
	public void setStatus(Status status);

	/**
	 * Sets game status based on recent guess.
	 * @param game status.
	 */
	public void setStatus();
	

	/**
	 * Initializes word list (candidates) for game.
	 */
	public void setCandidates();
	
	/**
	 * Identifies all candidate words that contain guess at
	 * common index position (or common index positions). Removes 
	 * non-candidates.
	 * @param c - single letter guess
	 */
	public void setNewList (char c);

	/**
	 * Hard-codes list of candidate words.
	 * @param word list
	 */
	public void setNewList (ArrayList<String> arr);

	/**
	 * Returns minimum edit operations needed to convert one string to another.
	 * @param a - first string
	 * @param b - second string
	 * @return - minimum edit operations
	 */
	public int editDistance (String a, String b);

	/**
	 * Checks if user-guessed letter is in word list.
	 * @param letter
	 * @param word list 
	 * @return true if present. False otherwise. 
	 */
	public boolean charInList (char c, ArrayList<String> list);

	/**
	 * Checks if guess was already made by user.
	 * @param letter
	 * @return true if present. False otherwise. 
	 */
	public boolean inPrevious (String c);

}

