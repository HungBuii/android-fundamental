package com.gohool.firstlook.grocerylist.UI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gohool.firstlook.grocerylist.Model.Grocery;
import com.gohool.firstlook.grocerylist.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>
{
    /*
        RecyclerView.Adapter là lớp trừu tượng cung cấp các phương thức cần thiết để hiển thị dữ liệu trong RecyclerView.

        <RecyclerViewAdapter.ViewHolder>: Chỉ định rằng RecyclerViewAdapter sẽ sử dụng lớp ViewHolder được định nghĩa bên trong nó.

        ViewHolder là một lớp lưu trữ các tham chiếu đến các view trong mỗi item của RecyclerView.
    */

    private Context context;
    /*
        Context là một lớp cơ bản đại diện cho môi trường toàn cục của ứng dụng hoặc một
        thành phần cụ thể trong ứng dụng. Nó cung cấp cách thức để truy cập các tài nguyên
        hệ thống và ứng dụng, cũng như tương tác với các thành phần khác của ứng dụng.

        Context như một "cầu nối" giữa code và hệ thống Android.

        Các chức năng chính của Context: truy cập tài nguyên, khởi chạy activity,
                                         truy cập dịch vụ hệ thống (các dịch vụ hệ thống như quản lý mạng, vị trí, cảm biến, v.v),
                                         lưu trữ dữ liệu (Shared Preferences hoặc database)

        Cần sử dụng Context trong trường hợp: inflate layout (muốn tạo một View từ file layout XML),
                                              khởi chạy Activity, ...

    */

    private List<Grocery> groceryItems;

    public RecyclerViewAdapter(Context context, List<Grocery> groceryItems) {
        this.context = context;
        this.groceryItems = groceryItems;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    /*
        Phương thức này được gọi khi RecyclerView cần tạo một ViewHolder mới.
        ViewHolder là một lớp lưu trữ các tham chiếu đến các view trong mỗi item của RecyclerView.

        parent: ViewGroup chứa ViewHolder.
        viewType: Loại view của item (nếu có nhiều loại item khác nhau).

        Phương thức này cần trả về một ViewHolder mới.
     */

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, parent, false);
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
    /*
        Phương thức này được gọi để liên kết data với ViewHolder tại vị trí position.

        holder: ViewHolder được sử dụng để hiển thị dữ liệu.
        position: Vị trí của item trong danh sách.
    */

        Grocery grocery = groceryItems.get(position);
        holder.groceryItemName.setText(grocery.getName());
        holder.quantity.setText(grocery.getQuantity());
        holder.dateAdded.setText(grocery.getDateItemAdded());

    }

    @Override
    public int getItemCount() {
    /*
        Phương thức này trả về số lượng item trong danh sách.
     */

        return groceryItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Lớp ViewHolder kế thừa từ RecyclerView.ViewHolder

        public TextView groceryItemName;
        public TextView quantity;
        public TextView dateAdded;
        public Button editButton;
        public Button deleteButton;

        public int id;



        public ViewHolder(@NonNull View view, Context ctx)
        // Constructor của ViewHolder nhận một View (đại diện cho layout của một item)
        // và gọi constructor của lớp cha.

        {
            super(view);

            context = ctx;

            groceryItemName = (TextView) view.findViewById(R.id.name);
            quantity = (TextView) view.findViewById(R.id.quantity);
            dateAdded = (TextView) view.findViewById(R.id.dateAdded);
            editButton = (Button) view.findViewById(R.id.editButton);
            deleteButton = (Button) view.findViewById(R.id.deleteButton);

            editButton.setOnClickListener(this);
            deleteButton.setOnClickListener(this);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Go to next screen
                }
            });
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.editButton) {

            }

            if (v.getId() == R.id.deleteButton) {

            }
        }
    }
}
