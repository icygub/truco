package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Welcome to Truco!\nWhat is your name? ");
        String playerOne = sc.nextLine();
        System.out.print("How many sets? ");
        int sets = sc.nextInt();
        System.out.print("How many games per set? ");
        int games = sc.nextInt();
        Deck gameDeck = new Deck();
        gameDeck.create();

        Hand handOne = new Hand();
        Hand handTwo = new Hand();

//        gameDeck.print();
//        System.out.println("\nHand one");
//        handOne.print();
//        System.out.println("\nHand two");
//        handTwo.print();
//        System.out.println("\nDeck size");
        Truco truco = new Truco(gameDeck, handOne, handTwo, playerOne, "Computer", sets, games);
        truco.start();
    }
}
