package com.example.administrador.loginapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class BancoController {

    private SQLiteDatabase db;
    private CriaBanco banco;

    // Construtor Padrão
    public BancoController(Context context){
        banco = new CriaBanco(context);
    }

    // Metodo para inserir dados
    public String insereDado(String tituloC, String categoriaC, String classificacC) {
        ContentValues valores;
        long resultado;

        // Abre Banco
        db = banco.getWritableDatabase();
        // Insere Valores
        valores = new ContentValues();
        valores.put(CriaBanco.titulo, tituloC);
        valores.put(CriaBanco.categoria, categoriaC);
        valores.put(CriaBanco.classificacao, classificacC);

        resultado = db.insert(banco.tabela, null, valores);
        // Fecha Banco
        db.close();

        // Retorna mensagem de sucesso ou erro
        if (resultado == -1) {
            return "Erro ao inserir registro";
        } else {
            return "Registro inserido com sucesso";
        }
    }

    public Cursor carregaDados(){
        // salva as informações que são retornadas do banco de dados
        Cursor cursor;
        // Array que armazena o que sera puxado do banco
        String[] campos = {banco.id, banco.titulo, banco.categoria, banco.classificacao};
        // .GetRead diz que sera apenas operação de leitura
        db = banco.getReadableDatabase();

        cursor = db.query(banco.tabela, campos, null, null, null, null, null, null);

        // Antes do cursor ser retornado para ser tratado na interface do usuário, deve-se
        // mover seu conteúdo para a primeira posição para que todos os dados sejam exibidos.
        if (cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

}

