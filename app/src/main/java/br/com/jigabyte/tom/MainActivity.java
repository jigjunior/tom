package br.com.jigabyte.tom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.com.jigabyte.tom.model.Usuario;
import br.com.jigabyte.tom.model.Voo;

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

        Intent i;

        if (NAVIGATION == 0)
            i = new Intent(this, LoginActivity.class);

        else if (NAVIGATION == NAV_TICKET_LIST)
            i = new Intent(this, MeusTickets.class);

        else if (NAVIGATION == NAV_TICKET_DETALHES)
            i = new Intent(this, LoginActivity.class); // TicketDetalhes

        else if (NAVIGATION == NAV_VOO_BUSCAR)
            i = new Intent(this, VooBuscar.class);

        else if (NAVIGATION == NAV_VOO_LIST)
            i = new Intent(this, LoginActivity.class); // VooList

        else if (NAVIGATION == NAV_VOO_COMPRAR)
            i = new Intent(this, LoginActivity.class); // VooComprar

        else if (NAVIGATION == NAV_VOO_PAGAR)
            i = new Intent(this, LoginActivity.class); // VooPagar

        else
            i = new Intent(this, MeusTickets.class);

        startActivityForResult(i, NAVIGATION);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        NAVIGATION = resultCode;

        buscaNavegacao();
    }


    @Override
    protected void onResume() {
        super.onResume();
        buscaNavegacao();
    }



}
