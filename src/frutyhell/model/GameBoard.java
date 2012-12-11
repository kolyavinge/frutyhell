package frutyhell.model;

import java.util.*;
import static frutyhell.model.BoardItemState.*;

public class GameBoard {

	private int width, height;
	private BoardItem[][] items;

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
		// инвертируем ячейки по столбцам
		for (int i = 0; i < width; i++) {
			items[row][i].switchState();
		}

		// инвертируем ячейки по строкам
		for (int i = 0; i < height; i++) {
			items[i][col].switchState();
		}

		// после инверсии по строкам элемент на позиции [row][col]
		// окажется повторно инвертированным
		// по-этому инвертим его еще раз
		items[row][col].switchState();
	}

	void doStep(BoardItem item) {
		doStep(item.getRow(), item.getCol());
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
