package com.example.visitasdrawer.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.example.visitasdrawer.DB.dbTabelaAuto;
import com.example.visitasdrawer.DB.dbTabelaCidade;
import com.example.visitasdrawer.utils.Auto;
import com.example.visitasdrawer.utils.Cidade;

import java.util.ArrayList;
import java.util.List;

public class AutoDAO {
    private SQLiteDatabase db;
    private dbTabelaAuto dbTabela;


    public AutoDAO(Context context) {
        dbTabela = new dbTabelaAuto(context);
    }

    public void save(Auto a) {

        ContentValues values = new ContentValues();
        values.put("estabelecimento", a.getEstabelecimento());
        values.put("obs", a.getObs());
        values.put("equipe", a.getEquipe());
        values.put("artigo", a.getArtigo());
        values.put("data", a.getData());
        values.put("recusa_receber", a.getRecura_receber());
        values.put("tipo_auto", a.getTipoAuto());


        try {
            db = dbTabela.getWritableDatabase();
            db.insert("auto", null, values);
            db.close();
        } catch (SQLiteException ee) {
            Log.e("AutoDAO", "Erro ao inserir Auto: " + ee.getMessage());
        }
    }

    public List<Auto> retornarTodos(){

        String [] colunas = {"_id,recusa_receber,obs,equipe,estabelecimento,artigo,data,tipo_auto"};

        ArrayList<Auto> listaAuto= new ArrayList<Auto>();
        Cursor cursor = null;
        db = dbTabela.getReadableDatabase();

        cursor=db.query("auto",colunas,null,null,null,null,null,null);

        while(cursor.moveToNext()==true){
            int id = cursor.getInt(cursor.getColumnIndex("_id"));

            String recusa_receber = cursor.getString(cursor.getColumnIndex("recusa_receber"));

            String obs = cursor.getString(cursor.getColumnIndex("obs"));
            String equipe = cursor.getString(cursor.getColumnIndex("equipe"));
            String estabelecimento = cursor.getString(cursor.getColumnIndex("estabelecimento"));
            String artigo = cursor.getString(cursor.getColumnIndex("artigo"));
            String data = cursor.getString(cursor.getColumnIndex("data"));
            String tipo_auto = cursor.getString(cursor.getColumnIndex("tipo_auto"));

            Auto a = new Auto();

            a.setId(id);
            a.setArtigo(artigo);
            a.setData(data);
            a.setEquipe(equipe);
            a.setEstabelecimento(estabelecimento);
            a.setObs(obs);
            a.setRecura_receber(recusa_receber);
            a.setTipoAuto(tipo_auto);

            listaAuto.add(a);
        }
        cursor.close();

        return listaAuto;
    }
    public void delete(int id)
    {
        String[] args = {String.valueOf(id)};

        try {
            db = dbTabela.getWritableDatabase();
            db.delete("auto", "_id=?", args);
            db.close();
        }catch(SQLiteException e){
            Log.e("AutoDAO", "Erro ao deletar auto: " + e.getMessage());
        }

    }
}
