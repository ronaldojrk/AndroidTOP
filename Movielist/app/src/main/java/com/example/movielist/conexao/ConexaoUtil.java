package com.example.movielist.conexao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexaoUtil extends SQLiteOpenHelper {
    public static final String NOME_BANCO = "banco.db";
    public static final String TABELA = "favoritos";
    public static final String ID = "_id";
    public static final String NOME = "nome";
    public static final String ID_FILME = "idFilme";
    public static final String ID_USER = "idUser";
    public static final int VERSAO = 1;

    public ConexaoUtil(Context context){
        super(context, NOME_BANCO,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABELA+"(" + ID + " integer primary key autoincrement," + NOME + " text,"+ ID_FILME + " text," + ID_USER + " text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(db);
    }
}
