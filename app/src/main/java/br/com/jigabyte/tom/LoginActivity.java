package br.com.jigabyte.tom;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import br.com.jigabyte.tom.databinding.Tela00LoginBinding;
import br.com.jigabyte.tom.model.Usuario;
import br.com.jigabyte.tom.model.response.UsuarioResponse;
import br.com.jigabyte.tom.rest.ApiClient;
import br.com.jigabyte.tom.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {


    private static String TAG;
    private Usuario usuario;
    private Tela00LoginBinding bindingLogin;
    private MyClickHandlers handlers;

    List<Usuario> listaDeUsuarios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        bindingLogin = DataBindingUtil.setContentView(this, R.layout.tela_00_login);


        handlers = new MyClickHandlers(this);
        bindingLogin.setHandlers(handlers);
        fechaViewsDoCadastro();


        // Consumindo API
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<UsuarioResponse> call = apiService.getBuscarAllUsuarios();
        call.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                //List<Movie> movies = response.body().getResults();
                listaDeUsuarios = response.body().getResults();
                String body = response.body().toString();
                Log.d(TAG, "Numero de usuarios recebidos: " + listaDeUsuarios.size());
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });







    }

    private void fechaViewsDoCadastro() {
        bindingLogin.txtLoginNome.setVisibility(View.GONE);
        bindingLogin.edtLoginNome.setVisibility(View.GONE);
        bindingLogin.txtLoginEmail.setVisibility(View.GONE);
        bindingLogin.edtLoginEmail.setVisibility(View.GONE);

        bindingLogin.btnLoginOK.setVisibility(View.VISIBLE);
        bindingLogin.txtCadastreOK.setVisibility(View.VISIBLE);
        bindingLogin.btnLoginCadastre.setText("Cadastre-se");
    }

    private void abreViewsDoCadastro() {
        bindingLogin.txtLoginNome.setVisibility(View.VISIBLE);
        bindingLogin.edtLoginNome.setVisibility(View.VISIBLE);
        bindingLogin.txtLoginEmail.setVisibility(View.VISIBLE);
        bindingLogin.edtLoginEmail.setVisibility(View.VISIBLE);

        bindingLogin.btnLoginOK.setVisibility(View.GONE);
        bindingLogin.txtCadastreOK.setVisibility(View.GONE);
        bindingLogin.btnLoginCadastre.setText("Cadastrar");
    }

    private void returnToken(){
        if(usuario.getToken() != null) {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("token", usuario.getToken());
            setResult(RESULT_OK, returnIntent);
        }
    }


    public class MyClickHandlers {

        Context context;

        public MyClickHandlers(Context context) {
            this.context = context;
        }

        public void onCadastreClicked(View view){

            if (bindingLogin.btnLoginCadastre.getText().toString().equalsIgnoreCase("Cadastre-se")) {
                //Toast.makeText(getApplicationContext(), "Cadastre-se clicked!", Toast.LENGTH_SHORT).show();
                abreViewsDoCadastro();


            } else {
                if (!validaCadastro()) {
                    // campos em branco
                    Toast.makeText(getApplicationContext(), "Informe todos os campos", Toast.LENGTH_SHORT).show();
                } else {
                    // Executa saveUsuario na API do Daves


                }


            }


        }

        public void onEntrarClicked(View view) {
            Toast.makeText(getApplicationContext(), "Entrar clicked!", Toast.LENGTH_SHORT).show();

            // Executa Login na API do Daves


            // Trata JSON e inicializa new Usuario().setters;


            // retorna token para MainActivity

        }


        private boolean validaCadastro() {
            if (bindingLogin.edtLoginEmail.getText().toString().trim().length() == 0) return false;
            if (bindingLogin.edtLoginNome.getText().toString().trim().length() == 0) return false;
            if (bindingLogin.edtLoginLogin.getText().toString().trim().length() == 0) return false;
            if (bindingLogin.edtLoginSenha.getText().toString().trim().length() == 0) return false;
            return true;
        }

    }


    /*

    // Usuario já está setado para Observers @Binding
    // Não necessitando forçar Observers estáticos
    public static class Usuario {
        public static ObservableField<String> login = new ObservableField<>();
        public static ObservableField<String> senha = new ObservableField<>();

        public ObservableField<String> getLogin() {
            return login;
        }

        public ObservableField<String> getSenha() {
            return senha;
        }
    }
    */


}
