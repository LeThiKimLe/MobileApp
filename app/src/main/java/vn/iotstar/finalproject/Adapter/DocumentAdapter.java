package vn.iotstar.finalproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import vn.iotstar.finalproject.Model.BaiHoc;
import vn.iotstar.finalproject.Model.KhoaHoc;
import vn.iotstar.finalproject.PageActivity.MainActivity;
import vn.iotstar.finalproject.R;

public class DocumentAdapter extends RecyclerView.Adapter<DocumentAdapter.MyViewHolder>{
    Context context;
    List<BaiHoc> array;


    public DocumentAdapter(Context context, List<BaiHoc> array)
    {
        this.context=context;
        this.array=array;
    }
    @NonNull
    @Override
    public DocumentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.document_item, null);
        DocumentAdapter.MyViewHolder myViewHolder= new DocumentAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DocumentAdapter.MyViewHolder holder, int position) {
        BaiHoc product= array.get(position);
        holder.tenbh.setText(product.getTenBaiHoc());
        holder.noidung.setText(product.getMoTaNoiDung());
    }


    @Override
    public int getItemCount() {
        return array==null? 0 :array.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView tenbh,noidung;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            tenbh=(TextView)itemView.findViewById(R.id.tv_nameDoc) ;
            noidung= (TextView)itemView.findViewById(R.id.tv_NDDoc);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Toast.makeText(context,"Bạn đã chọn Khóa học", Toast.LENGTH_SHORT).show();
                    MainActivity.getInstance().addTabHost((String) tenbh.getText());
                }
            });
        }
    }
}
