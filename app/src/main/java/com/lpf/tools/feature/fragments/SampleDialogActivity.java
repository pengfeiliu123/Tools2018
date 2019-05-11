package com.lpf.tools.feature.fragments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lpf.tools.R;

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
                sampleImageFragment.show(getSupportFragmentManager(),"弹出dialogFragment");
            }
        });
    }
}
