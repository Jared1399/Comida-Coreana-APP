package com.cc.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NOMBRE = "CC_APP";
    public static final String TABLE_USUARIOS = "usuarios";
    public static final String TABLE_PLATILLOS = "platillos";


    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null , DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(
                "CREATE TABLE " + TABLE_USUARIOS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "correo_electronico TEXT NOT NULL," +
                "password TEXT NOT NULL," +
                "admin INTEGER)"
        );

        sqLiteDatabase.execSQL(
                "CREATE TABLE " + TABLE_PLATILLOS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre_platillo TEXT NOT NULL," +
                "descripcion TEXT NOT NULL)"
        );

        sqLiteDatabase.execSQL("INSERT INTO usuarios (nombre,correo_electronico,password,admin) VALUES (" +
                "  'admin'," +
                "  'admin'," +
                "  'admin'," +
                "  '1'" +
                "); ");

        sqLiteDatabase.execSQL("INSERT INTO platillos (nombre_platillo,descripcion) VALUES (" +
                "  'platillo1'," +
                "  '9999 -- Platillo comida'" +
                "); ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_USUARIOS);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_PLATILLOS);

        onCreate(sqLiteDatabase);
    }
}
