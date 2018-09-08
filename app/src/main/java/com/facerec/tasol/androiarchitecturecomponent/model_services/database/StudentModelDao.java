package com.facerec.tasol.androiarchitecturecomponent.model_services.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.facerec.tasol.androiarchitecturecomponent.model_services.model.StudentModel;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by tasol on 10/7/18.
 */

@Dao
public interface StudentModelDao {
    @Query("select * from StudentModel")
    LiveData<List<StudentModel>> getStudentData();

    @Query("select * from StudentModel where studentId = :STUDENTID")
    StudentModel getStudentDetail(String STUDENTID);

    @Insert(onConflict = REPLACE)
    void insertStudent(StudentModel model);

    @Delete
    void deleteStudent(StudentModel model);

}
