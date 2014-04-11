/*
 * HighLow game developed for training
 * Deck class used in the game
 */

package highlow;

import java.util.Collections;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * An object of type Deck is used in the game. It contains 52 shuffled Cards
 * @author Liviu
 */
public class Deck {
    
    private final int deckSize = 52;
    private final List<Card> deckOfCards = new ArrayList<>(deckSize);
    private Card card;
    
    /** Constructor. Create a shuffled deck of cards
     * @precon : None
     * @postcon: A deck of 52, shuffled cards is created
     */
    public Deck() {
        for(int suit = 0; suit <4; suit++) {
            for(int value=1; value<14; value++) {
                deckOfCards.add(new Card(value, suit));
            }
        }
        Collections.shuffle(deckOfCards);
        
    }
    
    /**
     * Shuffle all the cards in the deck into a random order
     */
    public void shuffle() {
        Collections.shuffle(deckOfCards);
    }
    
    /**
     *  Returns the size of the deck
     * @return the number of cards that are still left in the deck
     */
    public int size() {
        return deckOfCards.size();
    }
    
    /**
     * Determine if this deck is empty
     * @return true if this deck has no cards left in the deck
     */
    public boolean isEmpty() {
        return deckOfCards.isEmpty();
    }
    
    /**
     * Deal one card from this deck
     * @return a Card from the deck
     */
    public Card dealCard() {
        if(!deckOfCards.isEmpty()) {
            Iterator deckIterator = deckOfCards.iterator();
                while(deckIterator.hasNext()) {
                    card = (Card) deckIterator.next();
                }
            deckOfCards.remove(card);
        }
        return card;
    }
}
