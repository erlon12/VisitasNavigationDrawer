package com.example.visitasdrawer.Adapter;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.example.visitasdrawer.Activitys.AlterarEstabelecimento;
import com.example.visitasdrawer.DAO.EstabelecimentoDAO;
import com.example.visitasdrawer.R;
import com.example.visitasdrawer.utils.Estabelecimento;

import java.util.List;

import br.com.joinersa.oooalertdialog.Animation;
import br.com.joinersa.oooalertdialog.OnClickListener;
import br.com.joinersa.oooalertdialog.OoOAlertDialog;

public class EstabelecimentoAdapter extends RecyclerSwipeAdapter<EstabelecimentoAdapter.SimpleViewHolder> {

    private Context mContext;
    private List<Estabelecimento> dados;

    public EstabelecimentoAdapter(Context context, List<Estabelecimento> objects) {
        this.mContext = context;
        this.dados = objects;
    }


    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.linha_estabelecimento, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SimpleViewHolder viewHolder, final int position) {
        final Estabelecimento item = dados.get(position);

        viewHolder.cnpj.setText(item.getCnpj());
        viewHolder.estabelecimento.setText(item.getRazao());


        viewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);

        //dari kiri
        viewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Left, viewHolder.swipeLayout.findViewById(R.id.bottom_wrapper1));

        //dari kanan
        viewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, viewHolder.swipeLayout.findViewById(R.id.bottom_wraper));



        viewHolder.swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener() {
            @Override
            public void onStartOpen(SwipeLayout layout) {

            }

            @Override
            public void onOpen(SwipeLayout layout) {

            }

            @Override
            public void onStartClose(SwipeLayout layout) {

            }

            @Override
            public void onClose(SwipeLayout layout) {

            }

            @Override
            public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {

            }

            @Override
            public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {

            }
        });

        viewHolder.swipeLayout.getSurfaceView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        viewHolder.btnLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                new OoOAlertDialog.Builder((Activity) mContext)


                        .setTitle("Detalhes")
                        .setMessage("Cnpj: "+item.getCnpj()+'\n'+
                                "Razão: "+item.getRazao()+'\n'+
                                "Cidade: "+item.getCidade()+'\n'+
                                "Numero: "+item.getNumero()+'\n'+
                                "Cep: "+item.getCep())
                        .setAnimation(Animation.POP)
                        .setPositiveButton("Fechar", null)
                        .build();



            }
        });


        viewHolder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String cidade,ccnpj,razao,cep;
                int id,numero;

                cidade= item.getCidade();
                ccnpj= item.getCnpj();
                razao=item.getRazao();
                numero = item.getNumero();
                id =item.getId();
                cep = item.getCep();

                String nstring = String.valueOf(numero);
                Intent intent = new Intent(mContext, AlterarEstabelecimento.class);

                intent.putExtra("cidade",cidade);
                intent.putExtra("ccnpj",ccnpj);
                intent.putExtra("razao",razao);
                intent.putExtra("numero",nstring);
                intent.putExtra("id",id);
                intent.putExtra("cep",cep);



                mContext.startActivity(intent);
            }
        });

        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new OoOAlertDialog.Builder((Activity) mContext)


                        .setTitle("Atenção!!")
                        .setMessage("Deseja excluir o Estabelecimento:  "+item.getRazao())

                        .setAnimation(Animation.POP)
                        .setNegativeButton("Não",null)
                        .setPositiveButton("Sim", new OnClickListener() {
                            @Override
                            public void onClick() {

                                EstabelecimentoDAO dao = new EstabelecimentoDAO(mContext);


                                dao.delete(item.getId());
                                dados.remove(position);


                                notifyItemRemoved(position);
                                notifyItemRangeChanged(position, dados.size());
                                mItemManger.closeAllItems();
                            }
                        })
                        .build();
            }
        });

        mItemManger.bindView(viewHolder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder{
        public SwipeLayout swipeLayout;
        public TextView cnpj;
        public TextView estabelecimento;
        public TextView Delete;
        public TextView Edit;
        public TextView Share;
        public ImageButton btnEdit ;
        public ImageButton btnDelete ;
        public ImageButton btnLocation ;
        public SimpleViewHolder(View itemView) {
            super(itemView);

            swipeLayout = (SwipeLayout) itemView.findViewById(R.id.swipe);
            cnpj = (TextView) itemView.findViewById(R.id.cnpj);
            estabelecimento = (TextView) itemView.findViewById(R.id.estabelecimento);
            btnDelete = (ImageButton) itemView.findViewById(R.id.Delete);
            btnEdit = (ImageButton) itemView.findViewById(R.id.Edit);

            btnLocation = (ImageButton) itemView.findViewById(R.id.btnLocation);
        }
    }

}
