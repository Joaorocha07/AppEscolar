package com.example.appescolar.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.appescolar.modal.Matematica;

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
                "notaMatematicaQuartoBimestre TEXT," +
                "resultado TEXT)";
        db.execSQL(sqlTabelaCombustivel);
    }

    public SQLiteDatabase getDatabase() {
        return getWritableDatabase();
    }

    public void salvar(Matematica notaMatematica) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();

        dados.put("notaMatematicaPrimeiroBimestre", notaMatematica.getNotaPrimeiroBimestre());
        dados.put("notaMatematicaSegundoBimestre", notaMatematica.getNotaSegundoBimestre());
        dados.put("notaMatematicaTerceiroBimestre", notaMatematica.getNotaTerceiroBimestre());
        dados.put("notaMatematicaQuartoBimestre", notaMatematica.getNotaQuartoBimestre());
        dados.put("resultado", notaMatematica.getResultadoMedia());

        db.insert("Escola", null, dados);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void salvarObjeto(String tabela, ContentValues dados) {
        db.insert(tabela, null, dados);
    }
}
