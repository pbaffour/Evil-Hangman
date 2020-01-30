
// -------------------------------------------------------------------------
/**
 * An enumerated type that represents the status of an instance of an
 * EvilHangman game.
 *
 * @author  Perpetual Baffour 
 * @version 2019.11.27
 */
public enum Status {
	
    //~ Constants .............................................................

    /**
     * Represents a game in which the human player has made a correct guess of the
     * word. 
     */
	
    WON,

    /**
     * Represents a game in which the human player has exhausted all guess attempts
     * and did not correctly guess the word.
     */
    
    LOST,

    /**
     * Represents a game still in play; the user has not exhausted all guess attempts.
     */
    
    ACTIVE;
}