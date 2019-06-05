package com.lpf.tools.feature.fragments;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.lpf.tools.R;

import static com.lpf.tools.feature.fragments.SampleImageFragment.IMAGE_URL_ARRAY;

public class SampleDialogActivity extends AppCompatActivity {

    private SampleImageFragment sampleImageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_dialog);

        findViewById(R.id.showSampleDialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sampleImageFragment == null){
                    sampleImageFragment = new SampleImageFragment();
                }
                sampleImageFragment.setCancelable(false);

                Bundle bundle = new Bundle();
                String url = "http://crowd.is.autonavi.com/showpic/73fc67665c7f8e8d900bb10c9a1d5125";
                ArrayList<String> urls = new ArrayList<>();
                urls.add("http://crowd.is.autonavi.com/showpic/73fc67665c7f8e8d900bb10c9a1d5125");
                urls.add("http://crowd.is.autonavi.com/showpic/ddceab365d091b7bb247fdbd9f517faa");

                /**
                 * 单个图片,和多个图片，二选一
                 */
                //bundle.putString(IMAGE_URL, url);

                /**
                 * 多个图片，传list
                 */

                bundle.putStringArrayList(IMAGE_URL_ARRAY,urls);
                sampleImageFragment.setArguments(bundle);
                sampleImageFragment.show(getSupportFragmentManager(),"弹出dialogFragment");
            }
        });
    }
}
