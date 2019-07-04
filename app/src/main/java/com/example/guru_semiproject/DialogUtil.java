package com.example.guru_semiproject;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

public class DialogUtil {


    public static void showDialog(Context context, String title, String msg, String okMsg,
                                  DialogInterface.OnClickListener okListener, String cancelMsg, DialogInterface.OnClickListener cancelListener){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(msg);

        if(okListener != null){
            builder.setPositiveButton(okMsg, okListener);
        }

        if(cancelListener != null){
            builder.setNegativeButton(cancelMsg, cancelListener);
        }

        builder.show();
    }

}
