package com.example.user.greendao;

import android.app.Application;

import com.example.user.greendao.db.DaoMaster;
import com.example.user.greendao.db.DaoSession;

import org.greenrobot.greendao.database.Database;

public class UserApp extends Application {


    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper devOpenHelper= new DaoMaster.DevOpenHelper(this, "USER");
        Database database = devOpenHelper.getWritableDb();
        daoSession = new DaoMaster(database).newSession();
    }

    public DaoSession getDaoSession() {

        return daoSession;
    }
}
