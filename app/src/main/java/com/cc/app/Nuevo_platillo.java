package com.cc.app;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cc.app.db.DbPlatillo;

public class Nuevo_platillo extends AppCompatActivity {

    EditText etNombre,etPrecio, etDescripcion;
    Button btnGuardar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_platillo);
        etNombre = findViewById(R.id.txtNombre);
        etPrecio = findViewById(R.id.txtPrecio);
        etDescripcion = findViewById(R.id.txtDescripcion);
        btnGuardar = findViewById(R.id.btnGuardar);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FE6525")));

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbPlatillo dbPlatillo = new DbPlatillo(Nuevo_platillo.this);

                long id = dbPlatillo.insertarPlatillo(etNombre.getText().toString(), etPrecio.getText().toString() +"--"+ etDescripcion.getText().toString());

                if (id > 0){

                    Toast.makeText(Nuevo_platillo.this, "Platillo creado exitosamente", Toast.LENGTH_SHORT).show();

                    etNombre.setText("");
                    etPrecio.setText("");
                    etDescripcion.setText("");

                } else {
                    Toast.makeText(Nuevo_platillo.this, "Error", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}