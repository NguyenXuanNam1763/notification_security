package com.example.notificationsecurity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.provider.SettingsSlicesContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.example.notificationsecurity.database.NotificationEntity;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtNotifications;
    private NotificationReceiver nReceiver;
    private View txtNoNotificationAccess;
    private View btnEnableNotificationAccess;
    private Button btCreateNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNotifications = (TextView) findViewById(R.id.txtNotifications);
        txtNoNotificationAccess = findViewById(R.id.txtNoNotificationAccess);
        btnEnableNotificationAccess = findViewById(R.id.btnEnableNotificationAccess);
        this.btCreateNotification = findViewById(R.id.btnCreateNotify);
        this.btCreateNotification.setOnClickListener(this);


        nReceiver = new NotificationReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(NotificationListener.ACTION);
        registerReceiver(nReceiver, filter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (PermissionUtils.hasNotificationAccess(this)) {
            txtNoNotificationAccess.setVisibility(View.GONE);
            btnEnableNotificationAccess.setVisibility(View.GONE);
        } else {
            txtNoNotificationAccess.setVisibility(View.VISIBLE);
            btnEnableNotificationAccess.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(nReceiver);
    }

    public void buttonClicked(View v) {
        if (v.getId() == R.id.btnCreateNotify) {
            NotificationManager nManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if (nManager == null) {
                return;
            }
            String channel_id = "channel_id";
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(channel_id, channel_id, NotificationManager.IMPORTANCE_HIGH);
                nManager.createNotificationChannel(channel);
            }
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channel_id);
            builder.setContentTitle("My Notification");
            builder.setContentText("Notification Listener Service Example");
            builder.setTicker("Notification Listener Service Example");
            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setAutoCancel(true);
            nManager.notify((int) System.currentTimeMillis(), builder.build());
            Log.i("kikias", "buttonClicked: vaod day r");
        } else if (v.getId() == R.id.btnEnableNotificationAccess) {
            startActivity(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"));
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnCreateNotify) {
            NotificationManager nManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if (nManager == null) {
                return;
            }
            String channel_id = "channel_id";
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(channel_id, channel_id, NotificationManager.IMPORTANCE_HIGH);
                nManager.createNotificationChannel(channel);
            }
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channel_id);
            builder.setContentTitle("My Notification");
            builder.setContentText("Notification Listener Service Example");
            builder.setTicker("Notification Listener Service Example");
            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setAutoCancel(true);
            nManager.notify((int) System.currentTimeMillis(), builder.build());
            Log.i("kikias", "buttonClicked: vaod day r");

        }
    }

    class NotificationReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String temp = intent.getStringExtra(NotificationListener.ARG_MESSAGE) + "\n" + txtNotifications.getText();
            txtNotifications.setText(temp);
        }
    }


}
