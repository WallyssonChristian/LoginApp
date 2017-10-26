package com.example.administrador.loginapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditaFilmeActivity extends AppCompatActivity {

    EditText et_tit, et_cat, et_cla;
    Button bt_edit;
    //
    Button bt_delete;
    //
    Cursor cursor;
    BancoController crud;
    String codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edita_filme);

        codigo = this.getIntent().getStringExtra("codigo");

        crud = new BancoController(getBaseContext());

        et_tit = (EditText) findViewById(R.id.et_edit_titulo);
        et_cat = (EditText) findViewById(R.id.et_edit_categoria);
        et_cla = (EditText) findViewById(R.id.et_edit_classific);

        bt_edit = (Button) findViewById(R.id.bt_edit_alterar);
        bt_delete = (Button) findViewById(R.id.bt_delete);

        cursor = crud.carregaDadosByID(Integer.parseInt(codigo));
        et_tit.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.titulo)));
        et_cat.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.categoria)));
        et_cla.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.classificacao)));

        bt_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crud.alteraRegistro(Integer.parseInt(codigo),
                        et_tit.getText().toString(),
                        et_cat.getText().toString(),
                        et_cla.getText().toString());
                Intent telaConsulta = new Intent(EditaFilmeActivity.this, ConsultaActivity.class);
                startActivity(telaConsulta);
                finish();
            }
        });

        bt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crud.deletaRegistro(Integer.parseInt(codigo));
                Intent telaConsulta = new Intent(EditaFilmeActivity.this, ConsultaActivity.class);
                startActivity(telaConsulta);
                finish();
            }
        });
    }
}
