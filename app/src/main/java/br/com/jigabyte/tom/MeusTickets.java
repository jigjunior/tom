package br.com.jigabyte.tom;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
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
    private MeusTicketsHandlers handlers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_1_meus_tickets);

        listaDePassagens = new ArrayList<>();

        handlers = new MeusTicketsHandlers(this);
        bind.setHandlers(handlers);
        bind.setUsuario(MainActivity.usuario);



        // use a linear layout manager
        bind.recyclerViewListaDePassagens.setLayoutManager(new LinearLayoutManager(this));
        bind.recyclerViewListaDePassagens.setHasFixedSize(true);

        listaDePassagensAdapter = new PassagensAdapter(listaDePassagens);
        bind.recyclerViewListaDePassagens.setAdapter(listaDePassagensAdapter);

        buscaPassagens();
    }


    public void buscaPassagens(){
        String code = MainActivity.code;
        long id = (long) MainActivity.usuario.getId();
        u = null;

        // Consome API
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<Usuario> call = service.getBuscarUsuario(id, code);
        call.enqueue(new Callback<Usuario>() {

            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {

                if (response.isSuccessful()) {
                    u = response.body();

                    bind.setUsuario(u);

                    // atualiza recyclerView
                    listaDePassagensAdapter = new PassagensAdapter(u.getPassagens());
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


    public class MeusTicketsHandlers {
        Context context;

        public MeusTicketsHandlers(Context context) {
            this.context = context;
        }

        public void onFabClicked(View view) {
            Intent intent = new Intent(context, VooBuscar.class);
            startActivity(intent);
            finish();
        }
    }

}
