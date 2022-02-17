package com.aplicacion.primeraaplicacion;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class ActivityVideo extends AppCompatActivity {

    static final int REQUEST_VIDEO = 104;

    VideoView videoView;

    Button btnVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        btnVideo = (Button) findViewById(R.id.btnVideo);

        videoView = (VideoView) findViewById(R.id.videoView);

        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                tomarVideo();
            }
        });


    }

    private void tomarVideo(){
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

        if(intent.resolveActivity(getPackageManager()) != null){

//            startActivity();forcallback

            startActivityForResult(intent, REQUEST_VIDEO);



        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_VIDEO && requestCode == RESULT_OK){

            Uri videouri = data.getData();

            videoView.setVideoURI(videouri);



        }
    }
}