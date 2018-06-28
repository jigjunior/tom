package br.com.jigabyte.tom;

import android.app.DatePickerDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.jigabyte.tom.adapter.VoosAdapter;
import br.com.jigabyte.tom.databinding.Activity2VooBuscarBinding;
import br.com.jigabyte.tom.model.Aeroporto;
import br.com.jigabyte.tom.model.Poltrona;
import br.com.jigabyte.tom.model.Voo;
import br.com.jigabyte.tom.rest.ApiClient;
import br.com.jigabyte.tom.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VooBuscar extends AppCompatActivity {

    private static final String TAG = "";
    Activity2VooBuscarBinding binding;
    MeusClickHandlers handlers;
    List<Voo> listaDeVoos;
    ArrayList<Aeroporto> aeroportos_origem;
    ArrayList<Aeroporto> aeroportos_destino;
    ArrayList<Poltrona> poltronasDisponiveis;
    VoosAdapter voosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_2_voo_buscar);

        Aeroporto x = new Aeroporto(0, "Selecione", "");
        aeroportos_origem = new ArrayList<>();
        aeroportos_origem.add(x);
        aeroportos_destino = new ArrayList<>();
        aeroportos_destino.add(x);

        buscaVoos();

        handlers = new MeusClickHandlers(this);
        binding.setHandlers(handlers);

        // use a linear layout manager
        binding.recyclerViewVoos.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewVoos.setHasFixedSize(true);
    }

    private void pesquisarVoos() {

        // TODO - implementar os filtros que atendam data e aeroportos
        List<Voo> listaFiltrada = listaFiltrada();

        fecharFiltros();
        finalizarProgress();
        voosAdapter = new VoosAdapter(listaFiltrada);
        binding.recyclerViewVoos.setAdapter(voosAdapter);
    }

    private List<Voo> listaFiltrada() {
        List<Voo> lista = listaDeVoos;


        // TODO - implementar os filtros que atendam data e aeroportos
        /*
        Calendata data_1 = new Calendata(binding.dataInicial.getText().toString());
        Calendata data_2 = new Calendata(binding.dataFinal.getText().toString());

        Set<Aeroporto> hsOrigem = new HashSet<>();
        Set<Aeroporto> hsDestino = new HashSet<>();
        for (Voo voo : listaDeVoos) {
            hsOrigem.add(voo.getOrigem());
            hsDestino.add(voo.getDestino());
        }
        aeroportos_origem.addAll(hsOrigem);
        aeroportos_destino.addAll(hsDestino);

        */

        return lista;
    }

    private void buscaVoos() {

        // Consome API
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Voo>> call = service.getAllVoos(MainActivity.code);
        call.enqueue(new Callback<List<Voo>>() {

            @Override
            public void onResponse(Call<List<Voo>> call, Response<List<Voo>> response) {

                if (response.isSuccessful()) {
                    listaDeVoos = response.body();
                    abrirFiltros();

                } else {
                    Toast.makeText(getApplicationContext(), "Erro ao tentar carregar lista.", Toast.LENGTH_LONG).show();
                    Log.d(TAG, "Erro: " + response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<List<Voo>> call, Throwable t) {
                // Log error here since request failed
                Toast.makeText(getApplicationContext(), "Erro ao tentar acessar o serviço.", Toast.LENGTH_LONG).show();
                Log.e(TAG, "Erro: " + t.toString());
            }
        });
    }

    public void abrirFiltros() {
        binding.txtData1.setVisibility(View.VISIBLE);
        binding.dataInicial.setVisibility(View.VISIBLE);
        binding.txtData2.setVisibility(View.VISIBLE);
        binding.dataFinal.setVisibility(View.VISIBLE);
        binding.txtOrigem.setVisibility(View.VISIBLE);
        binding.txtDestino.setVisibility(View.VISIBLE);
        binding.btnPesquisar.setVisibility(View.VISIBLE);
        binding.textView.setVisibility(View.GONE);
        binding.txtCarregando.setVisibility(View.GONE);
        binding.btnFiltros.setVisibility(View.GONE);

        // não add duplicatas
        Set<Aeroporto> hsOrigem = new HashSet<>();
        Set<Aeroporto> hsDestino = new HashSet<>();
        for (Voo voo : listaDeVoos) {
            hsOrigem.add(voo.getOrigem());
            hsDestino.add(voo.getDestino());
        }
        aeroportos_origem.addAll(hsOrigem);
        aeroportos_destino.addAll(hsDestino);

        //preenche spinners
        ArrayAdapter<Aeroporto> adapterOrigem =
                new ArrayAdapter<>(
                        getApplicationContext(),
                        android.R.layout.simple_spinner_dropdown_item,
                        aeroportos_origem
                );
        ArrayAdapter<Aeroporto> adapterDestino =
                new ArrayAdapter<>(
                        getApplicationContext(),
                        android.R.layout.simple_spinner_dropdown_item,
                        aeroportos_destino
                );
        binding.origem.setAdapter(adapterOrigem);
        binding.destino.setAdapter(adapterDestino);
        binding.origem.setVisibility(View.VISIBLE);
        binding.destino.setVisibility(View.VISIBLE);
        binding.progressBarVoos.setVisibility(View.GONE);
    }

    public void fecharFiltros() {
        binding.txtData1.setVisibility(View.GONE);
        binding.dataInicial.setVisibility(View.GONE);
        binding.txtData2.setVisibility(View.GONE);
        binding.dataFinal.setVisibility(View.GONE);
        binding.txtOrigem.setVisibility(View.GONE);
        binding.origem.setVisibility(View.GONE);
        binding.txtDestino.setVisibility(View.GONE);
        binding.destino.setVisibility(View.GONE);
        binding.btnPesquisar.setVisibility(View.GONE);
        binding.textView.setVisibility(View.VISIBLE);
        binding.progressBarVoos.setVisibility(View.VISIBLE);
        binding.txtCarregando.setVisibility(View.VISIBLE);
        binding.btnFiltros.setVisibility(View.VISIBLE);
    }

    public void finalizarProgress() {
        binding.progressBarVoos.setVisibility(View.GONE);
        binding.txtCarregando.setVisibility(View.GONE);
        binding.recyclerViewVoos.setVisibility(View.VISIBLE);
    }

    public class MeusClickHandlers {
        // Get Current Date
        final LocalDateTime c = new LocalDateTime();
        Context context;
        int mYear = c.getYear();
        int mMonth = c.getMonthOfYear();
        int mDay = c.getDayOfMonth();

        public MeusClickHandlers(Context context) {
            this.context = context;
        }

        public void btnFiltrosClicked(View view) {
            abrirFiltros();
        }

        public void buscarClicked(View view) {
            pesquisarVoos();
        }

        public void pickDataInicial(View view) {
            DatePickerDialog datePickerDialog =
                    new DatePickerDialog(this.context, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            binding.dataInicial.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }

        public void pickDataFinal(View view) {
            DatePickerDialog datePickerDialog
                    = new DatePickerDialog(this.context, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    binding.dataFinal.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                }
            }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }


    }


}
