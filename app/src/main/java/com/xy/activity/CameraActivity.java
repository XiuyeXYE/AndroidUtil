package com.xy.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ImageView;

import androidx.core.content.FileProvider;

import com.xiuye.util.cls.XType;
import com.xy.util.Promise;
import com.xy.util.UIUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraActivity extends AbstractBaseActivity {

    private ImageView photoImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        this.<ImageView>byId(R.id.photoImageView).exist(v -> {
            photoImageView = v;
        });

        clickBind(R.id.takePhotoBtn2, v -> dispatchTakePictureIntent());
        clickBind(R.id.takePhotoBtn3, v -> dispatchTakePictureIntent2());


    }

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_TAKE_PHOTO = 2;

    private void dispatchTakePictureIntent() {
        Promise.resolve(new Intent(MediaStore.ACTION_IMAGE_CAPTURE))
                .then(intent -> {
                    Promise.resolve(intent.resolveActivity(getPackageManager()))
                            .exist(() -> {
                                to(intent, REQUEST_IMAGE_CAPTURE);
                            }).except(e -> {
                        UIUtil.log(this, "exception:", e);
                    });
                }).except(e -> {
            UIUtil.log(this, "exception:", e);
        });


    }

    private void dispatchTakePictureIntent2() {
        Promise.resolve(new Intent(MediaStore.ACTION_IMAGE_CAPTURE))
                .then(intent -> {
                    Promise.resolve(intent.resolveActivity(getPackageManager()))
                            .exist(() -> {
                                return createImageFile();
                            }).exist(photoFile -> {
                        Uri photoURI = FileProvider.getUriForFile(
                                this,
                                "com.xy.fileprovider"
                                , photoFile);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);

                        to(intent, REQUEST_TAKE_PHOTO);
                    }).except(e -> {//有error就执行，没有就跳过！
                        UIUtil.log(this, "Exception:", e);
                    });
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Promise.resolve(resultCode == RESULT_OK).andIf().then()
                .orAndIf().then()
                .orAndIf().and().and().and().then()
                .other();
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = XType.cast(extras.get("data"));
            photoImageView.setImageBitmap(imageBitmap);
        } else if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            UIUtil.log(data.getExtras());
        }
    }

    String currentPhotoPath;

    private File createImageFile() {
        return Promise.resolve(() -> {
            // Create an image file name
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String imageFileName = "JPEG_" + timeStamp + "_";
            File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            File image = File.createTempFile(
                    imageFileName,  /* prefix */
                    ".jpg",         /* suffix */
                    storageDir      /* directory */
            );


            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = image.getAbsolutePath();
//            UIUtil.log(this,storageDir,image,currentPhotoPath);
            return image;
        }).get();
    }
}
