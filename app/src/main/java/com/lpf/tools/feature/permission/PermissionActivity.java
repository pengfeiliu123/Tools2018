package com.lpf.tools.feature.permission;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lpf.tools.R;
import com.lpf.tools.helper.DialogHelper;
import com.lpf.utilcode.constant.PermissionConstants;
import com.lpf.utilcode.util.PermissionUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PermissionActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn_open_app_settings)
    Button btnOpenAppSettings;
    @BindView(R.id.btn_request_calendar)
    Button btnRequestCalendar;
    @BindView(R.id.btn_request_record_audio)
    Button btnRequestRecordAudio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        ButterKnife.bind(this);

        btnOpenAppSettings.setOnClickListener(this);
        btnRequestCalendar.setOnClickListener(this);
        btnRequestRecordAudio.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_open_app_settings:
                PermissionUtils.openAppSettings();
                break;
            case R.id.btn_request_calendar:
                PermissionUtils.permission(PermissionConstants.CALENDAR)
                        .rationale(new PermissionUtils.OnRationaleListener() {
                            @Override
                            public void rationale(ShouldRequest shouldRequest) {
                                DialogHelper.showRationaleDialog(shouldRequest);
                            }
                        })
                        .callback(new PermissionUtils.FullCallback() {
                            @Override
                            public void onGranted(List<String> permissionsGranted) {
                                Toast.makeText(PermissionActivity.this, "permission granted", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onDenied(List<String> permissionsDeniedForever, List<String> permissionsDenied) {
                                if (!permissionsDeniedForever.isEmpty()) {
                                    DialogHelper.showOpenAppSettingDialog();
                                }
//                                LogUtils.d(permissionsDeniedForever, permissionsDenied);

                            }
                        }).request();
                break;
            case R.id.btn_request_record_audio:
                PermissionUtils.permission(PermissionConstants.MICROPHONE)
                        .rationale(new PermissionUtils.OnRationaleListener() {
                            @Override
                            public void rationale(final ShouldRequest shouldRequest) {
                                DialogHelper.showRationaleDialog(shouldRequest);
                            }
                        })
                        .callback(new PermissionUtils.FullCallback() {
                            @Override
                            public void onGranted(List<String> permissionsGranted) {
                                Toast.makeText(PermissionActivity.this, "permission granted", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onDenied(List<String> permissionsDeniedForever,
                                                 List<String> permissionsDenied) {
                                if (!permissionsDeniedForever.isEmpty()) {
                                    DialogHelper.showOpenAppSettingDialog();
                                }
//                                LogUtils.d(permissionsDeniedForever, permissionsDenied);
                            }
                        })
                        .request();
                break;
        }
    }
}
