import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {

    @Test
    @DisplayName("Check if Game can be initialized")
    void testIfBoardCanBeInitialized() {
        Game game = new Game();
        assertEquals(Game.class, game.getClass());
    }


    @Nested
    class GameInitialized {

        Game testGame;

        @BeforeEach
        void setUp() {
            testGame = new Game();
            testGame.initGame();
        }

        @Test
        @DisplayName("Check if Game initialize board")
        void testIfGameInitializeBoard() {
            assertEquals(Board.class, testGame.getBoard().getClass());
        }

        @Test
        @DisplayName("Check if Game initialize currentState")
        void testIfGameInitializeGameState() {
            assertEquals(GameState.class, testGame.getCurrentState().getClass());
        }

        @Test
        @DisplayName("Check if Game initialize currentPlayer")
        void testIfGameInitializeCurrentPlayer() {
            assertEquals(Seed.class, testGame.getCurrentPlayer().getClass());
        }

        @Test
        @DisplayName("Check if randomPlayer() return player")
        void testIfRandomPlayerMethodReturnPlayer() {
            assertEquals(Seed.class, testGame.getCurrentPlayer().getClass());
        }

        @Nested
        class UpdateState{
            Game testGame;
            Integer testRow;
            Integer testColumn;

            @BeforeEach
            void setUp() {
                testGame = new Game();
                testGame.initGame();
                testRow = 1;
                testColumn = 2;
                testGame.setCurrentPlayer(Seed.CROSS);
            }

            @Test
            @DisplayName("Check if Update State method change player ")
            void testIfUpdateGameStateChangeCurrentPlayer() {
                testGame.updateGameState(testRow, testColumn);
                assertEquals(Seed.NOUGHT, testGame.getCurrentPlayer());
            }

            @Test
            @DisplayName("Check if Update State update board ")
            void testIfRandomPlayerMethodUpdateBoard() {
                testGame.updateGameState(testRow, testColumn);
                assertEquals(Seed.CROSS, testGame.getBoard().getCells()[testRow][testColumn].getContent());
            }

        }
    }
}
