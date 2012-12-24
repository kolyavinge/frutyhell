package frutyhell.model;

public class GameModelListenerStub implements GameModelListener {

	public boolean onLevelCompleteCall = false;
	public GameModel onLevelCompleteCallModelArgument = null;

	public void onLevelComplete(GameModel model) {
		onLevelCompleteCall = true;
		onLevelCompleteCallModelArgument = model;
	}
}
