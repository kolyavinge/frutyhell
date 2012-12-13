package frutyhell.model;

import java.util.*;
import static frutyhell.model.BoardItemState.*;

public class GameBoard {

	private int width, height;
	private BoardItem[][] items;
	private Collection<BoardItem> invertedItems;

	public GameBoard(int width, int height) {
		this.width = width;
		this.height = height;
		createGameMatrix();
	}

	private void createGameMatrix() {
		items = new BoardItem[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				items[i][j] = new BoardItem(i, j);
			}
		}
	}

	public Iterable<BoardItem> getItems() {
		Collection<BoardItem> result = new ArrayList<BoardItem>(width * height);
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				result.add(items[i][j]);
			}
		}

		return result;
	}

	public BoardItem getItem(int row, int col) {
		return items[row][col];
	}

	boolean isCompleted() {
		BoardItemState completeState = items[0][0].getState();

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (items[i][j].getState() != completeState) {
					return false;
				}
			}
		}

		return true;
	}

	void doStep(int row, int col) {
		invertedItems = new ArrayList<BoardItem>(width + height - 1);

		// инвертируем ячейки по столбцам
		for (int i = 0; i < width; i++) {
			items[row][i].switchState();
			invertedItems.add(items[row][i]);
		}

		// инвертируем ячейки по строкам
		for (int i = 0; i < height; i++) {
			if (i != row) {
				items[i][col].switchState();
				invertedItems.add(items[i][col]);
			}
		}
	}

	void doStep(BoardItem item) {
		doStep(item.getRow(), item.getCol());
	}

	Collection<BoardItem> getLastInvertedItems() {
		return invertedItems;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
