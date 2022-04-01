package com.ugb.miprimerproyecto;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class adaptadorImagenes  extends BaseAdapter {
    Context context;
    ArrayList<amigos> datosAmigosArrayList;
    LayoutInflater layoutInflater;
    amigos misAmigos;

    public adaptadorImagenes(Context context, ArrayList<amigos> datosAmigosArrayList) {
        this.context = context;
        this.datosAmigosArrayList = datosAmigosArrayList;
    }

    @Override
    public int getCount() {
        return datosAmigosArrayList.size();
    }
    @Override
    public Object getItem(int position) {
        return datosAmigosArrayList.get(position);
    }
    @Override
    public long getItemId(int position) {
        return 0;//Long.parseLong( datosAmigosArrayList.get(position).getIdAmigo() );
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(R.layout.listview_imagenes, parent, false);
        TextView tempVal = itemView.findViewById(R.id.lblTitulo);
        ImageView imgViewView = itemView.findViewById(R.id.imgPhoto);
        try{
            misAmigos = datosAmigosArrayList.get(position);
            tempVal.setText(misAmigos.getNombre());

            tempVal = itemView.findViewById(R.id.lblTelefono);
            tempVal.setText(misAmigos.getTelefono());

            tempVal = itemView.findViewById(R.id.lblemail);
            tempVal.setText(misAmigos.getEmail());

            Bitmap imagenBitmap = BitmapFactory.decodeFile(misAmigos.getUrlImg());
            imgViewView.setImageBitmap(imagenBitmap);

        }catch (Exception e){
        }
        return itemView;
    }
}
