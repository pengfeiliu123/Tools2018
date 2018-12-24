package com.lpf.tools;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.lpf.tools.entity.TagFlowEntity;
import com.lpf.tools.feature.banner.BannerActivity;
import com.lpf.tools.feature.collapsingToolbar.CollapsingToolActivity;
import com.lpf.tools.feature.customview.drawOneCircle.CustomViewsActivity;
import com.lpf.tools.feature.dbdemo.DbActivity;
import com.lpf.tools.feature.flowtag.FlowLayout;
import com.lpf.tools.feature.flowtag.TagAdapter;
import com.lpf.tools.feature.flowtag.TagFlowLayout;
import com.lpf.tools.feature.fragments.FragmentDoubleListActivity;
import com.lpf.tools.feature.login.LoginActivity;
import com.lpf.tools.feature.magicIndicator.IndicatorActivity;
import com.lpf.tools.feature.navigation.NavigationActivity;
import com.lpf.tools.feature.networkdemo.NetworkActivity;
import com.lpf.tools.feature.notification.NotificationUtil;
import com.lpf.tools.feature.permission.PermissionActivity;
import com.lpf.tools.feature.snackbar.TestSnackbarUtilsActivity;
import com.lpf.tools.feature.switcher.SwitchActivity;
import com.lpf.tools.feature.theme.SkinTestActivity;
import com.lpf.tools.feature.widgets.recyclerviewdemo.RecyclerViewActivity;
import com.lpf.tools.imageloader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TagFlowLayout flowLayout;
    private List<TagFlowEntity> tagDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //them method from xml
//        setTheme(SkinManager.get().getThemeId(ThemeStyles.MAIN_ACTIVITY_THEME));
        setContentView(R.layout.activity_main);
        setTitle("Tools2018");
        initTagDatas();
        initFlowLayout();
    }

    private void initTagDatas() {
        tagDatas = new ArrayList<>();
        tagDatas.add(new TagFlowEntity("permission", PermissionActivity.class));
        tagDatas.add(new TagFlowEntity("navigation", NavigationActivity.class));
        tagDatas.add(new TagFlowEntity("indicator", IndicatorActivity.class));
        tagDatas.add(new TagFlowEntity("banner", BannerActivity.class));
        tagDatas.add(new TagFlowEntity("network", NetworkActivity.class));
        tagDatas.add(new TagFlowEntity("widgets", RecyclerViewActivity.class));
        tagDatas.add(new TagFlowEntity("db", DbActivity.class));
        tagDatas.add(new TagFlowEntity("login", LoginActivity.class));
        tagDatas.add(new TagFlowEntity("fragment", FragmentDoubleListActivity.class));
        tagDatas.add(new TagFlowEntity("collapsingTool", CollapsingToolActivity.class));
        tagDatas.add(new TagFlowEntity("customviews", CustomViewsActivity.class));
        tagDatas.add(new TagFlowEntity("switch", SwitchActivity.class));
        tagDatas.add(new TagFlowEntity("snackbar", TestSnackbarUtilsActivity.class));
        tagDatas.add(new TagFlowEntity("skin", SkinTestActivity.class));
    }

    private void initFlowLayout() {
        final LayoutInflater mInflater = LayoutInflater.from(this);
        flowLayout = (TagFlowLayout) findViewById(R.id.flow_layout);
        flowLayout.setAdapter(new TagAdapter<TagFlowEntity>(tagDatas) {
            @Override
            public View getView(FlowLayout parent, int position, TagFlowEntity tagBean) {
                TextView tv = (TextView) mInflater.inflate(R.layout.item_tag_tv, flowLayout, false);
                tv.setText(tagBean.tagName);
                return tv;
            }
        });

        flowLayout.setMaxSelectCount(1);
        flowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                startActivity(tagDatas.get(position).tagClass);
                return true;
            }
        });
        //theme method from code
//        SkinManager.getSkinStrategy().setMainActivityBgColor(flowLayout);
    }


    private void startActivity(Class tagClass) {

//        sendNotification();

        Intent intent = new Intent(this, tagClass);
        startActivity(intent);
    }

    private static int NOTIFICATION_ID = 0x100001;

    private void sendNotification() {

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
                final Bitmap bitmap = ImageLoader.getInstance().loadBitmap(MainActivity.this, imageUrl);
                if (bitmap != null) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
//                            imageView.setImageBitmap(bitmap);

                            //创建大视图样式
                            NotificationCompat.BigPictureStyle bigPictureStyle =
                                    new NotificationCompat.BigPictureStyle()
                                            .setBigContentTitle("Big picture style notification ~ Expand title")
                                            .setSummaryText("Demo for big picture style notification ! ~ Expand summery")
                                            .bigLargeIcon(null)
                                            .bigPicture(bitmap);

                            builder.setStyle(bigPictureStyle);


                            notificationUtil.getManager(MainActivity.this).notify(NOTIFICATION_ID++, builder.build());

                        }
                    });
                }
            }
        }).start();
    }

}
