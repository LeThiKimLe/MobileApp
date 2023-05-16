package vn.iotstar.finalproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import vn.iotstar.finalproject.Model.KhoiLop;
import vn.iotstar.finalproject.R;

public class SpinnerAdapter extends BaseAdapter{
    private Context context;
    private List<KhoiLop> khoilop;
    LayoutInflater inflter;


    public SpinnerAdapter(Context context, List<KhoiLop> khoilop) {
        this.context = context;
        this.khoilop = khoilop;

        inflter = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return khoilop.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View rootview=inflter.inflate(R.layout.rowpinner_layout,null);
        TextView tenkhoi=rootview.findViewById(R.id.tenkhoi);

        tenkhoi.setText(khoilop.get(i).getTenKhoi());

        return  rootview;





    }
}
