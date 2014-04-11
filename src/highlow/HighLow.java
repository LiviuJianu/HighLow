/*
 * HighLow game developed for training
 * Main class used in the game
 */

package highlow;

import java.util.Scanner;

/**
 * This program lets the user play HighLow, a simple card game
 * that is described in the output statements at the beginning of
 * the main() method. After the user plays several games,
 * the user's average score is required.
 * @author Liviu
 */
public class HighLow {

    /**
     * @param args the command line arguments
     * @param keyboard read the command line arguments
     */
    
    static Scanner keyboard = new Scanner(System.in);
    
    public static void main(String[] args) {
    
        System.out.println("This program lets you play a simple HighLow,");
        System.out.println("card game. A card is dealt from a deck of cards");
        System.out.println("You have to guess whether the next card will be");
        System.out.println("Higher or Lower. Your score in the game is the");
        System.out.println("number of correct predictions you make before");
        System.out.println("you guess wrong. ");
        System.out.println("");
        
        /**
         * Number of games user has played
         */
        int gamesPlayed = 0;
        
        /**
         * The sum of all the scores from
         * all the games played
         */
        int sumOfScores = 0;
        
        /**
         * Average score, computed by dividing
         * sumOfScores by gamesPlayed.
         */
        double averageScore;
        
        /**
         * Record user's response when user is
         * asked whether he wants to play
         * another game.
         */
        boolean playAgain;
        
        do {
            int scoreThisGame;
            scoreThisGame = play();
            sumOfScores += scoreThisGame;
            gamesPlayed++;
            System.out.println(" Play again? ");
            playAgain = keyboard.nextBoolean();
        } while(playAgain);
        
        averageScore = ((double)sumOfScores) / gamesPlayed;
        
        System.out.println();
        System.out.println("You played "+ gamesPlayed + " games");
        System.out.printf("Your average score was %1.3f .\n", averageScore);
        
    }
    
    /**
     * Lets the user play one game of HighLow, and returns the
     * user's score on that game. The score is the number of
     * correct guesses that the user makes
     */
    
    private static int play() {
        /**
         * Get a new deck of cards, and store a reference to it
         * in the variable <code>deck</code>
         */
        Deck deck = new Deck();
        
        /**
         * @param currentCard the current card, which user sees
         */
        Card currentCard;
        
        /**
         * @param nextCard the next card in the deck. The user tries
         * to predict whether this is higher or lower than the current card
         */
        Card nextCard;
        
        /**
         * @param correctGuesses The number of correct guesses the user
         * has made. At the end of the game, the will be the user's score.
         */
        int correctGuesses;
        
        /**
         * @param guess The user's guess. 'H' if the user predicts that
         *  the next card will be higher, 'L' if the user predicts that it will
         * be lower
         */ 
        char guess;
        
        /**
         * shuffle the deck into a random order 
         * before starting the game
         */
        deck.shuffle();
        
        correctGuesses = 0;
        currentCard = deck.dealCard();
        System.out.println("The first card is the " + currentCard);
        
        /**
         * loop ends when the user's predicion is wrong
         */
        while(true) {
            /**
             * Get the user's prediction, 'H' or 'L' ( or 'h' or 'l' ).
             */
            
            System.out.print("Will the next card be higher(H) or lower(L)? ");
            do {
                guess = keyboard.next().charAt(0);
                guess = Character.toUpperCase(guess);
                if(guess != 'H' && guess != 'L')
                    System.out.println("Pleas respond with H or L: ");
            } while (guess != 'H' && guess != 'L');
            
            /**
             * get the next card and show it to the user
             */
            nextCard = deck.dealCard();
            
            System.out.println("The next card is: " + nextCard);
            
            /**
             * check the user's prediction
             */
            if(nextCard.getValue() == currentCard.getValue()) {
                System.out.println("The value is the same as the previous card. ");
                System.out.println("You lose on ties. Sorry!");
                break; // end the game
            } else if(nextCard.getValue() > currentCard.getValue()) {
                if (guess == 'H') {
                    System.out.println("Your guess is correct!");
                    correctGuesses++;
                } else {
                    System.out.println("Your guess was incorrect!");
                    break;
                }
            } else {
                if(guess == 'L') {
                    System.out.println("Your guess is correct!");
                    correctGuesses++;
                } else {
                    System.out.println("Your guess was incorrect!");
                    break;
                }
            }
            
            /**
             * To setup for the next iteration of the loop, the nextCard becomes 
             * the currentCard, since the currentCard has to be the card the user
             * sees, and the nextCard will be set to the next card in the deck 
             * after the user makes his guess
             */
            currentCard = nextCard;
            System.out.println();
            System.out.println("The card is " + currentCard);
        } // end of while loop
        
        System.out.println();
        System.out.println("The game is over!");
        System.out.println("You made " + correctGuesses
                                            + " correct predictions.");
        System.out.println();
        return correctGuesses;
    } //end play
} //end class