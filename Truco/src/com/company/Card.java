package com.company;

/**
 * Created by icyhot on 12/27/16.
 */
public class Card {

    private String value;
    private String suit;
    private String suits[] = {"Clubs", "Hearts", "Spades", "Diamonds"};

    public Card(int suit, int value){
        this.suit = suits[suit];
        if(value == 11)
            this.value = "Jack";
        else if(value == 12)
            this.value = "Queen";
        else if(value == 13)
            this.value = "King";
        else
            this.value = Integer.toString(value);
    }

    public String getCardName(){
        return value + " of " + suit;
    }

}
