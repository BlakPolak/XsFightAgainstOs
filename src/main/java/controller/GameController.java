package controller;

import model.Board;
import model.GameState;
import model.Seed;
import model.Game;

import java.util.ArrayList;

import static view.UI.*;

public class GameController {
    private static GameController gameController;
    private Game newGame;
    private Integer actualRow;
    private Integer actualColumn;

    private GameController() {
        this.newGame = new Game();
    }

    public static void startGame() {
        gameController = new GameController();
        String welcome =  prepareWelcomeText();
        printText(welcome);
        gameController.playing();

    }

    public void playing(){
        Seed currentPlayer = newGame.getCurrentPlayer();
        String whichPlayerStart = prepareWhichPlayerStartsText(currentPlayer);
        printText(whichPlayerStart);

        while(newGame.getCurrentState().equals(GameState.PLAYING)){
            printBoard(newGame.getBoard());
            String whichPlayersTurn = prepareWhichPlayersTurn(newGame.getCurrentPlayer());
            printText(whichPlayersTurn);
            ArrayList rowAndColumn = takeUserInput();
            this.setActualRow((Integer) rowAndColumn.get(0));
            this.setActualColumn((Integer) rowAndColumn.get(1));
//            Integer selectedRow = (Integer) rowAndColumn.get(0);
//            Integer selectedColumn = (Integer) rowAndColumn.get(1);
//            System.out.println(rowAndColumn.get(0));
//            System.out.println(rowAndColumn.get(1));
            newGame.updateGameState(this.getActualRow(), this.getActualColumn());
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
