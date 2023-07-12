package com.example.appescolar.controller;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.appescolar.database.EscolaDB;
import com.example.appescolar.modal.Matematica;
import com.example.appescolar.view.MainActivity;

public class MatematicaController extends EscolaDB {
    SharedPreferences preferences;
    SharedPreferences.Editor listaVip;

    public static final String NOME_PREFERENCES = "pref_listavip";

    public MatematicaController(MainActivity activity) {
        super(activity);
        preferences = activity.getSharedPreferences(NOME_PREFERENCES,0);
        listaVip = preferences.edit();
    }

    @NonNull
    @Override
    public String toString() {
        Log.d("MVC_ControllerMatematica", "ControllerMatematica Iniciado");
        return super.toString();
    }

    public Matematica salvar(Matematica notaMatematica) {
        ContentValues dados = new ContentValues();

        Log.d("MVP_MVC_controller", "Salvo: " + notaMatematica.toString());
        listaVip.putString("Nota primeiro bimestre: ", notaMatematica.getNotaPrimeiroBimestre());
        listaVip.putString("Nota segundo bimestre: ", notaMatematica.getNotaSegundoBimestre());
        listaVip.putString("Nota terceiro bimestre: ", notaMatematica.getNotaTerceiroBimestre());
        listaVip.putString("Nota quarto bimestre: ", notaMatematica.getNotaQuartoBimestre());
        listaVip.putString("Resultado Media: ", notaMatematica.getResultadoMedia());
        listaVip.apply();

        dados.put("Nota primeiro bimestre:", notaMatematica.getNotaPrimeiroBimestre());
        dados.put("Nota segundo bimestre:", notaMatematica.getNotaSegundoBimestre());
        dados.put("Nota terceiro bimestre:", notaMatematica.getNotaTerceiroBimestre());
        dados.put("Nota quarto bimestre:", notaMatematica.getNotaQuartoBimestre());
        dados.put("Resultado da media:", notaMatematica.getResultadoMedia());

        salvarObjeto("notaMatematica", dados);

        return notaMatematica;
    }

    public void limpar (){
        listaVip.clear();
        listaVip.apply();
    }
}
