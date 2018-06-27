package br.com.jigabyte.tom;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import br.com.jigabyte.tom.databinding.Tela00LoginBinding;
import br.com.jigabyte.tom.model.Usuario;
import br.com.jigabyte.tom.model.UsuarioCadastro;
import br.com.jigabyte.tom.model.UsuarioLogin;
import br.com.jigabyte.tom.rest.ApiClient;
import br.com.jigabyte.tom.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "";
    List<Usuario> listaDeUsuarios;
    private Usuario usuario = null;
    private Tela00LoginBinding bindingLogin;
    private MyClickHandlers handlers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        bindingLogin = DataBindingUtil.setContentView(this, R.layout.tela_00_login);

        handlers = new MyClickHandlers(this);
        bindingLogin.setHandlers(handlers);

        fechaViewsDoCadastro();


        /************************************* LOGIN AUTOMATICO ***********************************/
        UsuarioLogin userLogin = new UsuarioLogin("aaa", "aaa");
        loginDeUsuario(userLogin); // Executa Login na API do Daves
        /******************************************************************************************/


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

    private boolean validaCadastro() {
        if (bindingLogin.edtLoginEmail.getText().toString().trim().length() == 0) return false;
        if (bindingLogin.edtLoginNome.getText().toString().trim().length() == 0) return false;
        if (bindingLogin.edtLoginLogin.getText().toString().trim().length() == 0) return false;
        if (bindingLogin.edtLoginSenha.getText().toString().trim().length() == 0) return false;
        return true;
    }

    public void todosUsuarios() {

        /*Create handle for the RetrofitInstance interface*/
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Usuario>> call = service.getAllUsuarios();
        call.enqueue(new Callback<List<Usuario>>() {

            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                listaDeUsuarios = response.body();
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void gravaNovoUsuario(UsuarioCadastro user) {

        /*Create handle for the RetrofitInstance interface*/
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<Usuario> call = service.postSaveUsuario(user);
        call.enqueue(new Callback<Usuario>() {

            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                //  = (Usuario) response.body();
                if (response.isSuccessful()) {
                    // USUARIO CADASTRADO COM SUCESSO
                    usuario = response.body();
                    Log.d(TAG, "Usuario: " + usuario.toString());
                    fechaViewsDoCadastro();
                    Toast.makeText(getApplicationContext(), "Usuario cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d(TAG, "Erro: " + response.errorBody().toString());
                }


            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, "Erro: " + t.toString());
            }
        });
    }

    public void loginDeUsuario(UsuarioLogin user) {
        bindingLogin.progressBar.setVisibility(View.VISIBLE);

        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<Usuario> call = service.postLogin(user);
        call.enqueue(new Callback<Usuario>() {

            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                //  = (Usuario) response.body();
                if (response.isSuccessful()) {
                    // LOGIN EFETUDO COM SUCESSO
                    usuario = response.body();
                    Toast.makeText(getApplicationContext(), "Login efetuado com sucesso", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Usuario: " + usuario.toString());
                    MainActivity.usuario = usuario;
                    MainActivity.code = usuario.getToken();
                    setResult(MainActivity.NAV_TICKET_LIST);
                    finish();

                } else {
                    bindingLogin.progressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Logon inválido", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Erro: " + response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                bindingLogin.progressBar.setVisibility(View.GONE);
                // Log error here since request failed
                Toast.makeText(getApplicationContext(), "Erro de logon", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "Erro: " + t.toString());
            }
        });
    }

    public class MyClickHandlers {

        Context context;

        public MyClickHandlers(Context context) {
            this.context = context;
        }

        public void onCadastreClicked(View view) {

            if (bindingLogin.btnLoginCadastre.getText().toString().equalsIgnoreCase("Cadastre-se")) {
                //Toast.makeText(getApplicationContext(), "Cadastre-se clicked!", Toast.LENGTH_SHORT).show();
                abreViewsDoCadastro();

            } else {
                if (!validaCadastro()) {
                    // campos em branco
                    Toast.makeText(getApplicationContext(), "Informe todos os campos", Toast.LENGTH_SHORT).show();
                } else {

                    // Grava o novo usuario
                    gravaNovoUsuario(new UsuarioCadastro(
                            0,
                            bindingLogin.edtLoginNome.getText().toString(),
                            bindingLogin.edtLoginEmail.getText().toString(),
                            bindingLogin.edtLoginLogin.getText().toString(),
                            bindingLogin.edtLoginSenha.getText().toString()
                    ));
                }
            }
        }

        public void onEntrarClicked(View view) {
            //Toast.makeText(getApplicationContext(), "Entrar clicked!", Toast.LENGTH_SHORT).show();

            UsuarioLogin userLogin = new UsuarioLogin();
            userLogin.setLogin(bindingLogin.edtLoginLogin.getText().toString());
            userLogin.setSenha(bindingLogin.edtLoginSenha.getText().toString());

            loginDeUsuario(userLogin); // Executa Login na API do Daves
            // Trata JSON e inicializa new Usuario().setters;
            // retorna token para MainActivity
        }
    }


//    /*
//
//    // Usuario já está setado para Observers @Binding
//    // Não necessitando forçar Observers estáticos
//    public static class Usuario {
//        public static ObservableField<String> login = new ObservableField<>();
//        public static ObservableField<String> senha = new ObservableField<>();
//
//        public ObservableField<String> getLogin() {
//            return login;
//        }
//
//        public ObservableField<String> getSenha() {
//            return senha;
//        }
//    }
//    */


}
