package com.facerec.tasol.androiarchitecturecomponent.model_services.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.facerec.tasol.androiarchitecturecomponent.model_services.model.StudentModel;

/**
 * Created by tasol on 10/7/18.
 */

@Database(entities = {StudentModel.class},version = 2)
public abstract class StudentDatabase extends RoomDatabase {
    private static StudentDatabase instance;
    private static String DB_NAME = "student_db";


    public static StudentDatabase getDatabase(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),StudentDatabase.class,DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();

        }
        return instance;
    }

    public static void destroyDbInstance(){
        instance = null;
    }

    public abstract StudentModelDao studentModel();
}
