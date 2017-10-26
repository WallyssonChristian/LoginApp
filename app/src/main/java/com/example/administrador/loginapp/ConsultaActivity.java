package com.example.administrador.loginapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import static com.example.administrador.loginapp.R.layout.layout_dialog;

public class ConsultaActivity extends AppCompatActivity {
    private ListView lista;

    Button bt_add;
    Button bt_edit;
    Button bt_delete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

//        bt_edit = (Button) findViewById(R.id.bt_edit);
//        bt_add = (Button) findViewById(R.id.bt_add);
//        bt_delete = (Button) findViewById(R.id.bt_delete);

        // Cria instancia de bancoController
        BancoController crud = new BancoController(getBaseContext());
        final Cursor cursor = crud.carregaDados();

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


        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String codigoQ;
                cursor.moveToPosition(position);
                codigoQ = cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.id));
                showOptions(codigoQ);
                return true;
            }
        });

    }

    public void showOptions(final String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ConsultaActivity.this);
        builder.setView(R.layout.layout_dialog);

        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent telaDeletar = new Intent(ConsultaActivity.this, EditaFilmeActivity.class);
                telaDeletar.putExtra("codigo", id);
                startActivity(telaDeletar);
                finish();
            }
        });

        builder.setNeutralButton(R.string.edit, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent telaAlterar = new Intent(ConsultaActivity.this, EditaFilmeActivity.class);
                telaAlterar.putExtra("codigo", id);
                startActivity(telaAlterar);
                finish();
            }
        }) ;

//        builder.setNegativeButton(R.string.delete, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });

        builder.create();
        builder.show();
    }
}
