package src;

import java.util.Random;
import java.util.Scanner;

public class Hangman {

    public static void printCurrentHangman(int score, String wrongTries) {
        switch (score) {
            case 0:
                System.out.println(" ________");
                System.out.println(" |      |");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println(" |");
                System.out.println("_|___");
                break;
            case 1:
                System.out.println(" ________");
                System.out.println(" |      |");
                System.out.println(" |      O       Wrong tries:"+wrongTries);
                System.out.println(" |");
                System.out.println(" |");
                System.out.println("_|___");
                break;
            case 2:
                System.out.println(" ________");
                System.out.println(" |      |");
                System.out.println(" |      O       Wrong tries:"+wrongTries);
                System.out.println(" |      |");
                System.out.println(" |");
                System.out.println("_|___");
                break;
            case 3:
                System.out.println(" ________");
                System.out.println(" |      |");
                System.out.println(" |      O       Wrong tries:"+wrongTries);
                System.out.println(" |     /|");
                System.out.println(" |");
                System.out.println("_|___");
                break;
            case 4:
                System.out.println(" ________");
                System.out.println(" |      |");
                System.out.println(" |      O       Wrong tries:"+wrongTries);
                System.out.println(" |     /|\\");
                System.out.println(" |");
                System.out.println("_|___");
                break;
            case 5:
                System.out.println(" ________");
                System.out.println(" |      |");
                System.out.println(" |      O       Wrong tries:"+wrongTries);
                System.out.println(" |     /|\\");
                System.out.println(" |     /");
                System.out.println("_|___");
                break;
            case 6:
                System.out.println(" ________");
                System.out.println(" |      |");
                System.out.println(" |      O       Wrong tries:"+wrongTries);
                System.out.println(" |     /|\\");
                System.out.println(" |     / \\");
                System.out.println("_|___");
                break;
        }
    }

    public static void playHangMan() {
        System.out.println("\t\tWelcome to the Hangman Game!");
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
            startMultiplayer();
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

    public static void gameOver(String city){
        System.out.println("\t\tGame over!");
        System.out.println("The correct answer was "+city);
    }

    public static void gameWin(String[] cityToGuess, String city){
        for(int i=0;i<cityToGuess.length;i++){
            System.out.print(cityToGuess[i]+" ");
        }
        System.out.println("\n\t\t*** YOU WIN *** ");
        System.out.println(city+" is the correct answer!");
        System.out.print("\nDo you want to play again? (1-Yes, 2-No):");
        Scanner sc = new Scanner(System.in);
        if(sc.nextInt()==1){
            playHangMan();
        } else {
            System.out.println("Goodbye!");
        }
    }
    public static void gameForTwoWin(String[] cityToGuess, String city, String winner){
        for(int i=0;i<cityToGuess.length;i++){
            System.out.print(cityToGuess[i]+" ");
        }
        System.out.println("\n\t\t*** "+winner+" WON *** ");
        System.out.println(city+" is the correct answer!");
        System.out.print("\nDo you want to play again? (1-Yes, 2-No):");
        Scanner sc = new Scanner(System.in);
        if(sc.nextInt()==1){
            playHangMan();
        } else {
            System.out.println("Goodbye!");
        }
    }

    public static void startSinglePlayer() {
        String wrongTries = "";
        int score = 0;
        Scanner sc = new Scanner(System.in);
        String[] cityList = {"BLAGOEVGRAD", "BURGAS", "VARNA", "VELIKO TURNOVO", "VIDIN", "VRACA", "GABROVO", "DOBRICH", "LOVECH", "MONTANA", "PAZARDJIK", "PERNIK", "PLEVEN", "PLOVDIV", "RAZGRAD", "RUSE", "SILISTRA", "SLIVEN", "SOFIA", "STARA ZAGORA", "TURGOVISHTE", "HASKOVO", "SHUMEN", "YAMBOL"};
        Random rnd = new Random();
        int cityIndex = rnd.nextInt(cityList.length);
        String city = cityList[cityIndex];
        String[] letters = city.split("(?!^)");
        String[] cityToGuess = new String[city.length()];
        printCurrentHangman(score,wrongTries);
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
                wrongTries+=letter+", ";
                score++;
                printCurrentHangman(score,wrongTries);
                }
            if(score==6){
                gameOver(city);
                break;
            }
        }
        if(!emptySpaces(cityToGuess)){
        gameWin(cityToGuess, city);}
    }

    public static String setPlayerNames(int num){
        if(num==1){
        System.out.print("Set first player name:");}
        else{
            System.out.print("Set second player name:");
        }
        Scanner sc = new Scanner(System.in);
        return sc.next();
    }

    public static void startMultiplayer() {
        String player1=setPlayerNames(1);
        String player2=setPlayerNames(2);
        String wrongTries = "";
        int score = 0;
        Scanner sc = new Scanner(System.in);
        String[] cityList = {"BLAGOEVGRAD", "BURGAS", "VARNA", "VELIKO TURNOVO", "VIDIN", "VRACA", "GABROVO", "DOBRICH", "LOVECH", "MONTANA", "PAZARDJIK", "PERNIK", "PLEVEN", "PLOVDIV", "RAZGRAD", "RUSE", "SILISTRA", "SLIVEN", "SOFIA", "STARA ZAGORA", "TURGOVISHTE", "HASKOVO", "SHUMEN", "YAMBOL"};
        Random rnd = new Random();
        int cityIndex = rnd.nextInt(cityList.length);
        String city = cityList[cityIndex];
        String[] letters = city.split("(?!^)");
        String[] cityToGuess = new String[city.length()];
        printCurrentHangman(score,wrongTries);
        System.out.println("\nGuess the city!");
        for (int i = 0; i < cityToGuess.length; i++) {
            if (letters[i].equals(" ")) {
                cityToGuess[i] = " ";
            } else {
                cityToGuess[i] = "_";
            }
        }
        String player = player1;
        while (emptySpaces(cityToGuess)) {
            for (int i = 0; i < cityToGuess.length; i++) {
                System.out.print(cityToGuess[i] + " ");
            }
            System.out.print("\n"+player+"'s turn:");
            String letter = sc.next().toUpperCase();
            boolean guess = false;
            for (int i = 0; i < letters.length; i++) {
                if (letter.equals(letters[i])) {
                    cityToGuess[i] = letter;
                    guess = true;
                }
            }
            if (guess == false) {
                player=player2;
                wrongTries+=letter+", ";
                score++;
                printCurrentHangman(score,wrongTries);
            }
            if(score==6){
                gameOver(city);
                break;
            }
        }
        if(!emptySpaces(cityToGuess)){

            gameForTwoWin(cityToGuess, city, player);}
    }

    public static void main(String[] args) {
        playHangMan();
    }
}
