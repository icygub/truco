package com.company;

import java.util.ArrayList;

/**
 * Created by icyhot on 12/27/16.
 */
public class Hand{

    private ArrayList hand = new ArrayList<Card>();

    public Hand(){

    }

    public int getSize(){
        return hand.size();
    }

    public void add(Card card){
        hand.add(card);
    }

    public void print(){
        for(int i = 0; i < hand.size(); i++){
            System.out.println("(" + (hand.indexOf(hand.get(i))+1)+") " + ((Card)hand.get(i)).getCardName() );
        }
    }
}
