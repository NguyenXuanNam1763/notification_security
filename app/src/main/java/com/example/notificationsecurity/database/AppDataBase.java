package com.example.notificationsecurity.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {NotificationEntity.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    private static final String DATABASE_NAME = "notification_database";
    private static volatile AppDataBase appDataBase;

    public abstract NotificationDao notificationDao();

    public static AppDataBase getInstance(Context context) {
        if (appDataBase == null) {
            synchronized (AppDataBase.class) {
                if (appDataBase == null) {
                    appDataBase = Room.databaseBuilder(context.getApplicationContext(),
                            AppDataBase.class, DATABASE_NAME)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return appDataBase;
    }
}
