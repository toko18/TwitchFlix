package com.toko.twitchflix;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RecordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        CameraManager manager = (CameraManager) this.getSystemService(CAMERA_SERVICE);
        try {
            for (String cameraId : manager.getCameraIdList()) {
                CameraCharacteristics chars = manager.getCameraCharacteristics(cameraId);
                // Do something with the characteristics
                Integer facing = chars.get(CameraCharacteristics.LENS_FACING);

                if(facing != null && facing == CameraCharacteristics.LENS_FACING_BACK) {

                }
            }

        }
        catch (CameraAccessException e) {
                e.printStackTrace();
            }
    }
}
