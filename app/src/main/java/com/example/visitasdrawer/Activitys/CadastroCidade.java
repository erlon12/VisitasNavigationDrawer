package com.example.visitasdrawer.Activitys;

import android.app.Activity;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.visitasdrawer.DAO.CidadeDAO;
import com.example.visitasdrawer.R;
import com.example.visitasdrawer.utils.Cidade;

import br.com.joinersa.oooalertdialog.Animation;
import br.com.joinersa.oooalertdialog.OnClickListener;
import br.com.joinersa.oooalertdialog.OoOAlertDialog;

public class CadastroCidade extends AppCompatActivity {

    TextInputLayout edt_nome, edt_uf;
    Button btn_salvar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cidade);


        edt_nome = findViewById(R.id.edt_alter_nome_cidade);
        edt_uf = findViewById(R.id.edt_alter_uf);
        btn_salvar =(Button)findViewById(R.id.btn);


        btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Validar()){

                    Salvar();
                }

            }
        });
    }



    public void Salvar(){

        CidadeDAO dao = new CidadeDAO(CadastroCidade.this);

        Cidade c = new Cidade();
        c.setNome(edt_nome.getEditText().getText().toString());
        String uf =edt_uf.getEditText().getText().toString();

        c.setUf(uf.toUpperCase());


        dao.save(c);
        new OoOAlertDialog.Builder((Activity) CadastroCidade.this)


                .setTitle("Aviso!!")
                .setMessage("Cidade Cadastrada com Sucesso! ")

                .setAnimation(Animation.POP)

                .setPositiveButton("Fechar", new OnClickListener() {
                    @Override
                    public void onClick() {

                        finish();
                    }
                })
                .build();

    }

    public boolean Validar(){


        String nome = edt_nome.getEditText().getText().toString();
        String uf = edt_uf.getEditText().getText().toString();
        boolean validar = false;




        if(nome.isEmpty()){
            edt_nome.setError("Campo Obrigatorio");

        }else
        if(uf.isEmpty()){
            edt_uf.setError("Campo Obrigatorio");
            validar =false;
        }else{
            validar = true;
        }


        return validar;
    }
}
