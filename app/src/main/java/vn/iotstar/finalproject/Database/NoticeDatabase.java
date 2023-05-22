package vn.iotstar.finalproject.Database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import vn.iotstar.finalproject.Dao.CartDao;
import vn.iotstar.finalproject.Dao.NoticeDao;
import vn.iotstar.finalproject.Model.KhoaHoc;
import vn.iotstar.finalproject.Storage.CartItem;
import vn.iotstar.finalproject.Storage.NoticeRecord;

@Database(entities = {NoticeRecord.class}, version = 1, exportSchema = true)
public abstract class NoticeDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "notice.db";
    private static NoticeDatabase instance;

    public static synchronized NoticeDatabase getInstance(Context context)
    {
        if (instance== null){
//            instance = Room.databaseBuilder(context.getApplicationContext(), CartDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            NoticeDatabase.class, DATABASE_NAME).allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract NoticeDao noticeDao();

    @Override
    public void clearAllTables() {

    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(@NonNull DatabaseConfiguration databaseConfiguration) {
        return null;
    }
}
