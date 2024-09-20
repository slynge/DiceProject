package opgave03;

import java.util.Scanner;
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
        printRules();
        System.out.println();

        System.out.println("Hvad vil I gerne spille til? Vælg et positivt heltal.");
        int winCondition = scanner.nextInt();
        playPigs(winCondition);

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
            printPersonInTurn();

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
            int face = rollDie();
            increaseRollCounterForPlayer();
            if(face == 1) {
                System.out.println("Du slog en 1'er på dit kast - " +
                                   "du får derfor ingen point i denne runde og må ikke slå igen.");
                totalPointsThisRound = 0;
                break;
            }

            totalPointsThisRound += face;

            if(isWinner(totalPointsThisRound, winCondition)) {
                break;
            }

            System.out.println("Du slog en " + face + " på dit første kast og får derfor " + face + " point." +
                               " Du har nu " + totalPointsThisRound + " denne runde." );
            System.out.println("Vil du fortsætte? ('ja'/'nej')");
            answer = scanner.nextLine();
        }
        updateStatistics(totalPointsThisRound);
        increaseTurnCounterForPlayer();

    }

    private static boolean isWinner(int totalPointsThisRound, int winCondition) {
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

    private static void increaseTurnCounterForPlayer() {
        if(playerInTurn == PLAYER_ONE) {
            numberOfTurnsPlayerOne++;
        }
        else {
            numberOfTurnsPlayerTwo++;
        }
    }

    private static void increaseRollCounterForPlayer() {
        if(playerInTurn == PLAYER_ONE) {
            rollCountPlayerOne++;
        }
        else {
            rollCountPlayerTwo++;
        }
    }

    private static int rollDie() {
        return (int) (Math.random() * 6 + 1);
    }

    private static void updateStatistics(int point) {
        if(playerInTurn == PLAYER_ONE) {
            totalPointsPlayerOne += point;
        }
        else {
            totalPointsPlayerTwo += point;
        }
    }

    private static void printPersonInTurn() {
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
