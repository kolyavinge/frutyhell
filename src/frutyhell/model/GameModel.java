package frutyhell.model;

public class GameModel {

	private GameBoard board;

	public GameModel() {
		board = new GameBoard(6, 7);
		Shaker shaker = new Shaker(2);
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
}
