package frutyhell.app;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import frutyhell.model.GameModel;

public class FrutyHellActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		GameModel gameModel = new GameModel();
		GameView gameView = new GameView(gameModel, this);
		setTitle("Fruity Hell v1.0");
		setContentView(gameView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_fruty_hell, menu);
		return true;
	}
}
