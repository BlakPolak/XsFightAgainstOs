
public class Game {
    Board board;
    GameState currentState;
    Seed currentPlayer;

    public Game(){
        initGame();
    }

    public void initGame() {
        this.board = new Board();
        this.currentState = GameState.PLAYING;
        this.currentPlayer = getRandomPlayer();
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


    public void updateGameState(Seed currentPlayer, Integer row, Integer column) {
        if (this.board.hasWon(currentPlayer , row, column)) {
            if (currentPlayer.equals(Seed.CROSS)) {
                this.currentState = GameState.CROSS_WON;
            } else {
                this.currentState = GameState.NOUGHT_WON;
            }
        } else if (this.board.isDraw()) {
            this.currentState = GameState.DRAW;
        }

        if (currentPlayer.equals(Seed.CROSS)){
            this.currentPlayer = Seed.NOUGHT;
        } else {
            this.currentPlayer = Seed.CROSS;
        }
    }
}
