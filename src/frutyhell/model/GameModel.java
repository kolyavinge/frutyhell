package frutyhell.model;

public class GameModel {

	private GameBoard board;
	private GameModelListener listener;

	public GameModel() {
		board = new GameBoard(5, 5);
		Shaker shaker = new Shaker(5);
		shaker.shake(board);
	}

	public void doStep(int row, int col) {
		board.doStep(row, col);
	}

	public void doStep(BoardItem item) {
		board.doStep(item);
	}

	public GameBoard getBoard() {
		return board;
	}

	public void setListener(GameModelListener listener) {
		this.listener = listener;
	}
}
