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
import com.example.visitasdrawer.Activitys.AlterarAuto;
import com.example.visitasdrawer.Activitys.AlterarEstabelecimento;
import com.example.visitasdrawer.DAO.AutoDAO;
import com.example.visitasdrawer.DAO.EstabelecimentoDAO;
import com.example.visitasdrawer.R;
import com.example.visitasdrawer.utils.Auto;

import java.util.List;

import br.com.joinersa.oooalertdialog.Animation;
import br.com.joinersa.oooalertdialog.OnClickListener;
import br.com.joinersa.oooalertdialog.OoOAlertDialog;

public class AutoAdapter extends RecyclerSwipeAdapter<AutoAdapter.SimpleViewHolder> {

    private Context mContext;
    private List<Auto> dados;

    public AutoAdapter(Context context, List<Auto> objects) {
        this.mContext = context;
        this.dados = objects;
    }


    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.linha_auto, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SimpleViewHolder viewHolder, final int position) {
        final Auto item = dados.get(position);

        viewHolder.cnpj.setText(item.getCnpj());
        viewHolder.data.setText(item.getData());
        viewHolder.irregularidade.setText(item.getTipoAuto());


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
                                "Razão: "+item.getEstabelecimento()+'\n'+
                                "Data: "+item.getData()+'\n'+
                                "Irregularidade: "+item.getTipoAuto()+'\n'+
                                "Artigo: "+item.getArtigo()+'\n'+
                                "Recusou Receber: "+item.getRecura_receber()+'\n'+
                                "Observação: "+item.getObs())

                        .setAnimation(Animation.POP)
                        .setPositiveButton("Fechar", null)
                        .build();
            }
        });


        viewHolder.Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String obs,ccnpj,razao,data,irregularidade,recusou_receber,artigo,equipe;
                int id;

                obs= item.getObs();
                ccnpj= item.getCnpj();
                razao=item.getEstabelecimento();
                artigo = item.getArtigo();
                data = item.getData();
                recusou_receber = item.getRecura_receber();
                irregularidade = item.getTipoAuto();
                equipe =item.getEquipe();
                id =item.getId();


                Intent intent = new Intent(mContext, AlterarAuto.class);

                intent.putExtra("obs",obs);
                intent.putExtra("cnpj",ccnpj);
                intent.putExtra("razao",razao);
                intent.putExtra("artigo",artigo);
                intent.putExtra("id",id);
                intent.putExtra("data",data);
                intent.putExtra("receber",recusou_receber);
                intent.putExtra("irregularidade",irregularidade);

                intent.putExtra("equipe",equipe);

                mContext.startActivity(intent);
            }
        });

        viewHolder.btn_deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new OoOAlertDialog.Builder((Activity) mContext)


                        .setTitle("Atenção!!")
                        .setMessage("Deseja excluir Auto do Estabelecimento:  \n"+item.getEstabelecimento())

                        .setAnimation(Animation.POP)
                        .setNegativeButton("Não",null)
                        .setPositiveButton("Sim", new OnClickListener() {
                            @Override
                            public void onClick() {

                                AutoDAO dao = new AutoDAO(mContext);


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
        public TextView data;
        public TextView irregularidade;
        public ImageButton Edit;
        public TextView Share;
        public ImageButton btnLocation,btn_deletar;
        public SimpleViewHolder(View itemView) {
            super(itemView);

            swipeLayout = (SwipeLayout) itemView.findViewById(R.id.swipe);
            cnpj = (TextView) itemView.findViewById(R.id.cnpj);
            data = (TextView) itemView.findViewById(R.id.data);
            irregularidade = (TextView) itemView.findViewById(R.id.irregularidade);
            Edit = (ImageButton) itemView.findViewById(R.id.Edit);

            btn_deletar=(ImageButton)itemView.findViewById(R.id.delete) ;
            btnLocation = (ImageButton) itemView.findViewById(R.id.btnLocation);
        }
    }

}
