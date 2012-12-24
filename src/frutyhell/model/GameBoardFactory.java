package frutyhell.model;

class GameBoardFactory {

	public GameBoard makeGameBoard(int width, int height) {
		return new GameBoard(width, height);
	}
}
