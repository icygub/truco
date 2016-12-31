package com.company;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by icyhot on 12/27/16.
 */
public class Deck {

    private ArrayList deck = new ArrayList<Card>();

    public Deck(){

    }

    public void create(){
        for(int suit = 0; suit<4; suit++){
            for(int value = 1; value < 14; value++){
                deck.add(new Card(suit,value));
            }
        }
    }

    public void shuffle(){
        int ping;
        int pong;
        for(int i=0; i<deck.size(); i++){
            ping = (int) (Math.random()*52);
            do{
                pong = (int) (Math.random()*52);
            }while(pong == ping);
            Collections.swap(deck, ping, pong);
        }
    }

    public void playPoint(){

    }

    public void dealCards(Hand handOne, Hand handTwo){
        int size = deck.size();
        for(int i = deck.size(); i > size-6; i--){
            //System.out.println("works");
            if(handOne.getSize() == handTwo.getSize())
                handOne.add((Card)deck.get(i-1));
            else
                handTwo.add((Card)deck.get(i-1));
            deck.remove(i-1);
        }
    }

    public void print(){
        for(int i = 0; i < deck.size(); i++){
            System.out.println( ((Card)deck.get(i)).getCardName() );
        }
    }

    public int getSize(){
        return deck.size();
    }




}
