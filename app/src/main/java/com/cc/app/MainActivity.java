package com.cc.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cc.app.db.DbHelper;
import com.cc.app.db.DbUsuario;
import com.cc.app.entidades.Usuario;

public class MainActivity extends AppCompatActivity {

    EditText etUser, etPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUser = findViewById(R.id.etLoginUser);
        etPass = findViewById(R.id.etLoginPass);

        getSupportActionBar().hide();
    }


    private boolean existe(String correo, String pass) {
        DbUsuario dbUsuario = new DbUsuario(this);

        Usuario usr = dbUsuario.loginUsuario(correo, pass);

        if(usr != null){
            return true;
        }

        return false;

    }


    public void login(View view){

        try{

            String user = etUser.getText().toString()
                    ,pass = etPass.getText().toString();

            if ( existe(user, pass) ){

                DbUsuario dbUsuario = new DbUsuario(this);

                Usuario logging_user = dbUsuario.loginUsuario(user,pass);

                if(logging_user.isAdmin() == 0){
                    Intent i = new Intent(this, Listar_platillos_user.class);
                    startActivity(i);
                } else {
                    Intent i = new Intent(this, Listar_platillos_admin.class);
                    startActivity(i);
                }

            } else {
                Toast.makeText(this, "Login incorrecto", Toast.LENGTH_SHORT).show();
                return;
            }


        } catch (Exception ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void openRegister(View view){
        Intent i = new Intent(this, Register_user.class);
        startActivity(i);
    }

}