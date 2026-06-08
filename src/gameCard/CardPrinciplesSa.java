/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gameCard;

/**
 *
 * @author pepecamilo
 */
public class CardPrinciplesSa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Card[] hand = GenerateCard.generate(10);
        for(Card c : hand){
            System.out.println(c.getSuit()+" "+c.getValue());
        }
    }
    
}
