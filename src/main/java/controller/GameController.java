package controller;

import model.Board;
import model.GameState;
import model.Seed;
import model.Game;
import view.UI;

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

    private void playing(){
        Seed currentPlayer = newGame.getCurrentPlayer();
        String whichPlayerStart = prepareWhichPlayerStartsText(currentPlayer);
        printText(whichPlayerStart);
        while (true) {
            GameState gameState = newGame.getCurrentState();
            switch (gameState) {
                case PLAYING:
                    this.gameStatePlaying();
                    break;
                case CROSS_WON:
                    UI.prepareWhichPlayerWonText(newGame.getCurrentPlayer());
                    break;
                case NOUGHT_WON:
                    UI.prepareWhichPlayerWonText(newGame.getCurrentPlayer());
                    break;
                case DRAW:
                    break;
            }
        }
    }

    private void gameStatePlaying() {
        printBoard(newGame.getBoard());
        String whichPlayersTurn = prepareWhichPlayersTurn(newGame.getCurrentPlayer());
        printText(whichPlayersTurn);
        ArrayList rowAndColumn = takeUserInput();
        this.setRowAndColumn(rowAndColumn);
        newGame.updateGameState(this.getActualRow(), this.getActualColumn());
    }

    private Integer getActualRow() {
        return this.actualRow;
    }

    private Integer getActualColumn() {
        return this.actualColumn;
    }

    private void setRowAndColumn(ArrayList rowAndColumn) {
        System.out.println(rowAndColumn);
        this.actualRow = (Integer) rowAndColumn.get(0);
        this.actualColumn = (Integer) rowAndColumn.get(1);
    }
}
