/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;
import java.util.Scanner;
import game.BlackjackGameManager;
import model.Hand;
import rules.BlackjackScoreCalculator;
import model.HandStatus;
import model.Player;
import rules.BlackjackRules;
/**
 *
 * @author pepecamilo
 */
public class Menu {

    private final Scanner scanner;

    private BlackjackGameManager gameManager;

    public Menu() {

        this.scanner = new Scanner(System.in);
    }

    public void start() {

        boolean exit = false;

        while (!exit) {

            showMainMenu();

            int option = readMenuOption(1,3);

            switch (option) {

                case 1:
                    createNewTable();
                    break;

                case 2:
                    showRules();
                    break;

                case 3:
                    exit = true;
                    System.out.println(
                            "Thank you for playing Blackjack.");
                    break;

                default:
                    System.out.println(
                            "Invalid option.");
            }
        }
    }

    private void showMainMenu() {

        System.out.println(
                "====================");
        System.out.println(
                "BLACKJACK");
        System.out.println(
                "====================");
        System.out.println(
                "1. New Table");
        System.out.println(
                "2. Game Rules");
        System.out.println(
                "3. Exit");
        System.out.print(
                "Option: ");
    }

    private void createNewTable() {

    int playerCount;

    do {
        
        System.out.print(
                "Enter number of players (1-7): ");

        playerCount = readInt();

        if (playerCount < 1
                || playerCount > 7) {

            System.out.println(
                    "Invalid number of players.");
        }

    } while (playerCount < 1
            || playerCount > 7);

    gameManager =
            new BlackjackGameManager(
                    playerCount);
    
    System.out.println(
            "Table created successfully.");
    System.out.println(
            "Players: "
                    + playerCount);
    System.out.println(
            "Decks: 8");

    showTableMenu();
}

    private void showRules() {
        
        System.out.println(
                "====================");
        System.out.println(
                "BLACKJACK GAME RULES");
        System.out.println(
                "====================");

        System.out.println(
                "OBJECTIVE");
        System.out.println(
                "Get as close to 21 as possible without going over.");

        System.out.println();

        System.out.println(
                "CARD VALUES");
        System.out.println(
                "- Number cards are worth their face value.");
        System.out.println(
                "- J, Q and K are worth 10.");
        System.out.println(
                "- An Ace is worth 1 or 11.");

        System.out.println();

        System.out.println(
                "PLAYER ACTIONS");
        System.out.println(
                "- Hit");
        System.out.println(
                "- Stand");
        System.out.println(
                "- Split");

        System.out.println();

        System.out.println(
                "SPLIT RULES");
        System.out.println(
                "- One re-split is allowed.");
        System.out.println(
                "- Maximum of four hands.");
        System.out.println(
                "- Split Aces receive one card only.");

        System.out.println();

        System.out.println(
                "DEALER RULES");
        System.out.println(
                "- Dealer stands on all 17s.");

        System.out.println();

        System.out.println(
                "TABLE CONFIGURATION");
        System.out.println(
                "- 1 to 7 players.");
        System.out.println(
                "- An 8-deck shoe is used.");

        System.out.println();
    }

    private int readInt() {

        while (!scanner.hasNextInt()) {

            scanner.next();

            System.out.print(
                    "Enter a valid number: ");
        }

        int value = scanner.nextInt();

        scanner.nextLine();

        return value;
    }
    
    private int readMenuOption(
        int min,
        int max) {

    int option;

    do {

        option = readInt();

        if (option < min
                || option > max) {

            System.out.print(
                    "Invalid option. Try again: ");
        }

    } while (option < min
            || option > max);

    return option;
}
    
    private void showTableMenu() {

    boolean backToMainMenu = false;

    while (!backToMainMenu) {

        System.out.println(
                "====================");
        System.out.println(
                "TABLE READY");
        System.out.println(
                "====================");
        System.out.println(
                "1. Start First Round");
        System.out.println(
                "2. Main Menu");
        System.out.print(
                "Option: ");

        int option =
                readMenuOption(
                        1,
                        2);

        switch (option) {

            case 1:

                gameManager.startRound();

                playRound();

                backToMainMenu = true;

                break;

            case 2:

                backToMainMenu = true;

                break;

            default:
                break;
        }
    }
}
    
    private void displayCurrentTable() {

    System.out.println(
            "====================");
    System.out.println(
            "CURRENT TABLE");
    System.out.println(
            "====================");

    displayDealer();

    for (int i = 0;
            i < gameManager
                    .getPlayers()
                    .size();
            i++) {

        System.out.println(
                "--------------------");

        System.out.println(
                "Player "
                        + (i + 1));

        var player =
                gameManager
                        .getPlayers()
                        .get(i);

        for (int handIndex = 0;
                handIndex < player
                        .getHands()
                        .size();
                handIndex++) {

            displayHand(
                    player.getHands()
                            .get(handIndex),
                    handIndex + 1);
        }
    }
}
    
    private void displayDealer() {

    System.out.println(
            "Dealer");

    Hand dealerHand =
        gameManager
                .getDealer()
                .getMainHand();

    if (dealerHand
            .getCards()
            .size() >= 2) {

        System.out.println(
                "[Hidden Card]");

        System.out.println(
                dealerHand
                        .getCards()
                        .get(1));
    }
}
    
    private void displayHand(Hand hand,int handNumber) {

    System.out.println("Hand "+ handNumber);

    for (var card : hand.getCards()) {
        System.out.println(
                card);
    }

    System.out.println("Score: "+ BlackjackScoreCalculator.calculateScore(hand));
}
    
    private void playPlayerTurns() {

    for (Player player : gameManager.getPlayers()) {

        int handIndex = 0;

        while (handIndex < player.getHands().size()) {

            Hand hand = player.getHands().get(handIndex);

            playHand(
                    player,
                    hand,
                    handIndex + 1);

            handIndex++;
        }
    }
}
    
    private void playHand(
        Player player,
        Hand hand,
        int handNumber) {

    if (hand.getStatus()
            != HandStatus.ACTIVE) {

        return;
    }

    if (BlackjackRules
            .isNaturalBlackjack(hand)) {

        System.out.println(player.getName()
                        + " - Hand "
                        + handNumber
                        + " has a Natural Blackjack.");

        hand.setStatus(
                HandStatus.STAND);

        return;
    }

    boolean playing = true;

    while (playing
            && hand.getStatus()
            == HandStatus.ACTIVE) {

        System.out.println(
                "====================");

        System.out.println(
                player.getName()
                        + " - Hand "
                        + handNumber);

        System.out.println(
                "====================");

        displayHand(
                hand,
                handNumber);

        boolean canSplit =
        BlackjackRules.canSplit(hand)
        && player.getHands().size() < 4;

        System.out.println(
                "1. Hit");

        System.out.println(
                "2. Stand");

        if (canSplit) {

            System.out.println(
                    "3. Split");
        }

        System.out.print(
                "Option: ");

        int option =
                readMenuOption(
                        1,
                        canSplit ? 3 : 2);

        switch (option) {

            case 1:

                gameManager
                        .processHit(
                                hand);

                displayCurrentTable();

                if (hand.getStatus()
                        == HandStatus.BUST) {

                    System.out.println(
                            "Bust.");

                    playing = false;
                }

                break;

            case 2:

                gameManager
                        .processStand(
                                hand);

                playing = false;

                break;

            case 3:

                if (canSplit) {

                    gameManager.processSplit(player,hand);

                    displayCurrentTable();

                    playing = false;
                }

                break;

            default:
                break;
        }
    }
}
    
   private void playRound() {

    displayCurrentTable();

    playPlayerTurns();

    gameManager.playDealerTurn();

    if (!allPlayerHandsBust()) {

        showDealerFinalHand();
    }

    showResults();

    showRoundMenu();
}
   
   private void showDealerFinalHand() {

    System.out.println(
            "====================");

    System.out.println(
            "DEALER FINAL HAND");

    System.out.println(
            "====================");

    Hand dealerHand =
        gameManager
                .getDealer()
                .getMainHand();

    for (var card :
            dealerHand.getCards()) {

        System.out.println(card);
    }

    System.out.println(
            "Score: "
            + BlackjackScoreCalculator
                    .calculateScore(
                            dealerHand));
}
   
   private void showResults() {

    System.out.println(
            "====================");

    System.out.println(
            "ROUND RESULTS");

    System.out.println(
            "====================");

    for (Player player :
            gameManager.getPlayers()) {

        int handNumber = 1;

        for (Hand hand :
                player.getHands()) {

            System.out.println();

            System.out.println(
        player.getName()
        + " - Hand "
        + handNumber);

        System.out.println(
                "Score: "
                + BlackjackScoreCalculator
                        .calculateScore(
                                hand));

        System.out.println(
                "Result: "
                + gameManager
                        .determineResult(
                                hand));

            handNumber++;
        }
    }
}
   
  private void showRoundMenu() {

    boolean exitMenu = false;

    while (!exitMenu) {

        System.out.println(
                "====================");

        System.out.println(
                "ROUND COMPLETED");

        System.out.println(
                "====================");

        System.out.println(
                "1. Play Another Round");

        System.out.println(
                "2. New Table");

        System.out.println(
                "3. Main Menu");

        System.out.println(
                "4. Exit");

        System.out.print(
                "Option: ");

        int option =
                readMenuOption(
                        1,
                        4);

        switch (option) {

            case 1:

                gameManager.startRound();

                playRound();

                exitMenu = true;

                break;

            case 2:

                createNewTable();

                exitMenu = true;

                break;

            case 3:

                exitMenu = true;

                break;

            case 4:

                System.out.println(
                        "Thank you for playing Blackjack.");

                System.exit(0);

                break;

            default:
                break;
        }
    }
} 
  
  private boolean allPlayerHandsBust() {

    for (Player player :
            gameManager.getPlayers()) {

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
    
}
