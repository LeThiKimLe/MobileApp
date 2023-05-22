package vn.iotstar.finalproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import vn.iotstar.finalproject.Model.KhoaHoc;
import vn.iotstar.finalproject.Model.PhanMon;
import vn.iotstar.finalproject.PageActivity.MainActivity;
import vn.iotstar.finalproject.R;

public class chuyenmonAdapter extends RecyclerView.Adapter<chuyenmonAdapter.MyViewHolder>{
        Context context;
        List<PhanMon> array;

    public chuyenmonAdapter(Context context, List<PhanMon> array) {
        this.context = context;
        this.array = array;
    }
    @NonNull
    @Override
    public chuyenmonAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {


        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chuyenmon, null);
        chuyenmonAdapter.MyViewHolder myViewHolder= new chuyenmonAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull chuyenmonAdapter.MyViewHolder holder, int position) {
        PhanMon product= array.get(position);
        holder.pm.setText(product.getTenPhanMon());
        holder.pm.setText(product.getMaPhanMon());

    }


    @Override
    public int getItemCount() {
        return array==null? 0 :array.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public CheckBox pm;
        public TextView mapm;



        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            pm = (CheckBox) itemView.findViewById(R.id.checkBox);
            mapm=(TextView) itemView.findViewById(R.id.mapm);
        }
    }
}




