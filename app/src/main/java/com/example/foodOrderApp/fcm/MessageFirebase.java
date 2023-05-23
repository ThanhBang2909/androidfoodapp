package com.example.foodOrderApp.fcm;

import android.app.Notification;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


import com.example.foodOrderApp.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Date;
import java.util.Map;

public class MessageFirebase extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
        if (message.getData().size() > 0){
            Map<String, String> DataMesage = message.getData();
            String titleNotifi = DataMesage.get("titleMessage");
            String contentNotifi = DataMesage.get("contentMessage");
            sendNotification(titleNotifi,contentNotifi);
        }else {
            RemoteMessage.Notification notification = message.getNotification();
            if (notification != null){
                String titleNotifi = notification.getTitle();
                String contentNotifi = notification.getBody();
                sendNotification(titleNotifi,contentNotifi);
            }
        }
    }

    private void sendNotification(String title, String content){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        Notification notification = new NotificationCompat.Builder(this, PushNotification.CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
                .setLargeIcon(bitmap)
                .setColor(getResources().getColor(R.color.purple_500, null))
                .build();
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify((int) new Date().getTime(),notification);
    }
}
