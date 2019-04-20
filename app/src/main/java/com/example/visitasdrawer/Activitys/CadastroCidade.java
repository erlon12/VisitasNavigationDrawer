package com.example.visitasdrawer.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.visitasdrawer.DAO.CidadeDAO;
import com.example.visitasdrawer.R;
import com.example.visitasdrawer.utils.Cidade;

public class CadastroCidade extends AppCompatActivity {

    EditText edt_nome, edt_uf;
    Button btn_salvar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cidade);

       





        edt_nome = (EditText)findViewById(R.id.edt_nome);
        edt_uf = (EditText)findViewById(R.id.edt_uf);
        btn_salvar =(Button)findViewById(R.id.btn);


        btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CidadeDAO dao = new CidadeDAO(CadastroCidade.this);

                Cidade c = new Cidade();
                c.setNome(edt_nome.getText().toString());
                c.setUf(edt_uf.getText().toString());


                dao.save(c);
                finish();
            }
        });
    }
}
