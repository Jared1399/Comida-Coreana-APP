package com.cc.app.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.cc.app.entidades.Platillo;
import com.cc.app.entidades.Usuario;

import java.util.ArrayList;

public class DbUsuario extends DbHelper {
    Context context;

    public DbUsuario(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarUsuario(String nombre, String correo_electronico,String pass ,int admin) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("correo_electronico", correo_electronico);
            values.put("password", pass);
            values.put("admin", admin);

            id = db.insert(TABLE_USUARIOS, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }


    public Usuario verUsuario(int id) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Usuario usuario = null;
        Cursor cursorUsuarios;

        cursorUsuarios = db.rawQuery("SELECT * FROM " + TABLE_USUARIOS + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorUsuarios.moveToFirst()) {
            usuario = new Usuario();
            usuario.setId(cursorUsuarios.getInt(0));
            usuario.setNombre(cursorUsuarios.getString(1));
            usuario.setCorreo_electronico(cursorUsuarios.getString(2));
        }

        cursorUsuarios.close();

        return usuario;
    }


    public Usuario buscarUsuario(String correo) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Usuario usuario = null;
        Cursor cursorUsuarios;

        cursorUsuarios = db.rawQuery("SELECT * FROM " + TABLE_USUARIOS + " WHERE correo_electronico = '" + correo + "' LIMIT 1", null);

        if (cursorUsuarios.moveToFirst()) {
            usuario = new Usuario();
            usuario.setId(cursorUsuarios.getInt(0));
            usuario.setNombre(cursorUsuarios.getString(1));
            usuario.setCorreo_electronico(cursorUsuarios.getString(2));
        }

        cursorUsuarios.close();

        return usuario;
    }


    public Usuario loginUsuario(String correo, String pass) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Usuario usuario = null;
        Cursor cursorUsuarios;

        cursorUsuarios = db.rawQuery("SELECT * FROM " + TABLE_USUARIOS + " WHERE correo_electronico = '" + correo + "' AND password = '"+pass+"'", null);

        if (cursorUsuarios.moveToFirst()) {
            usuario = new Usuario();
            usuario.setId(cursorUsuarios.getInt(0));
            usuario.setNombre(cursorUsuarios.getString(1));
            usuario.setCorreo_electronico(cursorUsuarios.getString(2));
            usuario.setAdmin(cursorUsuarios.getInt(4));
        }

        cursorUsuarios.close();

        return usuario;
    }

}
