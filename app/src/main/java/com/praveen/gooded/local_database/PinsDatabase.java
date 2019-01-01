package com.praveen.gooded.local_database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.praveen.gooded.interfaces.PinsDao;
import com.praveen.gooded.modals.Pins;

@Database(entities = {Pins.class}, version = 1)
public abstract class PinsDatabase extends RoomDatabase {
    private static volatile PinsDatabase INSTANCE;
    public abstract PinsDao pinsDao();
    public static PinsDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PinsDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PinsDatabase.class, "pins_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static Callback sRoomDatabaseCallback =
            new Callback() {

                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final PinsDao mDao;

        PopulateDbAsync(PinsDatabase db) {
            mDao = db.pinsDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            return null;
        }
    }
}
