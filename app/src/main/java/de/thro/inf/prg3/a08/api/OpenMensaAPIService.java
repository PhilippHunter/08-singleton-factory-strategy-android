package de.thro.inf.prg3.a08.api;

import java.util.List;

import de.thro.inf.prg3.a08.model.Meal;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OpenMensaAPIService {
    private static OpenMensaAPIService ourInstance;
    private OpenMensaAPI mensaApiInstance;

    public static OpenMensaAPIService getInstance() {
        if (ourInstance == null) {
            ourInstance = new OpenMensaAPIService();
        }
        return ourInstance;
    }

    public OpenMensaAPI getApi() {
        return mensaApiInstance;
    }

    private OpenMensaAPIService() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://openmensa.org/api/v2/")
                .build();

        mensaApiInstance = retrofit.create(OpenMensaAPI.class);
    }
}
