package rapticon.tk.leavemanagement.helper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;


public class AlertDialogHelper {

    private AlertDialog alertDialog;

    public void showAlert(Activity activity) {
        alertDialog = new AlertDialog.Builder(activity).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Alert message to be shown");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public AlertDialog getAlertDialog() {
        return alertDialog;
    }
}
