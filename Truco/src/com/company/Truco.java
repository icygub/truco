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
    private int playerOneScore[] = new int[3]; //{sets, games, points}
    private int playerTwoScore[] = new int[3];
    private boolean trucoActive = false;
    private boolean playerOneRejectedTruco;
    private boolean pointHasBeenWon;
    private boolean trucohasBeenDealtWith;
    private int whoWonFirstPointInGame;
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
        int whoPlays = (int) (Math.random() * 2);
        if(whoPlays == 0)
            System.out.println(playerOne + " goes first!");
        else
            System.out.println(playerTwo + " goes first!");

        for(int set = 0; set < sets; set++){
            int whoWonPoint;
            System.out.println("This is set #" + set+1);
            for(int game = 0; game < games; game++) {
                gameDeck.shuffle();
                gameDeck.dealCards(handOne, handTwo);
                for (int point = 0; point < 3; point++) {
                    whoWonPoint = playPoint(whoPlays, handOne, handTwo);
                    if (whoWonPoint == 0 || whoWonPoint == 1) //if this wasn't a tie, then someone obviously gets points
                        winGames(getRightPoints(whoPlays));

                    if (bothPointScoresAtZero() == false) {
                        if (whoWonPoint == 0) //in case of tie, this will determine who wins the game
                            whoWonFirstPointInGame = 0;
                        else if (whoWonPoint == 1)
                            whoWonFirstPointInGame = 1;
                    }
                    if (playerOneScore[0] >= games || playerTwoScore[0] >= games) {
                        System.out.println();
                    }
                }
            }
            //if(playerOneScore[0])
        }
    }

    public boolean bothPointScoresAtZero(){
        if(playerOneScore[2] == 0 && playerTwoScore[2] == 0) //if no one has won a point yet
            return true;
        else
            return false;
    }

    public int playPoint(int whoPlays, Hand firstHand, Hand secondHand){
        int trueValueOne = playIndPoint(whoPlays, firstHand); //its called true value, because the card values in the game are kinda weird
        int trueValueTwo = playIndPoint(oppositeOf(whoPlays), secondHand);

        return 12345; //something for now
    }

    //a point will be when both players play one card each
    public int playIndPoint(int whoPlays, Hand hand){
        String choice = getValidInput(whoPlays);
        if(choice.equalsIgnoreCase("t")){
            trucoActive = true;
            trucoTime(whoPlays);
            trucohasBeenDealtWith = true;
            if(trucoActive == true){ //if the "truco streak" is still alive
                System.out.println("Oh snap! Winner of this truco gains ");

            }
            else{ //if someone rejected the truco
                if(playerOneRejectedTruco == true)
                    return 1; //p2 wins point
                else
                    return 0; //p1 wins point
            }
        }
        choice = getValidInput(whoPlays);
        hand.dealCard(gameDeck, Integer.parseInt(choice));


        return 12345; //don't know what yet
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
            trucoActive = false;
            whoRejectedTruco(whoPlays);
            //winGames(getRightPoints(oppositeOf(whoPlays)));
        }
        else if(trucoChoice.equalsIgnoreCase("t")){
            trucoValue += 1;
            trucoTime(oppositeOf(whoPlays));
        }
        else
            System.out.println("Accepted!"); //if they accept, then nothing really happens here. Everything just returns back to playPoint()
    }

    public void whoRejectedTruco(int whoPlays){
        if(whoPlays == 0){
            playerOneRejectedTruco = true;
        }
        else
            playerOneRejectedTruco = false;
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

    public Hand getRightHand(int whoPlays){
        if(whoPlays == 0)
            return handOne;
        else
            return handTwo;
    }

    public String getValidInput(int whoPlays){
        String input;
        while(true){
            if(whoPlays == 0){
                if(trucohasBeenDealtWith == true){
                    System.out.println("Play card: ");
                    input = sc.next();
                    if(input.equalsIgnoreCase("3") && getRightHand(whoPlays).indexExists(2))
                        return "2";
                    else if(input.equalsIgnoreCase("2") && getRightHand(whoPlays).indexExists(1))
                        return "1";
                    else if(input.equalsIgnoreCase("1")) //because of my program, this will always work, don't need to do additional vlaidation
                        return "0";
                    else
                        System.out.println("Invalid input.");
                }
                else if(trucoActive == true){
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
                    System.out.println("Play card (1-3) or truco (t): ");
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
