/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cardprinciplessa;
import java.util.Random;

/**
 *
 * @author pepecamilo
 */
public class GenerateCard {
    
    public static Card[] generate(int n) //num of card
    {
        //random 
        Random random = new Random();
        //range for suit, value - enum
        int numSuit = Card.Suit.values().length;
        int numValues = Card.Value.values().length;
        Card[] hand = new Card[n];
        for(int i=0; i<hand.length;i++)
            {
               Card.Suit s = Card.Suit.values()[random.nextInt(numSuit)];
               Card.Value v = Card.Value.values()[random.nextInt(numValues)];
               Card c = new Card(s,v);
               hand[i]=c;
            }
        return hand;
    }
    
}
