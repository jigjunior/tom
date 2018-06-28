package br.com.jigabyte.tom;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import br.com.jigabyte.tom.databinding.Activity3PoltronaComprarBinding;
import br.com.jigabyte.tom.model.Poltrona;
import br.com.jigabyte.tom.model.Voo;

public class PoltronaComprar extends AppCompatActivity {

    Activity3PoltronaComprarBinding bind;
    Voo voo;
    ArrayList<Poltrona> listaDePoltronas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_3_poltrona_comprar);


    }
}
