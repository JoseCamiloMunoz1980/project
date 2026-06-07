/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rules;
import model.Card;
import model.Hand;
import model.Rank;
/**
 *
 * @author pepecamilo
 */
public final class BlackjackScoreCalculator {

private BlackjackScoreCalculator() {
}

public static int calculateScore(Hand hand) {

    if (hand == null) {
        throw new IllegalArgumentException(
                "Hand cannot be null.");
    }

    int score = 0;
    int aceCount = 0;

    for (Card card : hand.getCards()) {

        Rank rank = card.getRank();

        switch (rank) {

            case ACE:
                score += 11;
                aceCount++;
                break;

            case JACK:
            case QUEEN:
            case KING:
            case TEN:
                score += 10;
                break;

            case TWO:
                score += 2;
                break;

            case THREE:
                score += 3;
                break;

            case FOUR:
                score += 4;
                break;

            case FIVE:
                score += 5;
                break;

            case SIX:
                score += 6;
                break;

            case SEVEN:
                score += 7;
                break;

            case EIGHT:
                score += 8;
                break;

            case NINE:
                score += 9;
                break;
        }
    }

    while (score > 21 && aceCount > 0) {
        score -= 10;
        aceCount--;
    }

    return score;
}
    
}
