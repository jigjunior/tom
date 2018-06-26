package br.com.jigabyte.tom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.jigabyte.tom.model.Usuario;

public class MainActivity extends AppCompatActivity {

    public static int NAVIGATION = 0;
    public static final int NAV_TICKET_LIST = 1;
    public static final int NAV_TICKET_DETALHES = 2;


    public static final int NAV_VOO_BUSCAR = 3;
    public static final int NAV_VOO_LIST = 4;
    public static final int NAV_VOO_COMPRAR = 5;
    public static final int NAV_VOO_PAGAR = 6;

    private static String TAG = "";
    public static String code = "";
    public static Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void buscaNavegacao() {
        // chama a tela de login se não tiver o token
        if (NAVIGATION == 0) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivityForResult(intent, NAVIGATION);
        } else if (NAVIGATION == NAV_TICKET_LIST) {
            Intent intent = new Intent(this, MeusTickets.class);
            intent.putExtra("usuario_logado", usuario);
            startActivityForResult(intent, NAVIGATION);
        } else if (NAVIGATION == NAV_TICKET_DETALHES) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivityForResult(intent, NAVIGATION);
        } else if (NAVIGATION == NAV_VOO_BUSCAR) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivityForResult(intent, NAVIGATION);
        } else if (NAVIGATION == NAV_VOO_LIST) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivityForResult(intent, NAVIGATION);
        } else if (NAVIGATION == NAV_VOO_COMPRAR) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivityForResult(intent, NAVIGATION);
        } else if (NAVIGATION == NAV_VOO_PAGAR) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivityForResult(intent, NAVIGATION);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        int request = requestCode;
        int result = resultCode;

        if (resultCode == NAV_TICKET_LIST) {
            usuario = (Usuario) data.getSerializableExtra("usuarioLogado");
            TAG = ("Usuario Logado: " + usuario.toString());
            //Toast.makeText(this, TAG, Toast.LENGTH_LONG).show();

            // Busca a lista de passagens do Usuario por ID
            // ApiInterface getBuscarUsuario
            buscaNavegacao();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        buscaNavegacao();
    }



}
