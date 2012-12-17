package frutyhell.app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

public class GridView extends View {

	private float cellSize;
	private int rows, cols;
	private Paint gridPaint, backPaint;

	public GridView(Context context) {
		super(context);
		gridPaint = new Paint();
		backPaint = new Paint();
	}

	public void setGridColor(int gridColor) {
		gridPaint.setColor(gridColor);
	}

	@Override
	public void setBackgroundColor(int color) {
		this.backPaint.setColor(color);
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
		calculateCellSize(right - left, bottom - top);
	}

	private void drawGrid(Canvas canvas) {
		drawBackground(canvas, backPaint);
		drawHorizontalLines(canvas, gridPaint);
		drawVerticalLines(canvas, gridPaint);
	}

	private void drawHorizontalLines(Canvas canvas, Paint paint) {
		float width = cellSize * cols;
		for (int row = 0; row <= rows; row++) {
			float y = cellSize * row;
			canvas.drawLine(0, y, width, y, paint);
		}
	}

	private void drawVerticalLines(Canvas canvas, Paint paint) {
		float height = cellSize * rows;
		for (int col = 0; col <= cols; col++) {
			float x = cellSize * col;
			canvas.drawLine(x, 0, x, height + 1, paint);
		}
	}

	private void drawBackground(Canvas canvas, Paint paint) {
		RectF rect = new RectF(0, 0, cellSize * cols, cellSize * rows);
		canvas.drawRect(rect, paint);
	}

	private void calculateCellSize(int width, int height) {
		float dx = width / cols;
		float dy = height / rows;

		cellSize = dx < dy ? dx : dy;
	}
}
