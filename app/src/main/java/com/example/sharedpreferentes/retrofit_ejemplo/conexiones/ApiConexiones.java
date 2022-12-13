package com.example.sharedpreferentes.retrofit_ejemplo.conexiones;

import com.example.sharedpreferentes.retrofit_ejemplo.models.Album;
import com.example.sharedpreferentes.retrofit_ejemplo.models.Photo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiConexiones {

    @GET("/albums")
    Call<ArrayList<Album>> getAlbums();

    @GET("/photos?")
    Call<ArrayList<Photo>> getPhotosAlbum(@Query("albumId") String albumId);

    // Parámetros dentro del PATH
    @GET("/albums/{albumId}/photos")
    Call<ArrayList<Photo>> getPhotosAlbumPath(@Path("albumId") String albumId);

    // Post para crear nuevos albums
    @POST("/albums")
    Call<Album> postAlbumCreate(@Body Album album);
}
