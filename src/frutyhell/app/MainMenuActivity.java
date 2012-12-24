package frutyhell.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import frutyhell.model.GameBoard;
import frutyhell.model.GameBoardException;
import frutyhell.shit.Tuple2;

public class MainMenuActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(frutyhell.app.R.layout.activity_main_menu);
	}

	public void startButtonClick(View view) {
		Tuple2<Integer, Integer> boardSizeTuple = getBoardSize();
		if (boardSizeTuple != null) {
			hideVirtualKeyboard();
			Intent intent = new Intent(this, GameActivity.class);
			intent.putExtra("boardWidth", boardSizeTuple.getItem1());
			intent.putExtra("boardHeight", boardSizeTuple.getItem2());
			startActivity(intent);
		}
	}

	private Tuple2<Integer, Integer> getBoardSize() {
		Tuple2<Integer, Integer> result = null;
		try {
			TextView tvWidth = (TextView) findViewById(R.id.etWidth);
			TextView tvHeight = (TextView) findViewById(R.id.etHeight);
			int width = Integer.parseInt(tvWidth.getText().toString());
			int height = Integer.parseInt(tvHeight.getText().toString());
			GameBoard.validateBoardSize(width, height);
			result = new Tuple2<Integer, Integer>(width, height);
		} catch (IllegalArgumentException exp) {
			SimpleAlertDialog.show(this, "", "Размер поля введен неверно");
		} catch (GameBoardException gameBoardExp) {
			SimpleAlertDialog.show(this, "", gameBoardExp.getMessage());
		}

		return result;
	}

	private void hideVirtualKeyboard() {
		InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
	}
}
