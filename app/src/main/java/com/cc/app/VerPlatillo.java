package com.cc.app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.cc.app.db.DbPlatillo;
import com.cc.app.entidades.Platillo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VerPlatillo extends AppCompatActivity {

    EditText txtNombre,txtPrecio, txtDescripcion;
    Button btnGuardar;
    FloatingActionButton fabEliminar;
    int id = 0;
    Platillo platillo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_platillo);
        getSupportActionBar().hide();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        txtNombre = findViewById(R.id.txtNombre);
        txtPrecio = findViewById(R.id.txtPrecio);
        txtDescripcion = findViewById(R.id.txtDescripcion);
        btnGuardar = findViewById(R.id.btnGuardar);
        fabEliminar = findViewById(R.id.fabEliminar);

        if (savedInstanceState ==  null){
            Bundle extras = getIntent().getExtras();

            if (extras == null){
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("id");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("id");
        }

        DbPlatillo dbPlatillo = new DbPlatillo(this);
        platillo = dbPlatillo.verPlatillo(id);

        if(platillo != null){
            txtNombre.setText(platillo.getNombre_platillo());
            txtPrecio.setText(platillo.getDescripcion().split("--")[0]);
            txtDescripcion.setText(platillo.getDescripcion().split("--")[1]);
        }

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtNombre.getText().equals("") || !txtPrecio.getText().equals("") || !txtDescripcion.getText().equals("")){

                    dbPlatillo.editarPlatillo(id,txtNombre.getText().toString() , txtPrecio.getText().toString()+"--"+txtDescripcion.getText().toString());
                    Toast.makeText(VerPlatillo.this, "Platillo editado exitosamente", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(VerPlatillo.this, Listar_platillos_admin.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                }
            }
        });

        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(VerPlatillo.this);


                builder.setMessage("¿Desea eliminar este platillo?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                dbPlatillo.eliminarPlatillo(id);
                                Intent intent = new Intent(VerPlatillo.this, Listar_platillos_admin.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();



            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }



}