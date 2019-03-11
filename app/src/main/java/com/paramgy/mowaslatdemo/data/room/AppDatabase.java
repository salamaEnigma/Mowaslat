package com.paramgy.mowaslatdemo.data.room;

import android.content.Context;
import android.os.AsyncTask;

import com.paramgy.mowaslatdemo.data.model.Location;
import com.paramgy.mowaslatdemo.data.model.Result;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Location.class, Result.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;
    public abstract AppDao appDAO();

    public static synchronized AppDatabase getInstance(Context context) {

        if (instance == null) {

            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "app_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomDbCallback).build();
        }
        return instance;
    }


    private static RoomDatabase.Callback roomDbCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{
        AppDao appDao;

        public PopulateDbAsyncTask(AppDatabase db) {
            this.appDao = db.appDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            appDao.insertLocation(new Location("فيكتوريا", 29.980958, 31.248910));
            appDao.insertLocation(new Location("المنشية", 29.893752, 31.199935));
            appDao.insertLocation(new Location("المندرة", 30.015959, 31.279985));

            /////////////////******* Adding Results ********////////////
            /////////////////////////////////////////////////////////////////////////
            appDao.insertResult(new Result("المنشية","المندرة",
                    0,"من ميدان الجندي المجهول اركب مشروع مندرة بحر، التكلفة 3.5 جنيه"));

            appDao.insertResult(new Result("المنشية","فيكتوريا",
                    0,"من ميدان الجندي المجهول اركب مشروع فيكتوريا، التكلفة 3.5 جنيه"));
            /////////////////////////////////////////////////////////////////////////
            appDao.insertResult(new Result("المندرة","فيكتوريا",
                    0,"بجوار نفق المندرة اركب مشروع فيكتوريا، التكلفة 2.5 جنيه"));

            appDao.insertResult(new Result("المندرة","المنشية",
                    0,"بجوار نفق المندرة اركب مشروع منشية بحر، التكلفة 3.5 جنيه"));
            /////////////////////////////////////////////////////////////////////////
            appDao.insertResult(new Result("فيكتوريا","المندرة",
                    0,"من ميدان فيكتوريا اركب مشروع مندرة جمال عبد الناصر، التكلفة 2.5 جنيه"));

            appDao.insertResult(new Result("فيكتوريا","المنشية",
                    0,"بجوار مدرسة فيكتوريا اركب مشروع منشية بحر، التكلفة 3.5 جنيه"));
            /////////////////////////////////////////////////////////////////////////


            return null;
        }
    }

}
