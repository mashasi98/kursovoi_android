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

import BaseClases.CaffeClass;
import BaseClases.MainModels;

public class CaffeAdapter extends RecyclerView.Adapter<CaffeAdapter.ViewHolder> {
    List<CaffeClass> caffeClasses;
    Context context;


    public CaffeAdapter(List<CaffeClass> caffeClasses, Context context) {
        this.caffeClasses = caffeClasses;
        this.context = context;

    }

    // зависимости от того,к чему принадлежит row_item дописать еще одно поле

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item_cafee,parent,false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Glide.with(context)
                    .load(caffeClasses.get(position).getImagePath())
                    .into(holder.imageButton);

            holder.adress.setText(caffeClasses.get(position).getAdress());
            holder.time.setText(caffeClasses.get(position).getTime());
            holder.contacts.setText(caffeClasses.get(position).getContacts());
    }

    @Override
    public int getItemCount() {
        return caffeClasses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageButton imageButton;
        TextView adress,contacts,time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageButton=itemView.findViewById(R.id.caffe_btn_item);
            time=itemView.findViewById(R.id.caffe_text3);
            adress=itemView.findViewById(R.id.caffe_txt2_item);
            contacts=itemView.findViewById(R.id.caffe_text4);
        }
    }
}
