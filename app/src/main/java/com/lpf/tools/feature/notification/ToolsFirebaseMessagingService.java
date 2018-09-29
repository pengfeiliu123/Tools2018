package com.lpf.tools.feature.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.ArrayMap;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.lpf.tools.MainActivity;
import com.lpf.tools.R;
import com.lpf.tools.feature.navigation.NavigationActivity;
import com.lpf.tools.imageloader.ImageLoader;

import java.util.Set;

/**
 * Created by liupengfei on 2018/9/27 16:33.
 */
public class ToolsFirebaseMessagingService extends FirebaseMessagingService {

    private static int NOTIFICATION_ID = 0x100001;
    private static int REQUESTCODE = 0x200001;

    // [START_EXCLUDE]
    // There are two types of messages data messages and notification messages. Data messages are handled
    // here in onMessageReceived whether the app is in the foreground or background. Data messages are the type
    // traditionally used with GCM. Notification messages are only received here in onMessageReceived when the app
    // is in the foreground. When the app is in the background an automatically generated notification is displayed.
    // When the user taps on the notification they are returned to the app. Messages containing both notification
    // and data payloads are treated as notification messages. The Firebase console always sends notification
    // messages. For more see: https://firebase.google.com/docs/cloud-messaging/concept-options
    // [END_EXCLUDE]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);


        if (remoteMessage.getData().size() > 0) {
            ArrayMap<String, String> map = (ArrayMap<String, String>) remoteMessage.getData();
            Set mapSet = map.keySet();

            String title = remoteMessage.getNotification().getTitle();
            String message = remoteMessage.getNotification().getBody();

//            String actionMx = map.get(EXTRA_ACTION_MX);
//            String videoMetadata = map.get(EXTRA_VIDEO_METADATA);
//            String type = map.get(EXTRA_TYPE);

            Bundle bundle = new Bundle();
//            bundle.putString(EXTRA_ACTION_MX, actionMx);
//            bundle.putString(EXTRA_VIDEO_METADATA, videoMetadata);
//            bundle.putString(EXTRA_TYPE, type);

//            if (TextUtils.isEmpty(actionMx) || TextUtils.isEmpty(videoMetadata) || TextUtils.isEmpty(type)) {
//                return;
//            }

//            Feed feed = Feed.createFeed(bundle);
//            if (feed != null) {
//                OnlineTrackingUtil.trackNotificationViewed(feed.getId(), type, feed.getType().typeName());
//                sendNotification(title, message, bundle);

        }

        Bundle bundle = new Bundle();
//        sendNotification("test Title" + Math.random() * 100, "content", bundle);
        sendNotification(this);
    }

    private void sendNotification(String title, String content, Bundle bundle) {
        try {

//            Intent openApp = new Intent(this, MainActivity.class);
//            openApp.putExtras(bundle);
////            openApp.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            PendingIntent pendingIntent = PendingIntent.getActivity(this, REQUESTCODE++, openApp, PendingIntent.FLAG_UPDATE_CURRENT);
//
//            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//            NotificationCompat.Builder notificationBuilder;
//
//
//            notificationBuilder = new NotificationCompat.Builder(this)
//                    .setContentTitle(title)
//                    .setContentText(content)
//                    .setAutoCancel(true)
//                    .setSound(defaultSoundUri);
////                    .setContentIntent(pendingIntent);
//
//
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                notificationBuilder.setSmallIcon(R.drawable.ic_notification_white);
//                notificationBuilder.setColor(ContextCompat.getColor(getApplicationContext(), R.color.notification_bg));
//            } else {
//                notificationBuilder.setSmallIcon(R.drawable.ic_notification_white);
//            }
//
//            final Notification notification = notificationBuilder.build();
//            final int notificationId = NOTIFICATION_ID++;


            NotificationUtil notificationUtil = NotificationUtil.getInstance();
            NotificationCompat.Builder builder = notificationUtil.getNotificationBuilder(this);
            builder.setContentTitle("change content");
            builder.setContentText("content text");

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder.setSmallIcon(R.drawable.ic_notification_white);
                builder.setColor(ContextCompat.getColor(getApplicationContext(), R.color.notification_bg));
            } else {
                builder.setSmallIcon(R.drawable.ic_notif_white_big);
            }

            //创建大视图样式
            NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle()
                    .setBigContentTitle("Big picture style notification ~ Expand title")
                    .setSummaryText("Demo for big picture style notification ! ~ Expand summery")
                    .bigPicture(BitmapFactory.decodeResource(getResources(), R.mipmap.big_style_picture));

            builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.ic_notifiation_big));
            builder.setStyle(bigPictureStyle);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                builder.setShowWhen(true);
            }
            notificationUtil.getManager(this).notify(NOTIFICATION_ID++, builder.build());


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void sendNotification(final Context context) {

        // create click pendingIntent
        Intent intent = new Intent(this, NavigationActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);

        final NotificationUtil notificationUtil = NotificationUtil.getInstance();
        final NotificationCompat.Builder builder = notificationUtil.getNotificationBuilder(this);
        builder.setContentTitle("change content");
        builder.setContentText("content text");
        builder.setContentIntent(pi);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder.setSmallIcon(R.drawable.ic_notification_white);
            builder.setColor(ContextCompat.getColor(getApplicationContext(), R.color.notification_bg));
        } else {
            builder.setSmallIcon(R.drawable.ic_notif_white_big);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            builder.setShowWhen(true);
        }

        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_notifiation_big));

        String imageUrl = "http://www.linkchant.com/manage/images/2012119203021580.jpg";
        new Thread(new Runnable() {
            @Override
            public void run() {
                String imageUrl = "http://www.linkchant.com/manage/images/2012119203021580.jpg";
                final Bitmap bitmap = ImageLoader.getInstance().loadBitmap(context,imageUrl);
                if (bitmap != null) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {

                            //创建大视图样式
                            NotificationCompat.BigPictureStyle bigPictureStyle =
                                    new NotificationCompat.BigPictureStyle()
                                            .setBigContentTitle("Big picture style notification ~ Expand title")
                                            .setSummaryText("Demo for big picture style notification ! ~ Expand summery")
                                            .bigLargeIcon(null)
                                            .bigPicture(bitmap);

                            builder.setStyle(bigPictureStyle);


                            notificationUtil.getManager(context).notify(NOTIFICATION_ID++, builder.build());

                        }
                    });
                }
            }
        }).start();
    }
}
