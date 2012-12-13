package frutyhell.model;

import static frutyhell.model.BoardItemState.STATE_1;
import static frutyhell.model.BoardItemState.STATE_2;
import junit.framework.TestCase;

public class BoardItemTest extends TestCase {

	private BoardItem item;
	private int row, col;

	public void setUp() {
		row = 1;
		col = 2;
		item = new BoardItem(row, col);
	}

	public void testConstructor() {
		assertEquals(row, item.getRow());
		assertEquals(col, item.getCol());
		assertEquals(STATE_1, item.getState());
	}

	public void testSwitchState() {
		item.switchState();
		assertEquals(STATE_2, item.getState());

		item.switchState();
		assertEquals(STATE_1, item.getState());

		item.switchState();
		assertEquals(STATE_2, item.getState());
	}

	public void testOnAfterSwitchState() {
		BoardItemListener listener = new BoardItemListener() {

			public void onBeforeSwitchState(BoardItem i) {
				assertSame(item, i);
				assertEquals(STATE_1, item.getState());
			}

			public void onAfterSwitchState(BoardItem i) {
				assertSame(item, i);
				assertEquals(STATE_2, item.getState());
			}
		};

		item.attachListener(listener);
		item.switchState();
	}
}
