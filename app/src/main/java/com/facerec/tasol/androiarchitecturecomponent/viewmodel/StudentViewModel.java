package com.facerec.tasol.androiarchitecturecomponent.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.facerec.tasol.androiarchitecturecomponent.model_services.database.StudentDatabase;
import com.facerec.tasol.androiarchitecturecomponent.model_services.model.StudentModel;

import java.util.List;

/**
 * Created by tasol on 10/7/18.
 */

public class StudentViewModel extends AndroidViewModel {
    private StudentDatabase mStudentDatabase;

    public StudentViewModel(Application application) {
        super(application);
        mStudentDatabase = StudentDatabase.getDatabase(this.getApplication());
    }


    public void addStudent(final StudentModel model) {
        // add the student data into the database
        new addStudentTask(mStudentDatabase).execute(model);
    }

    public LiveData<List<StudentModel>> getStudentList() {
        // get Student List
        return mStudentDatabase.studentModel().getStudentData();
    }

    private  class addStudentTask extends AsyncTask<StudentModel, Void, Void> {
        // Background  Task to add the data into the database
        StudentDatabase studentDatabase;

        addStudentTask(StudentDatabase database) {
            studentDatabase = database;
        }

        @Override
        protected Void doInBackground(StudentModel... studentModels) {
            studentDatabase.studentModel().insertStudent(studentModels[0]);
            return null;
        }
    }
}
