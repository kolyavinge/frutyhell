package frutyhell.app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import frutyhell.model.BoardItem;
import frutyhell.model.GameBoard;
import frutyhell.model.GameModel;

public class GameView extends View {

	private GameModel gameModel;
	private GameBoardView gameBoardView;
	private BoardItemBitmapFactory boardItemBitmapFactory;

	public GameView(GameModel gameModel, Context context) {
		super(context);
		this.gameModel = gameModel;
		this.boardItemBitmapFactory = new BoardItemBitmapFactory(getResources());
		this.gameBoardView = new GameBoardView(gameModel.getBoard(), context);
		this.gameBoardView.setStateBitmaps(boardItemBitmapFactory.getState1Bitmap(), boardItemBitmapFactory.getState2Bitmap());
		this.gameBoardView.layout(0, 1, 0, 0);
	}

	@Override
	public void draw(Canvas canvas) {
		fillBackground(canvas);
		gameBoardView.draw(canvas);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int row = (int) (event.getY() / gameBoardView.getCellSize());
		int col = (int) (event.getX() / gameBoardView.getCellSize());
		gameModel.doStep(row, col);
		invalidate();

		return super.onTouchEvent(event);
	}

	private void fillBackground(Canvas canvas) {
		Paint paint = new Paint();
		paint.setColor(Color.BLACK);
		canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), paint);
	}
}
