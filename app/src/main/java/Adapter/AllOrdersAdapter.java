package Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zatsepicoffee_v1.R;

import java.util.List;

import Activity.AllOredersActivity;
import Activity.OrderDeatailActivity;
import BaseClases.OrderClass;

public class AllOrdersAdapter extends RecyclerView.Adapter<AllOrdersAdapter.ViewHolder> {
    List<OrderClass> orderClasses;
    Context context;

    public AllOrdersAdapter() {
    }

    public AllOrdersAdapter(List<OrderClass> orderClasses, Context context) {
        this.orderClasses = orderClasses;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.all_order_row,parent,false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.date.setText(orderClasses.get(position).getDate());
        holder.time.setText(orderClasses.get(position).getTime());
        holder.amount.setText(String.valueOf(orderClasses.get(position).getTotalAmount()+"p") );

    }

    @Override
    public int getItemCount() {
        return orderClasses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        ImageButton imageButton;
        private Boolean click=false;
        private LinearLayout layout;
        private TextView date,time,amount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            layout=itemView.findViewById(R.id.layoutAllorders);
            date=itemView.findViewById(R.id.allOrderDate);
            time=itemView.findViewById(R.id.allOrderTime);
            amount=itemView.findViewById(R.id.allOrderAmount);
            layout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d("Tac","click");
           








//            int position = getAdapterPosition();
//            Log.d("TAG", String.valueOf(position));
//            Log.d("TAG", itemsClasses.get(position).getId());
//            Intent intent = new Intent(context, ItemDescriptionActivity.class);
//            intent.putExtra("itemAvaliable", itemsClasses.get(position).isAvaliable());
//            intent.putExtra("categoryId", itemsClasses.get(position).getCategory());
//
//            intent.putExtra("itemDescription", itemsClasses.get(position).getDiscription());
//            intent.putExtra("itemId", itemsClasses.get(position).getId());
//            intent.putExtra("itemImage", itemsClasses.get(position).getImagePath());
//            intent.putExtra("itemPop", itemsClasses.get(position).getPopular());
//            intent.putExtra("itemPrice", itemsClasses.get(position).getPrice());
//            intent.putExtra("itemSize", itemsClasses.get(position).getSize());
//            intent.putExtra("itemTitle", itemsClasses.get(position).getTitle());
//
//            context.startActivity(intent);
        }
    }
}
