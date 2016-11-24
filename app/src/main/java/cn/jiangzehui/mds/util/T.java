package cn.jiangzehui.mds.util;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class T {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    private static SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static Intent intent = new Intent();


    public static void show(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void open(Context context, Class<?> classs) {
        context.startActivity(intent.setClass(context, classs));
    }

    public static void open(Activity activity, Class<?> classs, boolean animate) {

        activity.startActivity(intent.setClass(activity, classs));


//        if (!animate) {
//            activity.startActivity(intent.setClass(activity, classs));
//        } else {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                activity.startActivity(intent.setClass(activity, classs), ActivityOptions.makeSceneTransitionAnimation(activity).toBundle());
//            } else {
//                activity.startActivity(intent.setClass(activity, classs));
//
//            }
//        }


    }

    public static void open(Context context, Class<?> classs, String... value) {
        for (int i = 1; i <= value.length; i++) {

            if (i % 2 == 0) {
                intent.putExtra(value[i - 2], value[i - 1]);
            }

        }
        context.startActivity(intent.setClass(context, classs));
    }

    public static void open(Activity activity, Class<?> classs, boolean animate, String... value) {
        for (int i = 1; i <= value.length; i++) {

            if (i % 2 == 0) {
                intent.putExtra(value[i - 2], value[i - 1]);
            }

        }
        activity.startActivity(intent.setClass(activity, classs));


//        if (animate) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                activity.startActivity(intent.setClass(activity, classs), ActivityOptions.makeSceneTransitionAnimation(activity).toBundle());
//            } else {
//                activity.startActivity(intent.setClass(activity, classs));
//            }
//        } else {
//            activity.startActivity(intent.setClass(activity, classs));
//        }

    }


    public static String getImei(Context context) {
        TelephonyManager mtm = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        return mtm.getDeviceId();
    }


    public static String getTime() {

        return sdf.format(new Date());
    }

    public static String getTime(long time) {

        return sdfs.format(time);
    }


}
