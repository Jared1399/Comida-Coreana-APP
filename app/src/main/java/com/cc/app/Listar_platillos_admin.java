package com.cc.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.cc.app.adaptadores.ListaPlatillosAdapter;
import com.cc.app.db.DbPlatillo;
import com.cc.app.entidades.Platillo;

import java.util.ArrayList;

public class Listar_platillos_admin extends AppCompatActivity {

    RecyclerView listaPlatillos;
    ArrayList<Platillo> listaArrayPlatillos;
    ListaPlatillosAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_platillos_admin);

        setContentView(R.layout.activity_listar_platillos_user);

        //#FE6525

       getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FE6525")));


        listaPlatillos = findViewById(R.id.listaPlatillos);
        listaPlatillos.setLayoutManager(new LinearLayoutManager(this));

        DbPlatillo dbPlatillo =  new DbPlatillo(Listar_platillos_admin.this);

        listaArrayPlatillos =  new ArrayList<>();

        adapter =  new ListaPlatillosAdapter(dbPlatillo.mostrarPlatillos());
        listaPlatillos.setAdapter(adapter);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_nuevo:
                nuevoPlatillo();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void nuevoPlatillo(){
        Intent i =  new Intent(this, Nuevo_platillo.class);
        startActivity(i);
    }
}