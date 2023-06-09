package src;

import java.util.Random;
import java.util.Scanner;

public class Hangman {

    public static void printCurrentHangman(int score){
        switch(score){
            case 0:
                System.out.println("--------");
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

    public static void startSinglePlayer() {
        int score=0;
        String[] cityList = {"BLAGOEVGRAD","BURGAS","VARNA","VELIKO TURNOVO","VIDIN","VRACA","GABROVO","DOBRICH","LOVECH","MONTANA","PAZARDJIK","PERNIK","PLEVEN","PLOVDIV","RAZGRAD","RUSE","SILISTRA","SLIVEN","SOFIA","STARA ZAGORA","TURGOVISHTE","HASKOVO","SHUMEN","YAMBOL"};
        Random rnd = new Random();
        int cityIndex = rnd.nextInt(cityList.length)+1;
        String city = cityList[cityIndex];
        String[] letters = city.split("(?!^)");
        String[] cityToGuess = new String[city.length()];
        printCurrentHangman(score);
        for (int i = 0; i < cityToGuess.length; i++) {
            cityToGuess[i]="_";
            System.out.print(cityToGuess[i]+" ");
        }

    }

    public static void startMultiplayer(){
        String[] cityList = {"BLAGOEVGRAD","BURGAS","VARNA","VELIKO TURNOVO","VIDIN","VRACA","GABROVO","DOBRICH","LOVECH","MONTANA","PAZARDJIK","PERNIK","PLEVEN","PLOVDIV","RAZGRAD","RUSE","SILISTRA","SLIVEN","SOFIA","STARA ZAGORA","TURGOVISHTE","HASKOVO","SHUMEN","YAMBOL"};

    }

    public static void main(String[] args) {
        playHangMan();
        }
}
