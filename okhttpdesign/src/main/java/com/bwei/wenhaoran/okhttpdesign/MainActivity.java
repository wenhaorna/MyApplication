package com.bwei.wenhaoran.okhttpdesign;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;

import android.net.Uri;

import android.provider.MediaStore;

public class MainActivity extends AppCompatActivity {

    private ImageView image;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = (ImageView) findViewById(R.id.image);
        iv = (ImageView) findViewById(R.id.iv);

        iv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("设置头像");
                String[] st = {"相机", "相册", "取消"};
                builder.setItems(st, new android.content.DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                Intent intent = new Intent();
                                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(intent, 1);
                                break;

                            case 1:
                                Intent intent2 = new Intent();
                                intent2.setAction(Intent.ACTION_PICK);
                                intent2.setType("image/*");
                                startActivityForResult(intent2, 2);

                            case 2:
                                builder.create();
                            default:
                                break;
                        }
                    }
                });
                builder.show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bitmap bit = data.getParcelableExtra("data");
            image.setImageBitmap(bit);

        } else if (requestCode == 2 && resultCode == RESULT_OK) {
            Uri data2 = data.getData();
            image.setImageURI(data2);
        }
    }
}