package com.lpf.tools.feature.widgets.recyclerviewdemo;

import com.google.gson.Gson;
import com.lpf.tools.App;
import com.lpf.tools.feature.widgets.recyclerviewdemo.entity.TabBean;

import org.json.JSONObject;

import java.io.InputStream;

import okio.BufferedSource;
import okio.Okio;

/**
 * Created by liupengfei on 2019/2/20 13:51.
 */
public class JsonUtil {

    public static TabBean test(int id) {
        try {

            InputStream inputStream = App.applicationContext().getResources().openRawResource(id);
            BufferedSource buffer = Okio.buffer(Okio.source(inputStream));
            String json = new String(buffer.readByteArray());
            buffer.close();

            Gson gson = new Gson();
            return gson.fromJson(json,TabBean.class);
        }catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
