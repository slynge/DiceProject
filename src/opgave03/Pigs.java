package opgave03;

import java.util.Scanner;
import static opgave01.RollTwoDice.rollDice;  // Importing rollDice method from RollTwoDice class
import static opgave03.Player.*;  // Importing constants from the Player enum

public class Pigs {

    // Variables to track game status and statistics
    private static Player playerInTurn = PLAYER_ONE;  // Tracks whose turn it is
    private static int totalPointsPlayerOne = 0;  // Total points for player one
    private static int totalPointsPlayerTwo = 0;  // Total points for player two
    private static int rollCountPlayerOne = 0;  // Number of rolls for player one
    private static int rollCountPlayerTwo = 0;  // Number of rolls for player two
    private static int numberOfTurnsPlayerOne = 0;  // Number of turns for player one
    private static int numberOfTurnsPlayerTwo = 0;  // Number of turns for player two

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Velkommen til spillet, PIGS.");

        // Ask players for the target score to win the game
        System.out.println("Hvad vil I gerne spille til? Vælg et positivt heltal.");
        int winCondition = scanner.nextInt();  // Input winning score

        printRules(winCondition);  // Print game rules
        System.out.println();

        playPigs(winCondition);  // Start the game

        System.out.println();
        System.out.println("Tak for at spille, PIGS.");  // Goodbye message
    }

    /**
     * Prints the rules of the game.
     * @param winCondition The score required to win the game.
     */
    private static void printRules(int winCondition) {
        System.out.println("=====================================================");
        System.out.println("Regler for PIGS");
        System.out.println("To spillere.");
        System.out.println("Første spiller kaster to terninger, indtil han slår en 1'er med en af terningerne " +
                "eller beslutter sig for at stoppe.");
        System.out.println("Hvis han slår en 1'er med én af terningerne, får han ingen point i denne runde.");
        System.out.println("Hvis han slår en 1'er med begge terninger, mistes alle point opnået i tidligere runder.");
        System.out.println("Hvis han beslutter sig for at stoppe inden han slår en 1'er, " +
                "lægges summen af alle hans kast sammen med hans samlede antal point.");
        System.out.println("Derefter går turen videre til den anden spiller.");
        System.out.println("Den første spiller der når " + winCondition + " point vinder.");
        System.out.println("=====================================================");
    }

    /**
     * Main gameplay loop that continues until one player reaches the winning score.
     * @param winCondition The score required to win the game.
     */
    private static void playPigs(int winCondition) {
        // Loop until either player reaches the win condition
        while (totalPointsPlayerOne < winCondition && totalPointsPlayerTwo < winCondition) {
            printPlayerInTurn();  // Show which player is in turn

            playTurn(winCondition);  // Play the current turn for the player

            printStandingsForEachTurn();  // Show current scores after the turn
        }

        printWinnerForTheGame();  // Declare the winner
        printStatisticsForTheGame();  // Show game statistics
    }

    /**
     * Prints the winner of the game based on scores.
     */
    private static void printWinnerForTheGame() {
        if (totalPointsPlayerOne > totalPointsPlayerTwo) {
            System.out.println("SPILLER 1 vandt.");
        } else {
            System.out.println("SPILLER 2 vandt.");
        }
    }

    /**
     * Manages the gameplay for the current player's turn.
     * @param winCondition The score required to win the game.
     */
    private static void playTurn(int winCondition) {
        doRolls(winCondition);  // Roll dice and calculate points for the turn
        switchPlayerInTurn();  // Switch the player after the turn
    }

    /**
     * Handles the rolling of dice and updating points for the current player's turn.
     * @param winCondition The score required to win the game.
     */
    private static void doRolls(int winCondition) {
        int totalPointsThisRound = 0;  // Points earned in this round
        Scanner scanner = new Scanner(System.in);
        System.out.println("Vil du starte med at kaste? ('ja'/'nej')");
        String answer = scanner.nextLine();

        // Continue rolling dice as long as the player chooses to roll
        while (!answer.equals("nej")) {
            int[] faces = rollDice();  // Roll the dice
            int faceOne = faces[0];
            int faceTwo = faces[1];
            int sumOfFaces = faceOne + faceTwo;

            increaseRollCounterForPlayerInTurn();  // Increase the roll count for the current player

            // If both dice show 1, player loses all points from previous rounds
            if (sumOfFaces == 2) {
                System.out.println("Du slog en 1'er på begge terninger i dit kast - " +
                        "du mister derfor alle dine point fra tidligere runder og må ikke slå igen.");
                resetTheTotalPointsForPlayerInTurn();  // Reset all points for the player
                break;
            }
            // If one of the dice shows 1, player loses points from this round
            else if (faceOne == 1 || faceTwo == 1) {
                System.out.println("Du slog en 1'er på en af terningerne i dit kast - " +
                        "du mister derfor alle dine point fra denne runde og må ikke slå igen.");
                totalPointsThisRound = 0;
                break;
            }

            totalPointsThisRound += sumOfFaces;  // Add the dice sum to this round's total points

            // Check if the player has won
            if (isPlayerInTurnWinner(totalPointsThisRound, winCondition)) {
                break;
            }

            System.out.println("Du slog en " + faceOne + " & " + faceTwo + " på dit kast og får derfor " + sumOfFaces +
                    " point. Du har nu " + totalPointsThisRound + " denne runde.");
            System.out.println("Vil du fortsætte? ('ja'/'nej')");
            answer = scanner.nextLine();  // Ask the player if they want to continue rolling
        }

        updateStatistics(totalPointsThisRound);  // Update the player's total score
        increaseTurnCounterForPlayerInTurn();  // Increase the turn count for the player
    }

    /**
     * Switches the turn between Player 1 and Player 2.
     */
    private static void switchPlayerInTurn() {
        // Toggle between PLAYER_ONE and PLAYER_TWO
        playerInTurn = (playerInTurn == PLAYER_ONE) ? PLAYER_TWO : PLAYER_ONE;
    }

    /**
     * Increases the roll counter for the player in turn.
     */
    private static void increaseRollCounterForPlayerInTurn() {
        if (playerInTurn == PLAYER_ONE) {
            rollCountPlayerOne++;
        } else {
            rollCountPlayerTwo++;
        }
    }

    /**
     * Resets the total points of the current player to 0 if they roll two 1's.
     */
    private static void resetTheTotalPointsForPlayerInTurn() {
        if (playerInTurn == PLAYER_ONE) {
            totalPointsPlayerOne = 0;
        } else {
            totalPointsPlayerTwo = 0;
        }
    }

    /**
     * Checks if the current player has won by exceeding or meeting the win condition.
     * @param totalPointsThisRound The total points earned in this round.
     * @param winCondition The score required to win the game.
     * @return True if the player wins, otherwise false.
     */
    private static boolean isPlayerInTurnWinner(int totalPointsThisRound, int winCondition) {
        if (playerInTurn == PLAYER_ONE && totalPointsThisRound + totalPointsPlayerOne >= winCondition) {
            return true;
        } else if (playerInTurn == PLAYER_TWO && totalPointsThisRound + totalPointsPlayerTwo >= winCondition) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Updates the total points for the player in turn after their round.
     * @param points The points earned this round.
     */
    private static void updateStatistics(int points) {
        if (playerInTurn == PLAYER_ONE) {
            totalPointsPlayerOne += points;
        } else {
            totalPointsPlayerTwo += points;
        }
    }

    /**
     * Increases the turn counter for the player in turn.
     */
    private static void increaseTurnCounterForPlayerInTurn() {
        if (playerInTurn == PLAYER_ONE) {
            numberOfTurnsPlayerOne++;
        } else {
            numberOfTurnsPlayerTwo++;
        }
    }

    /**
     * Prints the current standings of the game after each turn.
     * Displays the total points accumulated by each player so far.
     */
    private static void printStandingsForEachTurn() {
        System.out.println("\nStillingen efter turen:");
        System.out.println("-------");
        System.out.printf("%17s %4d\n", "Antal point for SPILLER 1:", totalPointsPlayerOne);
        System.out.printf("%17s %4d\n", "Antal point for SPILLER 2:", totalPointsPlayerTwo);
    }

    /**
     * Prints which player is currently in turn.
     */
    private static void printPlayerInTurn() {
        if (playerInTurn == PLAYER_ONE) {
            System.out.printf("\nDet er SPILLER 1's tur.\n");
        }
        else {
            System.out.printf("\nDet er SPILLER 2's tur.\n");
        }
    }

    /**
     * Prints the statistics for the entire game.
     * Displays the average number of rolls per turn for each player.
     */
    private static void printStatisticsForTheGame() {
        System.out.println("\nStatistikker for spillet:");
        System.out.println("-------");

        // Check if Player 1 has played any turns
        if (numberOfTurnsPlayerOne == 0) {
            System.out.printf("%17s %4d\n", "Gennemsnit af kast SPILLER 1 har lavet:", 0);
        }
        else {
            System.out.printf("%17s %4f\n", "Gennemsnit af kast SPILLER 1 har lavet:", (double) rollCountPlayerOne / numberOfTurnsPlayerOne);
        }

        // Check if Player 2 has played any turns
        if (numberOfTurnsPlayerTwo == 0) {
            System.out.printf("%17s %4d\n", "Gennemsnit af kast SPILLER 2 har lavet:", 0);
        }
        else {
            System.out.printf("%17s %4f\n", "Gennemsnit af kast SPILLER 2 har lavet:", (double) rollCountPlayerTwo / numberOfTurnsPlayerTwo);
        }
    }

}
