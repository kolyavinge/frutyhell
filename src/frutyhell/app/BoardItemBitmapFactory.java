package frutyhell.app;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BoardItemBitmapFactory {

	private Resources resources;

	public BoardItemBitmapFactory(Resources resources) {
		this.resources = resources;
	}

	public Bitmap getState1Bitmap() {
		return BitmapFactory.decodeResource(resources, R.drawable.img_5);
	}

	public Bitmap getState2Bitmap() {
		return BitmapFactory.decodeResource(resources, R.drawable.img_4);
	}
}
