package com.example.notificationsecurity;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class ServiceNotification extends Service implements KeyPhoneConstrainLayout.OnBackKeyListenerConstraint {

    private NotificationManager notificationManager;
    private WindowManager windowManager;
    private View view;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @SuppressLint("InflateParams")
    @Override
    public void onCreate() {
        super.onCreate();
        String CHANNEL_ID = "notification_test";
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_ID, NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        RemoteViews remoteViews=new RemoteViews(getPackageName(),R.layout.notification_hide);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setCustomContentView(remoteViews)
                .setCustomBigContentView(remoteViews)
                .setContentText("xuannam")
                .setSmallIcon(R.drawable.ic_launcher_background);
        startForeground(1, builder.build());
        windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        view = LayoutInflater.from(this).inflate(R.layout.windown_layout, null);
        int layoutStyle;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            layoutStyle = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            layoutStyle = WindowManager.LayoutParams.TYPE_PHONE;
        }
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                0, 0, layoutStyle,
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                PixelFormat.TRANSLUCENT
        );




//        this.view.setVisibility(View.VISIBLE);
//        this.windowManager.addView(this.view, lp);
//        KeyPhoneConstrainLayout keyPhoneConstrainLayout = (KeyPhoneConstrainLayout) this.view;
//        keyPhoneConstrainLayout.setListener(this);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onBackKeyPressed() {
//        Toast.makeText(this, "xuânnam", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onHomeKeyPressed() {
        Toast.makeText(this, "home", Toast.LENGTH_SHORT).show();
    }
}
