package com.example.lorempicsum_client;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Toolbar toolbar = findViewById (R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar (toolbar);
        setTitle (R.string.app_name);

        RecyclerView reView = findViewById(R.id.loremPicsum_Client);
        reView.setLayoutManager (new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        reView.addItemDecoration (new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl ("https://picsum.photos/")
                .addConverterFactory (GsonConverterFactory.create())
                .build ();

        ClientService api = retrofit.create (ClientService.class);
        api.getImagen ().enqueue (new Callback<List<Client>>() {
            @Override
            public void onResponse(Call<List<Client>> call, Response<List<Client>> response) {
                if (response.body () == null) return;
                List<Client> lista = response.body();
                runOnUiThread (() -> reView.setAdapter (new Adapter (getBaseContext (), lista)));
            }

            @Override
            public void onFailure(Call<List<Client>> call, Throwable t) {
                if (t.getMessage () != null) {
                    Log.e ("PKAT", t.getMessage ());
                    runOnUiThread (() ->
                            Toast.makeText (getBaseContext (), "Error", Toast.LENGTH_LONG).show ());
                }
            }

        });
    }
}
