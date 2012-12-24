package frutyhell.app;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import frutyhell.model.GameModel;
import frutyhell.model.GameModelFactory;
import frutyhell.model.GameModelListener;

public class GameActivity extends Activity {

	private GameModel gameModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		int width = getIntent().getIntExtra("boardWidth", 0);
		int height = getIntent().getIntExtra("boardHeight", 0);
		gameModel = GameModelFactory.getInstance().makeGameModel();
		gameModel.setListener(gameModelListener);
		gameModel.setBoardSize(width, height);
		setContentView(R.layout.activity_game);
		GameBoardView gameBoardView = (GameBoardView) findViewById(R.id.gameBoardView1);
		gameBoardView.setGameBoard(gameModel.getBoard());
		gameBoardView.setListener(gameBoardViewListener);
	}

	private final GameBoardViewListener gameBoardViewListener = new GameBoardViewListener() {

		public void onCellClick(GameBoardView gameBoardView, int row, int col) {
			gameModel.doStep(row, col);
		}
	};

	private final GameModelListener gameModelListener = new GameModelListener() {

		public void onLevelComplete(GameModel model) {
			DialogInterface.OnClickListener clickListener = new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					Intent intent = new Intent(GameActivity.this, MainMenuActivity.class);
					GameActivity.this.startActivity(intent);
				}
			};
			SimpleAlertDialog.show(GameActivity.this, "Поздравляем", "Уровень пройден !", clickListener);
		}
	};
}
