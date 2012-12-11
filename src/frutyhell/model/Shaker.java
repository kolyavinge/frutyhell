package frutyhell.model;

import java.util.Random;

public class Shaker {

	private int shakeItemsCount;

	public Shaker(int shakeItemsCount) {
		if (shakeItemsCount <= 0) {
			throw new IllegalArgumentException();
		}

		this.shakeItemsCount = shakeItemsCount;
	}

	public void shake(GameBoard board) {
		verifyShakeItemsCount(board);
		
		Random rand = new Random();
		boolean[][] stepMatrix = new boolean[board.getHeight()][board.getWidth()];

		for (int step = 0; step < shakeItemsCount;) {

			int row = rand.nextInt(board.getHeight());
			int col = rand.nextInt(board.getWidth());

			if (stepMatrix[row][col] == false) {
				stepMatrix[row][col] = true;
				board.doStep(row, col);
				step++;
			}
		}
	}

	private void verifyShakeItemsCount(GameBoard board) {
		if (shakeItemsCount > board.getWidth() * board.getHeight()) {
			throw new IllegalArgumentException();
		}
	}
}
