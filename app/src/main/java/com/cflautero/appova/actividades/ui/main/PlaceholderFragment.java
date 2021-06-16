package com.cflautero.appova.actividades.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.cflautero.appova.R;
import com.cflautero.appova.fragments.InstruccionAyudaFragment;
import com.cflautero.appova.fragments.InstruccionFinalFragment;
import com.cflautero.appova.fragments.InstruccionInformacionFragment;
import com.cflautero.appova.fragments.InstruccionIniciarFragment;
import com.cflautero.appova.fragments.InstruccionNickNameFragment;
import com.cflautero.appova.fragments.InstruccionRankingFragment;
import com.cflautero.appova.fragments.InstruccionesAjustesFragment;
import com.cflautero.appova.fragments.IntroduccionFragment;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public static Fragment newInstance(int index) {

        Fragment fragment=null;

        switch (index){
            case 1: fragment=new IntroduccionFragment(); break;
            case 2: fragment=new InstruccionIniciarFragment(); break;
            case 3: fragment=new InstruccionesAjustesFragment(); break;
            case 4: fragment=new InstruccionRankingFragment(); break;
            case 5: fragment=new InstruccionAyudaFragment(); break;
            case 6: fragment=new InstruccionNickNameFragment(); break;
            case 7: fragment=new InstruccionInformacionFragment(); break;
            case 8: fragment=new InstruccionFinalFragment(); break;
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_contenedor_instrucciones2, container, false);
        final TextView textView = root.findViewById(R.id.section_label);
        pageViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}