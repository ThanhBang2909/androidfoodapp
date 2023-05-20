package com.example.foodapp_doan.fcm;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class PushNotification extends Application {

    public static final String CHANNEL_ID = "CHANNEL_1";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChanel();
    }

    private void createNotificationChanel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Chanel_1";
            String description = "Thông tin chanel 1";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
