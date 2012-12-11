package frutyhell.model;

import junit.framework.TestCase;

public class ShakerTest extends TestCase {

	public void testShake() {
		GameBoard board = new GameBoard(5, 10);
		Shaker shaker = new Shaker(5);

		shaker.shake(board);

		assertFalse(board.isCompleted());
	}

	public void testZeroShakeItemsCount() {
		try {
			new Shaker(0);
			fail();
		} catch (IllegalArgumentException exp) {
		}
	}

	public void testNegativeShakeItemsCount() {
		try {
			new Shaker(-10);
			fail();
		} catch (IllegalArgumentException exp) {
		}
	}

	public void testBigShakeItemsCount() {
		try {
			GameBoard board = new GameBoard(5, 10);
			Shaker shaker = new Shaker(5 * 10 + 1);
			shaker.shake(board);
			fail();
		} catch (IllegalArgumentException exp) {
		}
	}
}
