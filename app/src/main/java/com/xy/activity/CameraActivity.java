package com.xy.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ImageView;

import androidx.core.content.FileProvider;

import com.xiuye.sharp.X;
import com.xiuye.util.cls.XType;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraActivity extends AbstractBaseActivity {

    private ImageView photoImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        this.<ImageView>byId(R.id.photoImageView).E(v -> {
            photoImageView = v;
            return X.DEFAULT_OBJECT;
        });

        clickBind(R.id.takePhotoBtn2, v -> dispatchTakePictureIntent());
        clickBind(R.id.takePhotoBtn3, v -> dispatchTakePictureIntent2());


    }

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_TAKE_PHOTO = 2;

    private void dispatchTakePictureIntent() {
        X.resolve(new Intent(MediaStore.ACTION_IMAGE_CAPTURE))
                .THEN(intent -> {
                    X.resolve(intent.resolveActivity(getPackageManager()))
                            .E(() -> {
                                to(intent, REQUEST_IMAGE_CAPTURE);
                            });
                    return X.DEFAULT_OBJECT;
                });


    }

    private void dispatchTakePictureIntent2() {
        X.resolve(new Intent(MediaStore.ACTION_IMAGE_CAPTURE))
                .THEN(intent -> {
                    X.resolve(intent.resolveActivity(getPackageManager()))
                            .E(d -> {
                                return createImageFile();
                            }).E(photoFile -> {
                        Uri photoURI = FileProvider.getUriForFile(
                                this,
                                "com.xy.fileprovider"
                                , photoFile);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);

                        to(intent, REQUEST_TAKE_PHOTO);
                        return X.DEFAULT_OBJECT;
                    });
                    return X.DEFAULT_OBJECT;
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
//            Bundle extras = data.getExtras();
//            Bitmap imageBitmap = XType.cast(extras.get("data"));
//            photoImageView.setImageBitmap(imageBitmap);
//        } else if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
//            UIUtil.log(data.getExtras());
//        }
//        UIUtil.log(this,currentPhotoPath);

        X.begin().MATCH(resultCode).AS(RESULT_OK).THEN(() -> {
            X.begin().MATCH(requestCode)
                    .AS(REQUEST_IMAGE_CAPTURE)
                    .THEN(() -> {
                        Bundle extras = data.getExtras();
                        Bitmap imageBitmap = XType.cast(extras.get("data"));
                        photoImageView.setImageBitmap(imageBitmap);
                    })
                    .AS(REQUEST_TAKE_PHOTO)
                    .THEN(() -> {
                        X.resolve(currentPhotoPath).E(d -> {
                            return BitmapFactory.decodeFile(d);
                        }).E(bitmap -> {
                            photoImageView.setImageBitmap(bitmap);
                            return X.DEFAULT_OBJECT;
                        });
                    }).end();
        }).end();

//        Promise.resolve(resultCode == RESULT_OK).truely(() -> {
//            Promise.resolve().begin().ef(requestCode == REQUEST_IMAGE_CAPTURE).then(() -> {
//                Bundle extras = data.getExtras();
//                Bitmap imageBitmap = XType.cast(extras.get("data"));
//                photoImageView.setImageBitmap(imageBitmap);
//            }).eeseEf(requestCode == REQUEST_TAKE_PHOTO).then(() -> {
//                Promise.resolve(currentPhotoPath).exist(d -> {
//                    return BitmapFactory.decodeFile(d);
//                }).except(e -> {
//                    UIUtil.log(this, e);
//                }).exist(bitmap -> {
//                    photoImageView.setImageBitmap(bitmap);
//                }).except(e -> {
//                    UIUtil.log(this, e);
//                });
//            }).end();
//
//
//        });

//        Promise.resolve(resultCode == RESULT_OK).and(requestCode == REQUEST_IMAGE_CAPTURE).truely(() -> {
//            Bundle extras = data.getExtras();
//            Bitmap imageBitmap = XType.cast(extras.get("data"));
//            photoImageView.setImageBitmap(imageBitmap);
//        });
//
//        Promise.resolve(resultCode == RESULT_OK).and(requestCode == REQUEST_TAKE_PHOTO).truely(d -> {
//            UIUtil.log(data.getExtras(), d);
//        });
//                Promise.resolve(resultCode == RESULT_OK).andIf().then()
//                .orAndIf().then()
//                .orAndIf().and().and().and().then()
//                .other();

    }

    String currentPhotoPath;

    private File createImageFile() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = null;
        try {
            image = File.createTempFile(
                    imageFileName,  /* prefix */
                    ".jpg",         /* suffix */
                    storageDir      /* directory */
            );
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
//            UIUtil.log(this,storageDir,image,currentPhotoPath);
        return image;
    }
}
