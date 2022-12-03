package com.cc.app.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.cc.app.entidades.Platillo;

import java.util.ArrayList;

public class DbPlatillo extends DbHelper {

    Context context;

    public DbPlatillo(@Nullable Context context) {

        super(context);
        this.context = context;
    }

    public long insertarPlatillo(String nombre, String descripcion) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre_platillo", nombre);
            values.put("descripcion", descripcion);

            id = db.insert(TABLE_PLATILLOS, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

    public ArrayList<Platillo> mostrarPlatillos() {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Platillo> listaPlatillos = new ArrayList<>();
        Platillo platillo;
        Cursor cursorPlatillos;

        cursorPlatillos = db.rawQuery("SELECT * FROM " + TABLE_PLATILLOS + " ORDER BY nombre_platillo ASC", null);

        if (cursorPlatillos.moveToFirst()) {
            do {
                platillo = new Platillo();
                platillo.setId(cursorPlatillos.getInt(0));
                platillo.setNombre_platillo(cursorPlatillos.getString(1));
                platillo.setDescripcion(cursorPlatillos.getString(2));
                listaPlatillos.add(platillo);
            } while (cursorPlatillos.moveToNext());
        }

        cursorPlatillos.close();

        return listaPlatillos;
    }

    public Platillo verPlatillo(int id) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Platillo platillo = null;

        Cursor cursorPlatillos = db.rawQuery("SELECT * FROM " + TABLE_PLATILLOS + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorPlatillos.moveToFirst()) {
            platillo = new Platillo();
            platillo.setId(cursorPlatillos.getInt(0));
            platillo.setNombre_platillo(cursorPlatillos.getString(1));
            platillo.setDescripcion(cursorPlatillos.getString(2));
        }

        cursorPlatillos.close();

        return platillo;
    }

    public boolean editarPlatillo(int id, String nombre, String descripcion) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_PLATILLOS + " SET nombre_platillo = '" + nombre + "', descripcion = '" + descripcion + "' WHERE id='" + id + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarPlatillo(int id) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_PLATILLOS + " WHERE id = '" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

}
