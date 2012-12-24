package frutyhell.model;

public class GameModelFactory {

	public static GameModelFactory instance = new GameModelFactory();

	public static GameModelFactory getInstance() {
		return instance;
	}

	private GameModelFactory() {
	}

	public GameModel makeGameModel() {
		GameBoardFactory gameBoardFactory = new GameBoardFactory();
		GameModel gameModel = new GameModel(gameBoardFactory);

		return gameModel;
	}
}
