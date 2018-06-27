package br.com.jigabyte.tom.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import br.com.jigabyte.tom.R;
import br.com.jigabyte.tom.model.Voo;

public class VoosAdapter extends RecyclerView.Adapter<VoosAdapter.MyViewHolder> {

    private List<Voo> vooList;

    public VoosAdapter(@NonNull List<Voo> listaDeVoos) {
        this.vooList = listaDeVoos;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        Tela20ViewListLayoutVooBinding binding = DataBindingUtil.inflate(inflater, R.layout.tela_20_view_list_layout_voo, parent, false);
        return new VoosAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Voo voo = vooList.get(position);
        holder.bind(voo);
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