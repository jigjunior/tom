package br.com.jigabyte.tom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static String token;
    final int ACTIVITY_2_REQUEST_TOKEN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public void buscaToken() {
        // chama a tela de login se não tiver o token
        if(token == null){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivityForResult(intent, ACTIVITY_2_REQUEST_TOKEN);
        } else {
            // chama activity de listar tickets
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

    @Override
    protected void onResume() {
        super.onResume();
        buscaToken();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        buscaToken();
    }


}
