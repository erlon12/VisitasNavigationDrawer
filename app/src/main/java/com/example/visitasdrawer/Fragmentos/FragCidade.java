package com.example.visitasdrawer.Fragmentos;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.visitasdrawer.Activitys.CadastroCidade;
import com.example.visitasdrawer.Activitys.CadastroEstabelecimento;
import com.example.visitasdrawer.Adapter.CidadeAdapter;
import com.example.visitasdrawer.Adapter.EstabelecimentoAdapter;
import com.example.visitasdrawer.DAO.CidadeDAO;
import com.example.visitasdrawer.DAO.EstabelecimentoDAO;
import com.example.visitasdrawer.R;
import com.example.visitasdrawer.utils.Cidade;
import com.example.visitasdrawer.utils.DeleteCall;
import com.example.visitasdrawer.utils.Estabelecimento;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragCidade extends Fragment {

    RecyclerView recyclerView;
    FloatingActionButton btn;
    CidadeAdapter adapter;

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


        habilitarExclusaoLinha();

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
            adapter = new CidadeAdapter(c,getContext());
        recyclerView.setAdapter(adapter);

    }
    private void habilitarExclusaoLinha() {
        DeleteCall swipeToDeleteCallback = new DeleteCall(getContext()) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


                final int position = viewHolder.getAdapterPosition();
                final Cidade item = adapter.getData().get(position);



                CidadeDAO cdao = new CidadeDAO(getContext());
                cdao.delete(item.getId());
                adapter.removeItem(position);



            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(recyclerView);
    }

}
