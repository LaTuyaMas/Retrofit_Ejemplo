package com.example.sharedpreferentes.retrofit_ejemplo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.sharedpreferentes.retrofit_ejemplo.conexiones.ApiConexiones;
import com.example.sharedpreferentes.retrofit_ejemplo.conexiones.RetrofitObject;
import com.example.sharedpreferentes.retrofit_ejemplo.databinding.ActivityMainBinding;
import com.example.sharedpreferentes.retrofit_ejemplo.models.Album;

import java.net.HttpURLConnection;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        doGetAlbums();

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void doGetAlbums() {
        // Creo los objetos para poder hacer la conexión
        Retrofit retrofit = RetrofitObject.getConexion();
        ApiConexiones conexiones = retrofit.create(ApiConexiones.class);

        // Preparo la llamada con el retorno de los datos
        Call<ArrayList<Album>> albums = conexiones.getAlbums();

        albums.enqueue(new Callback<ArrayList<Album>>() {
            @Override
            public void onResponse(Call<ArrayList<Album>> call, Response<ArrayList<Album>> response) {
                if (response.code() == HttpURLConnection.HTTP_OK){
                    ArrayList<Album> albums1 = response.body();
                    for (Album a : albums1) {
                        Log.d("ALBUM", "onResponse: " + a.toString());
                    }
                }
                else {
                    Toast.makeText(MainActivity.this, response.code() + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Album>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "ERROR, NO TIENES INTERNET", Toast.LENGTH_SHORT).show();
                Log.e("FAILURE", t.getLocalizedMessage());
            }
        });
    }
}