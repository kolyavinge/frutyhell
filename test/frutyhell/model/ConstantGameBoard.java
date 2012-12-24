package frutyhell.model;

public class ConstantGameBoard extends GameBoard {

	public ConstantGameBoard(int width, int height) {
		super(width, height);
	}

	@Override
	void doStep(int row, int col) {
	}

	@Override
	boolean isCompleted() {
		return true;
	}
}
