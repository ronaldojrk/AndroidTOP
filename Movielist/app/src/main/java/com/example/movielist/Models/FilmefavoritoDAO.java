package com.example.movielist.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.movielist.conexao.ConexaoUtil;

import java.util.ArrayList;
import java.util.List;

public class FilmefavoritoDAO {
    private SQLiteDatabase db;
    private ConexaoUtil conexao;

    public FilmefavoritoDAO(Context context){
        conexao = new ConexaoUtil(context);
    }

    public String insereDado(Filmefavoritos filmefavoritos){
        ContentValues valores;
        long resultado;

        db = conexao.getWritableDatabase();
        valores = new ContentValues();
        valores.put(ConexaoUtil.NOME, filmefavoritos.getNome());
        valores.put(ConexaoUtil.ID_FILME, filmefavoritos.getIdfilme());
        valores.put(ConexaoUtil.ID_USER, filmefavoritos.getIdusuario());
        // nullcolumnhack identificar coluna que aceite nulo
        resultado = db.insert(ConexaoUtil.TABELA, null, valores);
        db.close();

        if (resultado ==-1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";

    }

    public Cursor carregaDados(){
        Cursor cursor;
        String[] campos =  {conexao.ID,conexao.NOME,conexao.ID_FILME,conexao.ID_USER};
        db = conexao.getReadableDatabase();
        cursor = db.query(conexao.TABELA, campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor carregaDadoById(int id){
        Cursor cursor;
        String[] campos =  {conexao.ID,conexao.NOME,conexao.ID_FILME,conexao.ID_USER};
        String where = ConexaoUtil.ID + "=" + id;
        db = conexao.getReadableDatabase();
        cursor = db.query(ConexaoUtil.TABELA,campos,where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public void alteraRegistro(Filmefavoritos professor){
        ContentValues valores;
        String where;

        db = conexao.getWritableDatabase();

        where = ConexaoUtil.ID + "=" + professor.getId();

        valores = new ContentValues();
        valores.put(ConexaoUtil.NOME, professor.getNome());
        valores.put(ConexaoUtil.ID_FILME, professor.getIdfilme());
        valores.put(ConexaoUtil.ID_USER, professor.getIdusuario());

        db.update(ConexaoUtil.TABELA,valores,where,null);
        db.close();
    }

    public void deletaRegistro(Filmefavoritos professor){
        String where = ConexaoUtil.ID + "=" + professor.getId();
        db = conexao.getReadableDatabase();
        db.delete(ConexaoUtil.TABELA,where,null);
        db.close();
    }


    public static List<Filmefavoritos> listar(Context context){
        ConexaoUtil dbOpenHelper = new ConexaoUtil(context);
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();

        Cursor cursor;
        String TABELA = "banco.db";
        String[] campos =  {"id", "nome", "idfilme", "idusuario",};

        cursor = db.query(TABELA, campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
            List<Filmefavoritos> filmefavoritosList = new ArrayList<>();
            while (!cursor.isAfterLast()){

                int ID = Integer.parseInt(cursor.getString(0));
                String nome = cursor.getString(1);
                String idfilme = cursor.getString(2);
                String idusuario = cursor.getString(3);
                Filmefavoritos professor = new Filmefavoritos(ID, nome,idfilme,idusuario);
                filmefavoritosList.add(professor);

                cursor.moveToNext();
            }
            if (!cursor.isClosed()) {
                cursor.close();
            }
            return filmefavoritosList;
        }
        return null;
    }
}
