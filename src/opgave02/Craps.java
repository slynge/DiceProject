package opgave02;

import java.util.Scanner;
import static opgave01.RollTwoDice.rollDice;

public class Craps {

    // Variables to keep track of game statistics
    private static int numberOfGamesWon = 0;       // Total number of games won
    private static int numberOfGamesLost = 0;      // Total number of games lost

    public static void main(String[] args) {
        // Welcome message
        System.out.println("Velkommen til spillet, CRAPS.");
        printRules();  // Print the rules of the game
        System.out.println();

        playCraps();  // Start the game loop

        printStatisticsForAllGames();  // Display statistics for all games played

        System.out.println();
        System.out.println("Tak for at spille, RUL EN TERNING.");  // Goodbye message
    }

    /**
     * Prints the rules of the game to the console.
     */
    private static void printRules() {
        System.out.println("=====================================================");
        System.out.println("Regler for CRAPS");
        System.out.println("Spilleren ruller to terninger.");
        System.out.println("Udfaldet af et kast er summen af de to terningers øjne.");
        System.out.println("Det første kast kaldes 'come out roll'.");
        System.out.println("Spilleren vinder med det samme, hvis det første kast er 7 eller 11.");
        System.out.println("Spilleren taber med det samme, hvis det første kast er 2, 3 eller 12.");
        System.out.println("Hvis spillerens første kast er 4, 5, 6, 8, 9 eller 10, etableres dette tal som spillerens 'point'.");
        System.out.println("Spilleren bliver derefter ved med at kaste, indtil han enten kaster sit 'point' igen eller kaster 7.");
        System.out.println("Kaster han 7, har han tabt. Kaster han sit 'point', har han vundet.");
        System.out.println("=====================================================");
    }

    /**
     * Manages the logic of the Craps game. Rolls the dice, checks the first roll,
     * and determines whether the player wins or needs to continue rolling.
     */
    private static void playCraps() {
        // Roll two dice and store the results
        int[] diceArray = rollDice();  // Use the rollDice method from RollTwoDice class

        int point = diceArray[0] + diceArray[1];  // Calculate the sum of the two dice as the points

        checkFirstRoll(point);  // Check the outcome of the first roll

        playAgain();  // Ask if the player wants to play again
    }

    /**
     * Checks the outcome of the first roll (the "come out roll").
     * Determines if the player wins, loses, or sets a point to roll for.
     * @param points the sum of the first roll (two dice).
     */
    private static void checkFirstRoll(int points) {
        // Player wins instantly if the first roll is 7 or 11
        if (points == 7 || points == 11) {
            System.out.println("Du slog " + points + " og vandt derfor.");
            numberOfGamesWon++;  // Increment the number of games won
        }
        // Player loses instantly if the first roll is 2, 3, or 12
        else if (points == 2 || points == 3 || points == 12) {
            System.out.println("Du slog " + points + " og tabte derfor.");
            numberOfGamesLost++;  // Increment the number of games lost
        }
        // Otherwise, the player's point is set, and they need to roll again
        else {
            System.out.println("Du slog " + points + ", så dit point er " + points + " og du skal derfor slå igen.");
            rollForPoint(points);  // Roll for the player's point
        }
    }

    /**
     * Continuously rolls the dice until the player either rolls their point again (win)
     * or rolls a 7 (lose).
     * @param point the player's point to roll for.
     * @return true if the player wins, false if they lose.
     */
    private static boolean rollForPoint(int point) {

        while(true) {  // Keep rolling until the game is decided
            int[] newDiceArray = rollDice();  // Roll the dice again
            int newPoint = newDiceArray[0] + newDiceArray[1];  // Calculate the new sum

            // If the player rolls a 7, they lose
            if (newPoint == 7) {
                System.out.println("Du har desværre slået 7 og tabte.");
                numberOfGamesLost++;  // Increment the number of games lost
                return false;  // Player loses the game
            }
            // If the player rolls their point again, they win
            else if (newPoint == point) {
                System.out.println("Tillykke, du har slået " + point + " som var dit tidligere point. Du har derfor vundet!");
                numberOfGamesWon++;  // Increment the number of games won
                return true;  // Player wins the game
            }
            // Otherwise, the player needs to roll again
            else {
                System.out.println("Du slog " + newPoint + " og skal slå igen.");
            }
        }
    }

    /**
     * Asks the player if they want to play again. If yes, restarts the game.
     */
    private static void playAgain() {
        Scanner input = new Scanner(System.in);
        System.out.println("Vil du spille igen? ('ja'/'nej')");  // Ask if the player wants to play again
        String answer = input.nextLine();
        if(answer.equals("ja")) {
            playCraps();  // Restart the game if the player says "ja"
        }
        // If the player says "nej", the program exits automatically
    }


    /**
     * Prints the total number of games won and lost.
     */
    private static void printStatisticsForAllGames() {
        System.out.println("\nResults:");
        System.out.println("-------");
        // Print number of games won and lost
        System.out.printf("%17s %4d\n", "Antal vundet spil:", numberOfGamesWon);
        System.out.printf("%17s %4d\n", "Antal tabte spil:", numberOfGamesLost);
    }
}
