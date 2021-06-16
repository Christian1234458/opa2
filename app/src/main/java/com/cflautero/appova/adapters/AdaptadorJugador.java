package com.cflautero.appova.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cflautero.appova.R;
import com.cflautero.appova.clases.Utilidades;
import com.cflautero.appova.clases.vo.JugadorVo;

import java.util.List;

public class AdaptadorJugador extends RecyclerView.Adapter <AdaptadorJugador.ViewHolderJugador> implements View.OnClickListener {

    private View.OnClickListener listener;
    List<JugadorVo> listaJugadores;
    View vista;
    //int posicionMarcada=0;


    public AdaptadorJugador(List<JugadorVo> listaJugadores) {
        this.listaJugadores = listaJugadores;
    }
    @NonNull
    @Override
    public ViewHolderJugador onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_jugador,parent,false);
        vista.setOnClickListener(this);
        return new ViewHolderJugador(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderJugador viewHolderJugador, int i) {
        viewHolderJugador.imgAvatar.setImageResource(Utilidades.listaAvatars.get(listaJugadores.get(i).getAvatar()-1).getAvatarId());
        viewHolderJugador.txtNombre.setText(listaJugadores.get(i).getNombre());

        if(listaJugadores.get(i).getGenero().equals("M")){
            viewHolderJugador.txtGenero.setText("Masculino");
        }
        else{
            viewHolderJugador.txtGenero.setText("Femenino");
        }

    }

    @Override
    public int getItemCount() {
        return listaJugadores.size();
    }

    public  void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }
    @Override


    public void onClick(View view) {

        if (listener!=null){
            listener.onClick(view);
        }

    }

    public class ViewHolderJugador extends RecyclerView.ViewHolder {

        ImageView imgAvatar;
        TextView txtNombre;
        TextView txtGenero;

        public ViewHolderJugador(@NonNull View itemView) {
            super(itemView);

            imgAvatar=itemView.findViewById(R.id.idAvatar);
            txtNombre=itemView.findViewById(R.id.idNombre);
            txtGenero=itemView.findViewById(R.id.idGenero);

        }
    }
}
