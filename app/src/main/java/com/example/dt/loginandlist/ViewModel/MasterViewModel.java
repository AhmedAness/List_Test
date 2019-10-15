package com.example.dt.loginandlist.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.dt.loginandlist.Model.Course;
import com.example.dt.loginandlist.Model.Repository;

import java.util.ArrayList;
import java.util.List;

public class MasterViewModel extends AndroidViewModel {

    Repository repository;

    public MasterViewModel(@NonNull Application application) {
        super(application);
        repository=new Repository(application);
    }

    public void InsertNote(List<Course> course){
        repository.Insert(course);
    }

    public void UpdatetNote(Course course){
        repository.Update(course);
    }
    public void DeleteNote(Course course){
        repository.Delete(course);
    }
    public void DeleteAlltNote(ArrayList<Course> body){
        repository.DeleteAll(body);
    }

    public LiveData<List<Course>> GetAllNotes(int Last){
        return repository.getData();
    }

    public void RefreshList(int Last){
        repository.Refresh(Last);
    }

    public void GetAllExceptthis(List<Integer> coursesOnList) {
        repository.GetAllExceptthis(coursesOnList);

    }
}
