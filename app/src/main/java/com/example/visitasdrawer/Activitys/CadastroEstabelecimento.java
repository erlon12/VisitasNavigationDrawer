package com.example.visitasdrawer.Activitys;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.visitasdrawer.Adapter.CidadeAdapter;
import com.example.visitasdrawer.DAO.CidadeDAO;
import com.example.visitasdrawer.DAO.EstabelecimentoDAO;
import com.example.visitasdrawer.R;
import com.example.visitasdrawer.utils.Cidade;
import com.example.visitasdrawer.utils.Estabelecimento;

import java.util.ArrayList;
import java.util.List;

public class CadastroEstabelecimento extends AppCompatActivity  {

    EditText edt_numero, edt_cnpj, edt_razao, edt_cep, edt_cidade;
    Button btn_salvar,btn_select;
    Spinner spiner;
    String cidade="cidade1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_estabelecimento);


        edt_numero = (EditText) findViewById(R.id.edt_uf);

        edt_cnpj = (EditText) findViewById(R.id.edt_cnpj);

        edt_razao = (EditText) findViewById(R.id.edt_nome);

        edt_cep = (EditText) findViewById(R.id.edt_cep);

        edt_cidade = (EditText)findViewById(R.id.edt_cidade);
        btn_select = (Button)findViewById(R.id.btn_select);
        btn_salvar = (Button) findViewById(R.id.button2);



        edt_cidade.setEnabled(false);


        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_dialog();
            }
        });

        btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Validar()){
                    Salvar();
                }

            }
        });




    }






   public void Salvar() {


        EstabelecimentoDAO dao = new EstabelecimentoDAO(CadastroEstabelecimento.this);

        Estabelecimento e = new Estabelecimento();

        int numero = Integer.parseInt(edt_numero.getText().toString());


        e.setCep(edt_cep.getText().toString());
        e.setNumero(numero);
        e.setRazao(edt_razao.getText().toString());
        e.setCnpj(edt_cnpj.getText().toString());
        e.setCidade(cidade);
        dao.save(e);
        finish();




    }


    public boolean Validar(){



             String numero = edt_numero.getText().toString();
            String cnpj = edt_cnpj.getText().toString();
            String razao = edt_razao.getText().toString();
            String cep = edt_cep.getText().toString();
            boolean validar = false;




           if(numero.equals("")){
               edt_numero.setError("Campo Obrigatorio");

           }else
           if(cnpj.equals("")){
               edt_cnpj.setError("Campo Obrigatorio");
               validar =false;
           }else if(razao.equals("")){
               edt_razao.setError("Campo Obrigatorio");
               validar =false;
           }else if(cep.equals("")){
            edt_cep.setError("Campo Obrigatorio");
            validar =false;
            }else
            if (cidade.equals("Selecione a cidade")) {
                validar = false;
                Toast.makeText(this, "Selecione uma cidade", Toast.LENGTH_LONG).show();
            }else{
                validar = true;
             }




            return validar;
        }
    private void open_dialog(){

        RecyclerView lista = new RecyclerView(this);


        CidadeDAO dao = new CidadeDAO(this);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        lista.setLayoutManager(linearLayoutManager);


        List<Cidade> c = dao.retornarTodos();


        lista.setAdapter(new CidadeAdapter(c,this));



        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setCancelable(true);
        alert.setTitle("Escolha uma cidade");
        alert.setView(lista);

        final AlertDialog dialog = alert.create();
        dialog.show();










    }


}
