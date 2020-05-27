package com.example.notificationsecurity;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationManagerCompat;

import java.util.Set;

class PermissionUtils {
    static boolean hasNotificationAccess(@NonNull final Context context) {
        String packageName = context.getPackageName();
        Set<String> enabledPackages = NotificationManagerCompat.getEnabledListenerPackages(context);
        return enabledPackages.contains(packageName);
    }
}
