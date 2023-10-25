package com.example.convertidordemedidas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class UsuarioDataSource {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public UsuarioDataSource(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    public void close() {
        dbHelper.close();
    }

    public long insertUsuario(Usuario usuario) {
        ContentValues values = new ContentValues();
        values.put("nombreUsuario", usuario.getNombreUsuario());
        values.put("correo", usuario.getCorreo());
        values.put("contrasena", usuario.getContrasena());

        return database.insert("usuarios", null, values);
    }
    public boolean autenticaUsuario(String nombreUsuario, String contrasena) {
        String query = "SELECT * FROM usuarios WHERE nombreUsuario = ? AND contrasena = ?";
        Cursor cursor = database.rawQuery(query, new String[]{nombreUsuario, contrasena});

        try {
            if (cursor != null && cursor.moveToFirst()) {
                return true;
            } else {
                return false;
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

}