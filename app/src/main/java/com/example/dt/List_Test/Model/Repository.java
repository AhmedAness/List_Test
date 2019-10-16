package com.example.dt.List_Test.Model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class Repository {

    Context context;
    CoursesDao coursesDao;
    CoursesDatabase database;
    MutableLiveData<List<Course>> Data=new MutableLiveData<>();
    List<Course> Data2=new ArrayList<>();

    public Repository(Context context) {
        this.context=context;
        database= CoursesDatabase.getInstance(context);
        coursesDao =database.courseDao();
    }

    public LiveData<List<Course>> getData() {
        return Data;
    }


    public void Insert(List<Course> course){
        new InsertAsync(coursesDao).execute(course);
    }


    public void Update(Course course){
        new UpdateAsync(coursesDao).execute(course);

    }

    public void Delete(Course course){
        new DeleteAsync(coursesDao).execute(course);

    }

    public void DeleteAll(ArrayList<Course> body){
        new DeleteAllAsync(coursesDao).execute();
        Data2.addAll(body);
    }

    public void Refresh(int last) {
//        List<Course> courses = (List<Course>) new GetAllAsync(coursesDao).execute(last);
       new GetAllAsync(coursesDao).execute(last);
    }

    public void GetAllExceptthis(List<Integer> coursesOnList) {
        new GetAllExceptthis(coursesDao).execute(coursesOnList);
    }

    private class InsertAsync extends AsyncTask<List<Course>,Void,Void>{
        private CoursesDao coursesDao;
        public InsertAsync(CoursesDao coursesDao) {
            this.coursesDao = coursesDao;
        }

        @Override
        protected Void doInBackground(List<Course>... notes) {
            for (int i = 0; i < notes[0].size(); i++) {
                coursesDao.Insert( notes[0].get(i));
            }
            return null;
        }
    }

    private class UpdateAsync extends AsyncTask<Course,Void,Void>{
        private CoursesDao coursesDao;
        public UpdateAsync(CoursesDao coursesDao) {
            this.coursesDao = coursesDao;
        }

        @Override
        protected Void doInBackground(Course... courses) {
            coursesDao.Update(courses[0]);
            return null;
        }
    }
    private class GetAllAsync extends AsyncTask<Integer, Void, List<Course>> {
        private CoursesDao coursesDao;
        public GetAllAsync(CoursesDao coursesDao) {
            this.coursesDao = coursesDao;
        }

        @SuppressLint("WrongThread")
        @Override
        protected List<Course> doInBackground(Integer... courses) {
            return coursesDao.GeteAll(courses[0]);
        }

        @Override
        protected void onPostExecute(List<Course> courses) {

            super.onPostExecute(courses);
            Data.setValue(courses);
        }
    }

    private class GetAllExceptthis extends AsyncTask<List<Integer>, Void, List<Course>> {
        private CoursesDao coursesDao;
        public GetAllExceptthis(CoursesDao coursesDao) {
            this.coursesDao = coursesDao;
        }

        @SuppressLint("WrongThread")
        @Override
        protected List<Course> doInBackground(List<Integer>... courses) {
            return coursesDao.GeteAllExceptthis(courses[0]);
        }

        @Override
        protected void onPostExecute(List<Course> courses) {

            super.onPostExecute(courses);
            Data.setValue(courses);
        }
    }


    private class DeleteAsync extends AsyncTask<Course,Void,Void>{
        private CoursesDao coursesDao;
        public DeleteAsync(CoursesDao coursesDao) {
            this.coursesDao = coursesDao;
        }

        @Override
        protected Void doInBackground(Course... courses) {
            coursesDao.Delete(courses[0]);
            return null;
        }
    }

    private class DeleteAllAsync extends AsyncTask<Void,Void,Void>{
        private CoursesDao coursesDao;
        public DeleteAllAsync(CoursesDao coursesDao) {
            this.coursesDao = coursesDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            coursesDao.DeleteAll();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
//            coursesDao.Insert(new Course("asd","asd","asd"));

            new InsertAsync(coursesDao).execute(Data2);

        }
    }



}
