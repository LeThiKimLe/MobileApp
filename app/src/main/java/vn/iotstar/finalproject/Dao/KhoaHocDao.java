package vn.iotstar.finalproject.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import vn.iotstar.finalproject.Model.KhoaHoc;

@Dao
public interface KhoaHocDao {
    @Query("SELECT * FROM MyCourse")
    List<KhoaHoc> getAll();

    @Query("SELECT * FROM MyCourse WHERE maKhoaHoc = (:Ids)")
    List<KhoaHoc> loadAllByIds(String[] Ids);

    @Insert
    void insertAll(KhoaHoc... course);

    @Update
    void update(KhoaHoc... cartItems);

    @Delete
    void delete(KhoaHoc cartItems);

}
