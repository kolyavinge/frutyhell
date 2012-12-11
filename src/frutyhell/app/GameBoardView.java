package frutyhell.app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import frutyhell.model.BoardItem;
import frutyhell.model.GameBoard;

import java.util.ArrayList;
import java.util.Collection;

public class GameBoardView extends View {

	private float cellSize;
	private GameBoard board;
	private Paint paint;
	private Collection<BoardItemView> itemViewCollection;

	public GameBoardView(GameBoard board, Context context) {
		super(context);
		this.board = board;
		this.paint = new Paint();
		this.paint.setColor(Color.WHITE);
		createItemViewCollection();
	}

	public float getCellSize() {
		return cellSize;
	}

	public void setStateBitmaps(Bitmap state1, Bitmap state2) {
		for (BoardItemView itemView : itemViewCollection) {
			itemView.setStateBitmaps(state1, state2);
		}
	}

	@Override
	public void draw(Canvas canvas) {
		calculateCellSize(canvas);
		drawGrid(canvas);
		drawItems(canvas);
	}

	private void drawItems(Canvas canvas) {
		for (BoardItemView itemView : itemViewCollection) {
			int x = (int) (cellSize * itemView.getItem().getCol());
			int y = (int) (cellSize * itemView.getItem().getRow());
			int width = (int) (x + cellSize);
			int height = (int) (y + cellSize);
			itemView.layout(x, y, width, height);
			itemView.draw(canvas);
		}
	}

	private void drawGrid(Canvas canvas) {
		drawHorizontalLines(canvas, paint);
		drawVerticalLines(canvas, paint);
	}

	private void drawHorizontalLines(Canvas canvas, Paint paint) {
		float width = cellSize * board.getWidth() + getLeft();
		for (int row = 0; row <= board.getHeight(); row++) {
			float y = cellSize * row + getTop();
			canvas.drawLine(getLeft(), y, width, y, paint);
		}
	}

	private void drawVerticalLines(Canvas canvas, Paint paint) {
		float height = cellSize * board.getHeight() + getTop();
		for (int col = 0; col <= board.getWidth(); col++) {
			float x = cellSize * col + getLeft();
			canvas.drawLine(x, getTop(), x, height, paint);
		}
	}

	private void calculateCellSize(Canvas canvas) {
		Rect rect = canvas.getClipBounds();

		float width = rect.right - rect.left;
		float height = rect.bottom - rect.top;

		float dx = width / board.getWidth() - 0.1f;
		float dy = height / board.getHeight() - 0.1f;

		cellSize = dx < dy ? dx : dy;
	}

	private void createItemViewCollection() {
		itemViewCollection = new ArrayList<BoardItemView>(board.getWidth() * board.getHeight());
		for (BoardItem item : board.getItems()) {
			BoardItemView itemView = new BoardItemView(item, getContext());
			itemViewCollection.add(itemView);
		}
	}
}
