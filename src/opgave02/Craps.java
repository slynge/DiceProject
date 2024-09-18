package opgave02;

import com.sun.tools.javac.Main;

import java.awt.*;
import java.util.Scanner;

// 01) Lav en klasse kaldt Craps
public class Craps {

    public static int SumOfDice = 0;
    public static int point = 0;


    //Main method
    public static void main(String[] args) {


        playTwoDice();
        playCraps();
    }

    private static void playTwoDice() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Rul en terning? ('ja/nej') ");
        String answer = scanner.nextLine();
        while (!answer.equals("nej")) {
            int[] faces = playCraps();
            System.out.println("Du rullede: " + faces[0] + " & " + faces[1]);
            System.out.println();
            System.out.print("Rul en terning? ('ja/nej') ");
            answer = scanner.nextLine();
        }
        scanner.close();
    }

    public static int[] playCraps() {

        // to terninger tilgives et tilfældigt værdig mellem 1 0g 6 og gemmes i et array, derefter lægges sammen
        int[] diceArray = new int[2];

        diceArray[0] = (int) (Math.random() * 6 + 1);
        diceArray[1] = (int) (Math.random() * 6 + 1);

        SumOfDice = diceArray[0] + diceArray[1];

        if (SumOfDice == 7 || SumOfDice == 11) {
            System.out.println("Du vandt!");
        } else if (SumOfDice == 2 || SumOfDice == 3 || SumOfDice == 12) {
            System.out.println("Du tabte");
        } else {
            System.out.println("Point: " + SumOfDice);
            rollForPoint(SumOfDice);

        }

        return diceArray;
    }

    public static boolean rollForPoint(int point) {
        int[] newRollArray = new int[2];
        System.out.println("du slå " + SumOfDice + " og skal nu slå igen");
        do {
            newRollArray[0] = (int) (Math.random() * 6 + 1);
            newRollArray[1] = (int) (Math.random() * 6 + 1);
            int newPoint = newRollArray[0] + newRollArray[1];
            System.out.println("Du slog " + newPoint);
            if (newPoint == 7) {
                System.out.println("Du har desværre slået 7 og tabt ");
                return false;
            } else if (newPoint == point) {
                System.out.println("Tillykke, du har slået " + point + " Som var dit point ");
                return true;

            }
        } while (true);

    }
}

