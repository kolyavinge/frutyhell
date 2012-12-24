package frutyhell.app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class SimpleAlertDialog {

	public static void show(Context context, String title, String message, DialogInterface.OnClickListener clickListener) {
		AlertDialog alertDialog = new AlertDialog.Builder(context).setTitle(title).setMessage(message).create();
		alertDialog.setButton("OK", clickListener);
		alertDialog.show();
	}

	public static void show(Context context, String title, String message) {
		AlertDialog alertDialog = new AlertDialog.Builder(context).setTitle(title).setMessage(message).create();
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			}
		});
		alertDialog.show();
	}
}
