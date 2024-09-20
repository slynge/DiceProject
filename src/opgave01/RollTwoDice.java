package opgave01;

import examples.RollOneDie;

import java.util.Arrays;
import java.util.Scanner;

import static examples.RollOneDie.rollDie;

public class RollTwoDice {
    // Variables to keep track of game statistics
    private static int rollCount = 0;         // Total number of rolls
    private static int totalSumOfEyes = 0;    // Total sum of the dice rolled
    private static int pairCount = 0;         // Number of pairs rolled (when both dice show the same value)
    private static int highestSumOfEyes = 0;  // Highest sum of the two dice rolled in a single turn
    private static int[] diceCounts = new int[6];  // Array to track the frequency of each dice face (1-6)

    public static void main(String[] args) {
        // Welcome message
        System.out.println("Velkommen til spillet, RUL TO TERNINGER.");
        printRules();  // Print the rules of the game
        System.out.println();

        playTwoDice();  // Start the game loop

        System.out.println();
        System.out.println("Tak for at spille, RUL TO TERNINGER.");  // Goodbye message
    }

    /**
     * Prints the rules of the game to the console.
     */
    private static void printRules() {
        System.out.println("=====================================================");
        System.out.println("Regler for RUL TO TERNINGER");
        System.out.println("Spilleren ruller to terninger, så længe man lyster.");
        System.out.println("=====================================================");
    }

    /**
     * Manages the game loop, where the player can keep rolling dice until they choose to stop.
     */
    private static void playTwoDice() {
        Scanner scanner = new Scanner(System.in);  // Scanner to take user input
        System.out.print("Rul en terning? ('ja/nej') ");
        String answer = scanner.nextLine();  // Player's response

        // Loop allowing the player to roll the dice repeatedly until they say "nej" (no)
        while (!answer.equals("nej")) {
            int[] faces = rollDice();  // Roll two dice
            System.out.println("Du rullede: " + faces[0] + " & " + faces[1]);  // Print the result of the roll
            System.out.println();

            updateStatistics(faces);  // Update game statistics based on the roll

            // Ask the player if they want to roll again
            System.out.print("Rul en terning? ('ja/nej') ");
            answer = scanner.nextLine();
        }

        printStatistics();  // Display final game statistics
        scanner.close();  // Close the scanner resource
    }

    /**
     * Simulates rolling two dice by generating two random numbers between 1 and 6.
     * @return an array containing the results of the two dice rolls.
     */
    public static int[] rollDice() {
        int[] diceArray = new int[2];  // Array to store two dice results

        // Generate random values between 1 and 6 for both dice
        diceArray[0] = rollDie();
        diceArray[1] = rollDie();

        return diceArray;  // Return the result of the dice rolls
    }

    /**
     * Updates the statistics of the game based on the result of the latest dice roll.
     * @param faces the results of the two dice rolls.
     */
    private static void updateStatistics(int[] faces) {
        int dieOne = faces[0];  // Value of the first die
        int dieTwo = faces[1];  // Value of the second die

        int sumOfEyes = dieOne + dieTwo;  // Sum of the two dice
        rollCount++;  // Increment the total number of rolls
        totalSumOfEyes += sumOfEyes;  // Add the sum of the current roll to the total sum

        // Check if both dice show the same value (i.e., a pair)
        if (dieOne == dieTwo) {
            pairCount++;  // Increment pair count
        }

        // Check if the current sum is the highest so far
        if (sumOfEyes > highestSumOfEyes) {
            highestSumOfEyes = sumOfEyes;
        }

        // Update the frequency count for the faces of both dice
        diceCounts[dieOne - 1]++;
        diceCounts[dieTwo - 1]++;
    }

    /**
     * Prints the final statistics after the player has finished rolling the dice.
     */
    private static void printStatistics() {
        System.out.println("\nResults:");
        System.out.println("-------");
        // Displaying various statistics
        System.out.printf("%17s %4d\n", "Antal rul:", rollCount);  // Total number of rolls
        System.out.printf("%17s %4d\n", "Antal par:", pairCount);  // Total number of pairs rolled
        System.out.printf("%17s %4d\n", "Højeste sum:", highestSumOfEyes);  // Highest sum rolled
        System.out.printf("%17s %4d\n", "Totale sum:", totalSumOfEyes);  // Total sum of all rolls
        // Display the frequency of each dice face (1-6)
        System.out.printf("%17s %4s\n", "Frekvens af kast:", Arrays.toString(diceCounts));
    }
}
