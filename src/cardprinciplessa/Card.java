/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cardprinciplessa;

/**
 *
 * @author pepecamilo
 */
public class Card {
    
    public enum Suit {HEARTS, SPADES, CLUBS,DIAMONDS}
    public enum Value{ACE,TWO,THREE,FOUR,FIVE,SIX,SEVENEIGHT,NINE,TEN,JACK,QUEEN,KING}
    
    private Value value;
    private Suit suit;
    
    public Card(Suit s, Value v){
        
        this.suit=s;
        this.value=v;
        
    }

    /**
     * @return the value
     */
    public Value getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Value value) {
        this.value = value;
    }

    /**
     * @return the suit
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * @param suit the suit to set
     */
    public void setSuit(Suit suit) {
        this.suit = suit;
    }
    
}
