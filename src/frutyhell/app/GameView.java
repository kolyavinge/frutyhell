package frutyhell.app;

import android.content.Context;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.ViewGroup;
import frutyhell.model.GameModel;

public class GameView extends ViewGroup {

	private GameModel gameModel;
	private GameBoardView gameBoardView;

	public GameView(GameModel gameModel, Context context) {
		super(context);
		setBackgroundColor(Color.BLACK);
		this.gameModel = gameModel;
		this.gameBoardView = new GameBoardView(gameModel.getBoard(), context);
		addView(this.gameBoardView);
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		gameBoardView.layout(left, top + 1, right, bottom);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float row = event.getY() / gameBoardView.getCellSize();
		float col = event.getX() / gameBoardView.getCellSize();
		gameModel.doStep((int) row, (int) col);

		return super.onTouchEvent(event);
	}
}
