package frutyhell.app;

import static frutyhell.model.BoardItemState.*;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import frutyhell.model.BoardItem;

public class BoardItemView extends View {

	private BoardItem item;
	private Bitmap state1Bitmap, state2Bitmap;
	private Paint paint;

	public BoardItemView(BoardItem item, Context context) {
		super(context);
		this.item = item;
		this.paint = new Paint();
	}

	@Override
	public void draw(Canvas canvas) {
		Bitmap currentStateBitmap = item.getState() == STATE_1 ? state1Bitmap : state2Bitmap;
		RectF rect = new RectF(getLeft(), getTop(), getRight(), getBottom());
		canvas.drawBitmap(currentStateBitmap, null, rect, paint);
	}

	public BoardItem getItem() {
		return item;
	}

	public void setStateBitmaps(Bitmap state1, Bitmap state2) {
		this.state1Bitmap = state1;
		this.state2Bitmap = state2;
	}
}
