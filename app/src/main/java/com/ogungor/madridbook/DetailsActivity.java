package com.ogungor.madridbook;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class DetailsActivity extends AppCompatActivity {

    EditText nameEditText;
    EditText positionEditText;
    ImageView imageView;
    EditText uniformNumberEditTxt;
    byte[] imageArray;
    private Bitmap seletedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        nameEditText = findViewById(R.id.nameEditText);
        positionEditText = findViewById(R.id.positionEditText);
        imageView = findViewById(R.id.imageView);
        uniformNumberEditTxt = findViewById(R.id.uniformNumberEditText);

    }

    public void selectimage(View view) {

       Permission permission=new Permission();
       permission.PermissionImage();

        // Permission permission=new Permission();


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Intent intentToGalery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intentToGalery, 2);
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            Uri dataimage = data.getData();
            try {
                if (Build.VERSION.SDK_INT >= 28) {
                    ImageDecoder.Source source = ImageDecoder.createSource(this.getContentResolver(), dataimage);
                    seletedImage = ImageDecoder.decodeBitmap(source);
                    imageView.setImageBitmap(seletedImage);
                } else {
                    seletedImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(), dataimage);
                    imageView.setImageBitmap(seletedImage);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void save(View view) {

        String madridName = nameEditText.getText().toString();
        String position = positionEditText.getText().toString();
        String uniformNumber = uniformNumberEditTxt.getText().toString();
        Bitmap smallImage = Permission.makeSmallImage(seletedImage, 300);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        smallImage.compress(Bitmap.CompressFormat.PNG, 50, byteArrayOutputStream);
        imageArray = byteArrayOutputStream.toByteArray();


        Madrids madrids = new Madrids(madridName, position, uniformNumber, imageArray);
        DatabaseActivity databaseActivity = new DatabaseActivity(this, "HelloMadrids", null, 1);
        databaseActivity.AddDatabaseActivity(madrids);

    }
}