package com.lpf.tools.feature.progress;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lpf.tools.R;
import com.lpf.tools.views.MagicProgressCircle;

public class ProgressActivity extends AppCompatActivity {


    private MagicProgressCircle progressCircle;
    private Button addProgressBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        initViews();
    }

    private void initViews() {

        progressCircle = findViewById(R.id.progress_circle);
        addProgressBtn = findViewById(R.id.add_progress_btn);

//        progressCircle.setPercent(0.5f);

        addProgressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float currentValue = progressCircle.getPercent();
                if(currentValue < 1){
                    progressCircle.setPercent(currentValue + 0.1f);
                }
            }
        });
    }
}
