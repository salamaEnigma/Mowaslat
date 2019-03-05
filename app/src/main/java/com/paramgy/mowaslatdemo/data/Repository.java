package com.paramgy.mowaslatdemo.data;

import android.app.Application;
import android.os.AsyncTask;

import com.paramgy.mowaslatdemo.data.AppDao;
import com.paramgy.mowaslatdemo.data.AppDatabase;
import com.paramgy.mowaslatdemo.data.Location;
import com.paramgy.mowaslatdemo.data.Result;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    private AppDao appDAO;


    public Repository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        appDAO = database.appDAO();
    }


    public void insertLocation(Location location) {
        new InsertLocationAsyncTask(appDAO).execute(location);
    }

    public void insertResult(Result result) {
        new InsertResultAsyncTask(appDAO).execute(result);
    }

    public void deleteAllLocation() {
        new DeleteAllLocationsAsyncTask(appDAO).execute();
    }

    public void deleteAllResults() {
        new DeleteAllResultsAsyncTask(appDAO).execute();
    }


    // THIS NEED TO BE REVIEWED
    public List<Location> getAllLocations() {
        try {
            return new GetAllLocationsAsyncTask(appDAO).execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<Location>();
    }

    public Result getResult(String current, String destination, int method) {
        try {
            return new GetResultAsyncTask(appDAO, current, destination, method).execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //***************** Database Background Operations *******************//
    private static class InsertLocationAsyncTask extends AsyncTask<Location, Void, Void> {
        private AppDao appDao;

        public InsertLocationAsyncTask(AppDao appDao) {
            this.appDao = appDao;
        }

        @Override
        protected Void doInBackground(Location... locations) {
            appDao.insertLocation(locations[0]);
            return null;
        }
    } // end InsertLocationAsyncTask

    private static class InsertResultAsyncTask extends AsyncTask<Result, Void, Void> {
        private AppDao appDao;

        public InsertResultAsyncTask(AppDao appDao) {
            this.appDao = appDao;
        }

        @Override
        protected Void doInBackground(Result... results) {
            appDao.insertResult(results[0]);
            return null;
        }
    } // end InsertResultAsyncTask

    private static class DeleteAllLocationsAsyncTask extends AsyncTask<Void, Void, Void> {
        AppDao appDao;

        public DeleteAllLocationsAsyncTask(AppDao appDao) {
            this.appDao = appDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            appDao.deleteAllLocations();
            return null;
        }
    }// end DeleteAllLocationsAsyncTask

    private static class DeleteAllResultsAsyncTask extends AsyncTask<Void, Void, Void> {
        AppDao appDao;

        public DeleteAllResultsAsyncTask(AppDao appDao) {
            this.appDao = appDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            appDao.deleteAllResults();
            return null;
        }
    }// end DeleteAllResultsAsyncTask

    // THIS NEED TO BE REVIEWED
    private static class GetAllLocationsAsyncTask extends AsyncTask<Void, Void, List<Location>> {
        private AppDao appDao;

        public GetAllLocationsAsyncTask(AppDao appDao) {
            this.appDao = appDao;
        }

        @Override
        protected List<Location> doInBackground(Void... voids) {
            return appDao.getAllLocations();
        }
    } // end GetAllLocationsAsyncTask

    private static class GetResultAsyncTask extends AsyncTask<Void, Void, Result> {
        private AppDao appDao;
        private String current;
        private String destination;
        private int method;

        public GetResultAsyncTask(AppDao appDao, String current, String destination, int method) {
            this.appDao = appDao;
            this.current = current;
            this.destination = destination;
            this.method = method;
        }

        @Override
        protected Result doInBackground(Void... voids) {
            return appDao.getResult(current, destination, method);
        }

        @Override
        protected void onPostExecute(Result result) {
            super.onPostExecute(result);
        }
    } // end GetResultAsyncTask

}
