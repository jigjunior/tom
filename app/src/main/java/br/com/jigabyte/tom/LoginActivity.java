package br.com.jigabyte.tom;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import br.com.jigabyte.tom.databinding.Tela00LoginBinding;
import br.com.jigabyte.tom.model.Usuario;

public class LoginActivity extends AppCompatActivity {


    private Usuario usuario;
    private Tela00LoginBinding bindingLogin;
    private MyClickHandlers handlers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        bindingLogin = DataBindingUtil.setContentView(this, R.layout.tela_00_login);


        handlers = new MyClickHandlers(this);
        bindingLogin.setHandlers(handlers);
        pageInit();







    }

    private void pageInit(){
        bindingLogin.txtLoginNome.setVisibility(View.GONE);
        bindingLogin.edtLoginNome.setVisibility(View.GONE);
        bindingLogin.txtLoginEmail.setVisibility(View.GONE);
        bindingLogin.edtLoginEmail.setVisibility(View.GONE);
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
            Toast.makeText(getApplicationContext(), "Cadastre-se clicked!", Toast.LENGTH_SHORT).show();

        }

    }



}
