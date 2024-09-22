Kan I lave metoden om, så den tager en parameter og er en one liner?

    private static void printPlayerInTurn() {
        if (playerInTurn == PLAYER_ONE) {
            System.out.printf("\nDet er SPILLER 1's tur.\n");
        }
        else {
            System.out.printf("\nDet er SPILLER 2's tur.\n");
        }
    }

Denne metode

```
private static boolean isPlayerInTurnWinner(int totalPointsThisRound, int winCondition) {
    if (playerInTurn == PLAYER_ONE && totalPointsThisRound + totalPointsPlayerOne >= winCondition) {
        return true;
    } else if (playerInTurn == PLAYER_TWO && totalPointsThisRound + totalPointsPlayerTwo >= winCondition) {
        return true;
    } else {
        return false;
    }
}
```

Kan skrives som

    public static boolean isPlayerInTurnWinner(int totalPointsThisRound, int winCondition){
      return (playerInTurn == PLAYER_ONE && totalPointsThisRound + totalPointsPlayerOne >= winCondition) || (playerInTurn == PLAYER_TWO && totalPointsThisRound + totalPointsPlayerTwo >= winCondition);
    }

Metoden

`    private static void updateStatistics(int points)`

bør hedde `    updatePlayerPoints` da det er det den gør.

Hvis man ruller to etterer nulstiller I en spillers total point.

```
            if (sumOfFaces == 2) {
                System.out.println("Du slog en 1'er på begge terninger i dit kast - " +
                        "du mister derfor alle dine point fra tidligere runder og må ikke slå igen.");
                resetTheTotalPointsForPlayerInTurn();  // Reset all points for the player
                break;
            }            
```
Men I nulstiller ikke `totalPointsThisRound`, så man får point selv om man ruller to etterer. 
Pas på med at bruge `break;`, det gør det sværrere at læse koden, er det if-statementet man hopper ud, er det while eller er det hele metoden? 
