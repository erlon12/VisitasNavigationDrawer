package com.example.visitasdrawer.Fragmentos;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.visitasdrawer.Activitys.CadastroEstabelecimento;
import com.example.visitasdrawer.Adapter.EstabelecimentoAdapter;
import com.example.visitasdrawer.DAO.EstabelecimentoDAO;
import com.example.visitasdrawer.R;
import com.example.visitasdrawer.utils.Estabelecimento;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragEstabelecimento extends Fragment {

     RecyclerView recyclerView;
     FloatingActionButton btn;

    View vista;


    public FragEstabelecimento() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        vista = inflater.inflate(R.layout.fragment_frag__estabelecimento, container, false);

        recyclerView = (RecyclerView) vista.findViewById(R.id.recy_view);
        btn =(FloatingActionButton) vista.findViewById(R.id.btn_flutuante);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),CadastroEstabelecimento.class);
                startActivity(intent);

            }
        });


        return vista;


    }

    @Override
    public void onStart() {
        super.onStart();
        readDatabase();
    }

    private void readDatabase() {


        EstabelecimentoDAO dao = new EstabelecimentoDAO(getActivity());


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(linearLayoutManager);


        List<Estabelecimento> e = dao.retornarTodos();

        recyclerView.setAdapter(new EstabelecimentoAdapter(e));

    }
}
