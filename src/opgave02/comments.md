Rigtigt godt tænkt med playAgain metoden.
```
private static void playAgain() {
        Scanner input = new Scanner(System.in);
        System.out.println("Vil du spille igen? ('ja'/'nej')");  // Ask if the player wants to play again
        String answer = input.nextLine();
        if(answer.equals("ja")) {
            playCraps();  // Restart the game if the player says "ja"
        }
        // If the player says "nej", the program exits automatically
    }
```
Der er dog et problem med linjen

    Scanner input = new Scanner(System.in);

I laver et nyt Scanner objekt hver gang man kalder metoden, hvilket kan fører til problemer.
I skal enten sørge for at lukke den igen med.

    input.close();

eller lave et Scanner objekt igemmer i en felt variable som I genbruger.


