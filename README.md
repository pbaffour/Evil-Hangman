# Evil-Hangman
Implementation of the "Evil Hangman" game. Original instructions detailed here:
http://nifty.stanford.edu/2011/schwarz-evil-hangman/

Summary of task:
Build a program that bends the rules of the classic version of Hangman. Specifically, the computer creates a list of words, then updates the list each time the user guesses a letter. The computer updates the list to ensure all words are believable candidates for the final word that is revealed, given the user's guesses. In short, the hidden functionality allows the computer to cheat at Hangman by preserving as many options for the mystery word as possible! 

Executing the program:
To play the game, run runGame, which in turns creates an object of the HangmanIO class.
The HangmanIO class handles all interactions with the player, such as receiving input and presenting game results.
All game implementation is hidden in the HangmanLogic class.



