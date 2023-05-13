package vn.iotstar.finalproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import vn.iotstar.finalproject.Model.PhanMon;
import vn.iotstar.finalproject.R;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.MyViewHolder>{
    Context context;
    List<PhanMon> array;
    public SubjectAdapter(Context context, List<PhanMon> array)
    {
        this.context=context;
        this.array=array;
    }

    @NonNull
    @Override
    public SubjectAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.coursesubject_item_layout, null);
        SubjectAdapter.MyViewHolder myViewHolder= new SubjectAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectAdapter.MyViewHolder holder, int position) {
        PhanMon subject= array.get(position);
        holder.subId.setText(subject.getMaPhanMon());
        holder.subName.setText(subject.getTenPhanMon());
    }

    @Override
    public int getItemCount() {
        return array==null? 0 :array.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView subId, subName;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            subId = (TextView) itemView.findViewById(R.id.subjectId);
            subName = (TextView) itemView.findViewById(R.id.subjectName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"Bạn đã chọn product", Toast.LENGTH_SHORT).show();
//                    MenuActivity.getInstance().GetDetailProductInFrame(Integer.parseInt((String) idMeal.getText()));
                }
            });
        }
    }
}