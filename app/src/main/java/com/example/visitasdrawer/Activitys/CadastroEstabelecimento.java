package com.example.visitasdrawer.Activitys;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.visitasdrawer.DAO.CidadeDAO;
import com.example.visitasdrawer.DAO.EstabelecimentoDAO;
import com.example.visitasdrawer.R;
import com.example.visitasdrawer.utils.Cidade;
import com.example.visitasdrawer.utils.Estabelecimento;

import java.util.ArrayList;
import java.util.List;

public class CadastroEstabelecimento extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText edt_numero, edt_cnpj, edt_razao, edt_cep, edt_idcity;
    Button btn_salvar;
    Spinner spiner;
    String cidade;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_estabelecimento);


        edt_numero = (EditText) findViewById(R.id.edt_uf);

        edt_cnpj = (EditText) findViewById(R.id.edt_cnpj);

        edt_razao = (EditText) findViewById(R.id.edt_nome);

        edt_cep = (EditText) findViewById(R.id.edt_cep);

        spiner = (Spinner) findViewById(R.id.id_spinner);

        btn_salvar = (Button) findViewById(R.id.button2);


        CidadeDAO cdao = new CidadeDAO(CadastroEstabelecimento.this);

        List<Cidade> c = cdao.retornarTodos();

        ArrayList<String> nomesCidades = new ArrayList<String>();

        spiner.getBackground().setColorFilter(Color.parseColor("#FF0000"), PorterDuff.Mode.SRC_ATOP);


        for (int i = 0; i < c.size(); i++) {

            Cidade cidade = new Cidade();

            cidade.setId(c.get(i).getId());
            cidade.setNome(c.get(i).getNome());
            cidade.setUf(c.get(i).getUf());

            nomesCidades.add(c.get(i).getNome());
        }


        ArrayAdapter<String> adpter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, nomesCidades);

        adpter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);


        spiner.setAdapter(adpter);

        spiner.setOnItemSelectedListener(this);

        btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Validar()){
                    Salvar();
                }

            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

        String itenSelecionado = adapterView.getItemAtPosition(position).toString();
        cidade = itenSelecionado;


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

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
}
