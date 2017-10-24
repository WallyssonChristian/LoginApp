package com.example.administrador.loginapp;

import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class ConsultaActivity extends AppCompatActivity {
    private ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        BancoController crud = new BancoController(getBaseContext());
        Cursor cursor = crud.carregaDados();

        String[] nomeCampos = new String[] {CriaBanco.id, CriaBanco.titulo};
        int[] idViews = new int[] {R.id.tv_idFilme, R.id.tv_nomeFilme};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getBaseContext(),
                R.layout.layout_lista, cursor, nomeCampos, idViews, 0);
        lista = (ListView) findViewById(R.id.lv_filmes);
        lista.setAdapter(adapter);
    }
}
