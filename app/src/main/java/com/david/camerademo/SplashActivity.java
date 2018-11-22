package com.david.camerademo;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by cuiweixing on 2018/11/22.
 */

public class SplashActivity extends Activity {
    private String[] permissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.RECORD_AUDIO};
    private int requestCode = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for (String permission:permissions) {
            if (ContextCompat.checkSelfPermission(this,
                    permission)
                    != PackageManager.PERMISSION_GRANTED) {
                ++requestCode;
                ActivityCompat.requestPermissions(SplashActivity.this, new String[]{permission}, requestCode);
//                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
//                        permisssion)) {
//
//                } else {
//                    ActivityCompat.requestPermissions(this,
//                            new String[]{permisssion},
//                            0);
//                }
            }
        }

        if (requestCode == 0) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == this.requestCode) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }
}
