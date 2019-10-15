package com.example.dt.loginandlist.Model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Course.class,version = 2)
public abstract class CoursesDatabase extends RoomDatabase {

    private static CoursesDatabase instance;
    public abstract CoursesDao courseDao();

    public static CoursesDatabase getInstance(Context context) {
        if (instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),
                    CoursesDatabase.class,
                    "course_table")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
