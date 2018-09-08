package com.facerec.tasol.androiarchitecturecomponent.view.ui.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facerec.tasol.androiarchitecturecomponent.R;
import com.facerec.tasol.androiarchitecturecomponent.model_services.model.StudentModel;
import com.facerec.tasol.androiarchitecturecomponent.viewmodel.StudentViewModel;

import java.util.List;

public class HomeActivity extends AppCompatActivity {
    Button btnAddStudent,btnSisplayStudent;
    private StudentViewModel studentViewModel;
    private String TAGHOME = "%%%HomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAddStudent = (Button)findViewById(R.id.btnAddStudent);
        btnSisplayStudent= (Button)findViewById(R.id.btnSisplayStudent);

        studentViewModel = ViewModelProviders.of(HomeActivity.this).get(StudentViewModel.class);

        displayStudentData();

        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentViewModel.addStudent(new StudentModel("Ronaldo",35));
                studentViewModel.addStudent(new StudentModel("Messi",31));
                Toast.makeText(HomeActivity.this," Hello",Toast.LENGTH_SHORT).show();
            }
        });

        btnSisplayStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayStudentData();
            }
        });
    }

    private void displayStudentData(){
        studentViewModel.getStudentList().observe(HomeActivity.this, new Observer<List<StudentModel>>() {
            @Override
            public void onChanged(@Nullable List<StudentModel> studentModels) {
                if(studentModels!=null){
                    for(StudentModel row: studentModels){
                        Log.v(TAGHOME," ID : "+row.getStudentId());
                        Log.v(TAGHOME," Name : "+row.getStudentName());
                        Log.v(TAGHOME," Age : "+row.getAge());
                    }
                }
            }
        });
    }
}
