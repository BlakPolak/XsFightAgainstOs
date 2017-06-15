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
    private boolean play;

    private GameController() {
        this.newGame = new Game();
    }

    public static void startGame() {
        gameController = new GameController();
        String welcome =  prepareWelcomeText();
        printText(welcome);
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
        String whichPlayerStart = prepareWhichPlayerStartsText(currentPlayer);
        printText(whichPlayerStart);
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
        boolean isCorrectAnswer = false;
        while (!isCorrectAnswer) {
            try{
                printBoard(this.getGame().getBoard());
                String whichPlayersTurn = prepareWhichPlayersTurnText(this.getGame().getCurrentPlayer());
                printText(whichPlayersTurn);
                ArrayList rowAndColumn = takeUserInput();
                while (rowAndColumn.size() != 2) {
                    rowAndColumn = takeUserInput();
                }
                this.setRowAndColumn(rowAndColumn);
                newGame.updateGameState(this.getActualRow(), this.getActualColumn());
                isCorrectAnswer = true;
            } catch (IllegalArgumentException e){
                String occupiedText = prepareThisMoveIsForbiddenText();
                printText(occupiedText);
            }
        }
    }

    private void playerWon() {
        Game game = this.getGame();
        printBoard(game.getBoard());
        String wonText = UI.prepareWhichPlayerWonText(game.getCurrentPlayer());
        UI.printText(wonText);
        playAgain();
    }

    private void playAgain() {
        Game game = this.getGame();
        String playAgainText = UI.preparePlayAgainText();
        UI.printText(playAgainText);
        boolean isCorrectAnswer = false;
        while (!isCorrectAnswer) {
            try {
                boolean userDecision = UI.takeUserCharInput();
                isCorrectAnswer = true;
                if (userDecision) {
                    game.initGame();
                } else {
                    this.togglePlay();
                }
            } catch (IllegalArgumentException e) {
                String wrongArgumentText = prepareWrongArgumentText();
                printText(wrongArgumentText);
            }
        }
    }

    private void draw() {
        String drawText = UI.prepareDrawText();
        UI.printText(drawText);
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
