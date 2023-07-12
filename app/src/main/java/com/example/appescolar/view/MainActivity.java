package com.example.appescolar.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appescolar.R;
import com.example.appescolar.controller.MatematicaController;
import com.example.appescolar.modal.Matematica;

public class MainActivity extends AppCompatActivity {

    MatematicaController matematicaController;
    Matematica matematica;

    EditText edit_primeiraNotaMatematica;
    EditText edit_SegundaNotaMatematica;
    EditText edit_TerceiraNotaMatematica;
    EditText edit_QuartaNotaMatematica;

    Button btn_Calcular_Media_Matematica;
    ImageButton btn_Limpar_Notas_Matematica;
    Button btn_Salvar_Notas_Matematica;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        matematica = new Matematica();

        matematicaController = new MatematicaController(MainActivity.this);
        matematicaController.toString();

        edit_primeiraNotaMatematica = findViewById(R.id.editTextMatematicaBimestre1);
        edit_SegundaNotaMatematica = findViewById(R.id.editTextMatematicaBimestre2);
        edit_TerceiraNotaMatematica = findViewById(R.id.editTextMatematicaBimestre3);
        edit_QuartaNotaMatematica = findViewById(R.id.editTextMatematicaBimestre4);

        btn_Calcular_Media_Matematica = findViewById(R.id.btn_calcular_matematica);
        btn_Limpar_Notas_Matematica = findViewById(R.id.btn_excluir_matematica);
        btn_Salvar_Notas_Matematica = findViewById(R.id.btn_salvar_matematica);

        btn_Limpar_Notas_Matematica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Notas Matematica Limpas", Toast.LENGTH_LONG).show();
                edit_primeiraNotaMatematica.setText("");
                edit_SegundaNotaMatematica.setText("");
                edit_TerceiraNotaMatematica.setText("");
                edit_QuartaNotaMatematica.setText("");

                // Limpar os resultados dos TextViews
                TextView resultadoMediaMatematica = findViewById(R.id.resultadoMediaMatematica);
                TextView textViewResultadoMatematica = findViewById(R.id.textViewResultadoMatematica);
                resultadoMediaMatematica.setText("");
                textViewResultadoMatematica.setText("");

                matematicaController.limpar();
            }
        });

        btn_Salvar_Notas_Matematica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String primeiraNota = edit_primeiraNotaMatematica.getText().toString();
                String segundaNota = edit_SegundaNotaMatematica.getText().toString();
                String terceiraNota = edit_TerceiraNotaMatematica.getText().toString();
                String quartaNota = edit_QuartaNotaMatematica.getText().toString();

                matematica = new Matematica();
                matematica.setNotaPrimeiroBimestre(primeiraNota);
                matematica.setNotaSegundoBimestre(segundaNota);
                matematica.setNotaTerceiroBimestre(terceiraNota);
                matematica.setNotaQuartoBimestre(quartaNota);

                matematicaController.salvar(matematica);

                Toast.makeText(MainActivity.this, "Notas de Matemática Salvas: " + matematica.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        btn_Calcular_Media_Matematica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String primeiraNota = edit_primeiraNotaMatematica.getText().toString();
                String segundaNota = edit_SegundaNotaMatematica.getText().toString();
                String terceiraNota = edit_TerceiraNotaMatematica.getText().toString();
                String quartaNota = edit_QuartaNotaMatematica.getText().toString();

                double primeira = Double.parseDouble(primeiraNota);
                double segunda = Double.parseDouble(segundaNota);
                double terceira = Double.parseDouble(terceiraNota);
                double quarta = Double.parseDouble(quartaNota);

                double media = (primeira + segunda + terceira + quarta) / 4;

                // Exibir o resultado de aprovação/reprovação em um TextView
                TextView textViewResultadoMatematica = findViewById(R.id.textViewResultadoMatematica);
                if (media >= 60) {
                    textViewResultadoMatematica.setText("APROVADO");
                    textViewResultadoMatematica.setTextColor(getResources().getColor(R.color.verde));
                } else {
                    textViewResultadoMatematica.setText("REPROVADO");
                    textViewResultadoMatematica.setTextColor(getResources().getColor(R.color.vermelho));
                }

                // Exibir a média em um TextView com a cor apropriada
                TextView resultadoMediaMatematica = findViewById(R.id.resultadoMediaMatematica);
                resultadoMediaMatematica.setText(String.valueOf(media));
                if (media < 60) {
                    resultadoMediaMatematica.setTextColor(getResources().getColor(R.color.vermelho));
                } else {
                    resultadoMediaMatematica.setTextColor(getResources().getColor(R.color.verde));
                }
            }
        });

    }
}