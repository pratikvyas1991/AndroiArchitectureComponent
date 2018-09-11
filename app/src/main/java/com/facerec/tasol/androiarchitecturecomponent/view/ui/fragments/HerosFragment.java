package com.facerec.tasol.androiarchitecturecomponent.view.ui.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facerec.tasol.androiarchitecturecomponent.R;
import com.facerec.tasol.androiarchitecturecomponent.model_services.model.Hero;
import com.facerec.tasol.androiarchitecturecomponent.model_services.model.StudentModel;
import com.facerec.tasol.androiarchitecturecomponent.utility.widget.CountListener;
import com.facerec.tasol.androiarchitecturecomponent.view.adapters.HeroesAdapter;
import com.facerec.tasol.androiarchitecturecomponent.view.adapters.StudentAdapter;
import com.facerec.tasol.androiarchitecturecomponent.view.ui.activities.DrawerActivity;
import com.facerec.tasol.androiarchitecturecomponent.viewmodel.HerosViewModel;
import com.facerec.tasol.androiarchitecturecomponent.viewmodel.StudentViewModel;

import java.util.List;

/**
 * Created by tasol on 5/9/18.
 */

public class HerosFragment extends MasterFragment {
    private String TAG = "%%%%Display Fragment";
    RecyclerView recyclerView;
    HeroesAdapter adapter;
    HerosViewModel herosViewModel;
    List<Hero> heroList;
    public HerosFragment() {
    }

    @Override
    public View fragmentView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_heros, parent, false);
        recyclerView =(RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        herosViewModel = ViewModelProviders.of(this).get(HerosViewModel.class);

        herosViewModel.getHeroes().observe(getActivity(), new Observer<List<Hero>>() {
            @Override
            public void onChanged(@Nullable List<Hero> heroList) {
                adapter = new HeroesAdapter(getActivity(), heroList);
                recyclerView.setAdapter(adapter);
            }
        });
        return view;
    }


}
