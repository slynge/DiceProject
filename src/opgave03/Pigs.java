package opgave03;

import java.util.Scanner;
import static opgave03.Player.*;

public class Pigs {

    private static Player playerInTurn = PLAYER_ONE;
    private static int totalPointsPlayerOne = 0;
    private static int totalPointsPlayerTwo = 0;

    public static void main(String[] args) {
        System.out.println("Velkommen til spillet, PIGS.");
        printRules();
        System.out.println();

        playPigs(100);

        System.out.println();
        System.out.println("Tak for at spille, PIGS.");
    }

    private static void printRules() {
        System.out.println("=====================================================");
        System.out.println("Regler for PIGS");
        System.out.println("To spillere.");
        System.out.println("Første spiller kaster en terning, indtil han slår 1 " +
                           "eller beslutter sig for at stoppe.");
        System.out.println("Hvis han slår en 1'er, får han ingen point i denne runde.");
        System.out.println("Hvis han beslutter sig for at stoppe inden han slår en 1'er, " +
                           "lægges summen af alle hans kast sammen med hans samlede antal point.");
        System.out.println("Derefter går turen videre til den anden spiller.");
        System.out.println("Den første spiller der når 100 point vinder.");
        System.out.println("=====================================================");
    }

    private static void playPigs(int winCondition) {
        while(totalPointsPlayerOne < winCondition && totalPointsPlayerTwo < winCondition) {
            displayPersonInTurn();

            playTurn();

            printStatisticsForEachTurn();
        }

    }

    private static void playTurn() {
        doRolls();
        switchPlayerInTurn();
    }

    private static void switchPlayerInTurn() {
        playerInTurn = (playerInTurn == PLAYER_ONE) ? PLAYER_TWO : PLAYER_ONE;
    }

    private static void doRolls() {
        int totalPointsThisRound = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Vil du starte med at kaste? ('ja'/'nej')");
        String answer = scanner.nextLine();
        while(!answer.equals("nej")) {
            int face = rollDie();
            if(face == 1) {
                System.out.println("Du slog en 1'er på dit kast - " +
                                   "du får derfor ingen point i denne runde og må ikke slå igen.");
                totalPointsThisRound = 0;
                break;
            }
            totalPointsThisRound += face;
            System.out.println("Du slog en " + face + " på dit første kast og får derfor " + face + " point." +
                               " Du har nu " + totalPointsThisRound + " denne runde." );
            System.out.println("Vil du fortsætte? ('ja'/'nej')");
            answer = scanner.nextLine();
        }
        updateStatistics(playerInTurn, totalPointsThisRound);
    }

    private static int rollDie() {
        return (int) (Math.random() * 6 + 1);
    }

    private static void updateStatistics(Player playerInTurn, int point) {
        if(playerInTurn == PLAYER_ONE) {
            totalPointsPlayerOne += point;
        }
        else {
            totalPointsPlayerTwo += point;
        }
    }

    private static void displayPersonInTurn() {
        if (playerInTurn == PLAYER_ONE) {
            System.out.printf("\nDet er SPILLER 1's tur.\n");
        } else {
            System.out.printf("\nDet er SPILLER 2's tur.\n");
        }
    }

    private static void printStatisticsForEachTurn() {
        System.out.println("\nResults:");
        System.out.println("-------");
        System.out.printf("%17s %4d\n", "Antal point for SPILLER 1:", totalPointsPlayerOne);
        System.out.printf("%17s %4d\n", "Antal point for SPILLER 2:", totalPointsPlayerTwo);
    }

}
