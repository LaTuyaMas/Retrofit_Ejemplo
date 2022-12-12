package com.example.sharedpreferentes.retrofit_ejemplo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sharedpreferentes.retrofit_ejemplo.R;
import com.example.sharedpreferentes.retrofit_ejemplo.models.Photo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotoVH> {

    private final List<Photo> objects;
    private final int resources;
    private final Context context;

    public PhotosAdapter(List<Photo> objects, int resources, Context context) {
        this.objects = objects;
        this.resources = resources;
        this.context = context;
    }

    @NonNull
    @Override
    public PhotoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PhotoVH(LayoutInflater.from(context).inflate(resources, null));
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoVH holder, int position) {
        Photo p = objects.get(position);
        holder.lblTitulo.setText(p.getTitle());
        Picasso.get()
                .load(p.getThumbnailUrl()) // URl para la descargar de la imagen
                .error(R.drawable.ic_launcher_foreground) // Imagen de error de carga
                .placeholder(R.drawable.ic_launcher_background) // Imagen de muestra mientras carga
                .into(holder.imgPhoto); // ImageView donde cargaremos la imagen
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class PhotoVH extends RecyclerView.ViewHolder{

        ImageView imgPhoto;
        TextView lblTitulo;

        public PhotoVH(@NonNull View itemView) {
            super(itemView);

            imgPhoto = itemView.findViewById(R.id.imgImagenPhoto);
            lblTitulo = itemView.findViewById(R.id.lblTituloPhoto);
        }
    }
}
