package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;

public class Hangman {

    public static int pointsPlayerOne, pointsPlayerTwo;

    public static String generateRandomCity() {
        String allCities = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader("cityList.txt"));
            String line = "";
            while ((line = reader.readLine()) != null) {
                allCities += line;
                allCities += ";";
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        String[] cityList = allCities.split(";");
        Random rnd = new Random();
        int cityIndex = rnd.nextInt(cityList.length);
        return cityList[cityIndex];
    }

    public static void addPoints(String winner, String player1) {
        if (winner.equals(player1)) {
            pointsPlayerOne++;
        } else {
            pointsPlayerTwo++;
        }
    }

    public static void printPoints(String player1, String player2) {
        System.out.println("Points:");
        System.out.println(player1 + " -> " + pointsPlayerOne);
        System.out.println(player2 + " -> " + pointsPlayerTwo);
    }

    public static void printCurrentHangman(int wrongGuessesCount, String wrongGuesses) {
        switch (wrongGuessesCount) {
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
                System.out.println(" |      O       Wrong tries:" + wrongGuesses);
                System.out.println(" |");
                System.out.println(" |");
                System.out.println("_|___");
            }
            case 2 -> {
                System.out.println(" ________");
                System.out.println(" |      |");
                System.out.println(" |      O       Wrong tries:" + wrongGuesses);
                System.out.println(" |      |");
                System.out.println(" |");
                System.out.println("_|___");
            }
            case 3 -> {
                System.out.println(" ________");
                System.out.println(" |      |");
                System.out.println(" |      O       Wrong tries:" + wrongGuesses);
                System.out.println(" |     /|");
                System.out.println(" |");
                System.out.println("_|___");
            }
            case 4 -> {
                System.out.println(" ________");
                System.out.println(" |      |");
                System.out.println(" |      O       Wrong tries:" + wrongGuesses);
                System.out.println(" |     /|\\");
                System.out.println(" |");
                System.out.println("_|___");
            }
            case 5 -> {
                System.out.println(" ________");
                System.out.println(" |      |");
                System.out.println(" |      O       Wrong tries:" + wrongGuesses);
                System.out.println(" |     /|\\");
                System.out.println(" |     /");
                System.out.println("_|___");
            }
            case 6 -> {
                System.out.println(" ________");
                System.out.println(" |      |");
                System.out.println(" |      O       Wrong tries:" + wrongGuesses);
                System.out.println(" |     /|\\");
                System.out.println(" |     / \\");
                System.out.println("_|___");
            }
        }
    }

    public static void gameModeInput() {
        System.out.print("Enter GameMode (1-Single player, 2-Multiplayer):");
        Scanner sc = new Scanner(System.in);
        String playersCount = sc.next();
        while (!playersCount.equals("1") && !playersCount.equals("2") || (!playersCount.matches("[1-9]"))) {
            System.out.print("Incorrect input! Try again:");
            playersCount = sc.next();
        }
        printWelcomeMessage(Integer.parseInt(playersCount));
    }

    public static void printWelcomeMessage(int gameMode) {
        if (gameMode == 1) {
            System.out.println("Single player mode selected!");
            startSinglePlayer();
        } else {
            System.out.println("Multiplayer mode selected!");
            startMultiplayer(0, "", "");
        }
    }

    public static boolean checkForEmptySpaces(String[] cityToGuess) {
        boolean emptySpaces = false;
        for (String s : cityToGuess) {
            if (s.equals("_")) {
                emptySpaces = true;
                break;
            }
        }
        return emptySpaces;
    }

    public static void playAgainSingleplayer() {
        System.out.print("\nDo you want to play again? (1-Yes, 2-No, 3-Back to main menu):");
        Scanner sc = new Scanner(System.in);
        String choice = sc.next();
        while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3")) {
            System.out.print("Wrong input. Try again:");
            choice = sc.next();
        }
        switch (choice) {
            case "1" -> {
                startSinglePlayer();
            }
            case "2" -> {
                System.out.print("Goodbye!");
            }
            case "3" -> {
                gameModeInput();
            }
        }
    }

    public static void playAgainMultiplayer(String player1, String player2) {
        System.out.print("\nDo you want to play again? (1-Yes, 2-No, 3-Back to main menu):");
        Scanner sc = new Scanner(System.in);
        String choice = sc.next();
        while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3")) {
            System.out.print("Wrong input. Try again:");
            choice = sc.next();
        }
        switch (choice) {
            case "1" -> {
                startMultiplayer(1, player1, player2);
            }
            case "2" -> {
                printPoints(player1, player2);
                System.out.print("Goodbye!");
            }
            case "3" -> {
                pointsPlayerOne = 0;
                pointsPlayerTwo = 0;
                System.out.println("Players' points are reset!");
                gameModeInput();
            }
        }
    }

    public static void gameOver(String city) {
        System.out.println("\t\t\t\tGame over!");
        System.out.println("\t\tThe correct answer was " + city);
    }

    public static void multiplayerWinMessagePrint(String city, String winner) {
        System.out.println("\n\t\t*** " + winner + " WON *** ");
        System.out.println(city + " is the correct answer!");
        System.out.println(winner + " won this round and earned a point!");
    }

    public static void singleplayerWinMessagePrint(String city) {
        System.out.println("\n\t\t*** YOU WIN *** ");
        System.out.println(city + " is the correct answer!");
    }

    public static boolean checkFormat(String userGuess) {
        boolean wrongFormat = false;
        if (!userGuess.matches("^[a-zA-Z]+$")) {
            wrongFormat = true;
            System.out.print("Only letters allowed! Try again!: ");
        } else if (userGuess.length() != 1) {
            wrongFormat = true;
            System.out.print("You entered more than one letter! Try again!: ");
        }
        return wrongFormat;
    }

    public static String setName(int num) {
        if (num == 1) {
            System.out.print("Set first player name:");
        } else {
            System.out.print("Set second player name:");
        }
        Scanner sc = new Scanner(System.in);
        return sc.next();
    }

    public static void generateHiddenWord(String[] cityToGuess, String[] letters) {
        for (int i = 0; i < cityToGuess.length; i++) {
            if (letters[i].equals(" ")) {
                cityToGuess[i] = " ";
            } else {
                cityToGuess[i] = "_";
            }
        }
    }

    public static void printHiddenWord(String[] cityToGuess) {
        for (int i = 0; i < cityToGuess.length; i++) {
            System.out.print(cityToGuess[i] + " ");
        }
    }

    public static String changeCurrentPlayer(String currentPlayer, String player1, String player2) {
        if (currentPlayer == player2) {
            currentPlayer = player1;
        } else {
            currentPlayer = player2;
        }
        return currentPlayer;
    }

    public static boolean checkEnteredLetter(String letter, String[] letters, String[] cityToGuess) {
        boolean guess = false;
        for (int i = 0; i < letters.length; i++) {
            if (cityToGuess[i].contains(letter)) {
                System.out.println("All " + letter + "'s are already placed. Try again!");
                guess = true;
                break;
            }
            if (letter.equals(letters[i]) && !cityToGuess[i].contains(letter)) {
                cityToGuess[i] = letter;
                guess = true;
            }
        }
        return guess;
    }

    public static void startSinglePlayer() {
        String wrongGuesses = "";
        int wrongGuessesCount = 0;
        String city = generateRandomCity();
        String[] letters = city.split("(?!^)");
        String[] cityToGuess = new String[city.length()];
        printCurrentHangman(wrongGuessesCount, wrongGuesses);
        System.out.println("\nGuess the city!");
        generateHiddenWord(cityToGuess, letters);
        while (checkForEmptySpaces(cityToGuess) && wrongGuessesCount < 6) {
            printHiddenWord(cityToGuess);
            System.out.print("\nEnter your guess:");
            Scanner sc = new Scanner(System.in);
            String userGuessedLetter = sc.next().toUpperCase();
            while (checkFormat(userGuessedLetter)) {
                userGuessedLetter = sc.next().toUpperCase();
            }
            boolean guess = checkEnteredLetter(userGuessedLetter, letters, cityToGuess);
            if (wrongGuesses.contains(userGuessedLetter)) {
                System.out.print(userGuessedLetter + " is already marked as wrong. Try again!\n");
            } else if (!guess) {
                wrongGuesses += userGuessedLetter + ", ";
                wrongGuessesCount++;
                printCurrentHangman(wrongGuessesCount, wrongGuesses);
            }
            if (wrongGuessesCount == 6) {
                gameOver(city);
                playAgainSingleplayer();
                break;
            }
        }
        if (!checkForEmptySpaces(cityToGuess) && wrongGuessesCount < 6) {
            printHiddenWord(cityToGuess);
            singleplayerWinMessagePrint(city);
            playAgainSingleplayer();
        }
    }

    public static void startMultiplayer(int tries, String playerFirst, String playerSecond) {
        String player1 = playerFirst, player2 = playerSecond;
        if (tries == 0) {
            player1 = setName(1);
            player2 = setName(2);
        }
        String wrongGuesses = "";
        int wrongGuessesCount = 0;
        String city = generateRandomCity();
        String[] letters = city.split("(?!^)");
        String[] cityToGuess = new String[city.length()];
        printCurrentHangman(wrongGuessesCount, wrongGuesses);
        generateHiddenWord(cityToGuess, letters);
        String currentPlayer = player1;
        System.out.println("\nGuess the city!");
        Scanner sc = new Scanner(System.in);
        while (checkForEmptySpaces(cityToGuess)) {
            printHiddenWord(cityToGuess);
            System.out.print("\n" + currentPlayer + "'s turn:");
            String userGuessedLetter = sc.next().toUpperCase();
            while (checkFormat(userGuessedLetter)) {
                userGuessedLetter = sc.next().toUpperCase();
            }
            boolean guess = checkEnteredLetter(userGuessedLetter, letters, cityToGuess);
            if (wrongGuesses.contains(userGuessedLetter)) {
                System.out.print(userGuessedLetter + " is already marked as wrong. Try again!\n");
            } else if (!guess) {
                currentPlayer = changeCurrentPlayer(currentPlayer, player1, player2);
                wrongGuesses += userGuessedLetter + ", ";
                wrongGuessesCount++;
                printCurrentHangman(wrongGuessesCount, wrongGuesses);
            }
            if (wrongGuessesCount == 6) {
                gameOver(city);
                playAgainMultiplayer(player1, player2);
                break;
            }
        }
        if (!checkForEmptySpaces(cityToGuess)) {
            printHiddenWord(cityToGuess);
            multiplayerWinMessagePrint(city, currentPlayer);
            addPoints(currentPlayer, player1);
            playAgainMultiplayer(player1, player2);
        }
    }

    public static void main(String[] args) {
        System.out.println("\t\tWelcome to the Hangman Game!");
        gameModeInput();
    }
}