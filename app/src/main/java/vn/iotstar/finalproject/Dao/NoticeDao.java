package vn.iotstar.finalproject.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
import vn.iotstar.finalproject.Storage.NoticeRecord;
@Dao
public interface NoticeDao {

    @Query("SELECT * FROM NoticeTable WHERE maHocVien = (:Ids)")
    List<NoticeRecord> loadAllByNotice(String Ids);

    @Insert
    void insertAll(NoticeRecord... notice);

    @Update
    void update(NoticeRecord... notice);

    @Delete
    void delete(NoticeRecord notice);

}
