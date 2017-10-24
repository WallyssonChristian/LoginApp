package com.example.administrador.loginapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class CriaBanco extends SQLiteOpenHelper {
    private static final String nome_Banco = "banco.db";
    protected static final String tabela = "filmes";
    protected static final String id = "_id";
    protected static final String titulo = "titulo";
    protected static final String categoria = "categoria";
    protected static final String classificacao = "classificacao";
    private static final int versao = 1;

    public CriaBanco(Context context) {
        super(context, nome_Banco, null, versao);
    }

    // é chamado quando a aplicação cria o banco de dados pela primeira vez. Nesse método devem ter
    // todas as diretrizes de criação e população inicial do banco.
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + tabela + "("
                + id + " integer primary key autoincrement,"
                + titulo + " text,"
                + categoria + " text,"
                + classificacao + " text"
                + ")";
        sqLiteDatabase.execSQL(sql);
    }

    // é o método responsável por atualizar o banco de dados com alguma informação estrutural que
    // tenha sido alterada. Ele sempre é chamado quando uma atualização é necessária.
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + tabela);
        onCreate(sqLiteDatabase);
    }
}
