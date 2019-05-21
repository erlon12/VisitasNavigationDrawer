package com.example.visitasdrawer.Adapter;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.ActionBarContainer;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;


import com.example.visitasdrawer.Activitys.CadastroCidade;
import com.example.visitasdrawer.DAO.EstabelecimentoDAO;
import com.example.visitasdrawer.R;
import com.example.visitasdrawer.utils.Estabelecimento;

import java.util.List;


public class EstabelecimentoAdapter extends RecyclerView.Adapter<EstabelecimentoAdapter.ViewHolderEstabelecimento> {



    private List<Estabelecimento> dados;
    private final Context context;
    int po;


    public EstabelecimentoAdapter(List<Estabelecimento> dados,Context context){

        this.dados=dados;
        this.context = context;


    }

    @NonNull
    @Override
    public ViewHolderEstabelecimento onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.linha_estabelecimento, parent, false);


        ViewHolderEstabelecimento holderVisitas = new ViewHolderEstabelecimento(view,context);

        return holderVisitas;
    }

    @Override
    public void onBindViewHolder(@NonNull EstabelecimentoAdapter.ViewHolderEstabelecimento holder, int position) {


        if((dados!= null) && (dados.size()>0)) {

            Estabelecimento e = dados.get(position);

          // int n = Integer.parseInt(e.getCnpj());

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

        public ViewHolderEstabelecimento(final View itemView, final Context context) {
            super(itemView);


            txtCnpj = itemView.findViewById(R.id.txt_cnpj);
            txtEstabelecimento = itemView.findViewById(R.id.txt_razao);


            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    PopupMenu popup = new PopupMenu(context,view);
                    MenuInflater inflater = popup.getMenuInflater();

                    final Estabelecimento e = dados.get(getLayoutPosition());
                    Toast.makeText(context,"ID: "+e.getId(),Toast.LENGTH_SHORT).show();
                    Log.e("EstabeAdapter", "Nome: " + e.getRazao());

                    AlertDialog alert;
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);

                    builder.setTitle("Excluir Estabelecimento!");

                    builder.setMessage("Excluir Estabelecimento: "+e.getRazao());

                    final int id = e.getId();

                    builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            EstabelecimentoDAO dao = new EstabelecimentoDAO(context);
                            dao.delete(id);

                           // EstabelecimentoAdapter.this.notifyItemRemoved(po);


                        }
                    });

                    builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    alert = builder.create();

                    alert.show();

                    return true;
                }
            });





        }
    }

    private void buildAlertDialog(){

        AlertDialog alert;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle("Exemplo Titulo!");

        builder.setMessage("Mensagem do Alert Dialog");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "Botão Ok", Toast.LENGTH_LONG).show();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "Botão Cancel", Toast.LENGTH_LONG).show();
            }
        });

        alert = builder.create();

        alert.show();
    }
}
