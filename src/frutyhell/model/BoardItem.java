package frutyhell.model;

import static frutyhell.model.BoardItemState.STATE_1;
import static frutyhell.model.BoardItemState.STATE_2;

import java.util.ArrayList;
import java.util.Collection;

public class BoardItem {

	private int row, col;
	private BoardItemState state;
	private Collection<BoardItemListener> listeners;

	BoardItem(int row, int col) {
		this.row = row;
		this.col = col;
		this.state = STATE_1;
		this.listeners = new ArrayList<BoardItemListener>();
	}
	
	public void attachListener(BoardItemListener listener) {
		this.listeners.add(listener);
	}
	
	public void detachListener(BoardItemListener listener) {
		this.listeners.remove(listener);
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
		raiseOnBeforeSwitch();
		state = state == STATE_1 ? STATE_2 : STATE_1;
		raiseOnAfterSwitch();
	}
	
	private void raiseOnAfterSwitch() {
		for (BoardItemListener listener : listeners) {
			listener.onAfterSwitchState(this);
		}
	}
	
	private void raiseOnBeforeSwitch() {
		for (BoardItemListener listener : listeners) {
			listener.onBeforeSwitchState(this);
		}
	}
}
