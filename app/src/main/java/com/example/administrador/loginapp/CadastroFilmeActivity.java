package com.example.administrador.loginapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroFilmeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_filme);

        Button bt_cadastrar = (Button) findViewById(R.id.bt_cadastrarfilme);

        bt_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BancoController crud = new BancoController(getBaseContext());
                EditText tituloF = (EditText) findViewById(R.id.et_titulo);
                EditText categoriaF = (EditText) findViewById(R.id.et_categoria);
                EditText classificF = (EditText) findViewById(R.id.et_classific);
                String tituloString = tituloF.getText().toString();
                String categoriaString = categoriaF.getText().toString();
                String classificString = classificF.getText().toString();
                String resultado;

                resultado = crud.insereDado(tituloString, categoriaString, classificString);
                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_SHORT).show();

            }
        });
    }
}
