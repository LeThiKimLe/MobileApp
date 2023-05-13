package vn.iotstar.finalproject.Storage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import vn.iotstar.finalproject.Model.HocVien;
import vn.iotstar.finalproject.PageActivity.MainActivity;

public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "HocVienregisterlogin";

    private static final String KEY_NAME = "keyname";
    private static final String KEY_IMAGE = "keyimage";
    private static final String KEY_USERNAME = "keyusername";

    private static final String KEY_EMAIL = "keyemail";
    private static final String KEY_SDT = "keysdt";
    private static final String KEY_DATE = "keydate";

    private static final String KEY_ID = "keyid";

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

    public void hocvienLogin(HocVien hv)
    {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString(KEY_ID, hv.getMaHocVien());
        editor.putString(KEY_NAME, hv.getTenHocVien());
        editor.putString(KEY_EMAIL, hv.getEmail());
        editor.putString(KEY_IMAGE,hv.getImage());

        editor.putString(KEY_SDT, hv.getSdt());
        editor.putString(KEY_DATE, hv.getNgaySinh());
        editor.apply();
    }

    public boolean isLoggedIn()
    {
        SharedPreferences sharedPreferences=ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME, null)!= null;
    }

    public HocVien getHocVien()
    {
        SharedPreferences sharedPreferences= ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);



            return new HocVien(
                    sharedPreferences.getString(KEY_ID, null),

                    sharedPreferences.getString(KEY_DATE, null),
                    sharedPreferences.getString(KEY_SDT, null),
                    sharedPreferences.getString(KEY_EMAIL, null),
                    sharedPreferences.getString(KEY_IMAGE, null),
                    sharedPreferences.getString(KEY_NAME, null)
            );

    }

    public void logout()
    {
        SharedPreferences sharedPreferences= ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.clear();
        editor.apply();
        ctx.startActivity(new Intent(ctx, MainActivity.class));
    }

}
