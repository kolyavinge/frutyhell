package frutyhell.model;

import java.util.ArrayList;
import java.util.Collection;

public class GameBoard {

	public static final int MIN_WIDTH = 4;
	public static final int MIN_HEIGHT = 4;
	public static final int MAX_WIDTH = 10;
	public static final int MAX_HEIGHT = 10;

	private int width, height;
	private BoardItem[][] items;
	private Collection<BoardItem> invertedItems;

	public static boolean isValidBoardSize(int width, int height) {
		return (MIN_WIDTH <= width && width <= MAX_WIDTH) && (MIN_HEIGHT <= height && height <= MAX_HEIGHT);
	}

	public static void validateBoardSize(int width, int height) {
		if (isValidBoardSize(width, height) == false) {
			String message = String.format("Допустимый размер поля от %dх%d до %dх%d", MIN_WIDTH, MIN_HEIGHT, MAX_WIDTH, MAX_HEIGHT);
			throw new GameBoardException(message);
		}
	}

	GameBoard(int width, int height) {
		validateBoardSize(width, height);
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

	private void tryDoStep(int row, int col) {
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

	void doStep(int row, int col) {
		if (inBoard(row, col) == false) {
			throw new GameBoardException();
		}

		tryDoStep(row, col);
	}

	public boolean inBoard(int row, int col) {
		return (0 <= row && row < height) && (0 <= col && col < width);
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
