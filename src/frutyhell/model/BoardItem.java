package frutyhell.model;

import static frutyhell.model.BoardItemState.*;

public class BoardItem {

	private int row, col;
	private BoardItemState state;

	public BoardItem(int row, int col) {
		this.row = row;
		this.col = col;
		this.state = STATE_1;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public BoardItemState getState() {
		return state;
	}

	void switchState() {
		state = state == STATE_1 ? STATE_2 : STATE_1;
	}
}
