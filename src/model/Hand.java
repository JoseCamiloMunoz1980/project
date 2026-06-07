/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author pepecamilo
 */
public class Hand {

private final List<Card> cards;

private HandStatus status;

private final HandOrigin origin;

private boolean splitAces;

public Hand(HandOrigin origin) {

    if (origin == null) {
        throw new IllegalArgumentException(
                "Origin cannot be null.");
    }

    this.cards = new ArrayList<>();
    this.status = HandStatus.ACTIVE;
    this.origin = origin;
    this.splitAces = false;
}

public void addCard(Card card) {

    if (card == null) {
        throw new IllegalArgumentException(
                "Card cannot be null.");
    }

    cards.add(card);
}

public List<Card> getCards() {
    return Collections.unmodifiableList(cards);
}

public HandStatus getStatus() {
    return status;
}

public void setStatus(HandStatus status) {

    if (status == null) {
        throw new IllegalArgumentException(
                "Status cannot be null.");
    }

    this.status = status;
}

public HandOrigin getOrigin() {
    return origin;
}

public boolean isSplitAces() {
    return splitAces;
}

public void setSplitAces(
        boolean splitAces) {

    this.splitAces = splitAces;
}
}
