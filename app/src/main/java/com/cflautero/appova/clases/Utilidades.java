package com.cflautero.appova.clases;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cflautero.appova.R;
import com.cflautero.appova.clases.vo.AvatarVo;
import com.cflautero.appova.clases.vo.JugadorVo;

import java.util.ArrayList;

public class Utilidades {

        public static AvatarVo avatarSeleccion=null;
        public static int avatarIdSeleccion=0;

        public static ArrayList<AvatarVo> listaAvatars=null;
        public static ArrayList<JugadorVo> listaJugadores=null;

        public static final String NOMBRE_BD="ova_bd";

        public static final String TABLA_JUGADOR="jugador";
        public static final String CAMPO_ID="id";
        public static final String CAMPO_NOMBRE="nombre";
        public static final String CAMPO_GENERO="genero";
        public static final String CAMPO_AVATAR="avatar";

        public static final String CREAR_TABLA_JUGADOR=" CREATE TABLE "+TABLA_JUGADOR+" ("+CAMPO_ID+" INTEGETER PRIMARY KEY, "+CAMPO_NOMBRE+" TEXT, "+CAMPO_GENERO+" TEXT, "+CAMPO_AVATAR+" INTEGER )";


        public static void obtenerListaAvatars(){
            listaAvatars=new ArrayList<AvatarVo>();

            listaAvatars.add(new AvatarVo(1, R.drawable.avatar2,"Avatar1"));
            listaAvatars.add(new AvatarVo(2, R.drawable.avatar3,"Avatar2"));
            listaAvatars.add(new AvatarVo(3, R.drawable.avatar4,"Avatar3"));
            listaAvatars.add(new AvatarVo(4, R.drawable.avatar5,"Avatar4"));
            listaAvatars.add(new AvatarVo(5, R.drawable.avatar6,"Avatar5"));
            listaAvatars.add(new AvatarVo(6, R.drawable.avatar7,"Avatar6"));
            listaAvatars.add(new AvatarVo(7, R.drawable.avatar8,"Avatar7"));
            listaAvatars.add(new AvatarVo(8, R.drawable.avatar9,"Avatar8"));
            listaAvatars.add(new AvatarVo(9, R.drawable.avatar10,"Avatar9"));
            listaAvatars.add(new AvatarVo(10, R.drawable.avatar11,"Avatar10"));
            listaAvatars.add(new AvatarVo(11, R.drawable.avatar12,"Avatar11"));
            listaAvatars.add(new AvatarVo(12, R.drawable.avatar13,"Avatar12"));
            listaAvatars.add(new AvatarVo(13, R.drawable.avatar14,"Avatar13"));
            listaAvatars.add(new AvatarVo(14, R.drawable.avatar15,"Avatar14"));
            listaAvatars.add(new AvatarVo(15, R.drawable.avatar16,"Avatar15"));
            listaAvatars.add(new AvatarVo(16, R.drawable.avatar17,"Avatar16"));
            listaAvatars.add(new AvatarVo(17, R.drawable.avatar18,"Avatar17"));
            listaAvatars.add(new AvatarVo(18, R.drawable.avatar19,"Avatar18"));
            listaAvatars.add(new AvatarVo(19, R.drawable.avatar20,"Avatar19"));
            listaAvatars.add(new AvatarVo(20, R.drawable.avatar21,"Avatar20"));
            listaAvatars.add(new AvatarVo(21, R.drawable.avatar22,"Avatar21"));
            listaAvatars.add(new AvatarVo(22, R.drawable.avatar23,"Avatar22"));
            listaAvatars.add(new AvatarVo(23, R.drawable.avatar24,"Avatar23"));
            listaAvatars.add(new AvatarVo(24, R.drawable.avatar25,"Avatar24"));
            listaAvatars.add(new AvatarVo(25, R.drawable.avatar26,"Avatar25"));
            listaAvatars.add(new AvatarVo(26, R.drawable.avatar27,"Avatar26"));

            avatarSeleccion= listaAvatars.get(0);
        }

        public static void consultarListaJugadores(Activity actividad){


            ConexionSQLiteHelper conn = new ConexionSQLiteHelper(actividad,NOMBRE_BD,null,1);
            SQLiteDatabase db = conn.getReadableDatabase();


            JugadorVo jugador=null;
            listaJugadores=new ArrayList<JugadorVo>();

            Cursor cursor = db.rawQuery("SELECT * FROM "+Utilidades.TABLA_JUGADOR, null);

            while (cursor.moveToNext()){

                    jugador=new JugadorVo();
                    jugador.setId(cursor.getInt(0));
                    jugador.setNombre(cursor.getString(1));
                    jugador.setGenero(cursor.getString(2));
                    jugador.setAvatar(cursor.getInt(3));

                listaJugadores.add(jugador);
            }
            db.close();
        }
}
