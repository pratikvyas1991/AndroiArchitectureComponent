package com.facerec.tasol.androiarchitecturecomponent.view.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facerec.tasol.androiarchitecturecomponent.R;

/**
 * Created by tasol on 5/9/18.
 */

public class ConnectFragment extends MasterFragment {

    public ConnectFragment() {
    }
    @Override
    public View fragmentView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_connect, parent, false);
        displayMsg();
        return view;
    }
}
