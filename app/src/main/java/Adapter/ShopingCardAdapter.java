package Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.zatsepicoffee_v1.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import Activity.ItemDescriptionActivity;
import Activity.ShopingCardActivity;
import BaseClases.Choice;
import BaseClases.ItemsClass;
import BaseClases.ShopingCardClass;

public class ShopingCardAdapter extends RecyclerView.Adapter<ShopingCardAdapter.ViewHolder> {

    List<ShopingCardClass> shopingCardClasses;
    Context context;
    private int amount;
    private String total;
    private  int position,totalAmount=0;
    private DocumentReference update;
    private FirebaseFirestore  db ;
    private FirebaseAuth mAuth;
    private String phone,path;
    private DocumentReference document;

    public ShopingCardAdapter() {
    }

    public ShopingCardAdapter(List<ShopingCardClass> shopingCardClasses, Context context) {
        this.shopingCardClasses = shopingCardClasses;
        this.context = context;

    }

    public Context getContext() {
        return context;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_row,parent,false);

        return new ViewHolder(view);


    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        int amountFromClass=shopingCardClasses.get(position).getAmount();

        String url=shopingCardClasses.get(position).getImagePath();
        Glide
                .with(context)
                .load(url)
                .centerCrop()
                .into(holder.imageView);
        holder.title.setText(shopingCardClasses.get(position).getTitle());
        holder.price.setText(String.valueOf(shopingCardClasses.get(position).getPrice()*amountFromClass)+"P");
        holder.size.setText(shopingCardClasses.get(position).getSize());
        holder.amountTxt.setText(Integer.toString(shopingCardClasses.get(position).getAmount()) );



    }


    @Override
    public int getItemCount() {
        return shopingCardClasses.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageButton imageButtonPlus,imageButtonMinus;
        ImageView imageView;
        TextView title,price,size,amountTxt;
//        TextView allPrice, totalPrise;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView =itemView.findViewById(R.id.card_ror_image);
            imageButtonPlus=itemView.findViewById(R.id.plus_amountCard_btn);
            imageButtonMinus=itemView.findViewById(R.id.minus_amountCard_btn);
            title=itemView.findViewById(R.id.card_ror_title_txt);
            price=itemView.findViewById(R.id.card_ror_price_txt);
            size=itemView.findViewById(R.id.card_ror_size_txt);
            amountTxt=itemView.findViewById(R.id.amountCard_count);


            db = FirebaseFirestore.getInstance();
            mAuth = FirebaseAuth.getInstance();
            phone=mAuth.getCurrentUser().getPhoneNumber();
            Log.d("PHONE",phone);

            imageButtonPlus.setOnClickListener(this);
            imageButtonMinus.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.plus_amountCard_btn:
                    changeAmount("+");
                    Log.d("TAG","onClik+"+amount);
                    break;
                case R.id.minus_amountCard_btn:
                    changeAmount("-");
                    Log.d("TAG","onClik-");
                    break;
            }
        }

        private void changeAmount(String choice){
            position= getAdapterPosition();
            Log.d("______", "____________");
            Log.d("Position ++", String.valueOf(position));
            amount=shopingCardClasses.get(position).getAmount();
            switch (choice){
                case "+":
                    amount++;
                    changeInformation();
                    break;
                case "-":
                    if (amount!=1){
                        amount--;
                        changeInformation();
                    }else {
                        deleteItemBase();
//                        ShopingCardAdapter.this.getContext().finish();
                        Intent a = new Intent(ShopingCardAdapter.this.getContext(), ShopingCardActivity.class);
                        ShopingCardAdapter.this.getContext().startActivity(a);

                    }
                break;
            }

        }

        private void changeInformation(){
            shopingCardClasses.get(position).setAmount(amount);
            total= Integer.toString( shopingCardClasses.get(position).getPrice()*amount)+"P";
            amountTxt.setText(Integer.toString(amount));
            price.setText(total);
            document = db.document("users/"+phone+"/"+"shopingCard/"+shopingCardClasses.get(position).getId()+"/");
//            Log.d("DOCUMENT ++", String.valueOf(document));
//            Log.d("Path  ++","users/"+phone+"/"+"shopingCard/"+shopingCardClasses.get(position).getId()+"/");
//            Log.d("Id ++", shopingCardClasses.get(position).getId());
            updateCardBase();



        }

        private void updateCardBase(){
            Log.d("Updating ", "star!!!!!");
            document
                    .update("amount", amount)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d("TAG", "DocumentSnapshot successfully updated!");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("TAG", "Error updating document", e);
                        }
                    });

        }

        private void deleteItemBase(){
            Log.d("Deliting  ", "star!!!!!");
            document
                    .delete()
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d("TAG", "DocumentSnapshot successfully updated!");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("TAG", "Error updating document", e);
                        }
                    });

        }

    }
}
