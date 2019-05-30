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

import com.example.visitasdrawer.Activitys.CadastroAuto;
import com.example.visitasdrawer.Activitys.CadastroCidade;
import com.example.visitasdrawer.Adapter.AutoAdapter;
import com.example.visitasdrawer.Adapter.CidadeAdapter;
import com.example.visitasdrawer.DAO.AutoDAO;
import com.example.visitasdrawer.DAO.CidadeDAO;
import com.example.visitasdrawer.R;
import com.example.visitasdrawer.utils.Auto;
import com.example.visitasdrawer.utils.Cidade;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragAutos extends Fragment {

    RecyclerView recyclerView;
    FloatingActionButton btn;

    View vista;

    public FragAutos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       vista =inflater.inflate(R.layout.fragment_frag_autos, container, false);



        recyclerView = (RecyclerView) vista.findViewById(R.id.rc_autos);
        btn =(FloatingActionButton) vista.findViewById(R.id.btn_flu_autos);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CadastroAuto.class);
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


        AutoDAO dao = new AutoDAO(getActivity());


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(linearLayoutManager);


        List<Auto> a = dao.retornarTodos();

        recyclerView.setAdapter(new AutoAdapter(a,getContext()));

    }

}