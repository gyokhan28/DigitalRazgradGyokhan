package src;

import java.util.Scanner;
public class Hangman {

    public static void playHangMan(){
        System.out.println("Welcome to the Hangman Game!");
        System.out.print("Enter GameMode (1-Single player, 2-Multiplayer");
        Scanner sc = new Scanner(System.in);
        byte playersCount = sc.nextByte();
        while(playersCount!=1&&playersCount!=2){
            System.out.print("Incorrect input! Try again:");
            playersCount = sc.nextByte();
        }
    }
    public static void main(String[] args) {
        playHangMan();
    }
}
