package frutyhell.app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.ViewGroup;
import frutyhell.model.BoardItem;
import frutyhell.model.GameBoard;

import java.util.ArrayList;
import java.util.Collection;

public class GameBoardView extends ViewGroup {

	private GameBoard board;
	private GridView gridView;
	private Collection<BoardItemView> itemViewCollection;
	private BoardItemBitmapFactory boardItemBitmapFactory;

	public GameBoardView(GameBoard board, Context context) {
		super(context);
		this.board = board;
		initGridView(board.getHeight(), board.getWidth());
		initItemViewCollection();
		initItemBitmaps();
	}

	private void initGridView(int rows, int cols) {
		gridView = new GridView(getContext());
		gridView.setGridSize(rows, cols);
		gridView.setGridColor(Color.WHITE);
		addView(gridView);
	}

	private void initItemViewCollection() {
		itemViewCollection = new ArrayList<BoardItemView>(board.getWidth() * board.getHeight());
		for (BoardItem item : board.getItems()) {
			BoardItemView itemView = new BoardItemView(item, getContext());
			itemViewCollection.add(itemView);
			addView(itemView);
		}
	}

	private void initItemBitmaps() {
		this.boardItemBitmapFactory = new BoardItemBitmapFactory(getResources());
		setStateBitmaps(boardItemBitmapFactory.getState1Bitmap(), boardItemBitmapFactory.getState2Bitmap());
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		gridView.layout(l, t, r, b);
		layoutItems();
	}

	private void layoutItems() {
		float cellSize = gridView.getCellSize();
		for (BoardItemView itemView : itemViewCollection) {
			float left = cellSize * itemView.getItem().getCol() + getLeft();
			float top = cellSize * itemView.getItem().getRow() + getTop();
			float right = left + cellSize;
			float bottom = top + cellSize;
			itemView.layout((int) left, (int) top, (int) right, (int) bottom);
		}
	}

	public float getCellSize() {
		return gridView.getCellSize();
	}

	private void setStateBitmaps(Bitmap state1, Bitmap state2) {
		for (BoardItemView itemView : itemViewCollection) {
			itemView.setStateBitmaps(state1, state2);
		}
	}
}
