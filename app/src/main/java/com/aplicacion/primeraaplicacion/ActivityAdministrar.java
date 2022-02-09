package com.aplicacion.primeraaplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityAdministrar extends AppCompatActivity {


    EditText txtnombre;
    EditText txtapellido;
    EditText txtedad;
    EditText txtcorreo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrar);

        txtnombre = (EditText) findViewById(R.id.txteditnombre);
        txtapellido = (EditText) findViewById(R.id.txteditapellido);
        txtedad = (EditText) findViewById(R.id.txteditedad);
        txtcorreo = (EditText) findViewById(R.id.txteditcorreo);



        String codigo = getIntent().getStringExtra("codigo");
        String nombre = getIntent().getStringExtra("nombres");
        String apellido = getIntent().getStringExtra("apellidos");
        String edad = getIntent().getStringExtra("edad");
        String correo = getIntent().getStringExtra("correo");

        txtnombre.setText(nombre);
        txtapellido.setText(apellido);
        txtedad.setText(edad);
        txtcorreo.setText(correo);


        System.out.println(codigo);

    }
}