package frutyhell.app;

import static frutyhell.model.BoardItemState.STATE_1;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import frutyhell.model.BoardItem;
import frutyhell.model.BoardItemListener;
import frutyhell.model.DefaultBoardItemListener;

public class BoardItemView extends View {

	private static Bitmap state1Bitmap, state2Bitmap;

	private BoardItem item;
	private Bitmap currentStateBitmap;
	private Paint paint;

	public static void setStateBitmaps(Bitmap state1, Bitmap state2) {
		BoardItemView.state1Bitmap = state1;
		BoardItemView.state2Bitmap = state2;
	}

	public BoardItemView(BoardItem item, Context context) {
		super(context);
		this.item = item;
		this.item.attachListener(boardItemListener);
		this.paint = new Paint(Paint.FILTER_BITMAP_FLAG);
		setCurrentStateBitmap();
	}

	@Override
	public void draw(Canvas canvas) {
		float width = getRight() - getLeft();
		float height = getBottom() - getTop();
		RectF rect = new RectF(0, 0, width, height);
		canvas.drawBitmap(currentStateBitmap, null, rect, paint);
	}

	@Override
	protected void finalize() throws Throwable {
		this.item.detachListener(boardItemListener);
		super.finalize();
	}

	public BoardItem getItem() {
		return item;
	}

	private void setCurrentStateBitmap() {
		currentStateBitmap = item.getState() == STATE_1 ? state1Bitmap : state2Bitmap;
	}

	private final BoardItemListener boardItemListener = new DefaultBoardItemListener() {
		@Override
		public void onBeforeSwitchState(BoardItem item) {
			Animation anim = Animations.getFadeOutAnimation();
			anim.setAnimationListener(animationListener);
			BoardItemView.this.startAnimation(anim);
		}
	};

	private final AnimationListener animationListener = new DefaultAnimationListener() {
		@Override
		public void onAnimationEnd(Animation animation) {
			setCurrentStateBitmap();
			BoardItemView.this.startAnimation(Animations.getFadeInAnimation());
		}
	};
}
