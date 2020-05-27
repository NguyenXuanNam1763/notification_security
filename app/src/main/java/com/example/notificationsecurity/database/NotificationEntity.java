package com.example.notificationsecurity.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notification_entity")
public class NotificationEntity {
    @PrimaryKey(autoGenerate = true)
    public int Id;

    @ColumnInfo(name = "package_name_notification")
    public String packageNameNotification;

    @ColumnInfo(name = "notification_content")
    public String notificationContent;

    @ColumnInfo(name = "notification_time_show")
    public String notificationTimeShow;


    public NotificationEntity() {
    }

    public NotificationEntity(int id, String packageNameNotification, String notificationContent, String notificationTimeShow) {
        Id = id;
        this.packageNameNotification = packageNameNotification;
        this.notificationContent = notificationContent;
        this.notificationTimeShow = notificationTimeShow;
    }


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getPackageNameNotification() {
        return packageNameNotification;
    }

    public void setPackageNameNotification(String packageNameNotification) {
        this.packageNameNotification = packageNameNotification;
    }

    public String getNotificationContent() {
        return notificationContent;
    }

    public void setNotificationContent(String notificationContent) {
        this.notificationContent = notificationContent;
    }

    public String getNotificationTimeShow() {
        return notificationTimeShow;
    }

    public void setNotificationTimeShow(String notificationTimeShow) {
        this.notificationTimeShow = notificationTimeShow;
    }

    @Override
    public String toString() {
        return "NotificatioEntity{" +
                "Id=" + Id +
                ", packageNameNotification='" + packageNameNotification + '\'' +
                ", notificationContent='" + notificationContent + '\'' +
                ", notificationTimeShow='" + notificationTimeShow + '\'' +
                '}';
    }
}
