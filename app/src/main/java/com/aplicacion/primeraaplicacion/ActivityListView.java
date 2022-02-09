package com.aplicacion.primeraaplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import configuraciones.SQLiteConexion;
import configuraciones.Transacciones;
import tablas.Empleado;

public class ActivityListView extends AppCompatActivity {

    /*
    * Variables globales
    * */

    SQLiteConexion conexion;

    ListView lista;

    ArrayList<Empleado> listaEmpleados;

    ArrayList<String>   arreglosEmpleados;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);


        conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);

        lista = (ListView) findViewById(R.id.lista);

        obtenerListaEmpleados();

        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arreglosEmpleados);

        lista.setAdapter(adp);





        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            String elementolista;

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                obtenerObjeto(i);
            }
        });

    }

    private void obtenerObjeto(int id) {

        Empleado emp = listaEmpleados.get(id);

        Intent intent = new Intent(getApplicationContext(), ActivityAdministrar.class);

        //Bundle bun = new Bundle();


        intent.putExtra("codigo", emp.getCodigo()+"");
        intent.putExtra("nombres", emp.getNombres());
        intent.putExtra("apellidos", emp.getApellidos());
        intent.putExtra("edad", emp.getEdad()+"");
        intent.putExtra("correo", emp.getCorreo());

        startActivity(intent);


    }

    private void obtenerListaEmpleados() {

        SQLiteDatabase db = conexion.getWritableDatabase();

        Empleado listEmple = null;

        listaEmpleados = new ArrayList<Empleado>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Transacciones.tablaempleados, null);

        while(cursor.moveToNext()){

            listEmple = new Empleado();

            listEmple.setCodigo(cursor.getInt(0));
            listEmple.setNombres(cursor.getString(1));
            listEmple.setApellidos(cursor.getString(2));
            listEmple.setEdad(cursor.getInt(3));
            listEmple.setCorreo(cursor.getString(4));

            listaEmpleados.add(listEmple);
        }

        cursor.close();

        llenarLista();

    }

    private void llenarLista(){
        arreglosEmpleados = new ArrayList<String>();

        for(int i=0; i < listaEmpleados.size();i++){
            arreglosEmpleados.add(listaEmpleados.get(i).getCodigo() + " | " +
                                  listaEmpleados.get(i).getNombres() + " | " +
                                  listaEmpleados.get(i).getApellidos() + " | " +
                                  listaEmpleados.get(i).getEdad() + " | " +
                                  listaEmpleados.get(i).getCorreo()
            );
        }


    }
}