package com.example.administrador.loginapp;

import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class ConsultaActivity extends AppCompatActivity {
    private ListView lista;
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
//                return false;
//
//            }
//        });
    }
}
