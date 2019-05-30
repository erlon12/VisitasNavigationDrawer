package com.example.visitasdrawer.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.visitasdrawer.R;
import com.example.visitasdrawer.utils.Auto;
import com.example.visitasdrawer.utils.Cidade;

import java.util.List;

public class AutoAdapter  extends RecyclerView.Adapter<AutoAdapter.ViewHolderAuto>{

    private List<Auto> dados;
    private final Context context;
    private View.OnClickListener mOnItemClickListener;

    public AutoAdapter(List<Auto> dados, Context context){

        this.dados = dados;
        this.context =context;

    }

    @NonNull
    @Override
    public AutoAdapter.ViewHolderAuto onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.linha_auto, parent, false);


        ViewHolderAuto holderVisitas = new ViewHolderAuto(view,context);

        return holderVisitas;
    }

    @Override
    public void onBindViewHolder(@NonNull AutoAdapter.ViewHolderAuto holder, int position) {


        if((dados!= null) && (dados.size()>0)) {

            Auto a = dados.get(position);

            // int n = Integer.parseInt(e.getCnpj());
           holder.txtTipo_auto.setText(a.getTipoAuto());
           holder.edt_obs.getEditText().setText(a.getObs());
           holder.txtEstabelecimento.setText(a.getEstabelecimento());
           holder.txtData.setText(a.getData());

        }
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }



    public class ViewHolderAuto extends RecyclerView.ViewHolder{


        public TextView txtEstabelecimento;
        public TextView txtData;
        public TextView txtObs;
        public TextView txtTipo_auto;
        TextInputLayout edt_obs;




        public ViewHolderAuto(View itemView, final Context context) {
            super(itemView);

            txtEstabelecimento = itemView.findViewById(R.id.txtEstabelecimento);
            txtData = itemView.findViewById(R.id.txtData);
            edt_obs = itemView.findViewById(R.id.edt_obss);
            txtTipo_auto = itemView.findViewById(R.id.txt_tipoAuto);

            edt_obs.getEditText().setEnabled(false);



            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);


        }
    }
}
