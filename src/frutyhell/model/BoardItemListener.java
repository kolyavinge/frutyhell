package frutyhell.model;

public interface BoardItemListener {

	void onBeforeSwitchState(BoardItem item);
	
	void onAfterSwitchState(BoardItem item);
}
