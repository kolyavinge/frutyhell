package frutyhell.model;

class BoardItemListenerStub implements BoardItemListener {

	public boolean onBeforeSwitchStateCall = false;
	public boolean onAfterSwitchStateCall = false;

	public BoardItem onBeforeSwitchStateBoardItem;
	public BoardItem onAfterSwitchStateBoardItem;

	public void onBeforeSwitchState(BoardItem item) {
		onBeforeSwitchStateCall = true;
		onBeforeSwitchStateBoardItem = item;
	}

	public void onAfterSwitchState(BoardItem item) {
		onAfterSwitchStateCall = true;
		onAfterSwitchStateBoardItem = item;
	}
}
