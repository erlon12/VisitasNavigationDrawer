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

    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //TODO: Step 4 of 4: Finally call getTag() on the view.
            // This viewHolder will have all required values.
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            // viewHolder.getItemId();
            // viewHolder.getItemViewType();
            // viewHolder.itemView;
           



        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        vista = inflater.inflate(R.layout.fragment_frag__estabelecimento, container, false);

        recyclerView = (RecyclerView) vista.findViewById(R.id.recy_view);
        btn =(FloatingActionButton) vista.findViewById(R.id.btn_flutuante);

       // habilitarExclusaoLinha();


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

        EstabelecimentoAdapter adapter = new EstabelecimentoAdapter(getContext(),e);

        ((EstabelecimentoAdapter) adapter).setMode(Attributes.Mode.Single);

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


   /* private void habilitarExclusaoLinha() {
        DeleteCall swipeToDeleteCallback = new DeleteCall(getContext()) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


                final int position = viewHolder.getAdapterPosition();
                final Estabelecimento item = adapter.getData().get(position);



                 EstabelecimentoDAO estbDao = new EstabelecimentoDAO(getContext());
                 estbDao.delete(item.getId());
                adapter.removeItem(position);



            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(recyclerView);
    }*/
}
