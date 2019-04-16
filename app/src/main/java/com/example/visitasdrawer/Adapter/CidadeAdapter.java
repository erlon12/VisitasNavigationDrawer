package com.example.visitasdrawer.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.visitasdrawer.R;
import com.example.visitasdrawer.utils.Cidade;

import java.util.List;

public class CidadeAdapter extends RecyclerView.Adapter<CidadeAdapter.ViewHolderCidade> {

    private List<Cidade> dados;

    public CidadeAdapter(List<Cidade> dados){

        this.dados = dados;

    }

    @NonNull
    @Override
    public ViewHolderCidade onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.linha_cidade, parent, false);


        ViewHolderCidade holderVisitas = new ViewHolderCidade(view);

        return holderVisitas;
    }

    @Override
    public void onBindViewHolder(@NonNull CidadeAdapter.ViewHolderCidade holder, int position) {


        if((dados!= null) && (dados.size()>0)) {

            Cidade e = dados.get(position);

            // int n = Integer.parseInt(e.getCnpj());
            holder.txtCidade.setText(e.getNome());

        }
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }



    public class ViewHolderCidade extends RecyclerView.ViewHolder{


        public TextView txtCidade;
        public TextView txtCnpj;
        public TextView txtEstabelecimento;

        public ViewHolderCidade(View itemView) {
            super(itemView);

            txtCidade = itemView.findViewById(R.id.txt_cidade);
            txtCnpj = itemView.findViewById(R.id.txt_cnpj);
            txtEstabelecimento = itemView.findViewById(R.id.txt_razao);

        }
    }
}
