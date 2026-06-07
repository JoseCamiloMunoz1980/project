/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rules;
import model.Card;
import model.Hand;
import model.HandOrigin;
import model.Rank;
/**
 *
 * @author pepecamilo
 */
public final class BlackjackRules {

private BlackjackRules() {
}

public static boolean isNaturalBlackjack(
        Hand hand) {

    if (hand == null) {
        throw new IllegalArgumentException(
                "Hand cannot be null.");
    }

    return hand.getCards().size() == 2
            && hand.getOrigin()
            == HandOrigin.ORIGINAL
            && BlackjackScoreCalculator
                    .calculateScore(hand) == 21;
}

public static boolean isBust(
        Hand hand) {

    if (hand == null) {
        throw new IllegalArgumentException(
                "Hand cannot be null.");
    }

    return BlackjackScoreCalculator
            .calculateScore(hand) > 21;
}

public static boolean canSplit(
        Hand hand) {

    if (hand == null) {
        throw new IllegalArgumentException(
                "Hand cannot be null.");
    }

    if (hand.getCards().size() != 2) {
        return false;
    }

    Card firstCard =
            hand.getCards().get(0);

    Card secondCard =
            hand.getCards().get(1);

    return getSplitValue(
            firstCard.getRank())
            == getSplitValue(
                    secondCard.getRank());
}

private static int getSplitValue(
        Rank rank) {

    switch (rank) {

        case TEN:
        case JACK:
        case QUEEN:
        case KING:
            return 10;

        case ACE:
            return 11;

        case TWO:
            return 2;

        case THREE:
            return 3;

        case FOUR:
            return 4;

        case FIVE:
            return 5;

        case SIX:
            return 6;

        case SEVEN:
            return 7;

        case EIGHT:
            return 8;

        case NINE:
            return 9;

        default:
            throw new IllegalArgumentException(
                    "Invalid rank.");
    }
}

public static Result determineResult(
        Hand playerHand,
        Hand dealerHand) {

    if (playerHand == null) {
        throw new IllegalArgumentException(
                "Player hand cannot be null.");
    }

    if (dealerHand == null) {
        throw new IllegalArgumentException(
                "Dealer hand cannot be null.");
    }

    boolean playerBlackjack =
            isNaturalBlackjack(playerHand);

    boolean dealerBlackjack =
            isNaturalBlackjack(dealerHand);

    if (playerBlackjack
            && dealerBlackjack) {

        return Result.PUSH;
    }

    if (playerBlackjack) {
        return Result.WIN;
    }

    if (dealerBlackjack) {
        return Result.LOSE;
    }

    int playerScore =
            BlackjackScoreCalculator
                    .calculateScore(
                            playerHand);

    int dealerScore =
            BlackjackScoreCalculator
                    .calculateScore(
                            dealerHand);

    if (playerScore > 21) {
        return Result.LOSE;
    }

    if (dealerScore > 21) {
        return Result.WIN;
    }

    if (playerScore > dealerScore) {
        return Result.WIN;
    }

    if (playerScore < dealerScore) {
        return Result.LOSE;
    }

    return Result.PUSH;
}
    
}
