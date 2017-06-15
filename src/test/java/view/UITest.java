package view;

import model.Seed;
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
    @DisplayName("PrintText prints the text as expected")
    void testIfPrintTextPrintsTextInExpectedWay() {
        String toPrintSting = "Simple text";
        String expectedString = "Simple text\n\n";
        UI.printText(toPrintSting);
        assertEquals(expectedString, outContent.toString());
    }

    @Test
    @DisplayName("Prepare Welcome Text Returns String Welcome In Tic Tac Toe Game")
    void testIfPrepareWelcomeTextReturnsExpectedString() {
        String expectedString = "Welcome in Tic Tac Toe game!";
        String actualString = UI.prepareWelcomeText();
        assertEquals(expectedString, actualString);
    }

    @Test
    @DisplayName("Prepare Which Player Starts Text Returns String 'Player X/O will be the first player this round!'")
    void testIfPrepareWhichPlayerStartsTextReturnsExpectedString() {
        String expectedString = "Player  'X'  will be the first player this round!";
        Seed player = Seed.CROSS;
        String actualString = UI.prepareWhichPlayerStartsText(player);
        assertEquals(expectedString, actualString);
    }

    @Test
    @DisplayName("Prepare Wrong Argument Text Returns String 'This move is not valid. Try again...'")
    void testIfPrepareWrongArgumentTextReturnsExpectedString() {
        String expectedString = "This move is not valid. Try again...";
        String actualString = UI.prepareWrongArgumentText();
        assertEquals(expectedString, actualString);
    }

    @Test
    @DisplayName("Prepare Which Player Won Text Returns String 'Player X/O won the game!'")
    void testIfPrepareWhichPlayerWonTextReturnsExpectedString() {
        String expectedString = "Player 'O' won the game!";
        Seed wonPlayer = Seed.NOUGHT;
        String actualString = UI.prepareWhichPlayerWonText(wonPlayer);
        assertEquals(expectedString, actualString);
    }

    @Test
    @DisplayName("Prepare Play Again Text Returns String 'Would you like to play again? [y/n]'")
    void testIfPreparePlayAgainTextReturnsExpectedString() {
        String expectedString = "Would you like to play again? [y/n]";
        String actualString = UI.preparePlayAgainText();
        assertEquals(expectedString, actualString);
    }


    @Test
    @DisplayName("Prepare Which Players Turn Text Returns String 'Player 'X/O', enter your move (row[1-3], column[1-3]):'")
    void testWhichPlayersTurnTextReturnsExpectedString() {
        String expectedString = "Player 'X', enter your move (row[1-3], column[1-3]): ";
        Seed currentPlayer = Seed.CROSS;
        String actualString = UI.prepareWhichPlayersTurnText(currentPlayer);
        assertEquals(expectedString, actualString);
    }

    @Test
    @DisplayName("Prepare Draw Text Returns String 'Unfortunately no one won - there is a draw!'")
    void testIfPrepareDrawTextReturnsExpectedString() {
        String expectedString = "Unfortunately no one won - there is a draw!";
        String actualString = UI.prepareDrawText();
        assertEquals(expectedString, actualString);
    }

    @Test
    @DisplayName("Prepare Cell Is Occupied Text Returns String 'Unfortunately no one won - there is a draw!'")
    void testIfPrepareCellIsOccupiedTextReturnsExpectedString() {
        String expectedString = "Sorry, but this field is already occupied, select another empty field";
        String actualString = UI.prepareCellIsOccupiedText();
        assertEquals(expectedString, actualString);
    }

}
