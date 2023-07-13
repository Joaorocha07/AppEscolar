package com.example.appescolar.controller;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.appescolar.database.EscolaDB;
import com.example.appescolar.modal.Matematica;
import com.example.appescolar.view.MainActivity;

public class MatematicaController extends EscolaDB {
    SharedPreferences preferences;
    SharedPreferences.Editor listaVip;

    public static final String NOME_PREFERENCES = "pref_listavip";
    private EscolaDB escolaDB;

    public MatematicaController(MainActivity activity) {
        super(activity);
        escolaDB = this;
        preferences = activity.getSharedPreferences(NOME_PREFERENCES,0);
        listaVip = preferences.edit();
    }

    @NonNull
    @Override
    public String toString() {
        Log.d("MVC_ControllerMatematica", "ControllerMatematica Iniciado");
        return super.toString();
    }

    public void salvar(Matematica notaMatematica) {
        ContentValues dados = new ContentValues();

        Log.d("MVP_MVC_controller", "Salvo: " + notaMatematica.toString());
        listaVip.putString("Nota primeiro bimestre: ", notaMatematica.getNotaPrimeiroBimestre());
        listaVip.putString("Nota segundo bimestre: ", notaMatematica.getNotaSegundoBimestre());
        listaVip.putString("Nota terceiro bimestre: ", notaMatematica.getNotaTerceiroBimestre());
        listaVip.putString("Nota quarto bimestre: ", notaMatematica.getNotaQuartoBimestre());
        listaVip.putString("Resultado Media: ", notaMatematica.getResultadoMedia());
        listaVip.apply();

        dados.put("notaMatematicaPrimeiroBimestre", notaMatematica.getNotaPrimeiroBimestre());
        dados.put("notaMatematicaSegundoBimestre", notaMatematica.getNotaSegundoBimestre());
        dados.put("notaMatematicaTerceiroBimestre", notaMatematica.getNotaTerceiroBimestre());
        dados.put("notaMatematicaQuartoBimestre", notaMatematica.getNotaQuartoBimestre());
        dados.put("resultado", notaMatematica.getResultadoMedia());

        // SQLiteDatabase db = getWritableDatabase(); // Use getWritableDatabase() do EscolaDB
        SQLiteDatabase db = escolaDB.getDatabase();
        db.insert("Escola", null, dados);
        db.close();
    }


    public void limpar (){
        listaVip.clear();
        listaVip.apply();
    }
}
