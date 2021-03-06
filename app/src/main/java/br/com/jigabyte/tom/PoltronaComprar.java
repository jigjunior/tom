package br.com.jigabyte.tom;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.jigabyte.tom.databinding.Activity3PoltronaComprarBinding;
import br.com.jigabyte.tom.model.Poltrona;
import br.com.jigabyte.tom.model.PoltronaResponse;
import br.com.jigabyte.tom.model.Voo;
import br.com.jigabyte.tom.rest.ApiClient;
import br.com.jigabyte.tom.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PoltronaComprar extends AppCompatActivity {

    String code = MainActivity.code;
    String assento;

    public static final String TAG = "PoltronaComprar";
    Activity3PoltronaComprarBinding bind;
    Voo voo;
    List<PoltronaResponse> listaDePoltronas;
    ArrayList<PoltronaResponse> poltronasDisponiveis;
    MeusClickHandlers handlers;
    Poltrona p;
    ArrayList<String> assentosDisponiveis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3_poltrona_comprar);
        poltronasDisponiveis = new ArrayList<>();
        assentosDisponiveis = new ArrayList<>();
        p = new Poltrona();
        String s = "";
        assentosDisponiveis.add(s);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_3_poltrona_comprar);


        voo = (Voo) getIntent().getSerializableExtra("voo");
        bind.setVoo(voo);

        handlers = new MeusClickHandlers(this);
        bind.setHandlers(handlers);

        buscaPoltronasDisponiveis();

//        bind.spinnerPoltronaDisponivel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                assento = (String) parent.getItemAtPosition(position);
//                // Notify the selected item text
//                Toast.makeText(getApplicationContext(), "Selected : " + assento, Toast.LENGTH_SHORT).show();
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
        ArrayAdapter<String> adapterSpinnerPoltrona = new ArrayAdapter<String>(
                getApplicationContext(),
                R.layout.spinner,
                assentosDisponiveis);
        bind.spinnerPoltronaDisponivel.setAdapter(adapterSpinnerPoltrona);
/*
        spinner_clientes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cliente = new Cliente();
                cliente.setNome(spinner_clientes.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        String[] clientes = {"", "Fulano", "Beltrano", "Ciclano"};
        String[] pagamento = {"À vista", "15-30 dias", "30-60-90 dias"};
        String[] tipo = {"Venda", "Orçamento", "Devolução"};

        ArrayAdapter<String> adapterClientes = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner, clientes);
        ArrayAdapter<String> adapterPagamento = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner, pagamento);
        ArrayAdapter<String> adapterTipo = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner, tipo);

        spinner_clientes.setAdapter(adapterClientes);
        spinner_forma_pagamento.setAdapter(adapterPagamento);
        spinner_tipo_pagamento.setAdapter(adapterTipo);
        */


    }

    public void buscaPoltronasDisponiveis() {

        poltronasDisponiveis = new ArrayList<>();

        /***************************** GET POLTRONAS ****************************/


            final long id = (long) voo.getId();
            String code = MainActivity.code;

            // Consome API
            ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
            Call<List<PoltronaResponse>> call = service.getPoltronas(id, code);
            call.enqueue(new Callback<List<PoltronaResponse>>() {

                @Override
                public void onResponse(Call<List<PoltronaResponse>> call, Response<List<PoltronaResponse>> response) {

                    if (response.isSuccessful()) {
                        listaDePoltronas = response.body();

                        for (PoltronaResponse poltrona : listaDePoltronas) {
                            if (!poltrona.getOcupado())
                                poltronasDisponiveis.add(poltrona);
                                assentosDisponiveis.add(poltrona.toString());
                        }
                        if (poltronasDisponiveis.size() == 0){
                            Toast.makeText(getApplicationContext(), "Nenhuma Poltrona Disponível",Toast.LENGTH_LONG).show();
                            bind.botaotxtPassagemCancelar.setVisibility(View.GONE);
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Erro ao tentar carregar poltronas.", Toast.LENGTH_LONG).show();
                        Log.d(TAG, "Erro: " + response.errorBody().toString());
                    }
                }

                @Override
                public void onFailure(Call<List<PoltronaResponse>> call, Throwable t) {
                    // Log error here since request failed
                    Toast.makeText(getApplicationContext(), "Erro ao tentar acessar o serviço GET POLTRONAS.", Toast.LENGTH_LONG).show();
                    Log.e(TAG, "Erro: " + t.toString());
                }
            });



    }

    public void comprar(long id_poltrona, long id_voo, String Code){

        Log.d("Poltronas Post", "voo:" + id_voo + " poltrona:" + assento);
        // Consome API
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<Poltrona> call = service.postSavePoltrona(id_voo, id_poltrona, code);
        call.enqueue(new Callback<Poltrona>() {

            @Override
            public void onResponse(Call<Poltrona> call, Response<Poltrona> response) {

                if (response.isSuccessful()) {
                    p = response.body();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Erro ao tentar comprar poltronas.", Toast.LENGTH_LONG).show();
                    Log.d(TAG, "Erro: " + response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<Poltrona> call, Throwable t) {
                // Log error here since request failed
                Toast.makeText(getApplicationContext(), "Erro ao tentar gravar POST POLTRONAS.", Toast.LENGTH_LONG).show();
                Log.e(TAG, "Erro: " + t.toString());
            }
        });
    }

    public class MeusClickHandlers {
        Context context;

        public MeusClickHandlers(Context context) {
            this.context = context;
        }

        public void comprarPassagem(View view) {
            assento = (String) bind.spinnerPoltronaDisponivel.getSelectedItem();
            if (assento.length() < 1)
                Toast.makeText(getApplicationContext(), "Selecione uma poltrona", Toast.LENGTH_SHORT).show();
            else {
                long id_poltrona = Long.parseLong(assento);
                comprar(id_poltrona, voo.getId(), code);
            }
        }

    }

}
