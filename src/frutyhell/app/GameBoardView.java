package frutyhell.app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import frutyhell.model.BoardItem;
import frutyhell.model.GameBoard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class GameBoardView extends ViewGroup {

	private GameBoard board;
	private GridView gridView;
	private Collection<BoardItemView> itemViewCollection;
	private BoardItemBitmapFactory boardItemBitmapFactory;
	private GameBoardViewListener listener;

	public GameBoardView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(null);
	}

	public GameBoardView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(null);
	}

	public GameBoardView(Context context) {
		super(context);
		init(null);
	}

	public GameBoard getGameBoard() {
		return board;
	}

	public void setGameBoard(GameBoard board) {
		this.board = board;
		init(board);
	}

	private void init(GameBoard board) {
		if (board != null) {
			initGridView(board.getHeight(), board.getWidth());
			initItemBitmaps();
			initItemViewCollection(board.getWidth(), board.getHeight(), board.getItems());
		} else {
			// дефолтное игровое поля для еклипсовского редактора вьюшек
			initGridView(5, 5);
			initItemViewCollection(5, 5, Collections.<BoardItem> emptyList());
		}
	}

	public GameBoardViewListener getListener() {
		return listener;
	}

	public void setListener(GameBoardViewListener listener) {
		this.listener = listener;
	}

	private void initGridView(int rows, int cols) {
		gridView = new GridView(getContext());
		gridView.setGridSize(rows, cols);
		gridView.setGridColor(Color.WHITE);
		addView(gridView);
	}

	private void initItemViewCollection(int boardWidth, int boardHeight, Iterable<BoardItem> boardItems) {
		itemViewCollection = new ArrayList<BoardItemView>(boardWidth * boardHeight);
		for (BoardItem item : boardItems) {
			BoardItemView itemView = new BoardItemView(item, getContext());
			itemViewCollection.add(itemView);
			addView(itemView);
		}
	}

	private void initItemBitmaps() {
		this.boardItemBitmapFactory = new BoardItemBitmapFactory(getResources());
		Random rand = new Random();
		Bitmap state1, state2;
		if (rand.nextInt(2) == 0) {
			state1 = boardItemBitmapFactory.getLemonBitmap();
			state2 = boardItemBitmapFactory.getStrawberryBitmap();
		} else {
			state1 = boardItemBitmapFactory.getStrawberryBitmap();
			state2 = boardItemBitmapFactory.getLemonBitmap();
		}
		BoardItemView.setStateBitmaps(state1, state2);
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		gridView.layout(0, 0, right - left, bottom - top);
		layoutItems();
	}

	private void layoutItems() {
		float cellSize = gridView.getCellSize();
		for (BoardItemView itemView : itemViewCollection) {
			float left = cellSize * itemView.getItem().getCol();
			float top = cellSize * itemView.getItem().getRow();
			float right = left + cellSize;
			float bottom = top + cellSize;
			itemView.layout((int) left, (int) top, (int) right, (int) bottom);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float row = event.getY() / gridView.getCellSize();
		float col = event.getX() / gridView.getCellSize();
		if (board.inBoard((int) row, (int) col)) {
			raiseOnCellClick((int) row, (int) col);
		}

		return super.onTouchEvent(event);
	}

	private void raiseOnCellClick(int row, int col) {
		if (listener != null) {
			listener.onCellClick(this, row, col);
		}
	}
}
