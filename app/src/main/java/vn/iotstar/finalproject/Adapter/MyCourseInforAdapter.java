package vn.iotstar.finalproject.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

import vn.iotstar.finalproject.Model.BaiHoc;
import vn.iotstar.finalproject.Model.KhoaHoc;
import vn.iotstar.finalproject.PageActivity.MainActivity;
import vn.iotstar.finalproject.PageActivity.MyLessionActivity;
import vn.iotstar.finalproject.R;
import vn.iotstar.finalproject.Storage.SharedPrefManager;

public class MyCourseInforAdapter extends RecyclerView.Adapter<MyCourseInforAdapter.MyViewHolder> {
    Context context;
    List<BaiHoc> array;

    boolean isHocVien;

    public BaiHoc current_baihoc;

    public int current_position;
    public MyCourseInforAdapter(Context context, List<BaiHoc> array, boolean isHocVien) {
        this.context = context;
        this.array = array;
        this.isHocVien= isHocVien;
    }

    @NonNull
    @Override
    public MyCourseInforAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.lesson_item_layout, null);
        MyCourseInforAdapter.MyViewHolder myViewHolder= new MyCourseInforAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyCourseInforAdapter.MyViewHolder holder, int position) {
        BaiHoc product= array.get(position);
        holder.tenbh.setText(product.getTenBaiHoc());
        holder.lessionid.setText(product.getMaBaiHoc());
        holder.lessonDesc.setText(product.getMoTaNoiDung());
        if (!this.isHocVien)
            holder.showContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MainActivity.getInstance().addTabHost(product.getTenBaiHoc(),product.getMaBaiHoc());
                }
            });

        if (!this.isHocVien)
            holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(MyLessionActivity.getInstance());
                    alert.setTitle("Xác nhận xóa");
                    alert.setMessage("Nếu xóa bài học bạn sẽ mất các tài nguyên trong bài học này !");
                    alert.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            removeItem(product);
                            MyLessionActivity.getInstance().setLesson("delete", product);
                            Toast.makeText(context, "Đã xóa bài học thành công", Toast.LENGTH_SHORT).show();
                        }
                    });
                    alert.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    alert.show();
                }
            });
        else
            holder.deleteBtn.setVisibility(View.INVISIBLE);
    }

    public void addItem(BaiHoc item)
    {
        array.add(item);
        notifyItemInserted(array.size()-1);
        Toast.makeText(context, "Đã thêm bài học thành công", Toast.LENGTH_SHORT).show();
        current_baihoc=item;
    }

    public void removeItem(BaiHoc item)
    {
        int index = array.indexOf(item);
        if(index<0)
            return;
        array.remove(index);
        notifyItemRemoved(index);
    }

    public void replaceItem(int position, BaiHoc item)
    {
        array.remove(position);
        array.add(position, item);
        notifyItemChanged(position);
        Toast.makeText(context, "Đã sửa thành công", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        return array==null? 0 :array.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView tenbh;

        public TextView lessionid, lessonDesc;

        public TextView showContent, deleteBtn;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);

            tenbh= (TextView)itemView.findViewById(R.id.textView_tenbaihoc);
            lessionid =(TextView)itemView.findViewById(R.id.id_lession);
            lessonDesc = (TextView)itemView.findViewById(R.id.lessonDesp);
            showContent = (TextView)itemView.findViewById(R.id.openBtn);
            deleteBtn = (TextView) itemView.findViewById(R.id.btnDelete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    current_position= getAdapterPosition();
                    current_baihoc = array.get(current_position);
                    MyLessionActivity.getInstance().showDescript(current_baihoc);
                }
            });
        }
    }
}
