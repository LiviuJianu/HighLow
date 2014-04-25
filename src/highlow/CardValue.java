/*
 * HighLow game developed for training
 * CardValue enum class used in the game
 */

package highlow;

/**
 *
 * @author Liviu
 */
public enum CardValue {

    ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, 
    NINE, TEN, JACK, QUEEN, KING;
    
    /**
     * Return the calue of this CardValue in the game of Blackjack.
     * Note that the value returned for an ace is 1.
     */
    public int blackJackValue() {
        if(this == JACK || this == QUEEN || this == KING)
            return 10;
        else
            return 1 + ordinal();
    }
    
    /**
     * Return a String representation of this CardValue, using numbers
     * for the numerical cards and names for the ace and face cards.
     */
    public String toString() {
        switch(this) { // "this" is one of the enumerated type values
            case ACE: 
                return "Ace";
            case JACK:
                return "Jack";
            case QUEEN:
                return "Queen";
            case KING:
                return "King";
            default:
                int numericValue = 1 + ordinal();
                return "" + numericValue;
        }
    }
}
