package com.example.notificationsecurity.database;

import android.widget.ListView;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NotificationDao {
    @Insert
    void insertNotification(NotificationEntity notificationEntity);

    @Update
    void updateNotification(NotificationEntity notificationEntity);

    @Query("select * from notification_entity")
    List<NotificationEntity> getAllNotification();

    @Query("select * from  notification_entity where package_name_notification=:packageName")
    List<NotificationEntity> getAllNotificationByPackageName(String packageName);


}
