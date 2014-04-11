/*
 * HighLow game developed for training
 * Hand class used in the game
 */

package highlow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A Hand object that is empty is created.
 * @author Liviu
 */
public class Hand {

    final private List<Card> cardsList = new ArrayList<>();
    
    /**
     *  Create a Hand object that is empty.
     *  @precon: None
     *  @postcon: An empty hand object is created.
     */
    public Hand() {
    }
    
    /**
     * Discard all cards from the hand, making the hand empty.
     * @precon: None.
     * @postcon: The hand object is empty.
     */
    public void clear() {
        cardsList.clear();
    }

    /**
     * If the specified card is in the hand, it is removed.
     * @param c the Card to be removed
     * @precon: c is a Card object and is non-null.
     * @postcon: The specified card is removed if it exists.
     */
    public void removeCard(Card c) {
        if ((null==c)&&(!(c instanceof  Card))) {
            System.out.println("Error: No card to be removed!");
        } else {
            cardsList.remove(c);
        }
    }
    
    /**
     * Add the card c to the hand.
     * @param c the Card to be added.
     * @precon: c is a Card object and is non-null.
     * @postcon: The hand object contains the Card c
     *                          and now has one more card.
     * @throws NullPointerException is thrown 
     */
    public void addCard(Card c) {
        if((null==c)&&(!(c instanceof  Card))) {
            System.out.println("Error: No card to be removed!");
        } else {
            cardsList.add(c);
        }
        
    }
    
    /**
     *  Remove the card in the specified position from the hand
     * @param position The Position of the card that is to be removed,
     *                   where position start from zero
     * @precon: position is valid i.e. 0 &lt; position &lt; number cards
     * @postcon: The card in the specified position is removed and
     * there is one less card in the hand
     * @throws IllegalArgumentException if the position does not exist
     * in the hand
     */
    public void removeCard(int position) {
        cardsList.remove(position);
    }
    
    /**
     * Return the number of cards in the hand.
     * @return integer the number of cards in the hand
     * @precon: none
     * @postcon: No change in the state of Hand
     */
    public int getCardCount() {
        return cardsList.size();
    }
    
    /**Gets the card in a specified position in the hand
     *  ( Note that this card is not removed from the hand ! )
     * @param position the position of the card that is to be returned
     * @return Card the Card at the specified position
     * @throws IllegalArgumentException if position does not exist
     * @precon: postition is valid i.e. 0 &lt; position &lt; number cards.
     * @postcon: The state of the Hand is unchanged
     */
    public Card getCard(int position) {
            return cardsList.get(position);
    }
    
    /** Sorts the cards in the hand in suit order and in value order
     *  within suits. Note that Aces have the lowest value, 1.
     * @precon: None.
     * @postcon: Cards of the same suit are grouped together,
     *      and within a suit, the cards are sorted by value.
     */
    public void sortBySuit() {
        Collections.sort(cardsList, new CardSuitComparator());
    }
    
    /** Sorts the cards in the hand so that cards are sorted into
     *  order of increasing value. Cards with the same value are 
     *  sorted by suit. Note that Aces are considered to have the
     *  lowest value.
     * @precon: None.
     * @postcon: Cards are sorted in order of increasing value.
     */
    public void sortByValue() {
        Collections.sort(cardsList, new CardValueComparator());
    }
}
