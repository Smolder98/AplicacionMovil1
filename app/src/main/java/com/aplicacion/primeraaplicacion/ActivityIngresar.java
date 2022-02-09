package com.aplicacion.primeraaplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import configuraciones.SQLiteConexion;
import configuraciones.Transacciones;

public class ActivityIngresar extends AppCompatActivity {

    EditText nombres, apellidos, edad, correo;
    Button btnagregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar);


        nombres = (EditText) findViewById(R.id.txtnombres);
        apellidos = (EditText) findViewById(R.id.txtapellidos);
        edad = (EditText) findViewById(R.id.txtedad);
        correo = (EditText) findViewById(R.id.txtcorreo);

        btnagregar = (Button) findViewById(R.id.btnagregar);


        btnagregar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                agregarPersonas();
            }
        });

    }

    private void agregarPersonas() {

        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);

        SQLiteDatabase db = conexion.getWritableDatabase();


        ContentValues valores = new ContentValues();

        valores.put(Transacciones.nombres, nombres.getText().toString());
        valores.put(Transacciones.apellidos, apellidos.getText().toString());
        valores.put(Transacciones.edad, edad.getText().toString());
        valores.put(Transacciones.correo, correo.getText().toString());

        Long resultado = db.insert(Transacciones.tablaempleados, Transacciones.id, valores);

        Toast.makeText(getApplicationContext(), "Registro ingreso con exito, Codigo " + resultado.toString()
        ,Toast.LENGTH_LONG).show();

        db.close();

        limpiarPantalla();
    }

    private void limpiarPantalla() {
        nombres.setText("");
        apellidos.setText("");
        edad.setText("");
        correo.setText("");
    }
}