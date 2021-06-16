package com.cflautero.appova.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cflautero.appova.R;
import com.cflautero.appova.adapters.AdaptadorAvatar;
import com.cflautero.appova.adapters.AdaptadorJugador;
import com.cflautero.appova.clases.Utilidades;
import com.cflautero.appova.clases.vo.JugadorVo;
import com.cflautero.appova.interfaces.IComunicaFragments;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GestionJugadorFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GestionJugadorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GestionJugadorFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    Activity actividad;
    View vista;
    IComunicaFragments iComunicaFragments;
    FloatingActionsMenu grupoBotones;
    FloatingActionButton fabConfirmar,fabActualizar,fabEliminar;

    RecyclerView recyclerAvatars,recyclerJugadores;

    ImageButton btnAtras,btnAyuda;
    TextView barraSeleccion;
    EditText campoNick;
    RadioButton radioM,radioF;

    JugadorVo jugadorSeleccionado;




    public GestionJugadorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GestionJugadorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GestionJugadorFragment newInstance(String param1, String param2) {
        GestionJugadorFragment fragment = new GestionJugadorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_gestion_jugador, container, false);


        recyclerAvatars=vista.findViewById(R.id.recyclerAvatarsId);
        recyclerJugadores=vista.findViewById(R.id.recyclerJugadoresId);

        btnAtras=vista.findViewById(R.id.btnIcoAtras);
        campoNick=vista.findViewById(R.id.campoNickname);
        radioF=vista.findViewById(R.id.radioF);
        radioM=vista.findViewById(R.id.radioM);
        barraSeleccion=vista.findViewById(R.id.barraSeleccionId);

        grupoBotones=vista.findViewById(R.id.grupoFab);
        fabConfirmar=vista.findViewById(R.id.idFabConfirmar);
        fabActualizar=vista.findViewById(R.id.idFabActualizar);
        fabEliminar=vista.findViewById(R.id.idFabEliminar);


        recyclerJugadores.setLayoutManager(new LinearLayoutManager(this.actividad));
        recyclerJugadores.setHasFixedSize(true);

        recyclerAvatars.setLayoutManager(new GridLayoutManager(this.actividad,3));
        recyclerAvatars.setHasFixedSize(true);




        fabConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(actividad,"Confirmar",Toast.LENGTH_LONG).show();
                grupoBotones.collapse();

            }
        });



        fabActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarJugador();
                grupoBotones.collapse();
            }
        });


        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(actividad,"Eliminar",Toast.LENGTH_LONG).show();

            }
        });


        llenarAdaptadorJugadores();
        llenarAdaptadoresAvatars(0);

        return vista;
    }

    private void actualizarJugador() {
        Toast.makeText(actividad,"Actualizar",Toast.LENGTH_LONG).show();
    }

    private void llenarAdaptadorJugadores() {

        AdaptadorJugador miAdaptadorJugadores=new AdaptadorJugador(Utilidades.listaJugadores);

        miAdaptadorJugadores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grupoBotones.collapse();
                jugadorSeleccionado=Utilidades.listaJugadores.get(recyclerJugadores.getChildAdapterPosition(view));
                campoNick.setText(jugadorSeleccionado.getNombre());

                if (jugadorSeleccionado.getGenero().equals("M")){
                    radioM.setChecked(true);
                }else{
                    radioF.setChecked(true);
                }
                llenarAdaptadoresAvatars(jugadorSeleccionado.getAvatar());
            }
        });

        recyclerJugadores.setAdapter(miAdaptadorJugadores);
    }

    private void llenarAdaptadoresAvatars(int avatarId) {
        Utilidades.avatarIdSeleccion=avatarId;
        AdaptadorAvatar miAdaptadorAvatars=new AdaptadorAvatar(Utilidades.listaAvatars);
        recyclerAvatars.scrollToPosition(avatarId-1);
        recyclerAvatars.setAdapter(miAdaptadorAvatars);

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity) {
            this.actividad=(Activity) context;
           iComunicaFragments=(IComunicaFragments) this.actividad;
        }

        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
