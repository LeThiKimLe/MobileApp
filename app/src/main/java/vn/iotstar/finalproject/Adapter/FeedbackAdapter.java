package vn.iotstar.finalproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.List;

import vn.iotstar.finalproject.Model.Feedback;
import vn.iotstar.finalproject.Model.KhoaHoc;
import vn.iotstar.finalproject.PageActivity.MainActivity;
import vn.iotstar.finalproject.R;
import vn.iotstar.finalproject.Storage.NoticeRecord;
import vn.iotstar.finalproject.databinding.BillItemLayoutBinding;

import vn.iotstar.finalproject.databinding.SignupLayoutBinding;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.MyViewHolder> {
    Context context;
    List<Feedback> array;

    public FeedbackAdapter(Context context, List<Feedback> array)
    {
        this.context=context;
        this.array=array;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.feedback_item_layout, null);
        MyViewHolder myViewHolder= new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Feedback feedback= array.get(position);
        holder.name.setText(feedback.getTenNguoiDungFeedback());
        holder.rate.setRating(Integer.parseInt(feedback.getStarRate()));
        holder.msg.setText(feedback.getComment());
    }


    @Override
    public int getItemCount() {
        return array==null? 0 :array.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView name, msg;
        public RatingBar rate;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.feedName);
            rate=(RatingBar) itemView.findViewById(R.id.feedRate);
            msg=(TextView)itemView.findViewById(R.id.feedMsg);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Toast.makeText(context,"Bạn đã chọn product", Toast.LENGTH_SHORT).show();
//                    MainActivity.getInstance().goToBillDetail((String) maHoaDon.getText());
                }
            });
        }
    }
}
