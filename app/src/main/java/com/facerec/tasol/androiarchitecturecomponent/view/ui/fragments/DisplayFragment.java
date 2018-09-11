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
import com.facerec.tasol.androiarchitecturecomponent.model_services.model.StudentModel;
import com.facerec.tasol.androiarchitecturecomponent.utility.widget.CountListener;
import com.facerec.tasol.androiarchitecturecomponent.view.adapters.StudentAdapter;
import com.facerec.tasol.androiarchitecturecomponent.view.ui.activities.DrawerActivity;
import com.facerec.tasol.androiarchitecturecomponent.viewmodel.StudentViewModel;

import java.util.List;

/**
 * Created by tasol on 5/9/18.
 */

public class DisplayFragment extends MasterFragment {
    private String TAG = "%%%%Display Fragment";
    private StudentViewModel svm;
    private RecyclerView mRVStudents;
    private TextView mTvNoData;
    public StudentAdapter studentAdapter;
    private CountListener countListener;

    public DisplayFragment() {
    }

    @Override
    public View fragmentView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display, parent, false);
        svm = ViewModelProviders.of(getActivity()).get(StudentViewModel.class);
        countListener =new CountListener() {
            @Override
            public void getCount(String count) {
                ((DrawerActivity)getActivity()).setCount(count);
                Log.v(TAG," Count MSG "+count);
            }
        };
        mRVStudents = (RecyclerView)view.findViewById(R.id.rv_students);
        studentAdapter =new StudentAdapter(getActivity(),countListener);
        mRVStudents.setAdapter(studentAdapter);
        mRVStudents.setLayoutManager(new LinearLayoutManager(getActivity()));
        mTvNoData = (TextView)view.findViewById(R.id.tv_no_data);
        Log.v(TAG, " Display Fragment");
        displayAllStudents();
        return view;
    }

    private void displayAllStudents() {
        if (svm != null) {
            svm.getStudentList().observeForever(new Observer<List<StudentModel>>() {
                @Override
                public void onChanged(@Nullable List<StudentModel> studentModels) {
                    if (studentModels != null) {
                        if(studentModels.size()>0){
                            mTvNoData.setVisibility(View.GONE);
                            mRVStudents.setVisibility(View.VISIBLE);
                            studentAdapter.setData(studentModels);
                            for (StudentModel mode : studentModels) {
                                Log.v(TAG, " ID : " + mode.getStudentId() + " Name : " + mode.getStudentName() + " Age : " + mode.getAge());
                            }
                        }else {
                            mTvNoData.setVisibility(View.VISIBLE);
                            mRVStudents.setVisibility(View.GONE);
                        }
                    }
                }
            });
        }
    }


}
