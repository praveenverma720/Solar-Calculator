package com.praveen.gooded.modals;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.praveen.gooded.interfaces.PinsDao;
import com.praveen.gooded.local_database.PinsDatabase;

import java.util.List;

public class PinsRepository {
    private PinsDao pinsDao;
    private LiveData<List<Pins>> allPins;

    PinsRepository(Application application) {
        PinsDatabase db = PinsDatabase.getDatabase(application);
        pinsDao = db.pinsDao();
        allPins = pinsDao.getAllPins();
    }

    LiveData<List<Pins>> getAllWords() {
        return allPins;
    }

    public void insert (Pins word) {
        new insertAsyncTask(pinsDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<Pins, Void, Void> {

        private PinsDao mAsyncTaskDao;

        insertAsyncTask(PinsDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Pins... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}