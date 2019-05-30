package com.example.visitasdrawer.DB;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class dbTabelaAuto extends SQLiteOpenHelper {

    private static final String DATABASE ="dbAuto.db";
    private static  final int VERSION = 1;


    public dbTabelaAuto(Context context) {
        super(context,DATABASE,null,VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {


        String sql = "create table if not exists auto(" +
                "_id integer primary key autoincrement, " +
                "recusa_receber text, " +
                "obs text, " +
                "equipe text, " +
                "estabelecimento text, " +
                "artigo text, " +
                "data text, " +
                "tipo_auto text);";



        try{

            db.execSQL(sql);


        }catch (SQLException e){

            Log.e(null, "Erro ao criar Banco " + e.getMessage());
        }




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + "auto");


        // create new tables
        onCreate(db);

    }
}
