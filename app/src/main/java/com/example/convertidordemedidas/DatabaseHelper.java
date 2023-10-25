package com.example.convertidordemedidas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MiBaseDeDatos.db";
    private static final int DATABASE_VERSION = 1;

    // Sentencia SQL para crear la tabla de usuarios
    private static final String CREATE_TABLE_USUARIO = "CREATE TABLE usuarios (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nombreUsuario TEXT," +
            "correo TEXT," +
            "contrasena TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USUARIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        onCreate(db);
    }
}
