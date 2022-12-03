package com.cc.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cc.app.db.DbHelper;
import com.cc.app.db.DbUsuario;
import com.cc.app.entidades.Usuario;

public class Register_user extends AppCompatActivity {
    Button btnRegister;
    EditText etNombre, etCorreo, etPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FE6525")));

        btnRegister = findViewById(R.id.btnRegister);

        etNombre = findViewById(R.id.etRegisterName);
        etCorreo = findViewById(R.id.etRegisterCorreo);
        etPass = findViewById(R.id.etRegisterPass);
    }

    private boolean existe(String correo) {
        DbUsuario dbUsuario = new DbUsuario(this);

        Usuario usr = dbUsuario.buscarUsuario(correo);

        if(usr != null){
            return true;
        }

        return false;

    }

    public void register(View view){
        try{

            String  nombre = etNombre.getText().toString(),
                    correo = etCorreo.getText().toString(),
                    pass = etPass.getText().toString();

            if (etNombre.getText().toString().equals("") || etCorreo.getText().toString().equals("") ||etPass.getText().toString().equals("") ) {
                Toast.makeText(this, "Ingrese todos los campos", Toast.LENGTH_SHORT).show();
            }

            if(existe(correo)){
                Toast.makeText(this, "Ya existe un usuario registrado con este correo", Toast.LENGTH_SHORT).show();
            }

            DbUsuario dbUsuario = new DbUsuario(this);
            long id =  dbUsuario.insertarUsuario(nombre,correo,pass,0);

            Toast.makeText(this, "Usuario creado exitosamente", Toast.LENGTH_SHORT).show();


        } catch (Exception ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}