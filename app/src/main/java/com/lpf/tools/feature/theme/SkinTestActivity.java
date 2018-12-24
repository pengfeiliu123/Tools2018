package com.lpf.tools.feature.theme;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lpf.tools.R;
import com.lpf.tools.utilskin.SkinDelegate;

public class SkinTestActivity extends AppCompatActivity implements SkinDelegate {

    private Button changeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(SkinManager.get().getThemeId(ThemeStyles.SKIN_TEST_ACTIVITY_THEME));
        setContentView(R.layout.activity_skin_test);

        initViews();
    }

    private void initViews() {
        changeBtn = findViewById(R.id.skin_change_theme);
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
                SkinTestActivity.this.finish();
            }
        });
    }

    @Override
    public String getResourceNameForSkin(int id) {
        return SkinManager.getResourceNameForSkin(this, id);
    }
}
