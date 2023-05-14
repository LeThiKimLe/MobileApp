package vn.iotstar.finalproject.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "ThongBao")
public class ThongBao implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String content;
    private Date date_create;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate_create() {
        return date_create;
    }

    public void setDate_create(Date date_create) {
        this.date_create = date_create;
    }

    public ThongBao(int id, String title, String content, Date date_create) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date_create = date_create;
    }
}
