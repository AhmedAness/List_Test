package com.example.dt.List_Test.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.dt.List_Test.Model.Course;
import com.example.dt.List_Test.Model.Repository;

import java.util.ArrayList;
import java.util.List;

public class MasterViewModel extends AndroidViewModel {

    Repository repository;

    public MasterViewModel(@NonNull Application application) {
        super(application);
        repository=new Repository(application);
    }

    public void InsertCourse(List<Course> course){
        repository.Insert(course);
    }

    public void UpdatetCourse(Course course){
        repository.Update(course);
    }
    public void DeleteCourse(Course course){
        repository.Delete(course);
    }
    public void DeleteAlltCources(ArrayList<Course> body){
        repository.DeleteAll(body);
    }

    public LiveData<List<Course>> GetAllCourses(int Last){
        return repository.getData();
    }

    public void RefreshList(int Last){
        repository.Refresh(Last);
    }

    public void GetAllExceptthis(List<Integer> coursesOnList) {
        repository.GetAllExceptthis(coursesOnList);

    }
}
