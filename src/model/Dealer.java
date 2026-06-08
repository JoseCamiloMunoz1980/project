/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
/**
 *
 * @author pepecamilo
 */
public class Dealer extends Player {

private static final String DEALER_NAME = "Dealer";

public Dealer() {
    super(DEALER_NAME);
}
public Hand getMainHand() {

    return getHands().get(0);
}
    
}
