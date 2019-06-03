package com.example.visitasdrawer.Activitys;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.example.visitasdrawer.DAO.AutoDAO;
import com.example.visitasdrawer.DAO.EstabelecimentoDAO;
import com.example.visitasdrawer.R;
import com.example.visitasdrawer.utils.Auto;
import com.example.visitasdrawer.utils.Estabelecimento;

import java.util.ArrayList;
import java.util.List;

import br.com.joinersa.oooalertdialog.Animation;
import br.com.joinersa.oooalertdialog.OnClickListener;
import br.com.joinersa.oooalertdialog.OoOAlertDialog;

public class AlterarAuto extends AppCompatActivity {

    TextInputLayout edt_data, edt_tipoAuto, edt_artigo, edt_recura_receber, edt_obs, edt_equipe, edt_estabelecimento;


    String cnpj;


    ArrayList<Estabelecimento> estabelecimentos;
    Button btn_alterar,btn_selectEstab,btn_tipoAuto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_auto);

        edt_data = findViewById(R.id.edt_data_alter);
        edt_artigo = findViewById(R.id.edt_artigo_alter);
        edt_equipe = findViewById(R.id.edt_equipe_alter);
        edt_estabelecimento = findViewById(R.id.edt_estab_auto_alter);
        edt_obs = findViewById(R.id.edt_obs_alter);
        edt_recura_receber = findViewById(R.id.edt_recusa_alter);

        edt_tipoAuto = findViewById(R.id.edt_tipoauto_alter);

        btn_alterar = (Button)findViewById(R.id.btn_cadastro_alter);
        btn_tipoAuto = (Button)findViewById(R.id.btn_cad_tipoauto_alter);

        btn_selectEstab = (Button)findViewById(R.id.btn_estabAuto_alter);

        edt_estabelecimento.setEnabled(false);
        edt_tipoAuto.setEnabled(false);


        Intent intent=getIntent();

        String data = intent.getStringExtra("data");
        edt_data.getEditText().setText(data);
        String artigo =intent.getStringExtra("artigo");
        edt_artigo.getEditText().setText(artigo);
        String razao = intent.getStringExtra("razao");
        edt_estabelecimento.getEditText().setText(razao);
        String recusou_receber = intent.getStringExtra("receber");
        edt_recura_receber.getEditText().setText(recusou_receber);
        String irregularidade = intent.getStringExtra("irregularidade");
        edt_tipoAuto.getEditText().setText(irregularidade);
        String obs = intent.getStringExtra("obs");
        edt_obs.getEditText().setText(obs);
        String equipe =intent.getStringExtra("equipe");
        edt_equipe.getEditText().setText(equipe);

        String cnpjre = intent.getStringExtra("cnpj");

        final int id = intent.getIntExtra("id",999999999);




        btn_selectEstab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_listEstabelecimento();
            }
        });
        btn_tipoAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_listTipoAuto();
            }
        });
        btn_alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Validar()){
                    Auto auto = new Auto();
                    AutoDAO dao = new AutoDAO(AlterarAuto.this);

                    auto.setData(edt_data.getEditText().getText().toString());
                    auto.setRecura_receber(edt_recura_receber.getEditText().getText().toString());
                    auto.setTipoAuto(edt_tipoAuto.getEditText().getText().toString());
                    auto.setObs(edt_obs.getEditText().getText().toString());
                    auto.setArtigo(edt_artigo.getEditText().getText().toString());
                    auto.setEstabelecimento(edt_estabelecimento.getEditText().getText().toString());
                    auto.setEquipe(edt_equipe.getEditText().getText().toString());
                    auto.setCnpj(cnpj);
                    auto.setId(id);

                    dao.update(auto);
                    new OoOAlertDialog.Builder((Activity) AlterarAuto.this)


                            .setTitle("Aviso!!")
                            .setMessage("Auto Alterado com Sucesso! ")

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
        });



    }

    public boolean Validar(){

        String estabelecimento = edt_estabelecimento.getEditText().getText().toString();
        String artigo = edt_artigo.getEditText().getText().toString();
        String data = edt_data.getEditText().getText().toString();
        String tipoAuto = edt_tipoAuto.getEditText().getText().toString();
        String recusaAreceber = edt_recura_receber.getEditText().getText().toString();
        String obs = edt_obs.getEditText().getText().toString();
        String equipe = edt_equipe.getEditText().getText().toString();

        boolean validar = false;




        if(estabelecimento.isEmpty()){
            edt_estabelecimento.setError("Campo Obrigatório");

        }else
        if(data.isEmpty()){
            edt_data.setError("Campo Obrigatório");
            validar =false;
        }else if(equipe.isEmpty()){
            edt_equipe.setError("Campo Obrigatório");
            validar =false;
        }else if(tipoAuto.isEmpty()){
            edt_tipoAuto.setError("Campo Obrigatório");
            validar =false;

        }else if(artigo.isEmpty()){
            edt_artigo.setError("Campo Obrigatório");
            validar =false;
        }else
        if(recusaAreceber.isEmpty()){
            edt_recura_receber.setError("Campo Obrigatório");
            validar =false;
        }else
        if (obs.isEmpty()) {
            edt_obs.setError("Campo Obrigatório");
            validar = false;
            Toast.makeText(this, "Selecione uma cidade", Toast.LENGTH_LONG).show();
        }else{
            validar = true;
        }




        return validar;

    }

    private void dialog_listEstabelecimento(){
        EstabelecimentoDAO edao = new EstabelecimentoDAO(AlterarAuto.this);
        List<Estabelecimento> e = edao.retornarTodos();

        estabelecimentos = new ArrayList<>();

        final ArrayList<String> nomesEstab = new ArrayList<String>();
        final ArrayList<String> cnpjts = new ArrayList<String>();



        //ArrayList<Estabelecimento> estabelecimentos = new ArrayList<Estabelecimento>();
        for (int i = 0; i < e.size(); i++) {

            Estabelecimento estabelecimento = new Estabelecimento();

            estabelecimento.setId(e.get(i).getId());
            estabelecimento.setCnpj(e.get(i).getCnpj());
            estabelecimento.setCidade(e.get(i).getCidade());
            estabelecimento.setNumero(e.get(i).getNumero());
            estabelecimento.setRazao(e.get(i).getRazao());
            estabelecimento.setCep(e.get(i).getCep());

            nomesEstab.add(estabelecimento.getRazao());



            cnpjts.add(estabelecimento.getCnpj());
            estabelecimentos.add(estabelecimento);
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,nomesEstab);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Estabelecimentos Cadastrados");

        alert.setSingleChoiceItems(adapter, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                edt_estabelecimento.getEditText().setText(nomesEstab.get(i));
                cnpj = cnpjts.get(i);

                Log.e("CadastroAuto", "CNPJ AUTO: " + cnpj);

                dialogInterface.dismiss();
            }
        });


        final AlertDialog dialog = alert.create();
        dialog.show();

    }

    private void dialog_listTipoAuto(){

        final ArrayList<String> tipoDeAutos = new ArrayList<String>();
        tipoDeAutos.add("Auto de Infração");
        tipoDeAutos.add("Auto de Constatação");
        tipoDeAutos.add("Auto de Apreensão");
        tipoDeAutos.add("Nenhum");

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,tipoDeAutos);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Estabelecimentos Cadastrados");

        alert.setSingleChoiceItems(adapter, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                edt_tipoAuto.getEditText().setText(tipoDeAutos.get(i));

                dialogInterface.dismiss();
            }
        });


        final AlertDialog dialog = alert.create();
        dialog.show();


    }
}
