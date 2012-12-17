package frutyhell.app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.ViewGroup;
import frutyhell.model.BoardItem;
import frutyhell.model.GameBoard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class GameBoardView extends ViewGroup {

	private GameBoard board;
	private GridView gridView;
	private Collection<BoardItemView> itemViewCollection;
	private BoardItemBitmapFactory boardItemBitmapFactory;

	public GameBoardView(GameBoard board, Context context) {
		super(context);
		this.board = board;
		initGridView(board.getHeight(), board.getWidth());
		initItemBitmaps();
		initItemViewCollection();
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

	public float getCellSize() {
		return gridView.getCellSize();
	}
}
