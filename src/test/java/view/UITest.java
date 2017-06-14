package view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class UITest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    @DisplayName("PrintText prints the same text as it takes")
    void testPrintTextPrintsTheSameTextAsItTakes() {
        String toPrintSting = "Simple text";
        String expectedString = "Simple text\n";
        UI.printText(toPrintSting);
        assertEquals(expectedString, outContent.toString());
    }

    @Test
    @DisplayName("Prepare Welcome Text Returns String Welcome In Tic Tac Toe Game")
    void testPrepareWelcomeTextReturnsStringWelcomeInTicTacToeGame() {
        String expectedString = "Welcome in Tic Tac Toe game!";
        String actualString = UI.prepareWelcomeText();
        assertEquals(expectedString, actualString);
    }
}
