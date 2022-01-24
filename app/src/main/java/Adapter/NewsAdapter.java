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

import BaseClases.NewsClass;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    List<NewsClass> newsClasses;
    Context context;


    public NewsAdapter(List<NewsClass> newsClasses, Context context) {
        this.newsClasses = newsClasses;
        this.context = context;

    }

    // зависимости от того,к чему принадлежит row_item дописать еще одно поле

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item_news,parent,false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String url = newsClasses.get(position).getImagePath();
        Glide
                .with(context)
                .load(url)
                .centerCrop()
                .into(holder.imageButton);
        holder.title.setText(newsClasses.get(position).getTitle());
        holder.description.setText(newsClasses.get(position).getDiscription());


    }

    @Override
    public int getItemCount() {
        return newsClasses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageButton imageButton;
        TextView title,description;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageButton=itemView.findViewById(R.id.news_btns_item);
            title=itemView.findViewById(R.id.news_text1_item);
            description=itemView.findViewById(R.id.news_text2_item);

        }
    }
}
