package com.example.foodOrderApp.fcm;

import android.app.Notification;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.foodOrderApp.R;
import com.example.foodOrderApp.activity.login_page;
import com.example.foodOrderApp.activity.register_page;
import com.example.foodOrderApp.utils.SERVER;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MessageFirebase extends FirebaseMessagingService {

    private String tittleNotification;
    private String bodyNotification;

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
        if (message.getData().size() > 0){
            Map<String, String> DataMesage = message.getData();
            tittleNotification = DataMesage.get("titleMessage");
            bodyNotification = DataMesage.get("contentMessage");
            setNotification(tittleNotification, bodyNotification);
            sendNotification(tittleNotification,bodyNotification);
        }else {
            RemoteMessage.Notification notification = message.getNotification();
            if (notification != null){
                tittleNotification = notification.getTitle();
                bodyNotification = notification.getBody();
                setNotification(tittleNotification, bodyNotification);
                sendNotification(tittleNotification,bodyNotification);
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


    private void setNotification(String tittleNotification, String bodyNotification){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, SERVER.addNotification, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("Success")) {

                } else if (response.equals("Failure")) {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("tittle", tittleNotification);
                data.put("body", bodyNotification);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}
