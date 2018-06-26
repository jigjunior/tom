package br.com.jigabyte.tom.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.jigabyte.tom.R;
import br.com.jigabyte.tom.databinding.Tela10ViewListLayoutPassagensBinding;
import br.com.jigabyte.tom.model.Passagem;

public class PassagensAdapter extends RecyclerView.Adapter<PassagensAdapter.MyViewHolder> {

    private List<Passagem> passagensList;

    public PassagensAdapter(@NonNull List<Passagem> listaDePassagens) {
        this.passagensList = listaDePassagens;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        Tela10ViewListLayoutPassagensBinding binding = DataBindingUtil.inflate(inflater, R.layout.tela_10_view_list_layout_passagens, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Passagem passagem = passagensList.get(position);
        holder.bind(passagem);
    }

    @Override
    public int getItemCount() {
        return passagensList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private Tela10ViewListLayoutPassagensBinding mBinding;
        public MyViewHolder(Tela10ViewListLayoutPassagensBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(@NonNull Passagem passagem){
            mBinding.setPassagem(passagem);
            mBinding.executePendingBindings();
        }
    }

}
