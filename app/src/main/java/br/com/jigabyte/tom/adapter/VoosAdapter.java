package br.com.jigabyte.tom.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.jigabyte.tom.PoltronaComprar;
import br.com.jigabyte.tom.R;
import br.com.jigabyte.tom.databinding.Tela20ViewListLayoutVooBinding;
import br.com.jigabyte.tom.model.Poltrona;
import br.com.jigabyte.tom.model.Voo;

public class VoosAdapter extends RecyclerView.Adapter<VoosAdapter.MyViewHolder> {

    private List<Voo> vooList;

    Context context;
    private Tela20ViewListLayoutVooBinding binding;
    private List<Poltrona> listaDePoltronas;

    public VoosAdapter(@NonNull List<Voo> listaDeVoos) {
        this.vooList = listaDeVoos;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        binding = DataBindingUtil.inflate(inflater, R.layout.tela_20_view_list_layout_voo, parent, false);
        MyViewHolder voosViewHolder = new MyViewHolder(binding);
        return (voosViewHolder);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Voo voo = vooList.get(position);
        holder.bind(voo);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PoltronaComprar.class);
                intent.putExtra("voo", voo);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return vooList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private Tela20ViewListLayoutVooBinding mBinding;

        public MyViewHolder(Tela20ViewListLayoutVooBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(@NonNull Voo voo) {
            mBinding.setVoo(voo);
            mBinding.executePendingBindings();
        }
    }

}