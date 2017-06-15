package controller;

import model.GameState;
import model.Seed;
import model.Game;
import view.UI;

import java.util.ArrayList;

public class GameController {
    private static GameController gameController;
    private UI ui;
    private Game newGame;
    private Integer actualRow;
    private Integer actualColumn;
    private boolean play;

    private GameController() {
        this.newGame = new Game();
        this.ui = new UI();
    }

    public static void startGame() {
        gameController = new GameController();
        String welcome = gameController.ui.prepareWelcomeText();
        gameController.ui.printText(welcome);
        gameController.setPlayOn();
        gameController.playing();
    }

    private void setPlayOn() {
        this.play = true;
    }

    private void togglePlay() {
        this.play = !this.play;
    }

    private void playing(){
        Seed currentPlayer = this.getGame().getCurrentPlayer();
        String whichPlayerStart = this.ui.prepareWhichPlayerStartsText(currentPlayer);
        this.ui.printText(whichPlayerStart);
        while (this.play) {
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
                    draw();
                    break;
            }
        }
    }

    private void gameStatePlaying() {
        this.ui.printBoard(this.getGame().getBoard());
        String whichPlayersTurn = this.ui.prepareWhichPlayersTurnText(this.getGame().getCurrentPlayer());
        this.ui.printText(whichPlayersTurn);
        ArrayList rowAndColumn = this.ui.takeUserInput();
        while (rowAndColumn.size() != 2) {
            rowAndColumn = this.ui.takeUserInput();
        }
        this.setRowAndColumn(rowAndColumn);
        newGame.updateGameState(this.getActualRow(), this.getActualColumn());
    }

    private void playerWon() {
        Game game = this.getGame();
        this.ui.printBoard(game.getBoard());
        String wonText = this.ui.prepareWhichPlayerWonText(game.getCurrentPlayer());
        this.ui.printText(wonText);
        playAgain();
    }

    private void playAgain() {
        Game game = this.getGame();
        String playAgainText = this.ui.preparePlayAgainText();
        this.ui.printText(playAgainText);
        boolean isCorrectAnswer = false;
        while (!isCorrectAnswer) {
            try {
                boolean userDecision = this.ui.takeUserCharInput();
                isCorrectAnswer = true;
                if (userDecision) {
                    game.initGame();
                } else {
                    this.togglePlay();
                }
            } catch (IllegalArgumentException e) {
                String wrongArgumentText = this.ui.prepareWrongArgumentText();
                this.ui.printText(wrongArgumentText);
            }
        }
    }

    private void draw() {
        String drawText = this.ui.prepareDrawText();
        this.ui.printText(drawText);
        playAgain();
    }

    private Integer getActualRow() {
        return this.actualRow;
    }

    private Integer getActualColumn() {
        return this.actualColumn;
    }

    private void setRowAndColumn(ArrayList rowAndColumn) {
        this.actualRow = (Integer) rowAndColumn.get(0);
        this.actualColumn = (Integer) rowAndColumn.get(1);
    }

    private Game getGame() {
        return this.newGame;
    }
}
