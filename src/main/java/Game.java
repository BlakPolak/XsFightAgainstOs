
public class Game {
    Board board;
    GameState currentState;
    Seed currentPlayer;

    public void initGame() {
    }

    public Board getBoard() {
        return board;
    }

    public Object getCurrentState() {
        return currentState;
    }

    public Object getCurrentPlayer() {
        return currentPlayer;
    }

    public Seed getRandomPlayer() {
        Seed randomPlayer;

        int randomNumber = (int) Math.random() *100 -1;
        if ( randomNumber >= 50 ) {
            randomPlayer = Seed.CROSS;
        } else {
            randomPlayer = Seed.NOUGHT;
        }
        return  randomPlayer;
    }

    public void updateGameState(Seed testSeed, Integer testRow, Integer testColumn) {
    }
}
