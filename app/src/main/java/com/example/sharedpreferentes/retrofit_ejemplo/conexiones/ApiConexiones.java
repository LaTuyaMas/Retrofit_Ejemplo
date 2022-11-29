package com.example.sharedpreferentes.retrofit_ejemplo.conexiones;

import com.example.sharedpreferentes.retrofit_ejemplo.models.Album;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiConexiones {

    @GET("/albums")
    Call<ArrayList<Album>> getAlbums();

    

}
