package com.example.appescolar.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EscolaDB extends SQLiteOpenHelper {
    private static final String DB_NAME = "escola.db";
    private static final int DB_VERSION = 1;

    Cursor cursor;

    SQLiteDatabase db;

    public EscolaDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlTabelaCombustivel = "CREATE TABLE Escola (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "notaMatematicaPrimeiroBimestre TEXT, " +
                "notaMatematicaSegundoBimestre TEXT," +
                "notaMatematicaTerceiroBimestre TEXT," +
                "notaMatematicaQUartoBimestre TEXT," +
                "resultado TEXT)";
        db.execSQL(sqlTabelaCombustivel);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void salvarObjeto(String tabela, ContentValues dados) {
        db.insert(tabela, null, dados);
    }
}
