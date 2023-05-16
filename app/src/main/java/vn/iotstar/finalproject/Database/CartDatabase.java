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
import vn.iotstar.finalproject.Model.KhoaHoc;
import vn.iotstar.finalproject.Storage.CartItem;

@Database(entities = {CartItem.class}, version = 2)
public abstract class CartDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "cart.db";
    private static CartDatabase instance;

    public static synchronized CartDatabase getInstance(Context context)
    {
        if (instance== null){
//            instance = Room.databaseBuilder(context.getApplicationContext(), CartDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            CartDatabase.class, DATABASE_NAME).allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract CartDao cartDao();

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
