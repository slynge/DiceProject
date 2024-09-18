package examples;

import java.util.Arrays;
import java.util.Scanner;

public class RollTwoDice {
    private static int rollCount = 0;
    private static int totalSumOfEyes = 0;
    private static int pairCount = 0;
    private static int highestSumOfEyes = 0;
    private static int[] diceCounts = new int[6];

    public static void main(String[] args) {
        System.out.println("Velkommen til spillet, rul en terning.");
        printRules();
        System.out.println();

        playTwoDice();

        System.out.println();
        System.out.println("Tak for at spille, rul en terning.");
    }

    private static void printRules() {
        System.out.println("=====================================================");
        System.out.println("Regler for rul en terning");
        System.out.println("Spilleren ruller en terning, så længde man lyster.");
        System.out.println("=====================================================");
    }

    private static void playTwoDice() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Rul en terning? ('ja/nej') ");
        String answer = scanner.nextLine();
        while (!answer.equals("nej")) {
            int[] faces = rollDice();
            System.out.println("Du rullede: " + faces[0] + " & " + faces[1]);
            System.out.println();

            updateStatistics(faces);

            System.out.print("Rul en terning? ('ja/nej') ");
            answer = scanner.nextLine();
        }

        printStatistics();
        scanner.close();
    }

    private static int[] rollDice() {
        int[] diceArray = new int[2];

        diceArray[0] = (int) (Math.random() * 6 + 1);
        diceArray[1] = (int) (Math.random() * 6 + 1);

        return diceArray;
    }

    private static void updateStatistics(int[] faces) {
        int dieOne = faces[0];
        int dieTwo = faces[1];

        int sumOfEyes = dieOne + dieTwo;
        rollCount++;
        totalSumOfEyes += sumOfEyes;

        if(dieOne == dieTwo) {
            pairCount++;
        }

        if(sumOfEyes > highestSumOfEyes) {
            highestSumOfEyes = sumOfEyes;
        }

        diceCounts[dieOne - 1]++;
        diceCounts[dieTwo - 1]++;

    }

    private static void printStatistics() {
        System.out.println("\nResults:");
        System.out.println("-------");
        System.out.printf("%17s %4d\n", "Antal rul:", rollCount);
        System.out.printf("%17s %4d\n", "Antal par:", pairCount);
        System.out.printf("%17s %4d\n", "Højeste sum:", highestSumOfEyes);
        System.out.printf("%17s %4d\n", "Totale sum:", totalSumOfEyes);
        System.out.printf("%17s %4s\n", "Frekvens af kast:", Arrays.toString(diceCounts));
    }

}
