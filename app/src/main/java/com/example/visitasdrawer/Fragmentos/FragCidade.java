package com.example.visitasdrawer.Fragmentos;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.swipe.util.Attributes;
import com.example.visitasdrawer.Activitys.CadastroCidade;
import com.example.visitasdrawer.Adapter.CidadeAdapter;
import com.example.visitasdrawer.DAO.CidadeDAO;
import com.example.visitasdrawer.R;
import com.example.visitasdrawer.utils.Cidade;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragCidade extends Fragment {

    RecyclerView recyclerView;
    FloatingActionButton btn;


    View vista;


    public FragCidade() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        vista = inflater.inflate(R.layout.fragment_frag_cidade, container, false);



        recyclerView = (RecyclerView) vista.findViewById(R.id.rc_cidade);
        btn =(FloatingActionButton) vista.findViewById(R.id.btn_flu_cidade);




        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CadastroCidade.class);
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


        CidadeDAO dao = new CidadeDAO(getActivity());


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(linearLayoutManager);


        List<Cidade> c = dao.retornarTodos();

        CidadeAdapter adapter = new CidadeAdapter(getContext(),c);

        ((CidadeAdapter) adapter).setMode(Attributes.Mode.Single);

        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.e("RecyclerView", "onScrollStateChanged");
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });


    }


}
