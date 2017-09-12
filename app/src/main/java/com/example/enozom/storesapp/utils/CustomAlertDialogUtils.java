package com.example.enozom.storesapp.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.enozom.storesapp.R;

public class CustomAlertDialogUtils extends AlertDialog {
    private Context context;
    private static final String TAG = "CustomAlertDialogUtils";

    public CustomAlertDialogUtils(Context _context) {
        super(_context);
        context = _context;
    }

    public static class Builder extends AlertDialog.Builder {
        Context builderContext;

        public Builder(Context context) {
            super(context);
            builderContext = context;
        }


        @Override
        public AlertDialog show() {
            try {
                final AlertDialog dialog = super.show();
                int paddings = (int) builderContext.getResources().getDisplayMetrics().density * 6;
                Button b = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
                Button b2 = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);

                // negative button
                if (b2 != null) {
                    b2.setBackgroundResource(R.drawable.red_button_background);
                    b2.setTextColor(ContextCompat.getColor(builderContext,R.color.white));
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
                    if (b != null) {
                        params.setMargins(10, 0, 10, 0);
                    }
                    b2.setLayoutParams(params);
                    b2.setPadding(paddings, paddings, paddings, paddings);
                    b2.setAllCaps(false);

                }
                // positive button
                if (b != null) {
                    b.setBackgroundResource(R.drawable.red_button_background);
                    b.setTextColor(ContextCompat.getColor(builderContext,R.color.white));
                    b.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
                    b.setPadding(paddings, paddings, paddings, paddings);
                    b.setAllCaps(false);
                }
                //Title
                int titleId = builderContext.getResources().getIdentifier("alertMessage", "id", "android");
                View title = dialog.findViewById(titleId);
                if (title != null) {
                    ((TextView) title).setTextSize(16);
                }
                return dialog;
            }catch (IllegalStateException ex){
            }
            return null;

        }

    }
}
