package com.example.administrador.loginapp;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class ConsultaActivity extends AppCompatActivity {
    private ListView lista;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        // Cria instancia de bancoController
        BancoController crud = new BancoController(getBaseContext());
        Cursor cursor = crud.carregaDados();

        // Seleciona Quais informações vai receber
        String[] nomeCampos = new String[] {CriaBanco.id, CriaBanco.titulo, CriaBanco.categoria, CriaBanco.classificacao};
        // Seleciona onde vai colocar as informações que recebeu em "nomeCampos"
        int[] idViews = new int[] {R.id.tv_lay_idFilme, R.id.tv_lay_nomeFilme, R.id.tv_lay_categFilme, R.id.tv_lay_classificFilme};


        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getBaseContext(),
                R.layout.layout_lista, cursor, nomeCampos, idViews, 0);
        // GetBaseContext - Tela Atual
        // layout.<xml> - Qual vai ser o layout de tela Atual
        // cursor - dados que vem do bando
        // nomeCampos - onde são salvos
        // idViews - onde serão posicionados

        // Preenche a variavel lista com o que recebeu do banco
        lista = (ListView) findViewById(R.id.lv_filmes);
        // Joga na tela
        lista.setAdapter(adapter);

//        lista.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                showOptions();
//                return true;
//
//            }
//        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showOptions(position);
                return true;
            }
        });

    }

    public void showOptions(int itemPosition){
        AlertDialog.Builder builder = new AlertDialog.Builder(ConsultaActivity.this);
        builder.setView(R.layout.layout_dialog);


        spinner = (Spinner) findViewById(R.id.spinner_menu);

            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.spinner_opt, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if (spinner != null) {
            spinner.setAdapter(adapter);
        } else {
            Toast.makeText(this, "NoWay", Toast.LENGTH_SHORT).show();
        }


        builder.create();
        builder.show();
    }


}
