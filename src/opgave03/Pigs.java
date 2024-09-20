package opgave03;

import java.util.Scanner;

import static opgave01.RollTwoDice.rollDice;
import static opgave03.Player.*;

public class Pigs {

    private static Player playerInTurn = PLAYER_ONE;
    private static int totalPointsPlayerOne = 0;
    private static int totalPointsPlayerTwo = 0;
    private static int rollCountPlayerOne = 0;
    private static int rollCountPlayerTwo = 0;
    private static int numberOfTurnsPlayerOne = 0;
    private static int numberOfTurnsPlayerTwo = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Velkommen til spillet, PIGS.");

        System.out.println("Hvad vil I gerne spille til? Vælg et positivt heltal.");
        int winCondition = scanner.nextInt();

        printRules(winCondition);
        System.out.println();

        playPigs(winCondition);

        System.out.println();
        System.out.println("Tak for at spille, PIGS.");
    }

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

    private static void playPigs(int winCondition) {
        while(totalPointsPlayerOne < winCondition && totalPointsPlayerTwo < winCondition) {
            printPlayerInTurn();

            playTurn(winCondition);

            printStandingsForEachTurn();
        }
        printWinnerForTheGame();
        printStatisticsForTheGame();

    }

    private static void printWinnerForTheGame() {
        if(totalPointsPlayerOne > totalPointsPlayerTwo) {
            System.out.println("SPILLER 1 vandt.");
        }
        else {
            System.out.println("SPILLER 2 vandt.");
        }
    }

    private static void playTurn(int winCondition) {
        doRolls(winCondition);
        switchPlayerInTurn();
    }

    private static void switchPlayerInTurn() {
        playerInTurn = (playerInTurn == PLAYER_ONE) ? PLAYER_TWO : PLAYER_ONE;
    }

    private static void doRolls(int winCondition) {
        int totalPointsThisRound = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Vil du starte med at kaste? ('ja'/'nej')");
        String answer = scanner.nextLine();
        while(!answer.equals("nej")) {
            int[] faces = rollDice();
            int faceOne = faces[0];
            int faceTwo = faces[1];
            int sumOfFaces = faceOne + faceTwo;

            increaseRollCounterForPlayerInTurn();

            if(sumOfFaces == 2) {
                System.out.println("Du slog en 1'er på begge terninger i dit kast - " +
                                   "du mister derfor alle dine point fra tidligere runder og må ikke slå igen.");
                resetTheTotalPointsForPlayerInTurn();
                break;
            }

            else if (faceOne == 1 || faceTwo == 1) {
                System.out.println("Du slog en 1'er på en af terningerne i dit kast - " +
                        "du mister derfor alle dine point fra denne runde og må ikke slå igen.");
                totalPointsThisRound = 0;
                break;
            }

            totalPointsThisRound += sumOfFaces;

            if(isPlayerInTurnWinner(totalPointsThisRound, winCondition)) {
                break;
            }

            System.out.println("Du slog en " + faceOne+ " & " + faceTwo + " på dit kast og får derfor " + sumOfFaces + " point." +
                               " Du har nu " + totalPointsThisRound + " denne runde." );
            System.out.println("Vil du fortsætte? ('ja'/'nej')");
            answer = scanner.nextLine();
        }
        updateStatistics(totalPointsThisRound);
        increaseTurnCounterForPlayerInTurn();

    }

    private static void resetTheTotalPointsForPlayerInTurn() {
        if(playerInTurn == PLAYER_ONE) {
            totalPointsPlayerOne = 0;
        }
        else {
            totalPointsPlayerTwo = 0;
        }
    }

    private static boolean isPlayerInTurnWinner(int totalPointsThisRound, int winCondition) {
        if(playerInTurn == PLAYER_ONE && totalPointsThisRound + totalPointsPlayerOne >= winCondition) {
            return true;
        }
        else if(playerInTurn == PLAYER_TWO && totalPointsThisRound + totalPointsPlayerTwo >= winCondition) {
                return true;
        }
        else {
            return false;
        }
    }

    private static void increaseTurnCounterForPlayerInTurn() {
        if(playerInTurn == PLAYER_ONE) {
            numberOfTurnsPlayerOne++;
        }
        else {
            numberOfTurnsPlayerTwo++;
        }
    }

    private static void increaseRollCounterForPlayerInTurn() {
        if(playerInTurn == PLAYER_ONE) {
            rollCountPlayerOne++;
        }
        else {
            rollCountPlayerTwo++;
        }
    }

    private static void updateStatistics(int point) {
        if(playerInTurn == PLAYER_ONE) {
            totalPointsPlayerOne += point;
        }
        else {
            totalPointsPlayerTwo += point;
        }
    }

    private static void printPlayerInTurn() {
        if (playerInTurn == PLAYER_ONE) {
            System.out.printf("\nDet er SPILLER 1's tur.\n");
        }
        else {
            System.out.printf("\nDet er SPILLER 2's tur.\n");
        }
    }

    private static void printStandingsForEachTurn() {
        System.out.println("\nStillingen efter turen:");
        System.out.println("-------");
        System.out.printf("%17s %4d\n", "Antal point for SPILLER 1:", totalPointsPlayerOne);
        System.out.printf("%17s %4d\n", "Antal point for SPILLER 2:", totalPointsPlayerTwo);
    }

    private static void printStatisticsForTheGame() {
        System.out.println("\nStatistikker for spillet:");
        System.out.println("-------");
        System.out.printf("%17s %4f\n", "Gennemsnit af kast SPILLER 1 har lavet:", (double) rollCountPlayerOne/numberOfTurnsPlayerOne);
        System.out.printf("%17s %4f\n", "Gennemsnit af kast SPILLER 2 har lavet:", (double) rollCountPlayerTwo/numberOfTurnsPlayerTwo);
    }

}
