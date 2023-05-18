package vn.iotstar.finalproject.Storage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import vn.iotstar.finalproject.Model.GiaoVien;
import vn.iotstar.finalproject.Model.HocVien;
import vn.iotstar.finalproject.Model.QuanTriVien;
import vn.iotstar.finalproject.PageActivity.LoginActivity;

public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "HocVienregisterlogin";
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    private static final String KEY_NAME = "keyname";
    private static final String KEY_IMAGE = "keyimage";
    private static final String KEY_USERNAME = "keyusername";

    private static final String KEY_EMAIL = "keyemail";
    private static final String KEY_SDT = "keysdt";
    private static final String KEY_DATE = "keydate";

    private static final String KEY_ID = "keyid";

    private static final String KEY_CCCD = "keycccd";

    private static final String KEY_COOPDATE = "keycoop";

    private static final String KEY_ADDRESS ="keyaddr";

    private static final String KEY_PHANMON ="keyPhanMon";

    private static final String ROLE = "role";

    private static SharedPrefManager mInstance;
    private static Context ctx;

    private SharedPrefManager(Context context)
    {
        ctx=context;

    }

    public static synchronized SharedPrefManager getInstance(Context context)
    {
        if (mInstance==null)
        {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public void userLogin(HocVien hv)
    {
        Toast.makeText(ctx, "Đã lưu", Toast.LENGTH_SHORT).show();
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString(KEY_ID, hv.getMaHocVien());
        editor.putString(KEY_NAME, hv.getTenHocVien());
        editor.putString(KEY_EMAIL, hv.getEmail());
        editor.putString(KEY_IMAGE,hv.getImage());
        editor.putString(KEY_SDT, hv.getSdt());
        editor.putString(KEY_DATE, hv.getNgaySinh());
        editor.putString(ROLE, "HV");
        editor.apply();
    }

    public void userLogin(GiaoVien gv)
    {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString(ROLE, "GV");
        editor.putString(KEY_ID, gv.getMaGiaoVien());
        editor.putString(KEY_NAME, gv.getTenGiaoVien());
        editor.putString(KEY_SDT, gv.getSdt());
        editor.putString(KEY_CCCD, gv.getCccd());
        editor.putString(KEY_ADDRESS, gv.getDiaChi());
        editor.putString(KEY_EMAIL, gv.getEmail());
        editor.putString(KEY_COOPDATE,formatter.format(gv.getNgayKyKet()));
        Gson gson = new Gson();
        String json = gson.toJson(gv.getChuyen());
        editor.putString(KEY_PHANMON, json);
//        Cách đọc kiểu Serieslizable
//        Gson gson = new Gson();
//        String json = mPrefs.getString("SerializableObject", "");
//        yourSerializableObject = gson.fromJson(json, YourSerializableObject.class);
        editor.apply();
    }

    public void userLogin(QuanTriVien qtv)
    {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString(ROLE, "QTV");
        editor.putString(KEY_ID, qtv.getMaQtv());
        editor.putString(KEY_NAME, qtv.getHoTen());
        editor.putString(KEY_SDT, qtv.getSdt());
        editor.putString(KEY_EMAIL, qtv.getEmail());
        editor.putString(KEY_ADDRESS, qtv.getDiaChi());
        editor.putString(KEY_CCCD, qtv.getCccd());
        editor.apply();
    }


    public boolean isLoggedIn()
    {
        SharedPreferences sharedPreferences=ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(ROLE, null)!= null;
    }

    public HocVien getHocVien()
    {
        SharedPreferences sharedPreferences= ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            return new HocVien(
                    sharedPreferences.getString(KEY_ID, null),
                    sharedPreferences.getString(KEY_NAME, null),
                    sharedPreferences.getString(KEY_DATE, null),
                    sharedPreferences.getString(KEY_SDT, null),
                    sharedPreferences.getString(KEY_EMAIL, null),
                    sharedPreferences.getString(KEY_IMAGE, null)
            );
    }

    public QuanTriVien getQuanTriVien()
    {
        SharedPreferences sharedPreferences= ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new QuanTriVien( sharedPreferences.getString(KEY_ID, null),
        sharedPreferences.getString(KEY_NAME, null),
        sharedPreferences.getString(KEY_SDT, null),
        sharedPreferences.getString(KEY_EMAIL, null),
        sharedPreferences.getString(KEY_ADDRESS, null),
        sharedPreferences.getString(KEY_CCCD, null));

    }

    public GiaoVien getGiaoVien() throws ParseException {
        SharedPreferences sharedPreferences= ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new GiaoVien( sharedPreferences.getString(KEY_ID, null),
        sharedPreferences.getString(KEY_NAME, null),
        sharedPreferences.getString(KEY_SDT, null),
        sharedPreferences.getString(KEY_CCCD, null),
        sharedPreferences.getString(KEY_ADDRESS, null),
        sharedPreferences.getString(KEY_EMAIL, null),
        formatter.parse(sharedPreferences.getString(KEY_COOPDATE,null)).getTime(),
        sharedPreferences.getString(KEY_PHANMON, null));
    }

    public String getRole()
    {
        SharedPreferences sharedPreferences= ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(ROLE, null);
    }

    public String getUsername()
    {
        SharedPreferences sharedPreferences= ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_NAME, null);
    }

    public String getEmail()
    {
        SharedPreferences sharedPreferences= ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_EMAIL, null);
    }



    public void logout()
    {
        SharedPreferences sharedPreferences= ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.clear();
        editor.apply();
        ctx.startActivity(new Intent(ctx, LoginActivity.class));
    }

}
