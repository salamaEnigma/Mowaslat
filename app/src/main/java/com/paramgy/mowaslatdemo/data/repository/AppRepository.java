package com.paramgy.mowaslatdemo.data.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.paramgy.mowaslatdemo.data.model.Location;
import com.paramgy.mowaslatdemo.data.model.Result;
import com.paramgy.mowaslatdemo.data.room.AppDao;
import com.paramgy.mowaslatdemo.data.room.AppDatabase;

import java.util.List;

import androidx.lifecycle.LiveData;

public class AppRepository {
    private AppDao appDAO;

    /*
    Singleton pattern
     */
    private static AppRepository appRepository;

    private AppRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        appDAO = database.appDAO();
    }

    public static synchronized AppRepository getInstance(Application application) {
        if (appRepository == null) {
            appRepository = new AppRepository(application);
        }
        return appRepository;
    }

    //***************** Operations ********************//
    public LiveData<List<Location>> getAllLocations() {
        return appDAO.getAllLocations();
    }

    public void deleteAllLocation() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                appDAO.deleteAllLocations();
            }
        });
    }

    public void deleteAllResults() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                appDAO.deleteAllResults();
            }
        });
    }

    public void updateResult(final Result result) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                appDAO.updateResult(result);
            }
        });
    }

    // THIS NEED TO BE REVIEWED
    // WE HAVE TO MAKE THE USER WAIT UNTIL THE RESULT IS FETCHED CORRECTLY (progress bar maybe with a timer)
    public Result getResult(String current, String destination, int method) {
        try {
            return new GetResultAsyncTask(appDAO, current, destination, method).execute().get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //***************** Database Background Operations *******************//

    // THIS NEED TO BE REVIEWED
    private static class GetResultAsyncTask extends AsyncTask<Void, Void, Result> {
        private AppDao appDao;
        private String current;
        private String destination;
        private int method;

        private GetResultAsyncTask(AppDao appDao, String current, String destination, int method) {
            this.appDao = appDao;
            this.current = current;
            this.destination = destination;
            this.method = method;
        }

        @Override
        protected Result doInBackground(Void... voids) {
            return appDao.getResult(current, destination, method);
        }
    } // end GetResultAsyncTask

}// end AppRepository Class
