package Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zatsepicoffee_v1.R;

public class Adapt extends RecyclerView.Adapter<Adapt.MyAdaptViewHolder> {


    @NonNull
    @Override
    public MyAdaptViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdaptViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyAdaptViewHolder extends RecyclerView.ViewHolder {
        ImageButton imageButton;
        TextView adress,contacts,time;

        public MyAdaptViewHolder(@NonNull View itemView) {
            super(itemView);
            imageButton=itemView.findViewById(R.id.caffe_btn_item);
            time=itemView.findViewById(R.id.caffe_text3);
            adress=itemView.findViewById(R.id.caffe_txt2_item);
            contacts=itemView.findViewById(R.id.caffe_text4);
        }
    }
}
