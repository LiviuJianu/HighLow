/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package highlow;

import java.util.Comparator;

/**
 *
 * @author Liviu
 */
public class CardValueComparator implements Comparator<Card>{

    @Override
    public int compare(Card c1, Card c2) {
        return c1.getValue() - c2.getValue();
    }


    
}
