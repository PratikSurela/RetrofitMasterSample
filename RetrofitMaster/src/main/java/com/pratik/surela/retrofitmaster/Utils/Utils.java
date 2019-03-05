package com.pratik.surela.retrofitmaster.Utils;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pratik.surela.retrofitmaster.R;

/**
 * Created by web on 3/3/2018.
 */

public class Utils {

    private static final String TAG = Utils.class.getSimpleName();

    /**
     * Shows the message passed in the parameter in the Toast.
     *
     * @param msg Message to be show in the toast.
     * @return Toast object just shown
     **/
    public static Toast showToast(Context context, CharSequence msg) {

        LinearLayout layout = new LinearLayout(context);
        layout.setBackgroundResource(R.drawable.bg_btn_blue_default);
        TextView tv = new TextView(context);
        // set the TextView properties like color, size etc
        tv.setTextColor(Color.WHITE);
        tv.setTextSize(15);
        tv.setGravity(Gravity.CENTER);
        // set the text you want to show in  Toast
        tv.setText(msg);
        layout.addView(tv);
        Toast toast = new Toast(context); //context is object of Context write "this" if you are an Activity
        // Set The layout as Toast View
        toast.setView(layout);
        // Position you toast here toast position is 50 dp from bottom you can give any integral value
        toast.setGravity(Gravity.BOTTOM, 0, 50);
        toast.show();

        return toast;
    }

    public static boolean isValueNull(Object object) {
        if (object != null) {
            return false;
        } else {
            return true;
        }
    }
}