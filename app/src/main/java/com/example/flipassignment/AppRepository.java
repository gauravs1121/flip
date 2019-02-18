package com.example.flipassignment;

import android.app.Application;
import android.os.AsyncTask;

import com.example.flipassignment.database.AppDatabase;
import com.example.flipassignment.database.dao.CoinDao;
import com.example.flipassignment.database.dao.EventDao;
import com.example.flipassignment.database.dao.WatcherDao;
import com.example.flipassignment.database.entity.CoinEntity;
import com.example.flipassignment.database.entity.EventEntity;
import com.example.flipassignment.database.entity.WatcherEntity;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class AppRepository {
    private CoinDao mCoinDao;
    private WatcherDao mWatcherDao;
    private EventDao mEventDao;

//    private List<CoinEntity> mAllCoins;
//    private static AppRepository sInstance;

//    public AppRepository(AppDatabase database) {
//
//    }
//
//
//    public static AppRepository getInstance(final AppDatabase database) {
//        if (sInstance == null) {
//            synchronized (AppRepository.class) {
//                if (sInstance == null) {
//                    sInstance = new AppRepository(database);
//                }
//            }
//        }
//        return sInstance;
//    }

    public AppRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mCoinDao = db.coinDao();
        mWatcherDao = db.watcherDao();
        mEventDao = db.eventDao();
    }

    public List<CoinEntity> getmAllCoins() {
//        mCoinDao.getAllCoins();
//        return mAllCoins;
        try {
            return new GetAllCoinData().execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }


    }

    public void insert(List<CoinEntity> CoinEntitys) {
        new insertCoinAsyncTask(mCoinDao).execute(CoinEntitys);
    }

    public void deleteCoin() {
        new DeleteCoinAsyncTask().execute();
    }

    public void insert(WatcherEntity watcherEntity) {
        new InsertWatcherAsyncTask(mWatcherDao).execute(watcherEntity);
    }

    public List<String> geWatcherCoinIds() {
        try {
            return new GetWatcherCoinIdList().execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }


    }

    public List<WatcherEntity> geWatcherCoinData() {
//        mCoinDao.getAllCoins();
//        return mAllCoins;
        try {
            return new GetWatcherCoinData().execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }


    }

    public List<EventEntity> getAllEvents() {
//        mCoinDao.getAllCoins();
//        return mAllCoins;
        try {
            return new GetAllEventData().execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }


    }

    public void insertEvent(List<EventEntity> CoinEntitys) {
        new inserteventAsyncTask(mEventDao).execute(CoinEntitys);
    }

    public void deleteEvent() {
        new DeleteEventAsyncTask().execute();
    }


    private static class insertCoinAsyncTask extends AsyncTask<List<CoinEntity>, Void, Void> {

        private CoinDao mAsyncTaskDao;

        insertCoinAsyncTask(CoinDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final List<CoinEntity>... params) {
            mAsyncTaskDao.insertCoin(params[0]);
            return null;
        }
    }


    private class GetAllCoinData extends AsyncTask<Void, Void,List<CoinEntity>> {
        @Override
        protected List<CoinEntity> doInBackground(Void... url) {
            return mCoinDao.getAllCoins();
        }
    }

    private class DeleteCoinAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... url) {
            mCoinDao.deleteAll();
            return null;
        }

    }

    private static class InsertWatcherAsyncTask extends AsyncTask<WatcherEntity, Void, Void> {

        private WatcherDao mAsyncTaskDao;

        InsertWatcherAsyncTask(WatcherDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final WatcherEntity... params) {
            mAsyncTaskDao.insertWatcher(params[0]);
            return null;
        }
    }

    private class GetWatcherCoinIdList extends AsyncTask<Void, Void,List<String>> {
        @Override
        protected List<String> doInBackground(Void... url) {
            return mWatcherDao.getAllWatcher();
        }
    }

    private class GetWatcherCoinData extends AsyncTask<Void, Void,List<WatcherEntity>> {
        @Override
        protected List<WatcherEntity> doInBackground(Void... url) {
            return mWatcherDao.getAllWatcherData();
        }
    }

    private static class inserteventAsyncTask extends AsyncTask<List<EventEntity>, Void, Void> {

        private EventDao mAsyncTaskDao;

        inserteventAsyncTask(EventDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final List<EventEntity>... params) {
            mAsyncTaskDao.insertEvent(params[0]);
            return null;
        }
    }


    private class GetAllEventData extends AsyncTask<Void, Void,List<EventEntity>> {
        @Override
        protected List<EventEntity> doInBackground(Void... url) {
            return mEventDao.getAllEvents();
        }
    }

    private class DeleteEventAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... url) {
            mEventDao.deleteAllEvent();
            return null;
        }

    }
}
