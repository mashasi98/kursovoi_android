package Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


import com.example.zatsepicoffee_v1.R;

import java.util.ArrayList;

import Apapter.CategoryAdapter;
import BaseClases.MainModels;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton backToMainBtb;
    RecyclerView recyclerViewCategory;
    ArrayList<MainModels> category_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        RecyclerView.ItemAnimator horiz_scrl_animation = new DefaultItemAnimator();
        //кнопки корзины и возврата

        backToMainBtb=findViewById(R.id.back_toMain_btn);
        backToMainBtb.setOnClickListener(this);


        //recycleView
        Integer[] ListImageCategory= {R.drawable.category_classic, R.drawable.category_aliter, R.drawable.category_author, R.drawable.category_iced, R.drawable.category_warming, R.drawable.category_fresh, R.drawable.category_meal};
        String[] categoryName = {"Классика", "Альтернатива", "Авторское", "Охлаждает","Согревает","Освежает","Перекусить"};
        recyclerViewCategory=findViewById(R.id.recycle_view_category);
        recyclerViewCategory(ListImageCategory,categoryName,horiz_scrl_animation,recyclerViewCategory,category_list);

    }
    public void onClick(View v) {
        if ( v.getId() == R.id.back_toMain_btn ) {
            Intent intent = new Intent(MenuActivity.this, MainMenu.class);
            startActivity(intent);
        }
    }

    public void recyclerViewCategory(Integer[] listImage,String[] categoryName, RecyclerView.ItemAnimator itemAnimator, RecyclerView recyclerViewMain, ArrayList<MainModels> arrayList){
        StaggeredGridLayoutManager staggeredGridLayoutManager= new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        arrayList = new ArrayList<>();
        for (int i = 0, listImageLength = listImage.length; i < listImageLength; i++) {

            arrayList.add(new MainModels(listImage[i],categoryName[i]));
        }
        recyclerViewMain.setLayoutManager(staggeredGridLayoutManager);
        recyclerViewMain.setItemAnimator(itemAnimator);
        recyclerViewMain.setAdapter(new CategoryAdapter(arrayList, MenuActivity.this,R.layout.row_item_category));
    }
}

