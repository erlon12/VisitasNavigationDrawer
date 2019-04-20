package com.example.visitasdrawer.Adapter;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.visitasdrawer.R;
import com.example.visitasdrawer.utils.Estabelecimento;

import java.util.List;

public class EstabelecimentoAdapter extends RecyclerView.Adapter<EstabelecimentoAdapter.ViewHolderEstabelecimento> {



    private List<Estabelecimento> dados;


    public EstabelecimentoAdapter(List<Estabelecimento> dados){

        this.dados=dados;


    }

    @NonNull
    @Override
    public ViewHolderEstabelecimento onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.linha_estabelecimento, parent, false);


        ViewHolderEstabelecimento holderVisitas = new ViewHolderEstabelecimento(view);

        return holderVisitas;
    }

    @Override
    public void onBindViewHolder(@NonNull EstabelecimentoAdapter.ViewHolderEstabelecimento holder, int position) {


        if((dados!= null) && (dados.size()>0)) {

            Estabelecimento e = dados.get(position);

          // int n = Integer.parseInt(e.getCnpj());
            holder.txtCidade.setText(e.getCidade());
            holder.txtEstabelecimento.setText(e.getRazao());
            holder.txtCnpj.setText(e.getCnpj());

        }
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }



    public class ViewHolderEstabelecimento extends RecyclerView.ViewHolder{


        public TextView txtCidade;
        public TextView txtCnpj;
        public TextView txtEstabelecimento;

        public ViewHolderEstabelecimento(View itemView) {
            super(itemView);

            txtCidade = itemView.findViewById(R.id.txt_cidade);
            txtCnpj = itemView.findViewById(R.id.txt_cnpj);
            txtEstabelecimento = itemView.findViewById(R.id.txt_razao);

        }
    }
}
