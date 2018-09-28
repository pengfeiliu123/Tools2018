package com.lpf.tools.feature.notification;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by liupengfei on 2018/9/27 16:35.
 */
public class ToolsFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "Tools";

    public ToolsFirebaseInstanceIDService() {
        super();
    }

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
//        sendRegistrationToServer(refreshedToken);
    }
}
