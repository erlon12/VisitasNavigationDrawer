package com.example.visitasdrawer.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.example.visitasdrawer.Activitys.AlterarCidade;
import com.example.visitasdrawer.Activitys.AlterarEstabelecimento;
import com.example.visitasdrawer.DAO.CidadeDAO;
import com.example.visitasdrawer.DAO.EstabelecimentoDAO;
import com.example.visitasdrawer.R;
import com.example.visitasdrawer.utils.Cidade;

import java.util.List;

import br.com.joinersa.oooalertdialog.Animation;
import br.com.joinersa.oooalertdialog.OnClickListener;
import br.com.joinersa.oooalertdialog.OoOAlertDialog;

public class CidadeAdapter extends RecyclerSwipeAdapter<CidadeAdapter.SimpleViewHolder> {

    private Context mContext;
    private List<Cidade> dados;

    public CidadeAdapter(Context context, List<Cidade> objects) {
        this.mContext = context;
        this.dados = objects;
    }


    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.linha_cidade, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SimpleViewHolder viewHolder, final int position) {
        final Cidade item = dados.get(position);

        viewHolder.uf.setText(item.getUf());
        viewHolder.cidade.setText(item.getNome());


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
                        .setMessage("UF: "+item.getUf()+'\n'+
                                "Cidade: "+item.getNome()
                                )
                        .setAnimation(Animation.POP)
                        .setPositiveButton("Fechar", null)
                        .build();
            }
        });


        viewHolder.btn_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String cidade,uf;
                int id;

                cidade= item.getNome();
                uf= item.getUf();
                id = item.getId();



                Intent intent = new Intent(mContext, AlterarCidade.class);

                intent.putExtra("cidade",cidade);
                intent.putExtra("uf",uf);
                intent.putExtra("id",id);
                mContext.startActivity(intent);
            }
        });

        viewHolder.btn_excluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new OoOAlertDialog.Builder((Activity) mContext)
                        .setTitle("Atenção!!")
                        .setMessage("Deseja excluir a Cidade:  "+item.getNome())

                        .setAnimation(Animation.POP)
                        .setNegativeButton("Não",null)
                        .setPositiveButton("Sim", new OnClickListener() {
                            @Override
                            public void onClick() {

                                CidadeDAO dao = new CidadeDAO(mContext);


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
        public TextView uf;
        public TextView cidade;



        public ImageButton btnLocation,btn_editar,btn_excluir;
        public SimpleViewHolder(View itemView) {
            super(itemView);

            swipeLayout = (SwipeLayout) itemView.findViewById(R.id.swipe);
            uf = (TextView) itemView.findViewById(R.id.uf);
            cidade = (TextView) itemView.findViewById(R.id.cidade);
            // Delete = (TextView) itemView.findViewById(R.id.Delete);
            btn_editar = (ImageButton) itemView.findViewById(R.id.img_editar);

            btn_excluir = (ImageButton) itemView.findViewById(R.id.img_delete);
            btnLocation = (ImageButton) itemView.findViewById(R.id.btnLocation);
        }
    }

}