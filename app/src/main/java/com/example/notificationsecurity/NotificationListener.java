package com.example.notificationsecurity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.example.notificationsecurity.database.AppDataBase;
import com.example.notificationsecurity.database.NotificationEntity;

import java.util.List;

public class NotificationListener extends NotificationListenerService {

    public static final String ACTION = "com.olivierpayen.notificationaccesssample.NOTIFICATION_LISTENER_EXAMPLE";
    public static final String ARG_MESSAGE = "notification_event";
    private final String TAG = "xuananma123";
    private AppDataBase appDataBase;
    private NotificationManager manager;
    private RemoteViews remoteViews;
    private NotificationCompat.Builder builder;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate() {
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        remoteViews = new RemoteViews(getPackageName(), R.layout.item_notification);
        String channel_id = "xuannam";
        NotificationChannel channel = new NotificationChannel(channel_id, channel_id, NotificationManager.IMPORTANCE_HIGH);
        if (manager == null) {
            return;
        }
        manager.createNotificationChannel(channel);
        builder = new NotificationCompat.Builder(this, channel_id);
        builder.setSmallIcon(R.drawable.ic_launcher_background);
        builder.setContentText("Notification hide")
                .setContentTitle("Notification Security");
        builder.setCustomContentView(remoteViews);
        builder.setStyle(new NotificationCompat.DecoratedCustomViewStyle());
        startForeground(1, builder.build());
        appDataBase = AppDataBase.getInstance(this);
        super.onCreate();

    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        final String msg = "ID: " + sbn.getId() + "\t" + sbn.getNotification().tickerText + "\t" + sbn.getPackageName();
        Log.i(TAG, msg);
        Intent i = new Intent(ACTION);
        i.putExtra(ARG_MESSAGE, msg);
//        if (!TextUtils.equals(sbn.getPackageName(), getPackageName())) {
//            cancelNotification(sbn.getKey());
//        }
        NotificationEntity entity = new NotificationEntity();
        entity.setPackageNameNotification(sbn.getPackageName());
        if (sbn.getNotification().tickerText != null) {
            entity.setNotificationContent(sbn.getNotification().tickerText.toString());
        }
        appDataBase.notificationDao().insertNotification(entity);
        cancelNotification(sbn.getKey());
        sendBroadcast(i);
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        List<NotificationEntity> entityList = this.appDataBase.notificationDao().getAllNotification();
        PackageManager packageManager = getPackageManager();
        try {
            Drawable drawable = packageManager.getApplicationIcon(entityList.get(0).packageNameNotification);
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            remoteViews.setImageViewBitmap(R.id.iv_icon1, bitmap);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        builder.setCustomContentView(remoteViews);
        startForeground(1,builder.build());
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }
        Matrix matrix = new Matrix();
        matrix.postRotate(90);
        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }
}
