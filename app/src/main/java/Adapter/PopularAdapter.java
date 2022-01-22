package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zatsepicoffee_v1.R;

import java.util.ArrayList;

import BaseClases.MainModels;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {
    ArrayList<MainModels> mainModels;
    Context context;
    int row;

    public PopularAdapter(ArrayList<MainModels> mainModels, Context context,int row) {
        this.mainModels = mainModels;
        this.context = context;
        this.row=row;
    }

    // зависимости от того,к чему принадлежит row_item дописать еще одно поле

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(row,parent,false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.imageButton.setImageResource(mainModels.get(position).getPicture());
//            holder.textView.setText(mainModels.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return mainModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageButton imageButton;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageButton=itemView.findViewById(R.id.popular_btns);
            textView=itemView.findViewById(R.id.popular_txts);
        }
    }
}
