import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.junit.Test;

public class HangmanLogicTest {

	//~ CONSTRUCTOR
	
	@Test
	public void TestHangmanLogic() {
		HangmanLogic O = new HangmanLogic();
		assertNotNull(O);
	}
	
	HangmanLogic O = new HangmanLogic();
	
	//~ GETTER METHODS
	
	@Test
	public void TestgetWordLength() {
		assertEquals(0,O.getWordLength());
	}
	
	@Test
	public void TestgetAttemptsMax() {
		assertEquals(0,O.getAttemptsMax());
	}
	
	@Test
	public void TestgetAttemptsMade() {
		assertEquals(0,O.getAttemptsMade());
	}
	
	@Test
	public void TestgetAttemptsLeft() {
		assertEquals(0,O.getAttemptsLeft());
	}
	
	@Test
	public void TestgetBoardDisplay() {
		assertEquals(null,O.getBoardDisplay()); 
	}
	
	@Test
	public void TestgetGuess () {
		assertEquals('_',O.getGuess());
	}
	
	@Test
	public void TestgetPrevious() {
		ArrayList<Character> expected = new ArrayList<Character>();
		assertEquals(expected,O.getPrevious());
	}
	
	@Test
	public void TestgetStatus() {
		assertEquals(Status.ACTIVE,O.getStatus());
	}
	
	@Test
	public void TestgetCandidates() {
		assertNotNull(O.getCandidates());
		assertEquals(0,O.getCandidates().size());
	}
	
	//~ SETTER METHODS
	
	@Test
	public void TestsetWordLength() {
		O.setWordLength("10");
		assertEquals(10,O.getWordLength());
		assertEquals(10,O.getWordLength());
	}
	
	@Test(expected=IllegalArgumentException.class) 
		public void TestsetWordLengthString() {
			O.setWordLength("four");
		}

	@Test(expected=IllegalArgumentException.class) 
	public void TestsetWordLengthNegative() {
		O.setWordLength("-15");
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void TestsetWordLengthZero() {
		O.setWordLength("0");
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void TestsetWordLengthLarge() {
		O.setWordLength("35");
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void TestsetWordLength26() {
		O.setWordLength("26");
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void TestsetWordLength27() {
		O.setWordLength("27");
	}
	
	@Test
	public void TestsetAttemptsMax() {
		O.setAttemptsMax("15");
		assertEquals(15,O.getAttemptsMax());
		O.setAttemptsMax("four");
		assertEquals(15,O.getAttemptsMax());
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void TestsetAttemptsMaxZero() {
		O.setAttemptsMax("0");
	}
	
	@Test(expected=IllegalArgumentException.class) 
	public void TestsetAttemptsMaxNegative() {
		O.setAttemptsMax("-7");
	}

	
	@Test
	public void TestsetAttemptsMade() {
		O.setAttemptsMax("15");
		O.setAttemptsMade();
		assertEquals(1,O.getAttemptsMade());
		assertEquals(15,O.getAttemptsMax());
		assertEquals(14,O.getAttemptsLeft());
		
		O.setAttemptsMade(3);
		assertEquals(3,O.getAttemptsMade());
		assertEquals(15,O.getAttemptsMax());
		assertEquals(12,O.getAttemptsLeft());
		
		O.setAttemptsMax("1");
		O.setAttemptsMade();
		assertEquals(0,O.getAttemptsLeft());
	}
	
	@Test
	public void TestsetAttemptsLeft() {
		O.setAttemptsMax("15");
		O.setAttemptsMade(3);
		O.setAttemptsLeft();
		assertEquals(12,O.getAttemptsLeft());
	}

	@Test
	public void TestsetBoardDisplay() {
		char[] test = {'_','A','M','E'};
		O.setBoardDisplay(test);
		assertEquals(test,O.getBoardDisplay());
	}
	
	@Test
	public void TestsetBoardDisplayGuess() {
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("game");
		O.setNewList(arr);
		
		char[] pre_Guess = {'_','a','m','e'};
		O.setBoardDisplay(pre_Guess);
		
		O.setGuess('g');
		O.setBoardDisplay();
		
		char[] post_Guess = {'g','a','m','e'};

		assertEquals('g',O.getBoardDisplay()[0]);
	}
	
	@Test
	public void TestsetBoardDisplayLength() {
		O.setBoardDisplay(5);
		char[] test = O.getBoardDisplay();
		assertNotNull(test);
		assertEquals(5,test.length);
	}
	
	
	@Test
	public void TestsetGuess() {
		char test = 'P';
		O.setGuess(test);
		assertEquals('P',O.getGuess());
	
	}


	@Test
	public void TestaddToPrevious() {
		O.addToPrevious("P");
		ArrayList<String> test = new ArrayList<String>();
		test.add("P");
		assertEquals(test,O.getPrevious());
		assertEquals(1,O.getPrevious().size());
		
		O.addToPrevious("R");
		assertEquals(2,O.getPrevious().size());
	}

	@Test
	public void TestsetStatusNoParam() {
		char[] board = {'_','n','_','e','r'};
		O.setBoardDisplay(board);
		
		O.setAttemptsMax("1");
		O.setAttemptsMade(1);
		O.setAttemptsLeft();
		O.setStatus();
		assertEquals(Status.LOST,O.getStatus());
				
		board[0] = 'u';
		board[2] = 'd';
		O.setBoardDisplay(board);
		O.setStatus();
		assertEquals(Status.WON,O.getStatus());
	}
	
	@Test
	public void TestsetStatus() {
		O.setStatus(Status.ACTIVE);
		assertEquals(Status.ACTIVE,O.getStatus());
		O.setStatus(Status.LOST);
		assertEquals(Status.LOST,O.getStatus());
	}
	

	@Test
	public void TestsetCandidates() {
		
		O.setWordLength("2");
		O.setCandidates();
		assertNotNull(O.getCandidates());
		assertEquals(5,O.getCandidates().size());
		
		HangmanLogic N = new HangmanLogic();
		N.setWordLength("20");
		N.setCandidates();
		assertNotNull(N.getCandidates());
		assertEquals(5,N.getCandidates().size());
		
		HangmanLogic P = new HangmanLogic();
		P.setWordLength("14");
		P.setCandidates();
		assertNotNull(P.getCandidates());
		assertEquals(5,P.getCandidates().size());
		
	}
	
	@Test
	public void TestsetNewList_HardCode () {
		ArrayList<String> test_arr = new ArrayList<String>();
			test_arr.add("game");
			test_arr.add("gone");
			test_arr.add("mean");
			test_arr.add("gong");
			test_arr.add("none");
		O.setNewList(test_arr);
		assertEquals(test_arr,O.getCandidates());
	}
	
	@Test
	public void TestsetNewList () {
		ArrayList<String> arr = new ArrayList<String>();
			arr.add("game");
			arr.add("gone");
			arr.add("mean");
			arr.add("gong");
			arr.add("none");

		O.setNewList(arr);
		O.setNewList('e');
		
		ArrayList<String> test_arr = new ArrayList<String>();
			test_arr.add("game");
			test_arr.add("gone");
			test_arr.add("none");
		
		assertEquals(test_arr,O.getCandidates());
		
		O.setNewList(arr);
		O.setNewList('x');
		assertEquals(arr,O.getCandidates());
		
		O.setNewList(arr);
		O.setNewList('m');
		ArrayList<String> new_arr = new ArrayList<String>();
			new_arr.add("gone");
			new_arr.add("gong");
			new_arr.add("none");
		assertEquals(new_arr,O.getCandidates());


	}

	//~ OTHER METHODS............................	
	
	@Test
	public void TesteditDistance () {
		int test1 = O.editDistance("word","worm");
		assertEquals(1,test1);
		
		int test2 = O.editDistance("maps","tabs");
		assertEquals(2,test2);
		
		int test3 = O.editDistance("forever","");
		assertEquals(7,test3);
		
		int test4 = O.editDistance("", "forever");
		assertEquals(7,test4);
		
		int test5 = O.editDistance("love", "love");
	}
		
	@Test
	public void TestcharInList () {
		
		ArrayList<String> test_arr = new ArrayList<String>();
			test_arr.add("game");
			test_arr.add("gone");
			test_arr.add("mean");
			test_arr.add("gong");
			test_arr.add("none");
				
		boolean test1 = O.charInList('e',test_arr);
		assertTrue(test1);
		
		boolean test2 = O.charInList('x',test_arr);
		assertFalse(test2);
		
	}
	
	@Test
	public void TestinPrevious() {
		ArrayList<String> test_arr = new ArrayList<String>();
			O.addToPrevious("g");
			O.addToPrevious("l");
			O.addToPrevious("m");
			O.addToPrevious("k");
				
		boolean check = O.inPrevious("k");
		assertTrue(check);
		
		check = O.inPrevious("m");
		assertTrue(check);
		
		check = O.inPrevious("z");
		assertFalse(check);
		
	}
	
}
