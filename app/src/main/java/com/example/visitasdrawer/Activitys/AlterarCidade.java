package com.example.visitasdrawer.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.visitasdrawer.DAO.CidadeDAO;
import com.example.visitasdrawer.R;
import com.example.visitasdrawer.utils.Cidade;

import br.com.joinersa.oooalertdialog.Animation;
import br.com.joinersa.oooalertdialog.OnClickListener;
import br.com.joinersa.oooalertdialog.OoOAlertDialog;

public class AlterarCidade extends AppCompatActivity {


    TextInputLayout alter_nome,alter_uf;
    Button btn_alter;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_cidade);

        alter_nome = findViewById(R.id.edt_alter_nome_cidade);
        alter_uf = findViewById(R.id.edt_alter_uf);
        btn_alter = (Button)findViewById(R.id.btn_alterar_cidade);


        Intent intent = getIntent();

        String uf= intent.getStringExtra("uf");
        alter_uf.getEditText().setText(uf);
        String cidade =intent.getStringExtra("cidade");
        alter_nome.getEditText().setText(cidade);


         id = intent.getIntExtra("id",999999);




         btn_alter.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if(Validar()){
                     Alterar();
                 }
             }
         });


    }

    public boolean Validar(){


        String nome = alter_nome.getEditText().getText().toString();
        String uf = alter_uf.getEditText().getText().toString();
        boolean validar = false;




        if(nome.isEmpty()){
            alter_nome.setError("Campo Obrigatorio");

        }else
        if(uf.isEmpty()){
            alter_uf.setError("Campo Obrigatorio");
            validar =false;
        }else{
            validar = true;
        }


        return validar;
    }

    public void Alterar(){


        CidadeDAO dao = new CidadeDAO(AlterarCidade.this);

        Cidade c = new Cidade();
        c.setNome(alter_nome.getEditText().getText().toString());
        String uf =alter_uf.getEditText().getText().toString();

        c.setUf(uf.toUpperCase());
        c.setId(id);


        dao.update(c);

        new OoOAlertDialog.Builder((Activity) AlterarCidade.this)


                .setTitle("Aviso!!")
                .setMessage("Cidade Alterada com Sucesso! ")

                .setAnimation(Animation.POP)

                .setPositiveButton("Fechar", new OnClickListener() {
                    @Override
                    public void onClick() {

                        finish();
                    }
                })
                .build();


    }
}
