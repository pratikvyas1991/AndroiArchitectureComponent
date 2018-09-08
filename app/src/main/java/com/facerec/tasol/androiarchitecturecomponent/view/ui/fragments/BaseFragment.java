package com.facerec.tasol.androiarchitecturecomponent.view.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by tasol on 5/9/18.
 */

public abstract class BaseFragment extends Fragment {

    public BaseFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View baseView = fragmentView(inflater,container,savedInstanceState);
        return baseView;
    }
    // This abstract method ensures that view in inflated
    public abstract View fragmentView(LayoutInflater inflater,ViewGroup parent,Bundle savedInstanceState);
}
