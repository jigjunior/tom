package br.com.jigabyte.tom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import br.com.jigabyte.tom.model.Usuario;

public class MainActivity extends AppCompatActivity {

    public static final int NAV_TICKET_LIST = 1;
    public static final int NAV_TICKET_DETALHES = 2;
    final int ACTIVITY_2_REQUEST_TOKEN = 1;
    public static final int NAV_VOO_BUSCAR = 3;
    public static final int NAV_VOO_LIST = 4;
    public static final int NAV_VOO_COMPRAR = 5;
    public static final int NAV_VOO_PAGAR = 6;
    public static int NAVIGATION;
    private static String TAG = "";
    public Usuario usuario;
    ArrayList<Usuario> listaDeUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NAVIGATION = 0;


    }

    public void buscaNavegacao() {
        // chama a tela de login se não tiver o token
        if (usuario.getToken() == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivityForResult(intent, ACTIVITY_2_REQUEST_TOKEN);
        } else {
            Intent intent = new Intent(this, TicketList.class);
            startActivityForResult(intent, ACTIVITY_TICKET_REQUEST_TOKEN);


        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTIVITY_2_REQUEST_TOKEN) {
            if(resultCode == RESULT_OK){
                usuario = (Usuario) data.getSerializableExtra("usuarioLogado");
                TAG = ("Usuario Logado: " + usuario.toString());
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        buscaNavegacao();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        buscaNavegacao();
    }


}
