package com.example.trabajo_clase_sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConexionDb extends SQLiteOpenHelper {
    //INICIAMOS LAS PROPIEDADES QUE SERAN LO CAMPOS DE LA TABLA
    public static final String NOMBRE_TABLA = "jugador";
    public static final String COLUMNA_ID = "id";
    public static final String COLUMNA_NOMBRE = "nombre";
    public static final String COLUMNA_DNI = "dni";

    //CREAMOS LA PROPIEDAD CON LA CONSULTA PARA CREAR LA TABLA
    public static final String SQL_CONSULTA = "CREATE TABLE " + NOMBRE_TABLA + "(" +
            COLUMNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMNA_NOMBRE + " TEXT UNIQUE NOT NULL," +
            COLUMNA_DNI + " INTEGER NOT NULL" +
            ")";

    //CREACION DEL CONSTRUCTOS SOBRECARGADO
    public ConexionDb(@Nullable Context context) {
        super(context, "clase3.db", null, 1);
    }

    //CON ESTE METODO SE CREA LA TABLA
    @Override
    public void onCreate(SQLiteDatabase db) {
        //EJECUTAMOS LA PROPIEDAD DEFINA PARA LA CREACION DE LA TABLA
        db.execSQL(SQL_CONSULTA);
    }

    //ESTE METODO ELIMNA LA TABLA SI YA EXISTE Y LA VUELVE A CREAR
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //ELLIMINAMOS LA TABLA
        db.execSQL("DROP TABLE IF EXISTS " + NOMBRE_TABLA);

        //EN CASO DE QUE SE ELIMINE EJECUTE EL METODO PARA CREAR
        onCreate(db);
    }
}
