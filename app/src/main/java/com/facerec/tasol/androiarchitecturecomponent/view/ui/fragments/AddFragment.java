package com.facerec.tasol.androiarchitecturecomponent.view.ui.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.facerec.tasol.androiarchitecturecomponent.R;
import com.facerec.tasol.androiarchitecturecomponent.model_services.model.StudentModel;
import com.facerec.tasol.androiarchitecturecomponent.viewmodel.StudentViewModel;

import java.util.List;

/**
 * Created by tasol on 5/9/18.
 */

public class AddFragment extends MasterFragment {
    private String TAG = "%%%%Add Fragment";
    StudentViewModel svm;
    private EditText mEtName, mEtAge;
    private Button mBtnAdd;

    public AddFragment() {
    }

    @Override
    public View fragmentView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, parent, false);
        mEtName = (EditText) view.findViewById(R.id.et_name);
        mEtAge = (EditText) view.findViewById(R.id.et_age);
        mBtnAdd = (Button) view.findViewById(R.id.btn_add);
        Log.v(TAG, " ADd Fragment");
        svm = ViewModelProviders.of(getActivity()).get(StudentViewModel.class);
        displayAllStudents();

        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mEtName.getText().toString();
                String age = mEtAge.getText().toString();
                svm.addStudent(new StudentModel(name,Integer.parseInt(age)));
                callDisplay();
            }
        });
        return view;
    }

    private void displayAllStudents() {
        svm.getStudentList().observeForever(new Observer<List<StudentModel>>() {
            @Override
            public void onChanged(@Nullable List<StudentModel> studentModels) {
                Log.v(TAG," display Called ");
                if (studentModels != null) {
                    for (StudentModel mode : studentModels) {
                        Log.v(TAG, " ID : " + mode.getStudentId() + " Name : " + mode.getStudentName() + " Age : " + mode.getAge());
                    }
                }
            }
        });
    }

}
