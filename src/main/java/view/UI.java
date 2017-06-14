package view;


import model.Board;
import model.Seed;

import java.util.Scanner;

public class UI {

    public static void printText(String text) {
        System.out.println(text);
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


    public static String takeUserInput() {
        String userInput;
        Scanner read = new Scanner(System.in);

        userInput = read.nextLine();

        return userInput;
    }

    public static void printBoard(Board board) {

        for (int row = 0; row < 3; row++) {
            for (int column = 0; column <= 2; column++) {

                if (board.getCells()[row][column].getContent().equals(Seed.EMPTY)) {
                    System.out.print("  ");
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
                System.out.println("\n--------");
            }
        }
    }

}