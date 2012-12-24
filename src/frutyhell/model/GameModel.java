package frutyhell.model;

public class GameModel {

	private GameBoardFactory gameBoardFactory;
	private GameBoard board;
	private GameModelListener listener;

	GameModel(GameBoardFactory gameBoardFactory) {
		this.gameBoardFactory = gameBoardFactory;
	}

	public void setBoardSize(int width, int height) {
		board = gameBoardFactory.makeGameBoard(width, height);
		Shaker shaker = new Shaker(width * height / 2);
		shaker.shake(board);
	}

	public void doStep(int row, int col) {
		board.doStep(row, col);
		if (board.isCompleted()) {
			raiseLevelComplete();
		}
	}

	public GameBoard getBoard() {
		return board;
	}

	public void setListener(GameModelListener listener) {
		this.listener = listener;
	}

	private void raiseLevelComplete() {
		if (listener != null) {
			listener.onLevelComplete(this);
		}
	}
}
