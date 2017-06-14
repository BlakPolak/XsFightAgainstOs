
public class Game {
    Board board;
    private GameState currentState;
    private Seed currentPlayer;

    public Game(){
        initGame();
    }

    public void initGame() {
        this.board = new Board();
        this.currentState = GameState.PLAYING;
        this.getRandomPlayer();
    }

    public Board getBoard() {
        return board;
    }

    public Object getCurrentState() {
        return currentState;
    }

    public void setCurrentPlayer(Seed currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Object getCurrentPlayer() {
        return currentPlayer;
    }

    private void getRandomPlayer() {
        int randomNumber = (int) Math.random() *100 -1;
        if ( randomNumber >= 50 ) {
            this.setCurrentPlayer(Seed.CROSS);
        } else {
            this.setCurrentPlayer(Seed.NOUGHT);
        }
    }

    public void updateGameState(Integer row, Integer column) {
        if (this.board.hasWon(currentPlayer , row, column)) {
            if (currentPlayer.equals(Seed.CROSS)) {
                this.currentState = GameState.CROSS_WON;
            } else {
                this.currentState = GameState.NOUGHT_WON;
            }
        } else if (this.board.isDraw()) {
            this.currentState = GameState.DRAW;
        }
        this.updateCurrentPlayer();
    }

    private void updateCurrentPlayer() {
        if (currentPlayer.equals(Seed.CROSS)){
            this.currentPlayer = Seed.NOUGHT;
        } else {
            this.currentPlayer = Seed.CROSS;
        }
    }
}
