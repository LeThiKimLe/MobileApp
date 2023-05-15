package vn.iotstar.finalproject.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import vn.iotstar.finalproject.Model.KhoaHoc;
import vn.iotstar.finalproject.Storage.CartItem;

@Dao
public interface CartDao {
    @Query("SELECT * FROM MyCourse where maHocVien= (:userId)")
    List<CartItem> getAll(String userId);

    @Query("SELECT * FROM MyCourse WHERE maKhoaHoc = (:Ids) and maHocVien= (:userId)")
    List<CartItem> checkCourse(String Ids, String userId);


    @Insert
    void insertAll(CartItem... cartItems);

    @Update
    void update(CartItem... cartItems);

    @Delete
    void delete(CartItem cartItems);

}
