package src;

import java.util.Random;
import java.util.Scanner;

public class Hangman {
    public static int pointsPlayerOne, pointsPlayerTwo;

    public static String generateCity(){
        String[] cityList = {"BLAGOEVGRAD", "BURGAS", "VARNA", "VELIKO TURNOVO", "VIDIN", "VRACA", "GABROVO", "DOBRICH", "LOVECH", "MONTANA", "PAZARDJIK", "PERNIK", "PLEVEN", "PLOVDIV", "RAZGRAD", "RUSE", "SILISTRA", "SLIVEN", "SOFIA", "STARA ZAGORA", "TURGOVISHTE", "HASKOVO", "SHUMEN", "YAMBOL"};
        Random rnd = new Random();
        int cityIndex = rnd.nextInt(cityList.length);
        return cityList[cityIndex];
    }
    public static void addPoints(String winner, String player1){
        if (winner.equals(player1)){
            pointsPlayerOne++;
        } else {
            pointsPlayerTwo++;
        }
    }

    public static void printPoints(String player1, String player2){
        System.out.println(player1+"'s points:"+pointsPlayerOne);
        System.out.println(player2+"'s points:"+pointsPlayerTwo);
    }
    public static void printCurrentHangman(int score, String wrongTries) {
        switch (score) {
            case 0 -> {
                System.out.println(" ________");
                System.out.println(" |      |");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println("_|___");
            }
            case 1 -> {
                System.out.println(" ________");
                System.out.println(" |      |");
                System.out.println(" |      O       Wrong tries:" + wrongTries);
                System.out.println(" |");
                System.out.println(" |");
                System.out.println("_|___");
            }
            case 2 -> {
                System.out.println(" ________");
                System.out.println(" |      |");
                System.out.println(" |      O       Wrong tries:" + wrongTries);
                System.out.println(" |      |");
                System.out.println(" |");
                System.out.println("_|___");
            }
            case 3 -> {
                System.out.println(" ________");
                System.out.println(" |      |");
                System.out.println(" |      O       Wrong tries:" + wrongTries);
                System.out.println(" |     /|");
                System.out.println(" |");
                System.out.println("_|___");
            }
            case 4 -> {
                System.out.println(" ________");
                System.out.println(" |      |");
                System.out.println(" |      O       Wrong tries:" + wrongTries);
                System.out.println(" |     /|\\");
                System.out.println(" |");
                System.out.println("_|___");
            }
            case 5 -> {
                System.out.println(" ________");
                System.out.println(" |      |");
                System.out.println(" |      O       Wrong tries:" + wrongTries);
                System.out.println(" |     /|\\");
                System.out.println(" |     /");
                System.out.println("_|___");
            }
            case 6 -> {
                System.out.println(" ________");
                System.out.println(" |      |");
                System.out.println(" |      O       Wrong tries:" + wrongTries);
                System.out.println(" |     /|\\");
                System.out.println(" |     / \\");
                System.out.println("_|___");
            }
        }
    }

    public static void playHangMan() {
        System.out.print("Enter GameMode (1-Single player, 2-Multiplayer):");
        Scanner sc = new Scanner(System.in);
        byte playersCount = sc.nextByte();
        while (playersCount != 1 && playersCount != 2) {
            System.out.print("Incorrect input! Try again:");
            playersCount = sc.nextByte();
        }
        welcomeMessage(playersCount);
    }
    public static void welcomeMessage(int playersCount) {
        int mode;
        if (playersCount == 1) {
            System.out.println("Single player mode selected!");
            mode = 1;
        } else {
            System.out.println("Multiplayer mode selected!");
            mode = 2;
        }
        if (mode == 1) {
            startSinglePlayer();
        } else {
            startMultiplayer(0, "", "");
        }
    }
    public static boolean emptySpaces(String[] cityToGuess) {
        boolean emptySpaces = false;
        for (int i = 0; i < cityToGuess.length; i++) {
            if (cityToGuess[i].equals("_")) {
                emptySpaces = true;
            }
        }
        return emptySpaces;
    }
    public static void playAgain() {
        System.out.print("\nDo you want to play again? (1-Yes, 2-No, 3-Back to main menu):");
        Scanner sc = new Scanner(System.in);
        switch(sc.nextInt()){
            case 1 -> {
                startSinglePlayer();
            }
            case 2 -> {
                System.out.println("Goodbye!");
            }
            case 3 -> {
                playHangMan();
            }
        }
    }
    public static void playAgainForTwo(String player1, String player2) {
        System.out.print("\nDo you want to play again? (1-Yes, 2-No, 3-Back to main menu):");
        Scanner sc = new Scanner(System.in);
        switch(sc.nextInt()){
            case 1 -> {
                startMultiplayer(1, player1, player2);
            }
            case 2 -> {
                printPoints(player1,player2);
                System.out.println("Goodbye!");
            }
            case 3 -> {
                playHangMan();
            }
        }
    }
    public static void gameOver(String city, String player1, String player2, String player) {
        System.out.println("\t\tGame over!");
        System.out.println("\t\tThe correct answer was " + city);
        if (player != "") {
            playAgainForTwo(player1, player2);
        } else {
            playAgain();
        }
    }
    public static void gameWin(String[] cityToGuess, String city, String winner, String player1, String player2) {
        for (int i = 0; i < cityToGuess.length; i++) {
            System.out.print(cityToGuess[i] + " ");
        }
        if (winner == "") {
            System.out.println("\n\t\t*** YOU WIN *** ");
            System.out.println(city + " is the correct answer!");
        } else {
            System.out.println("\n\t\t*** " + winner + " WON *** ");
            System.out.println(city + " is the correct answer!");
            System.out.println(winner+" won this round and earned a point!");
            addPoints(winner,player1);
        }
        if (winner != "") {
            playAgainForTwo(player1, player2);
        } else {
            playAgain();
        }
    }
    public static void startSinglePlayer() {
        String wrongTries = "";
        int score = 0;
        Scanner sc = new Scanner(System.in);
        String city = generateCity();
        String[] letters = city.split("(?!^)");
        String[] cityToGuess = new String[city.length()];
        printCurrentHangman(score, wrongTries);
        System.out.println("\nGuess the city!");
        for (int i = 0; i < cityToGuess.length; i++) {
            if (letters[i].equals(" ")) {
                cityToGuess[i] = " ";
            } else {
                cityToGuess[i] = "_";
            }
        }
        while (emptySpaces(cityToGuess)) {
            for (int i = 0; i < cityToGuess.length; i++) {
                System.out.print(cityToGuess[i] + " ");
            }
            System.out.print("\nEnter your guess:");
            String letter = sc.next().toUpperCase();
            boolean guess = false;
            for (int i = 0; i < letters.length; i++) {
                if (letter.equals(letters[i])) {
                    cityToGuess[i] = letter;
                    guess = true;
                }
            }
            if (guess == false) {
                wrongTries += letter + ", ";
                score++;
                printCurrentHangman(score, wrongTries);
            }
            if (score == 6) {
                gameOver(city, "", "", "");
                break;
            }
        }
        if (!emptySpaces(cityToGuess)) {
            gameWin(cityToGuess, city, "", "", "");
        }
    }
    public static String setPlayerNames(int num) {
        if (num == 1) {
            System.out.print("Set first player name:");
        } else {
            System.out.print("Set second player name:");
        }
        Scanner sc = new Scanner(System.in);
        return sc.next();
    }
    public static void startMultiplayer(int tries, String playerFirst, String playerSecond) {
        String player1 = playerFirst;
        String player2 = playerSecond;
        if (tries == 0) {
            player1 = setPlayerNames(1);
            player2 = setPlayerNames(2);
        }
        String wrongTries = "";
        int score = 0;
        Scanner sc = new Scanner(System.in);
        String city = generateCity();
        String[] letters = city.split("(?!^)");
        String[] cityToGuess = new String[city.length()];
        printCurrentHangman(score, wrongTries);
        System.out.println("\nGuess the city!");
        for (int i = 0; i < cityToGuess.length; i++) {
            if (letters[i].equals(" ")) {
                cityToGuess[i] = " ";
            } else {
                cityToGuess[i] = "_";
            }
        }
        String currentPlayer = player1;
        while (emptySpaces(cityToGuess)) {
            for (int i = 0; i < cityToGuess.length; i++) {
                System.out.print(cityToGuess[i] + " ");
            }
            System.out.print("\n" + currentPlayer + "'s turn:");
            String letter = sc.next().toUpperCase();
            boolean guess = false;
            for (int i = 0; i < letters.length; i++) {
                if (letter.equals(letters[i])) {
                    cityToGuess[i] = letter;
                    guess = true;
                }
            }
            if (!guess) {
                if (currentPlayer == player2) {
                    currentPlayer = player1;
                } else {
                    currentPlayer = player2;
                }
                wrongTries += letter + ", ";
                score++;
                printCurrentHangman(score, wrongTries);
            }
            if (score == 6) {
                gameOver(city, player1, player2, currentPlayer);
                break;
            }
        }
        if (!emptySpaces(cityToGuess)) {
            gameWin(cityToGuess, city, currentPlayer, player1, player2);
        }
    }
    public static void main(String[] args) {
        System.out.println("\t\tWelcome to the Hangman Game!");
        playHangMan();
    }
}