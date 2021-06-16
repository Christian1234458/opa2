package com.cflautero.appova.fragments;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cflautero.appova.R;
import com.cflautero.appova.adapters.AdaptadorAvatar;
import com.cflautero.appova.clases.ConexionSQLiteHelper;
import com.cflautero.appova.clases.Utilidades;
import com.cflautero.appova.interfaces.IComunicaFragments;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegistroJugadorFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegistroJugadorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistroJugadorFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    View vista;
    Activity actividad;
    IComunicaFragments iComunicaFragments;
    RecyclerView recyclerAvatars;

    FloatingActionButton fabRegistro;
    EditText campoNick;
    RadioButton radioM,radioF;



    public RegistroJugadorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistroJugadorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistroJugadorFragment newInstance(String param1, String param2) {
        RegistroJugadorFragment fragment = new RegistroJugadorFragment();
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

        vista=inflater.inflate(R.layout.fragment_registro_jugador, container, false);
        recyclerAvatars = vista.findViewById(R.id.recyclerAvatarsId);
        fabRegistro=vista.findViewById(R.id.idFabRegistro);
        campoNick=vista.findViewById(R.id.campoNickname);
        radioF=vista.findViewById(R.id.radioF);
        radioM=vista.findViewById(R.id.radioM);
        recyclerAvatars.setLayoutManager(new GridLayoutManager(this.actividad,3));
        recyclerAvatars.setHasFixedSize(true);
        fabRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarJugador();
            }
        });

        final AdaptadorAvatar miAdaptador=new AdaptadorAvatar(Utilidades.listaAvatars);

        recyclerAvatars.setAdapter(miAdaptador);




        return vista;
    }

    private void registrarJugador() {

        String genero="";

        if (radioM.isChecked()==true){
            genero="M";
        }
        else if (radioF.isChecked()==true){
            genero="F";
        }else{
            genero="No seleccionado";
        }

        if (!genero.equals("No ha seleccionado ningun genero")&&
                campoNick.getText().toString()!=null && !campoNick.getText().toString().trim().equals("")){

            int avatarId=Utilidades.avatarSeleccion.getId();
            String nickName=campoNick.getText().toString();


            String registro="Nombre: "+campoNick.getText().toString()+"\n";
            registro+="Genero:"+genero+"\n";
            registro+="Avatar Id: "+Utilidades.avatarSeleccion.getId();


            ConexionSQLiteHelper conn= new ConexionSQLiteHelper(actividad,Utilidades.NOMBRE_BD,null,1);

            SQLiteDatabase db = conn.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(Utilidades.CAMPO_NOMBRE,nickName);
            values.put(Utilidades.CAMPO_GENERO,genero);
            values.put(Utilidades.CAMPO_AVATAR,avatarId);

            Long idResultante=db.insert(Utilidades.TABLA_JUGADOR,Utilidades.CAMPO_ID,values);

            if (idResultante!=-1) {

                System.out.println("registrar"+registro);
                Toast.makeText(actividad,"El jugador fue registrado\n"+idResultante+" - "+registro,Toast.LENGTH_SHORT).show();
                campoNick.setText("");
            }
            else{
                Toast.makeText(actividad,"El jugador no fue registrado\n"+registro,Toast.LENGTH_SHORT).show();
            }
            db.close();

        }else{
            Toast.makeText(actividad,"Verifique los datos del registro",Toast.LENGTH_SHORT).show();
        }
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

            this.actividad= (Activity) context;

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
