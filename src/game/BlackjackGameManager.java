/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Dealer;
import model.Deck;
import model.Hand;
import model.HandOrigin;
import model.HandStatus;
import model.HumanPlayer;
import model.Rank;
import rules.BlackjackRules;
import rules.BlackjackScoreCalculator;
import rules.Result;
/**
 *
 * @author pepecamilo
 */
public class BlackjackGameManager {

private final List<HumanPlayer> players;
private final Dealer dealer;
private final Deck deck;

public BlackjackGameManager(
        int playerCount) {

    if (playerCount < 1 || playerCount > 7) {
        throw new IllegalArgumentException(
                "Player count must be between 1 and 7.");
    }

    this.players = new ArrayList<>();

    for (int i = 1; i <= playerCount; i++) {

        players.add(
                new HumanPlayer(
                        "Player " + i));
    }

    this.dealer = new Dealer();

    this.deck = new Deck(8);

    deck.shuffle();
}

public void startRound() {

    if (!deck.hasEnoughCardsForRound(
            players.size())) {

        throw new IllegalStateException(
                "Not enough cards to start a new round.");
    }

    resetRound();

    createInitialHands();

    dealInitialCards();
}

private void createInitialHands() {

    for (HumanPlayer player : players) {

        player.addHand(
                new Hand(
                        HandOrigin.ORIGINAL));
    }

    dealer.addHand(
            new Hand(
                    HandOrigin.ORIGINAL));
}

private void dealInitialCards() {

    for (int i = 0; i < 2; i++) {

        for (HumanPlayer player : players) {

            player.getHands()
                    .get(0)
                    .addCard(
                            deck.drawCard());
        }

        dealer.getHands()
                .get(0)
                .addCard(
                        deck.drawCard());
    }
}

public void processHit(
        Hand hand) {

    if (hand == null) {
        throw new IllegalArgumentException(
                "Hand cannot be null.");
    }

    if (hand.getStatus()
            != HandStatus.ACTIVE) {

        return;
    }

    if (hand.isSplitAces()) {
        return;
    }

    if (BlackjackRules
            .isNaturalBlackjack(hand)) {

        return;
    }

    hand.addCard(
            deck.drawCard());

    if (BlackjackRules.isBust(hand)) {

        hand.setStatus(
                HandStatus.BUST);
    }
}

public void processStand(
        Hand hand) {

    if (hand == null) {
        throw new IllegalArgumentException(
                "Hand cannot be null.");
    }

    hand.setStatus(
            HandStatus.STAND);
}

public void processSplit(
        HumanPlayer player,
        Hand hand) {

    if (player == null) {
        throw new IllegalArgumentException(
                "Player cannot be null.");
    }

    if (hand == null) {
        throw new IllegalArgumentException(
                "Hand cannot be null.");
    }

    if (!BlackjackRules.canSplit(hand)) {
        throw new IllegalStateException(
                "Hand cannot be split.");
    }

    boolean splitAces =
            hand.getCards()
                    .get(0)
                    .getRank()
                    == Rank.ACE;

    Hand firstHand =
            new Hand(
                    HandOrigin.SPLIT);

    Hand secondHand =
            new Hand(
                    HandOrigin.SPLIT);

    firstHand.addCard(
            hand.getCards().get(0));

    secondHand.addCard(
            hand.getCards().get(1));

    firstHand.addCard(
            deck.drawCard());

    secondHand.addCard(
            deck.drawCard());

    if (splitAces) {

        firstHand.setSplitAces(true);
        secondHand.setSplitAces(true);

        firstHand.setStatus(
                HandStatus.STAND);

        secondHand.setStatus(
                HandStatus.STAND);
    }

    player.removeHand(hand);

    player.addHand(firstHand);

    player.addHand(secondHand);
}

public void playDealerTurn() {

    if (allPlayerHandsBust()) {
        return;
    }

    Hand dealerHand =
            dealer.getHands().get(0);

    while (!BlackjackRules
            .isBust(dealerHand)) {

        int score =
                BlackjackScoreCalculator
                        .calculateScore(
                                dealerHand);

        if (score >= 17) {

            dealerHand.setStatus(
                    HandStatus.STAND);

            break;
        }

        dealerHand.addCard(
                deck.drawCard());
    }

    if (BlackjackRules
            .isBust(dealerHand)) {

        dealerHand.setStatus(
                HandStatus.BUST);
    }
}

private boolean allPlayerHandsBust() {

    for (HumanPlayer player : players) {

        for (Hand hand :
                player.getHands()) {

            if (hand.getStatus()
                    != HandStatus.BUST) {

                return false;
            }
        }
    }

    return true;
}

public Result determineResult(
        Hand playerHand) {

    if (playerHand == null) {
        throw new IllegalArgumentException(
                "Player hand cannot be null.");
    }

    return BlackjackRules
            .determineResult(
                    playerHand,
                    dealer.getHands().get(0));
}

public void resetRound() {

    for (HumanPlayer player : players) {
        player.resetForNewRound();
    }

    dealer.resetForNewRound();
}

public List<HumanPlayer> getPlayers() {

    return Collections
            .unmodifiableList(
                    players);
}

public Dealer getDealer() {
    return dealer;
}

public Deck getDeck() {
    return deck;
}
    
}
