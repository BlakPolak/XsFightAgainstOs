package controller;

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
        Seed currentPlayer = this.getGame().getCurrentPlayer();
        String whichPlayerStart = prepareWhichPlayerStartsText(currentPlayer);
        printText(whichPlayerStart);
        while (true) {
            GameState gameState = this.getGame().getCurrentState();
            switch (gameState) {
                case PLAYING:
                    this.gameStatePlaying();
                    break;
                case CROSS_WON:
                    this.playerWon();
                    break;
                case NOUGHT_WON:
                    this.playerWon();
                    break;
                case DRAW:
                    break;
            }
        }
    }

    private void gameStatePlaying() {
        printBoard(this.getGame().getBoard());
        String whichPlayersTurn = prepareWhichPlayersTurn(this.getGame().getCurrentPlayer());
        printText(whichPlayersTurn);
        ArrayList rowAndColumn = takeUserInput();
        this.setRowAndColumn(rowAndColumn);
        newGame.updateGameState(this.getActualRow(), this.getActualColumn());
    }

    private void playerWon() {
        Game game = this.getGame();
        printBoard(game.getBoard());
        String wonText = UI.prepareWhichPlayerWonText(game.getCurrentPlayer());
        UI.printText(wonText);
        String playAgainText = UI.preparePlayAgaintText();
        UI.printText(playAgainText);
        boolean userDecision = UI.takeUserCharInput();
        if (userDecision) {
            game.initGame();
        } else {
            game.initGame();
        }
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

    private Game getGame() {
        return this.newGame;
    }
}
