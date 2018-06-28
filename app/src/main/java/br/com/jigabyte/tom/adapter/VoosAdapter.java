package br.com.jigabyte.tom.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.jigabyte.tom.MainActivity;
import br.com.jigabyte.tom.R;
import br.com.jigabyte.tom.databinding.Tela20ViewListLayoutVooBinding;
import br.com.jigabyte.tom.model.Poltrona;
import br.com.jigabyte.tom.model.Voo;
import br.com.jigabyte.tom.rest.ApiClient;
import br.com.jigabyte.tom.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VoosAdapter extends RecyclerView.Adapter<VoosAdapter.MyViewHolder> {

    private List<Voo> vooList;

    Context context;
    private Tela20ViewListLayoutVooBinding binding;
    private List<Poltrona> listaDePoltronas;
    private ArrayList<Poltrona> listaDePoltronasDisponiveis;
    private ArrayAdapter poltronasDisponiveisAdapter;

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
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Voo voo = vooList.get(position);
        holder.bind(voo, context);
    }

    @Override
    public int getItemCount() {
        return vooList.size();
    }

    private void toogleProgressToSpinner() {
        binding.spinnerPoltrona.setVisibility(View.VISIBLE);
        binding.progressBarPoltronas.setVisibility(View.GONE);
    }

    private void preencheSpinnerPoltronas(final Voo voo, final Context context) {

        long id = (long) voo.getId();
        String code = MainActivity.code;

        listaDePoltronasDisponiveis = new ArrayList<>();

        // Consome API
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Poltrona>> call = service.getPoltronas(id, code);
        call.enqueue(new Callback<List<Poltrona>>() {

            @Override
            public void onResponse(Call<List<Poltrona>> call, Response<List<Poltrona>> response) {

                if (response.isSuccessful()) {

                    listaDePoltronas = response.body();
                    listaDePoltronasDisponiveis = new ArrayList<>();

                    for (Poltrona poltrona : listaDePoltronas) {
                        // checa se esta disponivel
                        if (!poltrona.getOcupado())
                            listaDePoltronasDisponiveis.add(poltrona);
                    }


                    poltronasDisponiveisAdapter = new ArrayAdapter<>(context, R.layout.support_simple_spinner_dropdown_item, listaDePoltronasDisponiveis);
                    binding.spinnerPoltrona.setAdapter(poltronasDisponiveisAdapter);

                    toogleProgressToSpinner();


                } else {
                    Toast.makeText(context, "Erro ao tentar carregar lista.", Toast.LENGTH_LONG).show();
//                    Log.d(TAG, "Erro: " + response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<List<Poltrona>> call, Throwable t) {
                // Log error here since request failed
                Toast.makeText(context, "Erro ao tentar acessar o serviço.", Toast.LENGTH_LONG).show();
//                Log.e(TAG, "Erro: " + t.toString());
            }
        });
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private Tela20ViewListLayoutVooBinding mBinding;

        public MyViewHolder(Tela20ViewListLayoutVooBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(@NonNull Voo voo, Context context) {
            mBinding.setVoo(voo);
            preencheSpinnerPoltronas(voo, context);
            mBinding.executePendingBindings();
        }
    }

}