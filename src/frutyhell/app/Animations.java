package frutyhell.app;

import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

public class Animations {

	public static Animation getFadeOutAnimation() {
		ScaleAnimation anim = new ScaleAnimation(
				1f, 0f, 1f, 0f,
				Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);

		anim.setDuration(250);

		return anim;
	}
	
	public static Animation getFadeInAnimation() {
		ScaleAnimation anim = new ScaleAnimation(
				0f, 1f, 0f, 1f,
				Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);

		anim.setDuration(250);
		anim.setFillAfter(true);

		return anim;
	}
}
