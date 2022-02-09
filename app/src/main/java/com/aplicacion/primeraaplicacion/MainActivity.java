package com.aplicacion.primeraaplicacion;

import static android.widget.Toast.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnsaludar;
    Button btnpasar;

    TextView txtnombre;
    TextView txtapellido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnsaludar = (Button)findViewById(R.id.btnsaludar);
        btnpasar = (Button)findViewById(R.id.btnsecundario);

        txtnombre = findViewById(R.id.txtnombre);
        txtapellido = findViewById(R.id.txtapellido);


        btnsaludar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "Bienvenido: " + txtnombre.getText().toString() + " " + txtapellido.getText().toString(), LENGTH_LONG).show();

            }
        });

        btnpasar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ActivityIngresar.class);

                intent.putExtra("nombre",txtnombre.getText().toString());
                intent.putExtra("apellido",txtapellido.getText().toString());

                startActivity(intent);
            }
        });

    }

}