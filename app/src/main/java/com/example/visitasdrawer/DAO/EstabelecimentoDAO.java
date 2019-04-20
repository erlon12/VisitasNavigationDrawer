package com.example.visitasdrawer.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.example.visitasdrawer.DB.dbTabelaEstabelecimento;
import com.example.visitasdrawer.Fragmentos.FragEstabelecimento;
import com.example.visitasdrawer.utils.Estabelecimento;

import java.util.ArrayList;
import java.util.List;

public class EstabelecimentoDAO {

    private SQLiteDatabase db;
    private dbTabelaEstabelecimento DbEstabelecimento;

    public EstabelecimentoDAO(Context context){

        DbEstabelecimento = new dbTabelaEstabelecimento(context);
    }


    public void save(Estabelecimento e){

        ContentValues values = new ContentValues();
        values.put("numero", e.getNumero());
        values.put("cnpj", e.getCnpj());
        values.put("razao", e.getRazao());
        values.put("cep", e.getCep());
        values.put("cidade", e.getCidade());

        try {
            db = DbEstabelecimento.getWritableDatabase();
            db.insert("estabelecimento", null, values);
            db.close();
        }catch(SQLiteException ee){
            Log.e("EstabelecimentoDAO", "Erro ao inserir Estabelecimento: " + ee.getMessage());
        }
    }

    public Cursor listCursor(){

        Cursor cursor = null;
        db = DbEstabelecimento.getReadableDatabase();

        try {
            cursor = db.query("estabelecimento", null, null, null, null, null, null);
        }catch(SQLiteException e){
            Log.e("estabelecimentoDAO", "Erro ao listar Estabelecimento: " + e.getMessage());
        }

        return cursor;
    }

    public List<Estabelecimento> retornarTodos(){

        String [] colunas = {"_id,numero,cnpj,razao,cep,cidade"};

        ArrayList<Estabelecimento> esta= new ArrayList<Estabelecimento>();
        Cursor cursor = null;
        db = DbEstabelecimento.getReadableDatabase();

        cursor=db.query("estabelecimento",colunas,null,null,null,null,null,null);

        while(cursor.moveToNext()==true){
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            int numero = cursor.getInt(cursor.getColumnIndex("numero"));
            String cnpj = cursor.getString(cursor.getColumnIndex("cnpj"));
            String razao = cursor.getString(cursor.getColumnIndex("razao"));
            String cep = cursor.getString(cursor.getColumnIndex("cep"));
            String cidade = cursor.getString(cursor.getColumnIndex("cidade"));

            Estabelecimento e = new Estabelecimento();
            e.setId(id);
            e.setNumero(numero);
            e.setCnpj(cnpj);
            e.setRazao(razao);
            e.setCep(cep);
            e.setCidade(cidade);

            esta.add(e);
        }
        cursor.close();
        return esta;
    }

}
