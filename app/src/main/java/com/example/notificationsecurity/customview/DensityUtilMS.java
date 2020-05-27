package com.example.notificationsecurity.customview;

import android.content.Context;

public class DensityUtilMS {
    public DensityUtilMS() {
    }

    public static int dip2pxx(Context context, float dpValue) {
        return (int)(dpValue * context.getResources().getDisplayMetrics().density + 0.5F);
    }

    public static float dip2px(Context context, float dpValue) {
        return dpValue * context.getResources().getDisplayMetrics().density + 0.5F;
    }

    public static int widthPixels(Context context) {
        return context != null && context.getResources() != null ? context.getResources().getDisplayMetrics().widthPixels : 720;
    }

    public static int heightPixels(Context context) {
        return context != null && context.getResources() != null ? context.getResources().getDisplayMetrics().heightPixels : 1280;
    }
}
