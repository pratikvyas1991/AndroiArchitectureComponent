package com.facerec.tasol.androiarchitecturecomponent.view.ui.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facerec.tasol.androiarchitecturecomponent.R;
import com.facerec.tasol.androiarchitecturecomponent.view.ui.activities.BaseActivity;
import com.facerec.tasol.androiarchitecturecomponent.viewmodel.StudentViewModel;

/**
 * Created by tasol on 5/9/18.
 */

public class MasterFragment extends BaseFragment {
    private String TAG = "%%%MasterFragment";
    @Override
    public View fragmentView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View baseView = fragmentView(inflater,parent,savedInstanceState);
        return baseView;
    }

    public void displayMsg(){
        Log.v(TAG," Display Msg");
    }

    void callDisplay() {
        Fragment fragment = new DisplayFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
//        mDrawerList.setItemChecked(0, true);
//        mDrawerList.setSelection(0);
//        setTitle(mNavigationDrawerItemTitles[0]);
    }
}
