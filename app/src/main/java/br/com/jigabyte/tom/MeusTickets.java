package br.com.jigabyte.tom;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.jigabyte.tom.adapter.PassagensAdapter;
import br.com.jigabyte.tom.databinding.Activity1MeusTicketsBinding;
import br.com.jigabyte.tom.model.Passagem;
import br.com.jigabyte.tom.model.Usuario;
import br.com.jigabyte.tom.rest.ApiClient;
import br.com.jigabyte.tom.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MeusTickets extends AppCompatActivity {


    private Activity1MeusTicketsBinding bind;
    private PassagensAdapter listaDePassagensAdapter;
    private ArrayList<Passagem> listaDePassagens;
    public static String TAG = "";
    private Usuario u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_1_meus_tickets);

        listaDePassagens = new ArrayList<>();

        buscaPassagens();


    }


    public void buscaPassagens(){
        String code = MainActivity.code;
        long id = (long) MainActivity.usuario.getId();
        u = null;

        /*Create handle for the RetrofitInstance interface*/
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<Usuario> call = service.getBuscarUsuario(id, code);
        call.enqueue(new Callback<Usuario>() {

            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {

                if (response.isSuccessful()) {
                    u = response.body();
                    //Toast.makeText(getApplicationContext(), "Login efetuado com sucesso", Toast.LENGTH_SHORT).show();
                    PassagensAdapter listaDePassagensAdapter = new PassagensAdapter(u.getPassagens());
                    bind.recyclerViewListaDePassagens.setAdapter(listaDePassagensAdapter);
                    bind.progressListaPassagens.setVisibility(View.GONE);
                    bind.txtCarregandoListaPassagens.setVisibility(View.GONE);
                } else {
                    Toast.makeText(getApplicationContext(), "Erro ao tentar carregar lista. Desligando", Toast.LENGTH_LONG).show();
                    Log.d(TAG, "Erro: " + response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                bind.progressListaPassagens.setVisibility(View.GONE);
                bind.txtCarregandoListaPassagens.setVisibility(View.GONE);
                // Log error here since request failed
                Toast.makeText(getApplicationContext(), "Erro ao tentar carregar lista. Desligando", Toast.LENGTH_LONG).show();
                Log.e(TAG, "Erro: " + t.toString());
                finish();
            }
        });

    }


}
