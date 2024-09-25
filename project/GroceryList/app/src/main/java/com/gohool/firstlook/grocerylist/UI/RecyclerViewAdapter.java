package com.gohool.firstlook.grocerylist.UI;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gohool.firstlook.grocerylist.Activities.DetailsActivity;
import com.gohool.firstlook.grocerylist.Data.DatabaseHandle;
import com.gohool.firstlook.grocerylist.Model.Grocery;
import com.gohool.firstlook.grocerylist.R;
import com.google.android.material.snackbar.Snackbar;

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

    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog dialog;
    private LayoutInflater inflater;

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
                    // Go to next screen => DetailsActivity
                    int position = getAdapterPosition();
                    Grocery grocery = groceryItems.get(position);
                    Intent intent = new Intent(context, DetailsActivity.class);
                    /*
                        Intent là một đối tượng đóng vai trò như một "sứ giả" để thực hiện các hành động và trao đổi
                        thông tin giữa các thành phần khác nhau của ứng dụng, hoặc giữa các ứng dụng với nhau.

                        Các chức năng chính: khởi chạy activity, khởi chạy service, gửi broadcast, ...
                    */

                    intent.putExtra("name", grocery.getName());
                    intent.putExtra("quantity", grocery.getQuantity());
                    intent.putExtra("id", grocery.getId());
                    intent.putExtra("date", grocery.getDateItemAdded());
                    context.startActivity(intent); // có thể sử dụng startActivity để chuyển sang màn hình khác
                                                   // mà không cần sử dụng context nếu RecyclerViewAdapter là một
                                                   // "activity" nhưng trong trường hợp này thì nó là 1 "class" thông thường
                                                   // cho nên cần sử dụng context.
                }
            });
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Grocery grocery = groceryItems.get(position);

            if (v.getId() == R.id.editButton) {
                editItem(grocery);
            }

            if (v.getId() == R.id.deleteButton) {
                deleteItem(grocery.getId());
            }
        }

        public void deleteItem(final int id)
                               // final same const: can't be changed after initialization value
        {
            // create an AlertDialog
            alertDialogBuilder = new AlertDialog.Builder(context);

            inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.confirmation_dialog, null);

            Button noButton = (Button) view.findViewById(R.id.noButton);
            Button yesButton = (Button) view.findViewById(R.id.yesButton);

            alertDialogBuilder.setView(view);
            dialog = alertDialogBuilder.create();
            dialog.show();

            noButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            yesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // delete the item
                    DatabaseHandle db = new DatabaseHandle(context);
                    db.deleteGrocery(id);
                    groceryItems.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());

                    dialog.dismiss();

                }
            });

        }

        public void editItem(final Grocery grocery)
        {
            alertDialogBuilder = new AlertDialog.Builder(context);

            inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.popup, null);

            final EditText groceryItem = (EditText) view.findViewById(R.id.groceryItem);
            final EditText quantity = (EditText) view.findViewById(R.id.groceryQty);
            final TextView title = (TextView) view.findViewById(R.id.title);

            title.setText("Edit Grocery");
            Button saveButton = (Button) view.findViewById(R.id.saveButton);

            alertDialogBuilder.setView(view);
            dialog = alertDialogBuilder.create();
            dialog.show();

            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseHandle db = new DatabaseHandle(context);

                    // Update item
                    grocery.setName(groceryItem.getText().toString());
                    grocery.setQuantity(quantity.getText().toString());

                    if (!groceryItem.getText().toString().isEmpty() &&
                        !quantity.getText().toString().isEmpty()) {
                        db.updateGrocery(grocery);
                        notifyItemChanged(getAdapterPosition(), grocery);
                    }
                    else {
                        Snackbar.make(view, "Add Grocery and Quantity", Snackbar.LENGTH_LONG).show();
                    }

                    dialog.dismiss();
                }
            });
        }

    }
}
