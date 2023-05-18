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
import vn.iotstar.finalproject.Model.GiaoDich;
import vn.iotstar.finalproject.Model.KhoaHoc;
import vn.iotstar.finalproject.PageActivity.MainActivity;
import vn.iotstar.finalproject.R;
import vn.iotstar.finalproject.Storage.NoticeRecord;
import vn.iotstar.finalproject.databinding.BillItemLayoutBinding;

import vn.iotstar.finalproject.databinding.SignupLayoutBinding;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.MyViewHolder> {
    Context context;
    List<GiaoDich> array;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public TransactionAdapter(Context context, List<GiaoDich> array)
    {
        this.context=context;
        this.array=array;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.transac_item_layout, null);
        MyViewHolder myViewHolder= new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        GiaoDich giaoDich = array.get(position);
        holder.noidung.setText(giaoDich.getNoiDungGiaoDich());
        holder.sodu.setText("Số dư: "+ giaoDich.getSoDuCapNhat().intValue() + " đ");
        holder.thoigian.setText(formatter.format(giaoDich.getNgayGiaoDich()));
        holder.maGD.setText("Mã GD: "+giaoDich.getMaGiaoDich());
        holder.sotien.setText("-"+ giaoDich.getSoTienGiaoDich().intValue()+" đ");
        holder.btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.getInstance().goToBillDetail((String) giaoDich.getMaGiaoDich());
            }
        });
    }

    @Override
    public int getItemCount() {
        return array==null? 0 :array.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView noidung, sotien, thoigian, sodu,maGD, btnView;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            noidung=(TextView)itemView.findViewById(R.id.noidungND);
            sotien=(TextView) itemView.findViewById(R.id.tienGD);
            thoigian=(TextView) itemView.findViewById(R.id.tgianGD);
            sodu= (TextView)itemView.findViewById(R.id.soduCapNhat);
            maGD= (TextView)itemView.findViewById(R.id.maGD);
            btnView = (TextView)itemView.findViewById(R.id.btnViewBill);

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
