package frutyhell.model;

import junit.framework.TestCase;

public class GameModelTest extends TestCase {

	private GameModel model;

	public void testLevelComplete() {
		GameBoardFactory gameBoardFactory = new GameBoardFactory() {
			@Override
			public GameBoard makeGameBoard(int width, int height) {
				return new ConstantGameBoard(width, height);
			}
		};
		GameModelListenerStub listener = new GameModelListenerStub();
		model = new GameModel(gameBoardFactory);
		model.setListener(listener);
		model.setBoardSize(6, 6);
		model.doStep(0, 0);
		assertTrue(listener.onLevelCompleteCall);
		assertSame(model, listener.onLevelCompleteCallModelArgument);
	}
}
