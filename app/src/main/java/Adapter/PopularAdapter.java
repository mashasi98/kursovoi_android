package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.zatsepicoffee_v1.R;

import java.util.ArrayList;
import java.util.List;

import BaseClases.ItemsClass;
import BaseClases.MainModels;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {
    List<ItemsClass> itemsClasses;
    Context context;

    public PopularAdapter() {
    }

    public PopularAdapter(List<ItemsClass> itemsClasses, Context context) {
        this.itemsClasses = itemsClasses;
        this.context = context;

    }

    // зависимости от того,к чему принадлежит row_item дописать еще одно поле

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item_popular,parent,false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String url=itemsClasses.get(position).getImagePath();
        Glide
                .with(context)
                .load(url)
                .centerCrop()
                .into(holder.imageButton);
        holder.title.setText(itemsClasses.get(position).getTitle());
//        holder.price.setText(itemsClasses.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return itemsClasses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageButton imageButton;
        TextView title,price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageButton=itemView.findViewById(R.id.popular_btns);
            title=itemView.findViewById(R.id.popular_txts);
            price=itemView.findViewById(R.id.popular_txtPrise);
        }
    }
}
