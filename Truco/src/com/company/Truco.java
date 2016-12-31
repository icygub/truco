package com.company;

import java.util.Scanner;

/**
 * What needs to be fixed:
 * start():
 *  in the for loop, playPoint() needs to return an int, to determine who will go first next point, because the winner of the last point goes first
 *  or, if someone rejects a truco, then the return value is 99
 * REMEMBER: The person who wins the point (the 1v1 card battle) goes first for the next point (3 points = 1 game)
 * I need to make sure that this feature works.
 *
 * I may need to add playIndPoint() method, because if someone says "truco" on the second half of the
 * playPoint() method, it won't make sense to write the same code for that situation again. Calling playIndPoint() twice
 * inside playPoint() assures that this issue is taken care of
 */
public class Truco {

    private Deck gameDeck;
    private Hand handOne;
    private Hand handTwo;
    private String playerOne;
    private String playerTwo;
    private int sets;
    private int games;
    private int playerOneScore[] = new int[2];
    private int playerTwoScore[] = new int[2];
    private boolean trucoCalled = false;
    private int trucoValue = 0;
    private String trucoOptions[] = {"truco", "mao de seis", "mao de doze"};
    private Scanner sc = new Scanner(System.in);


    public Truco (Deck gameDeck, Hand handOne, Hand handTwo, String playerOne, String playerTwo, int sets, int games){
        this.gameDeck = gameDeck;
        this.handOne = handOne;
        this.handTwo = handTwo;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.sets = sets;
        this.games = games;
    }

    public void start(){
        int whoPlays = 0;
        //int whoPlaysFirst = (int) (Math.random() * 2);
        if(whoPlays == 1) //its not 0, because it will go into oppositeOf() method
            System.out.println(playerOne + " goes first!");
        else
            System.out.println(playerTwo + " goes first!");

        for(int set = 0; set < sets; set++){
            int result;
            System.out.println("This is set #" + set+1);
            for(int game = 0; game < games; game++){
                gameDeck.shuffle();
                gameDeck.dealCards(handOne,handTwo);
                for(int point = 0; point < 3; point++){
                    result = playPoint(oppositeOf(whoPlays), handOne, handTwo);
                }
                if(playerOneScore[0] >= games || playerTwoScore[0] >= games){

                }


            }
            //if(playerOneScore[0])
        }
    }

    //a point will be when both players play one card each
    public int playPoint(int whoPlays, Hand firstHand, Hand secondHand){
        String choice = getValidInput(whoPlays);
        if(choice.equalsIgnoreCase("t")){
            trucoCalled = true;
            trucoTime(whoPlays);
            if(trucoCalled == true){ //if the "truco streak" is still alive
                System.out.println("Oh snap! Winner of this truco gains ");
            }
            else
                return 99;
        }


        else //
            return 99;
    }

    public int[] getRightPoints(int whoPlays){
        if(whoPlays == 0)
            return playerOneScore;
        else
            return playerTwoScore;
    }

    public void trucoTime(int whoPlays){ //it means someone called truco
        System.out.print("Opponent called " + trucoOptions[trucoValue] + "!");
        String trucoChoice = getValidInput(whoPlays);
        if(trucoChoice.equalsIgnoreCase("r")){ //if they reject the truco
            trucoCalled = false;
            winGames(getRightPoints(oppositeOf(whoPlays)));
        }
        else if(trucoChoice.equalsIgnoreCase("t")){
            trucoValue += 1;
            trucoTime(oppositeOf(whoPlays));
        }
        //if they accept, then nothing really happens here. Everything just returns back to playPoint()
    }

    public void winGames(int playerScore[]){
        playerScore[0] += trucoValueToPoints();
    }

    public int trucoValueToPoints(){
        if(trucoValue == 2)
            return 12;
        else if(trucoValue == 1)
            return 6;
        else
            return 3;
    }

    public int oppositeOf(int whoPlaysFirst){
        if(whoPlaysFirst == 0)
            return 1;
        else
            return 0;
    }

    //public void playCard

    public String getValidInput(int whoPlays){
        String input;
        while(true){
            if(whoPlays == 0){
                if(trucoCalled == true){
                    if(trucoValue == 2) //if mao de doze is current value
                        System.out.print("Accept (a), or reject (r): "); //then they can't truco anymore
                    else
                        System.out.print("Accept (a), reject (r), or truco again(t) with " + trucoOptions[trucoValue + 1] + ": ");

                    input = sc.next();
                    if(input.equalsIgnoreCase("t") && trucoValue == 2)
                        System.out.println("Can\'t go higher than mao de doze");
                    else if (input.equalsIgnoreCase("a") || input.equalsIgnoreCase("r") || input.equalsIgnoreCase("t"))
                        return input;
                    else
                        System.out.println("Invalid input.");
                }
                else{
                    input = sc.next();
                    if(input.equalsIgnoreCase("p"))
                        printScore();
                    else if(input.equals("1") || input.equals("2") || input.equals("3") || input.equalsIgnoreCase("t"))
                        return input;
                    else
                        System.out.println("Invalid input.");
                }
            }
            else{
                int randChoice = (int)(Math.random()*handTwo.getSize()) + 1;
                if(randChoice == 3)
                    return "3";
                else if(randChoice == 2)
                    return "2";
                else if(randChoice == 1)
                    return "1";
                else
                    return "t";
            }
        }
    }

    public void printScore(){
        System.out.printf("%s: %d games, %d sets", playerOne, playerOneScore[0], playerOneScore[1]);
        System.out.printf("%n%s: %d games, %d sets%n", playerTwo, playerTwoScore[0], playerTwoScore[1]);
    }
}
