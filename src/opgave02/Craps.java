package opgave02;
import java.awt.*;
import java.util.Scanner;
import static opgave01.RollTwoDice.rollDice;

// 01) Lav en klasse kaldt Craps
public class Craps {

    public static int SumOfDice = 0;
    public static int newPoint = 0;
    public static int numberOfGamesWon = 0;
    public static int numberOfGamesLost = 0;

    //Main method
    public static void main(String[] args) {
        playCraps();

        printStatisticsForAllGames();
    }

    public static void playCraps() {

        // to terninger tilgives et tilfældigt værdig mellem 1 0g 6 og gemmes i et array, derefter lægges sammen
        int[] diceArray = rollDice();

        SumOfDice = diceArray[0] + diceArray[1];

        checkFirstRoll(SumOfDice);

        playAgain();
    }

    private static void printStatisticsForAllGames() {
        System.out.println("\nResults:");
        System.out.println("--------------------------");
        System.out.printf("%17s %4d\n", "Antal vundet spil:", numberOfGamesWon);
        System.out.printf("%17s %4d\n", "Antal tabte spil:", numberOfGamesLost);
    }

    private static void playAgain() {
        Scanner input = new Scanner(System.in);
        System.out.println("Vil du spille igen? ('ja'/'nej')");
        String answer = input.nextLine();
        if(answer.equals("ja")) {
            playCraps();
        }

    }

    private static void checkFirstRoll(int sumOfDice) {
        if (SumOfDice == 7 || SumOfDice == 11) {
            System.out.println("Du slog " + SumOfDice + " og vandt derfor.");
            numberOfGamesWon++;
        } else if (SumOfDice == 2 || SumOfDice == 3 || SumOfDice == 12) {
            System.out.println("Du slog " + SumOfDice + " og tabte derfor.");
            numberOfGamesLost++;
        } else {
            System.out.println("Du slog " + SumOfDice + ", så dit point er " + SumOfDice + " og du skal derfor slå igen.");
            rollForPoint(SumOfDice);
        }
    }


    public static boolean rollForPoint(int point) {

        while(true) {
            int[] newDiceArray = rollDice();
            int newPoint = newDiceArray[0] + newDiceArray[1];

            if (newPoint == 7) {
                System.out.println("Du har desværre slået 7 og tabte.");
                numberOfGamesLost++;
                return false;
            }
            else if (newPoint == point) {
                System.out.println("Tillykke, du har slået " + point + " som var dit tidligere point. Du har derfor vundet!");
                numberOfGamesWon++;
                return true;
            }
            else {
                System.out.println("Du slog " + newPoint + " og skal slå igen.");
            }
        }

    }
}

