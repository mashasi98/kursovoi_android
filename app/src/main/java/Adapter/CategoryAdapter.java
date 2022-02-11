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

import Activity.MenuActivityItems;
import BaseClases.CategoryClass;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    List<CategoryClass> categoryClasses;
    Context context;


    public CategoryAdapter() {
    }


    public CategoryAdapter(List<CategoryClass> categoryClasses, Context context) {
        this.categoryClasses = categoryClasses;
        this.context = context;
    }
    // зависимости от того,к чему принадлежит row_item дописать еще одно поле

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item_category, parent, false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String url = categoryClasses.get(position).getImagePath();
        Glide
                .with(context)
                .load(url)
                .centerCrop()
                .into(holder.imageButton);
        holder.title.setText(categoryClasses.get(position).getTitle());


    }


    @Override
    public int getItemCount() {
        return categoryClasses.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener  {
        ImageButton imageButton;
        TextView title;
        LinearLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageButton = itemView.findViewById(R.id.popular_btns_pop);
            title = itemView.findViewById(R.id.popular_txts_pop);
            layout=itemView.findViewById(R.id.layoutCategory);
            layout.setOnClickListener(this);
            imageButton.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Log.d("TAG", String.valueOf(position));
            Log.d("TAG", categoryClasses.get(position).getId());
            Intent intent = new Intent(context, MenuActivityItems.class);
            intent.putExtra("categoryId", categoryClasses.get(position).getId());
            intent.putExtra("categoryTitle", categoryClasses.get(position).getTitle());
            context.startActivity(intent);

        }
    }
}

