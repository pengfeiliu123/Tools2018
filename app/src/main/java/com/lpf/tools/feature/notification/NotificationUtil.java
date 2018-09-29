package com.lpf.tools.feature.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;

import com.lpf.tools.R;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by liupengfei on 2018/9/28 14:16.
 */
public class NotificationUtil {

    private NotificationManager manager;
    public static final String id = "channel_1";
    public static final String name = "channel_name_1";

    private NotificationUtil() {
    }

    public static NotificationUtil getInstance() {
        return NotificationUtilSingleton.instance;
    }

    private static class NotificationUtilSingleton {
        private static NotificationUtil instance = new NotificationUtil();
    }

    public NotificationManager getManager(Context context) {
        if (manager == null) {
            manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        }
        return manager;
    }

    public NotificationCompat.Builder getNotificationBuilder(Context context) {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel channel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_DEFAULT);
            getManager(context).createNotificationChannel(channel);
        }
        return new NotificationCompat.Builder(context, id)
                .setAutoCancel(true)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_notifiation_big));
    }

}
