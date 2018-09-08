package com.facerec.tasol.androiarchitecturecomponent.view.ui.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facerec.tasol.androiarchitecturecomponent.viewmodel.StudentViewModel;

public class BaseActivity extends AppCompatActivity {
    private String TAGHOME = "%%%BaseActivity";
    public StudentViewModel studentViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(studentViewModel == null){
            studentViewModel = ViewModelProviders.of(BaseActivity.this).get(StudentViewModel.class);
        }
    }
}
