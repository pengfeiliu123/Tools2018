package com.lpf.tools.network.base;

import okhttp3.Request;

/**
 * Created by liupengfei on 2018/2/9 19:17.
 */

public final class RequestUtil {

    public RequestUtil() {

    }
    public static Request.Builder addHeader(Request.Builder builder) {
//        String uuid = PreferencesUtil.getInstance().getUuid();
//        String headerStr = "anonym=" + uuid;
//        String sid = PreferencesUtil.getInstance().getSessionId();
//        if (!TextUtils.isEmpty(sid))
//            headerStr += ";sid=" + sid;
//        String xLogId = PreferencesUtil.getInstance().getXLogId();
//        String country = Locale.getDefault().getCountry();
//        String lang = Locale.getDefault().getLanguage();
////        Log.d("test", "header:" + headerStr);
//        String xLocal = "";
//        try {
//            xLocal = URLEncoder.encode(Base64.encodeToString("Korea".getBytes(), Base64.DEFAULT), "utf-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return builder.header("Authorization", headerStr).header("X-Log-Id", xLogId)
//                .header("X-App-Version", BuildConfig.VERSION_NAME)
//                .header("X-Country", country)
//                .header("X-Lang", lang)
//                .header("X-Content-Locale", xLocal)
//                .header("X-Content-Timer", getTimeZone())
//                .header("X-Content-Code", getMccCode())
//                .header("X-Content-Num", getLongitude())
//                .header("X-Content-Value", getLatitude())
//                .header("X-Package-Name", MainApplication.getInstance().getPackageName());
        return builder.header("testHeader","testHeaderValue");
    }
}
