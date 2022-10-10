package com.example.lorempicsum_client;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ClientService {
    @GET("v2/list")
    Call<List<Client>> getImagen();

}
