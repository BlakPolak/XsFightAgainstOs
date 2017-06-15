package view;


import model.Board;
import model.Seed;

import java.util.ArrayList;
import java.util.Scanner;

public class UI {

    public static void printText(String text) {
        System.out.println(text);
        System.out.println();
    }

    public static String prepareWelcomeText() {
        String welcomeText = "Welcome in Tic Tac Toe game!";

        return welcomeText;
    }

    public static String prepareWhichPlayerStartsText(Seed currentPlayer) {
        String player;

        if (currentPlayer.equals(Seed.CROSS)) {
            player = " 'X' ";
        } else {
            player = " 'O' ";
        }
        String startPlayerText = String.format("Player %s will be the first player this round!", player);

        return startPlayerText;
    }


    public static String prepareWrongArgument(){
        String wrongArgument = "This move is not valid. Try again...";
        return wrongArgument;
    }

    public static String prepareWhichPlayerWonText(Seed winningPlayer) {
        String wonPlayerText = String.format("Player %s won the game!",  winningPlayer);
        return wonPlayerText;
    }

    public static String preparePlayAgaintText() {
        String welcomeText = "Would you like to play again? [y/n]";
        return welcomeText;
    }


    public static boolean takeUserCharInput(){
        boolean playAgainOrNot = false;
        try {
            Scanner read = new Scanner(System.in);
            String input = read.nextLine().replaceAll("\\s+","");
            if ( input.length() == 1 ) {
                char yesOrNo = input.charAt(0);
                if (Character.toString(yesOrNo) == "y") {
                    playAgainOrNot = true;
                } else if (Character.toString(yesOrNo) == "n") {
                    playAgainOrNot = false;
                }
            } else {
                throw new NumberFormatException();
            }
        } catch ( NumberFormatException e ){
            takeUserCharInput();
        }
        return playAgainOrNot;
    }


    public static ArrayList<Integer> takeUserInput() {
        ArrayList<Integer> rowAndColumnList = new ArrayList<>();
        try {
            Scanner read = new Scanner(System.in);

            String inputs = read.nextLine().replaceAll("\\s+","");
            if ( inputs.length() == 2 ) {
                Integer givenRow = Integer.parseInt(inputs.substring(0,1)) -1;
                Integer givenColumn = Integer.parseInt(inputs.substring(1,2)) -1;
                System.out.println(givenColumn);
                System.out.println(givenRow);
                rowAndColumnList.add(givenRow);
                rowAndColumnList.add(givenColumn);
            } else {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e){
            String textToPrint =prepareWrongArgument();
            printText(textToPrint);
            takeUserInput();
        }

        System.out.println(rowAndColumnList);
        return rowAndColumnList;
    }


    public static String prepareWhichPlayersTurn(Seed currentPlayer) {
        String player;

        if (currentPlayer.equals(Seed.CROSS)) {
            player = " 'X' ";
        } else {
            player = " 'O' ";
        }
        String whichPlayersTurnText = String.format("Player %s, enter your move (row[1-3], column[1-3]): ", player);

        return whichPlayersTurnText;
    }

    public static void printBoard(Board board) {

        for (int row = 0; row < 3; row++) {
            for (int column = 0; column <= 2; column++) {

                if (board.getCells()[row][column].getContent().equals(Seed.EMPTY)) {
                    System.out.print("   ");
                } else if (board.getCells()[row][column].getContent().equals(Seed.CROSS)) {
                    System.out.print(" X ");
                } else if (board.getCells()[row][column].getContent().equals(Seed.NOUGHT)) {
                    System.out.print(" O ");
                }
                if (column < board.getCells().length - 1) {
                    System.out.print("|");
                }
            }
            if (row < board.getCells().length - 1){
                System.out.println("\n-----------");
            }
        }
        System.out.println("\n");
    }

}