package controller;

import model.Game;
import model.GameState;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import view.UI;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GameControllerTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private UI ui;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
//        ui = new UI();
        ui = mock(UI.class);
    }

    @AfterEach
    void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    @DisplayName("Ask if continue playing if draw")
    void testAskIfContinuePlayingIfDraw() {
        GameController gameController = mock(GameController.class);
        Game game = mock(Game.class);
//        when(new Game()).thenReturn(game);
//        when(gameController.getGame()).thenReturn(game);
        when(game.getCurrentState()).thenReturn(GameState.DRAW);
        when(ui.takeUserCharInput()).thenReturn(false);
//        assertAll();

//        String toPrintSting = "Simple text";
        String expectedString = "Unfortunately no one won - there is a draw!";
//        ui.printText(toPrintSting);
        GameController.startGame();
        assertEquals(expectedString, outContent.toString());
    }

}
