package com.cflautero.appova.actividades;

import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.cflautero.appova.R;
import com.cflautero.appova.actividades.ui.main.SectionsPagerAdapter;
import com.cflautero.appova.fragments.InstruccionAyudaFragment;
import com.cflautero.appova.fragments.InstruccionFinalFragment;
import com.cflautero.appova.fragments.InstruccionInformacionFragment;
import com.cflautero.appova.fragments.InstruccionIniciarFragment;
import com.cflautero.appova.fragments.InstruccionNickNameFragment;
import com.cflautero.appova.fragments.InstruccionRankingFragment;
import com.cflautero.appova.fragments.InstruccionesAjustesFragment;
import com.cflautero.appova.fragments.IntroduccionFragment;

public class ContenedorInstruccionesActivity extends AppCompatActivity implements IntroduccionFragment.OnFragmentInteractionListener, InstruccionInformacionFragment.OnFragmentInteractionListener,
        InstruccionIniciarFragment.OnFragmentInteractionListener, InstruccionNickNameFragment.OnFragmentInteractionListener, InstruccionRankingFragment.OnFragmentInteractionListener,
        InstruccionesAjustesFragment.OnFragmentInteractionListener, InstruccionAyudaFragment.OnFragmentInteractionListener, InstruccionFinalFragment.OnFragmentInteractionListener{

    ViewPager viewPager;
    private LinearLayout linearPuntos;
    private TextView[] puntosSlide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenedor_instrucciones2);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        linearPuntos=findViewById(R.id.idLinearPuntos);
        agregaIndicadorPuntos(0);
        viewPager.addOnPageChangeListener(viewListener);

    }

    private void agregaIndicadorPuntos(int pos) {
        puntosSlide =new TextView[8];
        linearPuntos.removeAllViews();

        for (int i=0; i< puntosSlide.length; i++){
            puntosSlide[i]=new TextView(this);
            puntosSlide[i].setText(Html.fromHtml("&#8226;"));
            puntosSlide[i].setTextSize(55);
            puntosSlide[i].setTextColor(getResources().getColor(R.color.colorBlancoTransparente));
            linearPuntos.addView(puntosSlide[i]);
        }
        if(puntosSlide.length>0){
            puntosSlide[pos].setTextColor(getResources().getColor(R.color.colorBlanco));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int pos) {
            agregaIndicadorPuntos(pos);
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}