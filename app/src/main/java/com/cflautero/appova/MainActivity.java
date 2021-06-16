package com.cflautero.appova;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.cflautero.appova.actividades.ContenedorInstruccionesActivity;
import com.cflautero.appova.clases.ConexionSQLiteHelper;
import com.cflautero.appova.clases.Utilidades;
import com.cflautero.appova.fragments.GestionJugadorFragment;
import com.cflautero.appova.fragments.InicioFragment;
import com.cflautero.appova.fragments.RegistroJugadorFragment;
import com.cflautero.appova.interfaces.IComunicaFragments;

public class MainActivity extends AppCompatActivity implements IComunicaFragments, InicioFragment.OnFragmentInteractionListener,RegistroJugadorFragment.OnFragmentInteractionListener, GestionJugadorFragment.OnFragmentInteractionListener {

    Fragment fragmentInicio,registroJugadorFragment,gestionJugadorFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Utilidades.obtenerListaAvatars();
        Utilidades.consultarListaJugadores(this);


        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,Utilidades.NOMBRE_BD,null,1);

        fragmentInicio = new InicioFragment();
        registroJugadorFragment = new RegistroJugadorFragment();
        gestionJugadorFragment= new GestionJugadorFragment();

        getSupportFragmentManager(). beginTransaction().replace(R.id.contenedorFragments,fragmentInicio).commit();

    }

    public AlertDialog dialogoGestionUsuarios() {

        AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);

        builder.setTitle("GESTIONAR JUGADOR")
                .setMessage("Indique si desea registrar un nuevo jugador o si desea seleccionar uno ya existente.\n\n" +
                        "También podrá modificar un Jugador desde la opción SELECCIONAR")
                .setNegativeButton("REGISTRAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which){
                                //Toast.makeText(getApplicationContext(),"REGISTRAR JUGADOR",Toast.LENGTH_SHORT).show();
                                getSupportFragmentManager(). beginTransaction().replace(R.id.contenedorFragments,registroJugadorFragment).commit();
                            }
                        })

                .setPositiveButton("SELECCIONAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                               // Toast.makeText(getApplicationContext(),"SELECCIONAR JUGADOR",Toast.LENGTH_SHORT).show();
                                getSupportFragmentManager(). beginTransaction().replace(R.id.contenedorFragments,gestionJugadorFragment).commit();
                            }
                        });

        return builder.create();
    }


    @Override
    public void iniciarjuego() {
        Toast.makeText(getApplicationContext(),"Iniciar Juego",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void llamarAjustes() {
        Toast.makeText(getApplicationContext(),"Ajustes desde la actividad",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void consultarRanking() {
        Toast.makeText(getApplicationContext(),"Ranking desde la actividad",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void consultarInstrucciones() {
        //Toast.makeText(getApplicationContext(),"Ayuda desde la actividad",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this, ContenedorInstruccionesActivity.class);
        startActivity(intent);

    }

    @Override
    public void gestionarUsuario() {
        dialogoGestionUsuarios().show();
    }

    @Override
    public void consultarInformacion() {
        Toast.makeText(getApplicationContext(),"informacion",Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
