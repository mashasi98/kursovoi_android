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

import java.util.List;

import BaseClases.ItemsClass;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    List<ItemsClass> itemsClasses;
    Context context;

    public ItemAdapter() {
    }

    public ItemAdapter(List<ItemsClass> itemsClasses, Context context) {
        this.itemsClasses = itemsClasses;
        this.context = context;

    }

    // зависимости от того,к чему принадлежит row_item дописать еще одно поле

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item_tabel,parent,false);

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
        holder.price.setText(String.valueOf(itemsClasses.get(position).getPrice())+"P");
        holder.description.setText(itemsClasses.get(position).getDiscription());

    }

    @Override
    public int getItemCount() {
        return itemsClasses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageButton imageButton;
        TextView title,price,description;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageButton=itemView.findViewById(R.id.item_image_btn);
            title=itemView.findViewById(R.id.title_item_btn);
            price=itemView.findViewById(R.id.price_item_txt);
            description=itemView.findViewById(R.id.descrip_item_txt);
        }
    }
}