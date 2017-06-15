package controller;

import model.Board;
import model.GameState;
import model.Seed;
import model.Game;

import java.util.ArrayList;

import static view.UI.*;

public class GameController {
    static Game newGame;
    private Integer actualRow;
    private Integer actualColumn;


    public static void startGame() {
        newGame = new Game();
        String welcome =  prepareWelcomeText();
        printText(welcome);
        playing();

    }

    public static void playing(){
        Seed currentPlayer = newGame.getCurrentPlayer();
        String whichPlayerStart = prepareWhichPlayerStartsText(currentPlayer);
        printText(whichPlayerStart);

        while(newGame.getCurrentState().equals(GameState.PLAYING)){
            printBoard(newGame.getBoard());
            String whichPlayersTurn = prepareWhichPlayersTurn(newGame.getCurrentPlayer());
            printText(whichPlayersTurn);
            ArrayList rowAndColumn = takeUserInput();
            Integer selectedRow = (Integer) rowAndColumn.get(0);
            Integer selectedColumn = (Integer) rowAndColumn.get(1);
            System.out.println(rowAndColumn.get(0));
            System.out.println(rowAndColumn.get(1));
            newGame.updateGameState(selectedRow, selectedColumn);
        }
    }

    public Integer getActualRow() {
        return this.actualRow;
    }

    public Integer getActualColumn() {
        return this.actualColumn;
    }

    public void setActualColumn(Integer actualColumn) {
        this.actualColumn = actualColumn;
    }

    public void setActualRow(Integer actualRow) {
        this.actualRow = actualRow;
    }
}
