package com.cc.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.cc.app.adaptadores.ListaPlatillosAdapter;
import com.cc.app.db.DbPlatillo;
import com.cc.app.entidades.Platillo;

import java.util.ArrayList;

public class Listar_platillos_user extends AppCompatActivity {

    RecyclerView listaPlatillos;
    ArrayList<Platillo> listaArrayPlatillos;
    ListaPlatillosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_platillos_user);
        getSupportActionBar().hide();

        listaPlatillos = findViewById(R.id.listaPlatillos);
        listaPlatillos.setLayoutManager(new LinearLayoutManager(this));

        DbPlatillo dbPlatillo =  new DbPlatillo(Listar_platillos_user.this);

        listaArrayPlatillos =  new ArrayList<>();

        adapter =  new ListaPlatillosAdapter(dbPlatillo.mostrarPlatillos());
        listaPlatillos.setAdapter(adapter);



    }
}