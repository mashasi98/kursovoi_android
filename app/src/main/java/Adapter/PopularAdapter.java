package Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.zatsepicoffee_v1.R;

import java.util.List;

import Activity.ItemDescriptionActivity;
import BaseClases.ItemsClass;

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
        holder.price.setText(String.valueOf(itemsClasses.get(position).getPrice())+"P");
    }

    @Override
    public int getItemCount() {
        return itemsClasses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageButton imageButton;
        TextView title,price;
        LinearLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageButton=itemView.findViewById(R.id.popular_btns);
            title=itemView.findViewById(R.id.popular_txts);
            price=itemView.findViewById(R.id.popular_txtPrise);
            layout=itemView.findViewById(R.id.layoutPopularLay);
            layout.setOnClickListener(this);
            imageButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Log.d("TAG", String.valueOf(position));
            Log.d("TAG", itemsClasses.get(position).getId());
            Intent intent = new Intent(context, ItemDescriptionActivity.class);
            intent.putExtra("itemAvaliable", itemsClasses.get(position).isAvaliable());
            intent.putExtra("categoryId", itemsClasses.get(position).getCategory());

            intent.putExtra("itemDescription", itemsClasses.get(position).getDiscription());
            intent.putExtra("itemId", itemsClasses.get(position).getId());
            intent.putExtra("itemImage", itemsClasses.get(position).getImagePath());
            intent.putExtra("itemPop", itemsClasses.get(position).getPopular());
            intent.putExtra("itemPrice", itemsClasses.get(position).getPrice());
            intent.putExtra("itemSize", itemsClasses.get(position).getSize());
            intent.putExtra("itemTitle", itemsClasses.get(position).getTitle());

            context.startActivity(intent);
        }
    }
}
