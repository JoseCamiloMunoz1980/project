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
public abstract class Player {

private static final int MAX_HANDS = 4;

private final String name;

private final List<Hand> hands;

public Player(String name) {

    if (name == null || name.isBlank()) {
        throw new IllegalArgumentException(
                "Player name cannot be null or blank.");
    }

    this.name = name;
    this.hands = new ArrayList<>();
}

public String getName() {
    return name;
}

public List<Hand> getHands() {
    return Collections.unmodifiableList(hands);
}

public void addHand(Hand hand) {

    if (hand == null) {
        throw new IllegalArgumentException(
                "Hand cannot be null.");
    }

    if (hands.size() >= MAX_HANDS) {
        throw new IllegalStateException(
                "Maximum number of hands reached.");
    }

    hands.add(hand);
}

public void removeHand(Hand hand) {

    if (hand == null) {
        throw new IllegalArgumentException(
                "Hand cannot be null.");
    }

    hands.remove(hand);
}

public void resetForNewRound() {
    hands.clear();
}
}
