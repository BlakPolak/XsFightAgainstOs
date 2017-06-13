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
            assertEquals(Seed.class, testGame.getRandomPlayer());
        }

        @Nested
        class UpdateState{

            Seed testSeed;
            Integer testRow;
            Integer testColumn;

            @BeforeEach
            void setUp() {
                testSeed = Seed.CROSS;
                testRow = 1;
                testColumn = 2;
            }


            @Test
            @DisplayName("Check if Update State method change player ")
            void testIfRandomPlayerMethodUpdatePlayer() {
                testGame.updateGameState(testSeed, testRow, testColumn);
                assertEquals(Seed.NOUGHT, testGame.getCurrentPlayer());
            }

            @Test
            @DisplayName("Check if Update State update board ")
            void testIfRandomPlayerMethodUpdateBoard() {
                testGame.updateGameState(testSeed, testRow, testColumn);
                assertEquals(Seed.CROSS, testGame.getBoard().getCells()[testRow][testColumn].getContent());
            }

        }

    }
}
