package com.example.sharedpreferentes.retrofit_ejemplo.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sharedpreferentes.retrofit_ejemplo.PhotosActivity;
import com.example.sharedpreferentes.retrofit_ejemplo.R;
import com.example.sharedpreferentes.retrofit_ejemplo.models.Album;

import java.util.List;

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.AlbumVH> {

    private final List<Album> objects;
    private final int resources;
    private final Context context;

    public AlbumsAdapter(List<Album> objects, int resources, Context context) {
        this.objects = objects;
        this.resources = resources;
        this.context = context;
    }

    @NonNull
    @Override
    public AlbumVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View albumView = LayoutInflater.from(context).inflate(resources, null);
        albumView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                                ViewGroup.LayoutParams.WRAP_CONTENT));
        return new AlbumVH(albumView);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumVH holder, int position) {
        Album a = objects.get(position);
        holder.lblTitle.setText(a.getTitulo());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PhotosActivity.class);
                String albumId = String.valueOf(a.getId());
                Bundle bundle = new Bundle();
                bundle.putString("ID_ALBUM", albumId);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class AlbumVH extends RecyclerView.ViewHolder {

        TextView lblTitle;

        public AlbumVH(@NonNull View itemView) {
            super(itemView);
            lblTitle = itemView.findViewById(R.id.lblTitle);
        }
    }
}
