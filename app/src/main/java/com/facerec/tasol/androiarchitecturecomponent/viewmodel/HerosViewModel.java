package com.facerec.tasol.androiarchitecturecomponent.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.facerec.tasol.androiarchitecturecomponent.model_services.model.Hero;
import com.facerec.tasol.androiarchitecturecomponent.model_services.networking.NetworkApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tasol on 10/7/18.
 */

public class HerosViewModel extends AndroidViewModel {
    private MutableLiveData<List<Hero>> heroList;
    Retrofit retrofit;
    NetworkApi api;

    public HerosViewModel(Application application) {
        super(application);
        retrofit = new Retrofit.Builder()
                .baseUrl(NetworkApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(NetworkApi.class);
    }

    //we will call this method to get the data
    public LiveData<List<Hero>> getHeroes() {
        //if the list is null
        if (heroList == null) {
            heroList = new MutableLiveData<List<Hero>>();
            //we will load it asynchronously from server in this method
            loadHeroes();
        }

        //finally we will return the list
        return heroList;
    }

    //This method is using Retrofit to get the JSON data from URL
    private void loadHeroes() {
        Call<List<Hero>> call = api.getHeroes();
        call.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {

                //finally we are setting the list to our MutableLiveData
                heroList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {

            }
        });
    }

}
