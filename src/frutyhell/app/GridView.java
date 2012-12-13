package frutyhell.app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class GridView extends View {

	private float cellSize;
	private int rows, cols;
	private int gridColor;
	private Paint paint;

	public GridView(Context context) {
		super(context);
		paint = new Paint();
	}

	public int getGridColor() {
		return gridColor;
	}

	public void setGridColor(int gridColor) {
		this.gridColor = gridColor;
		paint.setColor(gridColor);
	}

	public void setGridSize(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
	}

	public float getCellSize() {
		return cellSize;
	}

	@Override
	public void draw(Canvas canvas) {
		drawGrid(canvas);
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		calculateCellSize(left, top, right, bottom);
	}

	private void drawGrid(Canvas canvas) {
		drawHorizontalLines(canvas, paint);
		drawVerticalLines(canvas, paint);
	}

	private void drawHorizontalLines(Canvas canvas, Paint paint) {
		float width = cellSize * cols + getLeft();
		for (int row = 0; row <= rows; row++) {
			float y = cellSize * row + getTop();
			canvas.drawLine(getLeft(), y, width, y, paint);
		}
	}

	private void drawVerticalLines(Canvas canvas, Paint paint) {
		float height = cellSize * rows + getTop();
		for (int col = 0; col <= cols; col++) {
			float x = cellSize * col + getLeft();
			canvas.drawLine(x, getTop(), x, height, paint);
		}
	}

	private void calculateCellSize(int left, int top, int right, int bottom) {
		float width = right - left;
		float height = bottom - top;

		float dx = width / cols - 0.1f;
		float dy = height / rows - 0.1f;

		cellSize = dx < dy ? dx : dy;
	}
}
