package com.example.visitasdrawer.Activitys;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.example.visitasdrawer.DAO.CidadeDAO;
import com.example.visitasdrawer.DAO.EstabelecimentoDAO;
import com.example.visitasdrawer.R;
import com.example.visitasdrawer.utils.Cidade;
import com.example.visitasdrawer.utils.Estabelecimento;

import java.util.ArrayList;
import java.util.List;

import br.com.joinersa.oooalertdialog.Animation;
import br.com.joinersa.oooalertdialog.OnClickListener;
import br.com.joinersa.oooalertdialog.OoOAlertDialog;

public class CadastroEstabelecimento extends AppCompatActivity  {





    Button btn_salvar,btn_cidades;

    TextInputLayout edt_numero,edt_cnpj, edt_razao, edt_cep,edt_cidade;

    ArrayList<Cidade> cidadesss;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_estabelecimento);


        edt_numero = findViewById(R.id.alter_numero);
        edt_cnpj = findViewById(R.id.alter_cnpj);
        edt_cep = findViewById(R.id.alter_cep);
        edt_razao = findViewById(R.id.alter_razao);

        edt_cidade=findViewById(R.id.alter_cidade);

        btn_cidades =(Button)findViewById(R.id.btn_select);
        btn_salvar = (Button) findViewById(R.id.btn_alterar);



        edt_cidade.setEnabled(false);






        btn_cidades.setOnClickListener(new View.OnClickListener() {
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

        int numero = Integer.parseInt(edt_numero.getEditText().getText().toString());


        e.setCep(edt_cep.getEditText().getText().toString());
        e.setNumero(numero);
        e.setRazao(edt_razao.getEditText().getText().toString());
        e.setCnpj(edt_cnpj.getEditText().getText().toString());
        e.setCidade(edt_cidade.getEditText().getText().toString());

        dao.save(e);

        new OoOAlertDialog.Builder((Activity) CadastroEstabelecimento.this)


                .setTitle("Aviso!!")
                .setMessage("Estabelecimento Cadastrado com Sucesso! ")

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



        String numero = edt_numero.getEditText().getText().toString();
        String cnpj = edt_cnpj.getEditText().getText().toString();
        String razao = edt_razao.getEditText().getText().toString();
        String cep = edt_cep.getEditText().getText().toString();
        String cidade = edt_cidade.getEditText().getText().toString();
        boolean validar = false;




        if(numero.isEmpty()){
            edt_numero.setError("Campo Obrigatorio");

        }else
        if(cnpj.isEmpty()){
            edt_cnpj.setError("Campo Obrigatorio");
            validar =false;
        }else if(razao.isEmpty()){
            edt_razao.setError("Campo Obrigatorio");
            validar =false;
        }else if(cep.isEmpty()){
            edt_cep.setError("Campo Obrigatorio");
            validar =false;
        }else
        if (cidade.isEmpty()) {
            validar = false;
            Toast.makeText(this, "Selecione uma cidade", Toast.LENGTH_LONG).show();
        }else{
            validar = true;
        }




        return validar;
    }


    private void open_dialog(){
        CidadeDAO cdao = new CidadeDAO(CadastroEstabelecimento.this);
        List<Cidade> c = cdao.retornarTodos();

        cidadesss = new ArrayList<>();

        final ArrayList<String> nomesCidades = new ArrayList<String>();



        ArrayList<Cidade> cidades = new ArrayList<Cidade>();
        for (int i = 0; i < c.size(); i++) {

            Cidade cidade = new Cidade();

            cidade.setId(c.get(i).getId());
            cidade.setNome(c.get(i).getNome());
            cidade.setUf(c.get(i).getUf());


            nomesCidades.add(cidade.getNome());

            cidadesss.add(cidade);
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,nomesCidades);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Escolha uma cidade");



        alert.setSingleChoiceItems(adapter, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                edt_cidade.getEditText().setText(nomesCidades.get(i));
                dialogInterface.dismiss();
            }
        });


        final AlertDialog dialog = alert.create();
        dialog.show();

    }
}
