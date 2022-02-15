package com.aplicacion.primeraaplicacion;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ActivityPhoto extends AppCompatActivity {

    ImageView objImagen;
    Button btn;
    String currentPhotoPath;

    static final int PETICION_ACCESO_CAM = 100; // Cualquier numero y tiene que ser entero
    static final int TAKE_PIC_REQUEST = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        objImagen = (ImageView) findViewById(R.id.viewfoto);

        btn = (Button) findViewById(R.id.btntomarfoto);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                permisos();

            }
        });




    }

    private void permisos() {
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PETICION_ACCESO_CAM);
        }else {
//            tomarFoto();
            dispatchTakePictureIntent();


        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == PETICION_ACCESO_CAM){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
//                tomarFoto();
                dispatchTakePictureIntent();


            }

        }else{
            Toast.makeText(getApplicationContext(), "Se nesecitan permisos de acceso a camara", Toast.LENGTH_LONG).show();
        }

    }

    private void tomarFoto() {

        Intent takepic = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(takepic.resolveActivity(getPackageManager()) != null){

            startActivityForResult(takepic, TAKE_PIC_REQUEST);
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == TAKE_PIC_REQUEST && resultCode == RESULT_OK){



            Intent inte = new Intent(getApplicationContext(), ActivityPhoto.class);

            startActivity(inte);


            Bundle extras = data.getExtras();

            Bitmap imagen = (Bitmap) extras.get("data");

            objImagen.setImageBitmap(imagen);
        }
    }

  /*******************************************************************************************************/
//  String currentPhotoPath;

    private File createImageFile() throws IOException {
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
        return image;
    }


    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;

            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                ex.toString();
            }
            // Continue only if the File was successfully created
          try {
              if (photoFile != null) {
                  Uri photoURI = FileProvider.getUriForFile(this,
                          "com.aplicacion.primeraaplicacion.provider",
                          photoFile);
                  takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                  startActivityForResult(takePictureIntent, TAKE_PIC_REQUEST);
              }
          }catch (Exception e){
              Log.i("Error", "dispatchTakePictureIntent: " + e.toString());
          }
        }
    }



}