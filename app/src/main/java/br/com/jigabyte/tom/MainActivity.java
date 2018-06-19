package br.com.jigabyte.tom;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import br.com.jigabyte.tom.databinding.ActivityMainBinding;
import br.com.jigabyte.tom.model.Usuario;

public class MainActivity extends AppCompatActivity {

    public static String token;
    final int ACTIVITY_2_REQUEST_TOKEN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // chama a tela de login se não tiver o token
        if(token == null){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivityForResult(intent, ACTIVITY_2_REQUEST_TOKEN);
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTIVITY_2_REQUEST_TOKEN) {
            if(resultCode == RESULT_OK){
                String resultado = data.getStringExtra("token");
                this.token = resultado;
            }
        }
    }
}
