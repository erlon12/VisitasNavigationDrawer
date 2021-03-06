package com.example.visitasdrawer.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.visitasdrawer.DB.dbTabelaCidade;
import com.example.visitasdrawer.utils.Cidade;
import com.example.visitasdrawer.utils.Estabelecimento;

import java.util.ArrayList;
import java.util.List;

public class CidadeDAO  {

    private SQLiteDatabase db;
    private dbTabelaCidade dbTabelaCidade;

    public CidadeDAO(Context context){

        dbTabelaCidade = new dbTabelaCidade(context);
    }


    public void save(Cidade e){

        ContentValues values = new ContentValues();
        values.put("nome", e.getNome());
        values.put("uf", e.getUf());


        try {
            db = dbTabelaCidade.getWritableDatabase();
            db.insert("cidade", null, values);
            db.close();
        }catch(SQLiteException ee){
            Log.e("cidadeDAO", "Erro ao inserir Cidade: " + ee.getMessage());
        }
    }



    public List<Cidade> retornarTodos(){

        String [] colunas = {"_id,nome,uf"};

        ArrayList<Cidade> esta= new ArrayList<Cidade>();
        Cursor cursor = null;
        db = dbTabelaCidade.getReadableDatabase();

        cursor=db.query("cidade",colunas,null,null,null,null,null,null);

        while(cursor.moveToNext()==true){
            int id = cursor.getInt(cursor.getColumnIndex("_id"));

            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            String uf = cursor.getString(cursor.getColumnIndex("uf"));

            Cidade c = new Cidade();
            c.setId(id);
            c.setNome(nome);
            c.setUf(uf);

            esta.add(c);
        }
        cursor.close();
        return esta;
    }

    public void delete(int id)
    {
        String[] args = {String.valueOf(id)};

        try {
            db = dbTabelaCidade.getWritableDatabase();
            db.delete("cidade", "_id=?", args);
            db.close();
        }catch(SQLiteException e){
        Log.e("CidadeDAO", "Erro ao deletar Cidade: " + e.getMessage());
    }

    }
    public void update(Cidade cidade)
    {
        String[] args = {String.valueOf(cidade.getId())};

        ContentValues v = new ContentValues();
        v.put("uf", cidade.getUf());
        v.put("nome", cidade.getNome());

        try {
            db = dbTabelaCidade.getWritableDatabase();
            db.update("cidade", v, "_id=?", args);

            db.close();
        }catch(SQLiteException e){
            Log.e("CidadeDAO", "Erro ao alterar Cidade: " + e.getMessage());
        }

    }
}
