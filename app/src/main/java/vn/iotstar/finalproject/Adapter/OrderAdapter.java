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

import vn.iotstar.finalproject.Model.BaiHoc;
import vn.iotstar.finalproject.Model.DonHang;
import vn.iotstar.finalproject.Model.Feedback;
import vn.iotstar.finalproject.Model.GiaoDich;
import vn.iotstar.finalproject.Model.KhoaHoc;
import vn.iotstar.finalproject.PageActivity.MainActivity;
import vn.iotstar.finalproject.R;
import vn.iotstar.finalproject.Storage.NoticeRecord;
import vn.iotstar.finalproject.databinding.BillItemLayoutBinding;

import vn.iotstar.finalproject.databinding.SignupLayoutBinding;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> {
    Context context;
    List<DonHang> array;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    public OrderAdapter(Context context, List<DonHang> array)
    {
        this.context=context;
        this.array=array;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item_layout, null);
        MyViewHolder myViewHolder= new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DonHang donHang = array.get(position);
        holder.maDonHang.setText("Mã đơn hàng: "+donHang.getMaDonHang());
        holder.sotien.setText(donHang.getTongSoTien() + " đ");
        holder.thoigian.setText("Thời gian đặt: "+donHang.getNgayTao());
        holder.maHocVien.setText("Học viên: "+ donHang.getHocVien());
        holder.btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.getInstance().goToOrderDetail(donHang);


            }
        });
    }

    public void removeItem(DonHang item)
    {
        int index = array.indexOf(item);
        if(index<0)
            return;
        array.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return array==null? 0 :array.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView sotien, thoigian, maHocVien, maDonHang, btnView;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            maDonHang=(TextView)itemView.findViewById(R.id.maHD);
            sotien=(TextView) itemView.findViewById(R.id.tienGD);
            thoigian=(TextView) itemView.findViewById(R.id.tgianGD);
            maHocVien= (TextView)itemView.findViewById(R.id.maHocVien);
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
