import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class HangmanIO {

	//~ INSTANCE VARIABLES
	
	private ArrayList<String> candidates; 
	private char[] board;

	//~ CONSTRUCTOR
	
	public HangmanIO() {
		
	}
	//~ I/O GAME COMPONENTS...............................
	
	/**
	 * Activates game for user in main(). Container method for
	 * all other I/O methods.
	 * 
	 */
	public void playGame() {
		
		// Constructs HangmanLogic object.
		HangmanLogic Evil = new HangmanLogic();
		
		// Welcomes user to game. Prompts and receives word length from user.
		System.out.println("Welcome to Hangman!");
		Evil.setWordLength(inputLength());
		
		// Receives desired number of attempts from user.
		Evil.setAttemptsMax(inputAttempts());
		
		// Initializes word list.
		Evil.setCandidates();
		candidates = Evil.getCandidates();
		
		// Initializes board.
		Evil.setBoardDisplay(Evil.getWordLength());
		
		while (Evil.getStatus()==Status.ACTIVE) {
			
			// Asks if word list should be revealed.
			outputCandidates();
			
			// Receives and validates user guess. 			
			String first_guess = inputGuess();
			String valid_guess = validateGuess(first_guess, Evil);
			Evil.addToPrevious(valid_guess);
			
			char letter = valid_guess.charAt(0);
			
			// Sets new list of candidates, finding largest family of words
			// to continue game with computer's advantage.
		
			Evil.setGuess(letter);	
			Evil.setNewList(letter);
			candidates = Evil.getCandidates();
			Evil.setAttemptsMade();
			Evil.setBoardDisplay();

			
			// Checks if letter is in new list. If so, prints new board.
			// If not, prints "incorrect guess."
			
			if (Evil.charInList(letter,candidates)==false) {
				System.out.println("Sorry, that guess is incorrect!");
			}
			else {	
				System.out.println("Yes, that letter appears on the board!");
			}
			
			outputBoard(Evil);
			Evil.setStatus();
						
		}
		
		outputEnd(Evil);		
		
	}	
	
	/**
	 * Inputs desired word length for game. 
	 * @return word length.
	 */
	public String inputLength() {		
		Scanner scan = new Scanner(System.in);
				
		System.out.println("To start, please enter"
				+ " your desired word length for this game. (WARNING:"
				+ " an invalid entry will exit you from the game.");
				
		String input_length = scan.next();
		
		return input_length;
	}
	
	/**
	 * Inputs maximum attempts for game.
	 * @return maximum attempts.
	 */
	public String inputAttempts() {
		
		Scanner scan = new Scanner(System.in);

		System.out.println("Thanks! Please enter"
				+ " desired number of attempts for this game. "
				+ "(WARNING: an invalid entry will exit you from the game.");
		
		String input_attempts = scan.next(); 
		return input_attempts;
	}
	
	/**
	 * Prompts user for new guess.
	 * @return letter that was guessed.
	 */
	public String inputGuess() {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("What letter would you like to guess?");
		
		String input_guess = scan.next().toLowerCase();
		
		return input_guess;
	}

	public String validateGuess(String input, HangmanLogic o) {
		String curr_guess = input;
		boolean valid = validGuess(curr_guess);
		
		while (valid==false || o.inPrevious(curr_guess)==true) {
			System.out.println("Hmm, looks like you've made this guess"
					+ " before or have made an invalid entry. Let's try"
					+ " this again.");
			
			curr_guess = inputGuess();
			System.out.println("Previous SIZE: " + o.getPrevious().size());
			valid = validGuess(curr_guess);
		}
		
		return curr_guess;
			
	}
	
	/**
	 * Validates guess as an alphabet letter.
	 * @return true if guess is valid. False otherwise.  
	 */
	private boolean validGuess(String input) {
		
		String c = input.toLowerCase();
		ArrayList<String> allowed = new ArrayList<>(Arrays.asList("a",
				"b","c","d","e","f","g","h","i","j","k","l","m","n",
				"o","p","q","r","s","t","u","v","w","x","y","z"));
		
		boolean in = allowed.contains(c);
		
		if (in) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Prints result of guess (i.e., correct, incorrect),
	 * prints number of guesses left, and
	 * prints latest state of the game board.
	 */	
	public void outputBoard (HangmanLogic o) {
		board = o.getBoardDisplay();
	
		System.out.println();
		System.out.println("Here's what your board looks like:");
		
		for (int i = 0; i < board.length; i++) {
			System.out.print(board[i] + " ");
		}
		
		System.out.println();
		System.out.println("Remaining guesses: " + o.getAttemptsLeft());
		System.out.println();
	}
		
	/**
	 * Asks user to confirm revealing of word list.
	 * If 'yes', will print list to display.
	 */
	public void outputCandidates() {
		
		Scanner scan = new Scanner(System.in);
				
		System.out.println("Would you like to see the list of"
				+ " candidates? :) Type 'yes' or 'no'.");
		
		String response = scan.next().toLowerCase();
		
		if (response.equals("yes")) {
				System.out.println("Number of words: " + candidates.size());

				for (int i = 0; i < candidates.size(); i++) {
					System.out.print(candidates.get(i) + " ");
				}
				System.out.println();
				return;
		}
		
		System.out.println("Okay then. Let's move on.");
		
	}
	
	/**
	 * Prints custom message to user based on game outcome.
	 */
	public void outputEnd (HangmanLogic o) {
		String word = o.getCandidates().get(0);

		if (o.getStatus()==Status.LOST) {
			System.out.println("Sorry, game over! This was the correct word:");
			System.out.println(word);
		}
		
		else if (o.getStatus()==Status.WON) {
			System.out.println("Congratulations! You correctly guessed the word.");
			return;
		}
	}

	
}

