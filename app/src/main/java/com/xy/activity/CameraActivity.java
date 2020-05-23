package com.xy.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;

import com.xiuye.util.cls.XType;
import com.xiuye.util.log.XLog;
import com.xy.util.Promise;

public class CameraActivity extends AbstractBaseActivity {

    private ImageView photoImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        this.<ImageView>byId(R.id.photoImageView).exist(v -> {
            photoImageView = v;
        });

        clickBind(R.id.takePhotoBtn2, v -> {
            dispatchTakePictureIntent();
        });


    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Promise.resolve(new Intent(MediaStore.ACTION_IMAGE_CAPTURE))
                .then(intent -> {
                    Promise.resolve(intent.resolveActivity(getPackageManager()))
                            .exist(() -> {
                                to(intent, REQUEST_IMAGE_CAPTURE);
                            });
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            XLog.lg(extras);
            Bitmap imageBitmap = XType.cast(extras.get("data"));

            photoImageView.setImageBitmap(imageBitmap);
        }
    }
}
