package com.facerec.tasol.androiarchitecturecomponent.model_services.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by tasol on 10/7/18.
 */

@Entity
public class StudentModel {
    @PrimaryKey(autoGenerate = true)
    public int studentId;
    private String studentName;
    private int age;

    public StudentModel(String studentName, int age) {
        this.studentName = studentName;
        this.age = age;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public int getAge() {
        return age;
    }
}
