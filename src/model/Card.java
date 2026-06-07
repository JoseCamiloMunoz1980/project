/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author pepecamilo
 */
public class Card {
    private final Suit suit;
    private final Rank rank; 
    public Card(Suit suit, Rank rank) { 
        if (suit == null) { 
            throw new IllegalArgumentException( "Suit cannot be null.");
        } if (rank == null) { 
            throw new IllegalArgumentException( "Rank cannot be null."); 
        } 
        this.suit = suit; 
        this.rank = rank; 
    } 
    public Suit getSuit() {
        return suit; 
    } 
    public Rank getRank() {
        return rank; 
    } 
    @Override 
    public String toString() {
        return rank + " of " + suit; 
    }
}
