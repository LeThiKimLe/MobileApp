package vn.iotstar.finalproject.Adapter;

import android.content.Context;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;


import vn.iotstar.finalproject.Model.HocVien;
import vn.iotstar.finalproject.databinding.ProfileFragmentBinding;

public class ProfileAdapter {
    private ProfileFragmentBinding binding;
    private Context context;
    private TableLayout tableLayout;
    private List<HocVien> dataList;

    public ProfileAdapter(Context context, TableLayout tableLayout, List<HocVien> dataList) {
        this.context = context;
        this.tableLayout = tableLayout;
        this.dataList = dataList;
    }

    public void updateData(List<HocVien> newDataList) {
        this.dataList = newDataList;
        refreshTable();
    }

    private void refreshTable() {
        // Xóa tất cả các hàng hiện có trong TableLayout
        tableLayout.removeView(binding.usernameBox);
        tableLayout.removeView(binding.emailBox);
        tableLayout.removeView(binding.sdtBox);
        tableLayout.removeView(binding.dateBox);

        // Tạo và thêm các hàng vào TableLayout
        for (HocVien rowData : dataList) {
            TableRow tableRow = new TableRow(context);

            // Tạo các ô dữ liệu
            TextView textView1 = new TextView(context);
            textView1.setText(rowData.getTenHocVien());
            TextView textView2 = new TextView(context);
            textView2.setText(rowData.getEmail());
            TextView textView3 = new TextView(context);
            textView2.setText(rowData.getNgaySinh());
            TextView textView4 = new TextView(context);
            textView2.setText(rowData.getSdt());


            // Thêm các ô vào hàng
            tableRow.addView(textView1);
            tableRow.addView(textView2);
            tableRow.addView(textView3);
            tableRow.addView(textView4);

            // Thêm hàng vào TableLayout
            tableLayout.addView(tableRow);
        }
    }
}
