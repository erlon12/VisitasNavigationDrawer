package com.example.visitasdrawer.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.visitasdrawer.R;
import com.example.visitasdrawer.utils.Cidade;

import java.util.ArrayList;
import java.util.List;

public class CidadeAdapter extends RecyclerView.Adapter<CidadeAdapter.ViewHolderCidade> {

    private List<Cidade> dados;
    private final Context context;
    private View.OnClickListener mOnItemClickListener;

    public CidadeAdapter(List<Cidade> dados,Context context){

        this.dados = dados;
        this.context =context;

    }

    @NonNull
    @Override
    public ViewHolderCidade onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.linha_cidade, parent, false);


        ViewHolderCidade holderVisitas = new ViewHolderCidade(view,context);

        return holderVisitas;
    }

    @Override
    public void onBindViewHolder(@NonNull CidadeAdapter.ViewHolderCidade holder, int position) {


        if((dados!= null) && (dados.size()>0)) {

            Cidade c = dados.get(position);

            // int n = Integer.parseInt(e.getCnpj());
            holder.txtCidade.setText(c.getNome());
            holder.txtUf.setText(c.getUf());

        }
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }
    public void removeItem(int position) {
        dados.remove(position);
        notifyItemRemoved(position);
    }


    public ArrayList<Cidade> getData() {
        return (ArrayList<Cidade>) dados;
    }


    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }



    public class ViewHolderCidade extends RecyclerView.ViewHolder{


        public TextView txtCidade;
        public TextView txtUf;
        public String nomecidade;



        public ViewHolderCidade(View itemView, final Context context) {
            super(itemView);

            txtCidade = itemView.findViewById(R.id.txtEstabelecimento);
            txtUf = itemView.findViewById(R.id.txtData);



            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);


        }
    }



    }




