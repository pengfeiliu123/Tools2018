package com.lpf.tools.feature.theme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lpf.tools.R;
import com.lpf.tools.feature.theme.customview.SkinImageView;
import com.lpf.tools.feature.theme.customview.SkinTextView;
import com.lpf.tools.feature.theme.util.SkinManager;
import com.lpf.tools.feature.theme.util.ThemeStyles;
import com.lpf.tools.utilskin.SkinDelegate;

public class SkinTestActivity extends AppCompatActivity implements SkinDelegate {

    private Button changeBtn;
    private SkinImageView skinTestImg;
    private SkinTextView skinTestTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SkinManager.get().getThemeId(ThemeStyles.SKIN_TEST_ACTIVITY_THEME));
        setContentView(R.layout.activity_skin_test);

        initViews();
    }

    private void initViews() {
        changeBtn = findViewById(R.id.skin_change_theme);
        skinTestImg = findViewById(R.id.skin_test_img_2);
        skinTestTv = findViewById(R.id.skin_test_tv);
        changeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SkinManager.get().isDarkTheme()) {
                    SkinManager.get().setLightTheme();
                    Log.d("lpftag","setLightTheme");
                } else {
                    SkinManager.get().setDarkTheme();
                    Log.d("lpftag","setDarkTheme");
                }

                boolean testBoolean = true;
                skinTestImg.setImageResource(testBoolean ? R.drawable.delete_all_enable__light : R.drawable.delete_all_unable__light);
                skinTestTv.setCustomTextColor(testBoolean ? R.color.delete_all_text_color_enable__light : R.color.delete_all_text_color_unable__light);

                skinTestImg.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        SkinTestActivity.this.finish();
                    }
                },1000);
            }
        });
    }

    @Override
    public String getResourceNameForSkin(int id) {
        return SkinManager.getResourceNameForSkin(this, id);
    }
}
