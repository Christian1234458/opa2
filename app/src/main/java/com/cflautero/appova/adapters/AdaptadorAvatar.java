package com.cflautero.appova.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.cflautero.appova.R;
import com.cflautero.appova.clases.Utilidades;
import com.cflautero.appova.clases.vo.AvatarVo;

import java.util.List;

public class AdaptadorAvatar extends RecyclerView.Adapter <AdaptadorAvatar.ViewHolderAvatar> {


    List<AvatarVo> listaAvatars;
    View vista;
    int posicionMarcada=0;


    public AdaptadorAvatar(List<AvatarVo> listaAvatars) {
        this.listaAvatars = listaAvatars;
    }



    @NonNull
    @Override
    public ViewHolderAvatar onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_avatar,parent,false);

        return new ViewHolderAvatar(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderAvatar viewHolderAvatar, int position) {
        viewHolderAvatar.imgAvatar.setImageResource(listaAvatars.get(position).getAvatarId());
        final int pos=position;


         viewHolderAvatar.cardAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posicionMarcada=pos;

                Utilidades.avatarSeleccion=listaAvatars.get(pos);
                Utilidades.avatarIdSeleccion=pos+1;
                notifyDataSetChanged();

            }
        });

        if (Utilidades.avatarIdSeleccion==0){

            if (posicionMarcada==position){
                viewHolderAvatar.barraSeleccion.setBackgroundColor(vista.getResources().getColor(R.color.colorPrimaryDark));
        }else
            {
                viewHolderAvatar.barraSeleccion.setBackgroundColor(vista.getResources().getColor(R.color.colorBlanco));
            }
        }else{
            if (Utilidades.avatarIdSeleccion-1==pos){
                viewHolderAvatar.barraSeleccion.setBackgroundColor(vista.getResources().getColor(R.color.colorPrimaryDark));
            }
            else{
                viewHolderAvatar.barraSeleccion.setBackgroundColor(vista.getResources().getColor(R.color.colorBlanco));
            }
        }


    }

    @Override
    public int getItemCount() {
        return listaAvatars.size();
    }

    public class ViewHolderAvatar extends RecyclerView.ViewHolder {

        CardView cardAvatar;
        ImageView imgAvatar;
        TextView barraSeleccion;



        public ViewHolderAvatar(@NonNull View itemView) {
            super(itemView);

            cardAvatar=itemView.findViewById(R.id.cardAvatar);
            imgAvatar=itemView.findViewById(R.id.idAvatar);
            barraSeleccion=itemView.findViewById(R.id.barraSeleccionId);

        }
    }
}
