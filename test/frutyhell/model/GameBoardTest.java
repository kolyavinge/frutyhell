package frutyhell.model;

import static frutyhell.model.BoardItemState.STATE_1;
import static frutyhell.model.BoardItemState.STATE_2;

import java.util.Collection;
import java.util.Iterator;

import junit.framework.TestCase;

public class GameBoardTest extends TestCase {

	private int width, height;
	private GameBoard board;

	public void setUp() {
		width = 10;
		height = 20;
		board = new GameBoard(width, height);
	}

	public void testConstructor() {
		Iterable<BoardItem> items = board.getItems();
		int row = 0, col = 0;
		for (BoardItem item : items) {
			assertEquals(row, item.getRow());
			assertEquals(col, item.getCol());
			assertEquals(STATE_1, item.getState());
			if (++col == width) {
				row++;
				col = 0;
			}
		}
	}

	public void testDoStep_LeftUpCorner() {
		board.doStep(0, 0);

		for (int i = 0; i < height; i++) {
			BoardItem item = board.getItem(i, 0);
			assertEquals(STATE_2, item.getState());
		}

		for (int i = 0; i < width; i++) {
			BoardItem item = board.getItem(0, i);
			assertEquals(STATE_2, item.getState());
		}

		for (int i = 1; i < height; i++) {
			for (int j = 1; j < width; j++) {
				BoardItem item = board.getItem(i, j);
				assertEquals(STATE_1, item.getState());
			}
		}
	}

	public void testDoStep_RightDownCorner() {
		board.doStep(height - 1, width - 1);

		for (int i = 0; i < height; i++) {
			BoardItem item = board.getItem(i, width - 1);
			assertEquals(STATE_2, item.getState());
		}

		for (int i = 0; i < width; i++) {
			BoardItem item = board.getItem(height - 1, i);
			assertEquals(STATE_2, item.getState());
		}

		for (int i = 0; i < height - 1; i++) {
			for (int j = 0; j < width - 1; j++) {
				BoardItem item = board.getItem(i, j);
				assertEquals(STATE_1, item.getState());
			}
		}
	}

	public void testGetLastInvertedItems() {
		board.doStep(5, 5);
		Collection<BoardItem> items = board.getLastInvertedItems();

		assertEquals(10 + 20 - 1, items.size());

		Iterator<BoardItem> iter = items.iterator();
		for (int i = 0; i < width && iter.hasNext(); i++) {
			BoardItem item = iter.next();
			assertEquals(i, item.getCol());
			assertEquals(5, item.getRow());
		}

		for (int i = 0; i < height && iter.hasNext(); i++) {
			if (i != 5) {
				BoardItem item = iter.next();
				assertEquals(5, item.getCol());
				assertEquals(i, item.getRow());
			}
		}
	}

	public void testIsCompleted_Init() {
		assertTrue(board.isCompleted());
	}

	public void testIsCompleted_AllItemsSwitched() {
		for (BoardItem item : board.getItems()) {
			item.switchState();
		}
		assertTrue(board.isCompleted());
	}
}
