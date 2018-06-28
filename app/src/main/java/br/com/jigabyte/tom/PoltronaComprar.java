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

import br.com.jigabyte.tom.model.Poltrona;
import br.com.jigabyte.tom.model.Voo;
import br.com.jigabyte.tom.rest.ApiClient;
import br.com.jigabyte.tom.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static br.com.jigabyte.tom.MainActivity.code;

public class PoltronaComprar extends AppCompatActivity {

    public static final String TAG = "";
    Activity3PoltronaComprarBinding bind;
    Voo voo;
    ArrayList<Poltrona> poltronasDisponiveis;
    MeusClickHandlers handlers;
    Poltrona p;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        poltronasDisponiveis = new ArrayList<>();
        p = new Poltrona();
        bind = DataBindingUtil.setContentView(this, R.layout.activity_3_poltrona_comprar);

        handlers = new MeusClickHandlers(this);
        bind.setHandlers(handlers);



        ArrayAdapter<Poltrona> adapterSpinnerPoltrona = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,
                poltronasDisponiveis);
        bind.spinnerPoltrona.setAdapter(adapterSpinnerPoltrona);

    }


    public void buscaPoltronasDisponiveis() {

        poltronasDisponiveis = new ArrayList<>();

        /***************************** GET POLTRONAS ****************************/


            long id = voo.getId();
            String code = MainActivity.code;

            // Consome API
            ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
            Call<List<Poltrona>> call = service.getPoltronas(id, code);
            call.enqueue(new Callback<List<Poltrona>>() {

                @Override
                public void onResponse(Call<List<Poltrona>> call, Response<List<Poltrona>> response) {

                    if (response.isSuccessful()) {
                        voo.setPoltronas(response.body());
                        Log.d(TAG, "Voo ID=" + voo.getId() + "Response OK= " + response.errorBody().toString());

                        for (Poltrona poltrona : voo.getPoltronas()) {
                            if (!poltrona.getOcupado())
                                poltronasDisponiveis.add(poltrona);
                        }
                        voo.setPoltronas(poltronasDisponiveis);

                    } else {
                        Toast.makeText(getApplicationContext(), "Erro ao tentar carregar poltronas.", Toast.LENGTH_LONG).show();
                        Log.d(TAG, "Erro: " + response.errorBody().toString());
                    }
                }

                @Override
                public void onFailure(Call<List<Poltrona>> call, Throwable t) {
                    // Log error here since request failed
                    Toast.makeText(getApplicationContext(), "Erro ao tentar acessar o serviço GET POLTRONAS.", Toast.LENGTH_LONG).show();
                    Log.e(TAG, "Erro: " + t.toString());
                }
            });



    }


    public class MeusClickHandlers {
        Context context;

        public MeusClickHandlers(Context context) {
            this.context = context;
        }

        public void comprarClicker(View view) {
            comprar(poltronasDisponiveis.get(bind.spinnerPoltrona.getSelectedItemPosition()).getId(), voo.getId(), code);
        }




    }


    public void comprar(long id_poltrona, long id_voo, String Code){

        // Consome API
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<Poltrona> call = service.postSavePoltrona(id_voo, id_poltrona, code);
        call.enqueue(new Callback<Poltrona>() {

            @Override
            public void onResponse(Call<Poltrona> call, Response<Poltrona> response) {

                if (response.isSuccessful()) {
                    p = response.body();
                    Log.d(TAG, "Voo ID=" + voo.getId() + "Response OK= " + response.errorBody().toString());

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

}
