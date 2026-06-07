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
public class Deck {

private final List<Card> cards;

private final int numberOfDecks;

public Deck(int numberOfDecks) {

    if (numberOfDecks != 1
            && numberOfDecks != 2
            && numberOfDecks != 4
            && numberOfDecks != 6
            && numberOfDecks != 8) {

        throw new IllegalArgumentException(
                "Number of decks must be 1, 2, 4, 6, or 8.");
    }

    this.numberOfDecks = numberOfDecks;
    this.cards = new ArrayList<>();

    createDeck();
}

private void createDeck() {

    for (int deck = 0; deck < numberOfDecks; deck++) {

        for (Suit suit : Suit.values()) {

            for (Rank rank : Rank.values()) {

                cards.add(
                        new Card(suit, rank));
            }
        }
    }
}

public void shuffle() {
    Collections.shuffle(cards);
}

public Card drawCard() {

    if (cards.isEmpty()) {
        throw new IllegalStateException(
                "No cards remaining in the deck.");
    }

    return cards.remove(0);
}

public boolean hasEnoughCardsForRound(
        int playerCount) {

    if (playerCount < 1
            || playerCount > 7) {

        throw new IllegalArgumentException(
                "Player count must be between 1 and 7.");
    }

    int requiredCards =
            (playerCount * 2) + 2;

    return cards.size() >= requiredCards;
}

public int getRemainingCards() {
    return cards.size();
}

public int getNumberOfDecks() {
    return numberOfDecks;
}
    
}
