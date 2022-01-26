package Helper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.zatsepicoffee_v1.R;

import com.google.common.net.InternetDomainName;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.FirebaseAppCheck;
import com.google.firebase.appcheck.safetynet.SafetyNetAppCheckProviderFactory;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import BaseClases.CaffeClass;
import BaseClases.CategoryClass;
import BaseClases.ItemsClass;
import BaseClases.NewsClass;


public class AddInfToFB extends AppCompatActivity {

    private FirebaseFirestore db;
    private DatabaseReference mDatabase;
// ...

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        FirebaseAppCheck firebaseAppCheck = FirebaseAppCheck.getInstance();
        firebaseAppCheck.installAppCheckProviderFactory(
                SafetyNetAppCheckProviderFactory.getInstance());

        setContentView(R.layout.activity_add_inf_to_fb);
        db=FirebaseFirestore.getInstance();
//        mDatabase = FirebaseDatabase.getInstance().getReference();
        System.out.println("ADD TO FIRESTORE__________________");


//        mDatabase = FirebaseDatabase.getInstance().getReference();
        //новости
//        List<String> imagePath = Arrays.asList("https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/News_image%2Fnews2.jpg?alt=media&token=c3455ec6-1b6e-4662-98d8-04121656ef72"
//                , "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/News_image%2Fnews2.jpg?alt=media&token=c3455ec6-1b6e-4662-98d8-04121656ef72"
//                , "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/News_image%2Fnews3.jpg?alt=media&token=44c55b3c-ccb2-49ba-aba4-22fb8b9fb142a",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/News_image%2Fnews5.jpg?alt=media&token=938ea7c4-f1dc-4d28-ac57-cc70f5e4cb29",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/News_image%2Fnews6.jpg?alt=media&token=26db61f5-659b-46cd-bdc6-e4f131d5fedc",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/News_image%2Fnews7.jpg?alt=media&token=1583e231-6d23-4e87-b8ad-0b864f41c937");
//
//        List<String>  title= Arrays.asList("События 10-16 января", "Зимнее меню", "День рождения Чапаева 88","Вечерние скидки на выпечку","Конкурс","НЕДЕЛЯ БЕСПЛАТНОГО КОФЕ ☕");
//
//        List<String>  discription= Arrays.asList("15 января (суббота) в 15:00 - Немецкий клуб «Aber bitte auf Deutsch» на Ставропольской, 224 - каждую субботу играем и разговариваем на немецком языке, разрушая языковые барьеры. Билет - любой напиток ☺️ Тут побольше о клубе: @aber_bitte_auf_deutsch\n" +
//                        "\n" +
//                        "16 января (воскресенье) в 19:00 - Мафия для любителей поржать на Северной, 299 - собираемся большой компанией, играем и хохочем \uD83D\uDE04 от души! Билет - любой напиток."
//                , " "
//                , "18 мая кофейне на Чапаева исполнилось 2 года! \uD83C\uDF82\n" +
//                        "\n" +
//                        "По этому случаю в субботу мы устраиваем вечеринку с большим тортом, игристым, диджеем @alexey_zanin и бенгальскими огнями.\n" +
//                        "Весь день мы будем дарить подарки:\n" +
//                        "с 7:30 до 12:00 - акция «Плати за кофе сколько хочешь»,\n" +
//                        "а с 12:00 до 18:00 - беспроигрышная лотерея.\n" +
//                        "Надеемся, вина и игристого хватит на всех \uD83C\uDF77\uD83C\uDF7E\n" +
//                        "\n" +
//                        "Итак: 22 мая, суббота, 18:00, Чапаева 88, бесплатный вход.\n" +
//                        "Берите друзей и зажжем!\uD83D\uDD25",
//                "На часах 21:30 - самое время зацепить согревающий ☕️ и побаловать себя свежей выпечкой , тем более что скидка -30% \uD83D\uDE0D\n" +
//                        "⠀\n" +
//                        "Режим работы:\n" +
//                        "\uD83D\uDCCDСеверная, 299: 8:00-01:00\n" +
//                        "\uD83D\uDCCDКрасная, 137: 7:30-00:00\n" +
//                        "\uD83D\uDCCDСтавропольская, 224: скоро...\n" +
//                        "⠀\n" +
//                        "Вы точно успеете\uD83D\uDE09\uD83D\uDE4C\uD83C\uDFFD",
//                "САМЫЙ ПОПУЛЯРНЫЙ УНИВЕР\uD83D\uDC69\u200D\uD83C\uDF93 ⠀\n" +
//                        "Ребята \uD83D\uDC4BК нам часто приходят студенты и преподователи из разных учебных заведений. Нам стало очень интересно: студенты и преподаватели какого университета нас чаще посещают?!\uD83E\uDD14И мы решили запустить конкурс!\uD83D\uDCA5 \uD83D\uDCA5 \uD83D\uDCA5 ⠀\n" +
//                        "\uD83D\uDD38Условия:\n" +
//                        "1️⃣ быть подписанным на @zatsepi_coffee ;\n" +
//                        "2️⃣ сделать фото с нашим стаканчиком на фоне учебного заведения/кафедры/аудитории/ с зачеткой;\n" +
//                        "3️⃣ поставить хэштег #зацепиконкурс ;\n" +
//                        "4️⃣отметить наш аккаунт;\n" +
//                        "5️⃣ написать название вашего учебного заведения под этим постом\uD83D\uDC47\n" +
//                        "P.S. ваш аккаунт должен быть открытым \uD83D\uDE09\n" +
//                        "⠀\n" +
//                        "\uD83C\uDFC6 Призы:\n" +
//                        "1️⃣ место - бонусная карта на 1000 бонусов\n" +
//                        "2️⃣ место - бонусная карта на 500 бонусов\n" +
//                        "3️⃣ место - авторский напиток на ваш выбор + круассан ☕️ \uD83E\uDD50 ⠀\n" +
//                        "⠀\n" +
//                        "Итоги конкурса подведём 15 апреля\n" +
//                        "Поехали\uD83D\uDE09\uD83D\uDCAB\uD83D\uDCAB\uD83D\uDCAB",
//                "У нас тут недавно опрос среди читателей был. Нам безумно приятно, что многие из вас знают нас ещё со времён, когда был только павильон-малыш на Северной. И не менее приятно, что вы помните, дату нашего открытия.\uD83E\uDD73\n" +
//                        ".\n" +
//                        "А это значит, что вы уже ждёте праздника. Ведь нам исполнилось 3️⃣ годика. Так что пора дарить подарки. Мы начнём первыми.\uD83C\uDF81\n" +
//                        ".\n" +
//                        "У нас есть три сертификата, которые мы с удовольствием вручим победителям.\n" +
//                        "1️⃣ место - неделя бесплатного кофе\n" +
//                        "2️⃣ место - 2 чашки классического кофе\n" +
//                        "3️⃣ место - 1 чашка классического кофе\n" +
//                        ".\n" +
//                        "Условия как всегда просты\n" +
//                        "✅ Быть подписанным на @zatsepi_coffee\n" +
//                        "✅ Отметить друга в комментарии\n" +
//                        "✅ Лайк на последние 3 поста\n" +
//                        ".\n" +
//                        "Количество комментариев от одного человека не ограничено\uD83D\uDD25\n" +
//                        ".\n" +
//                        "День рождения будем отмечать 28 сентября, поэтому 27го проведём прямой эфир и определим победителей конкурса с помощью сервиса @lizaonair .\n" +
//                        "Это не единственные подарки, которые мы для вас приготовили, так что следите за новостями \uD83D\uDE09\n" +
//                        ".");
//        for (int i = 0; i <imagePath.size() ; i++) {
//            writeNewNews(Integer.toString(i),imagePath.get(i),title.get(i),discription.get(i));
//        }


////        //кофейни
//        List<String> imagePath1 = Arrays.asList("https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/Cafe_image%2Fcaff_chap.jpg?alt=media&token=6a6978b8-78d7-4987-b24f-82996f300b5e"
//                ,"https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/Cafe_image%2Fcaff_kras.jpg?alt=media&token=1cfe123e-f4de-4507-a1dc-856b2079f7f8"
//                ,"https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/Cafe_image%2Fcaff_kras_small.jpg?alt=media&token=dc82f948-bae8-4d37-a1de-2870fbea9dbe"
//                ,"https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/Cafe_image%2Fcaff_neftyan.jpg?alt=media&token=82cf0196-c160-4d48-82fa-50b451145a56",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/Cafe_image%2Fcaff_sedina11.jpg?alt=media&token=fd0fa272-e05b-4a2c-bca2-af99c3a06940"
//                ,"https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/Cafe_image%2Fcaff_sev.jpg?alt=media&token=3b903723-ca18-42a3-8144-a90f5b150e59"
//                ,"https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/Cafe_image%2Fcaff_stadion.jpg?alt=media&token=0e123657-bba9-4c21-a0ef-2f4a56c73e93"
//                ,"https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/Cafe_image%2Fcaff_stavr131.jpg?alt=media&token=0735e8d7-f542-4bab-aff1-13660c662cb0"
//                ,"https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/Cafe_image%2Fcaff_stavr254.jpg?alt=media&token=6aa4d62e-cd9b-4c74-a757-07fbbaeda448");
//        List<String> adress = Arrays.asList("г.Краснодар,ул. Чапаева, 88",
//                "г.Краснодар,ул. Красная, 137",
//                "г.Краснодар,ул. Красная, 164",
//                "г.Краснодар,ул. ш. Нефтяников, 30",
//                "г.Краснодар,ул. Митрофана Седина, 11",
//                "г.Краснодар,ул. Северная, 299",
//                "г.Краснодар,ул. Восточно-Кругликовская, 18/1",
//                "г.Краснодар,ул. Ставропольская, 131",
//                "г.Краснодар,ул. Ставропольская, 224");
//        List<String> time = Arrays.asList("Понедельник-Суббота 07:30 – 23:00\n" + "Воскресеньe 09:00 – 23:00",
//                "Понедельник-Суббота 07:30 – 23:00\n" + "Воскресеньe 09:00 – 23:00",
//                "Понедельник-Суббота 09:00 – 23:00\n" + "Воскресеньe 10:00 – 23:00",
//                "Понедельник-Суббота 07:30 – 22:00\n" + "Воскресеньe 10:00 – 22:00",
//                "Понедельник-Пятница 07:30 – 18:00\n" + "Суббота-Воскресеньe Выходной",
//                "Понедельник-Воскресеньe 07:30 – 23:00",
//                "Понедельник-Суббота 08:00 – 23:00\n" + "Воскресеньe 09:00 – 23:00",
//                "Понедельник-Суббота 07:30 – 23:00\n" + "Воскресеньe 09:00 – 23:00",
//                "Понедельник-Суббота 07:30 – 23:00\n" + "Воскресеньe 09:00 – 23:00");
//        List<String> contacts = Arrays.asList("8 (928) 849-10-50");
//        System.out.println("ADD TO FIRESTORE befor for__________________");
//        for (int i = 0; i <imagePath1.size() ; i++) {
//            System.out.println("ADD TO FIRESTORE in for__________________");
//            writeNewNCaffee(Integer.toString(i),imagePath1.get(i),adress.get(i),time.get(i),contacts.get(0));
//        }


        //товар
        List<String> imagePath2 = Arrays.asList("https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/Menu_classic_image%2Famericano.jpg?alt=media&token=eecb71d0-cf75-4b7e-a08e-55f11ee6093d"
                ,"https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/Menu_classic_image%2Fcappuchino.jpeg?alt=media&token=fe52aa96-016c-455a-81ae-b87ffd918333"
                ,"https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/Menu_classic_image%2Flatte.jpg?alt=media&token=e4a0f7b6-1d43-4614-9874-c495ffa6309e"
                ,"https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/Menu_classic_image%2Ffalt_white.jpeg?alt=media&token=5140151d-0a48-4b57-801f-30ffad27a1a5",
                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/Menu_classic_image%2Fraf.jpg?alt=media&tokenS=f9379dc2-c57d-4c9f-8016-747a8ceb466b"
                ,"https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/Menu_classic_image%2Flatte.jpg?alt=media&token=e4a0f7b6-1d43-4614-9874-c495ffa6309e");
        List<Integer> price = Arrays.asList(149,139,229,179,269,259);
        List<String> title = Arrays.asList("Американо", "Капучино", "Латте", "Флэт уайт", "Раф классика", "Мокко");
        List<String> discription = Arrays.asList("Классический черный кофе",
                "Насыщенный кофейно-молочный напиток с плотной молочной пеной",
                "Молочный напиток на основе кофе с очень плотной молочной пеной",
                "Молочный напиток на основе эспрессо.",
                "Кофейный напиток со сливочно-ванильным вкусом с очень нежной и плотной текстурой.",
                "Классический шоколадный кофе с шоколадной крошкой.");
        List<String> sizeItem = Arrays.asList("250","400","400","250","400","300");
        List<String> category = Arrays.asList("Классика");
//        for (int i = 0; i <imagePath2.size() ; i++) {
//            writeNewPrefers(Integer.toString(i),price.get(i),sizeItem.get(i),imagePath2.get(i),title.get(i),discription.get(i),category.get(i));
//        }


//ТОВАР
        ArrayList<ItemsClass> itemsClassesAr=new ArrayList<>();
        for (int i = 0; i <imagePath2.size() ; i++) {

            writeNewItem(title.get(i),price.get(i),sizeItem.get(i)+"ml",imagePath2.get(i),title.get(i),discription.get(i),category.get(0),true);
        }

        //        Категория
//        List<String> imagePath3 = Arrays.asList("https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/Category_images%2Fcategory_classic.jpg?alt=media&token=74cce5fa-b9a0-42d5-8bbc-8199519b2095"
//                ,"https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/Category_images%2Fcategory_aliter.jpeg?alt=media&token=41b74273-ba85-415f-93e0-d607c2a1dff3"
//                ,"https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/Category_images%2Fcategory_author.jpg?alt=media&token=c3d21c2e-bddb-4fd8-b6f7-1a89cf91999e"
//                ,"https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/Category_images%2Fcategory_iced.png?alt=media&token=9fbc1aab-0df2-4adb-ba54-b4d2ee15d601",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/Category_images%2Fcategory_warming.jpg?alt=media&token=e98c69f1-5c8e-4026-8433-46601ea1d122"
//                ,"https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/Category_images%2Fcategory_fresh.jpeg?alt=media&token=071a183a-7d69-4c5a-b74e-880f4a8b4917"
//                ,"https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/Category_images%2Fcategory_meal.jpeg?alt=media&token=fb1b56b4-c3df-4cf1-9c0f-5fe6e37791b5");
//
//        List<String> title3 = Arrays.asList("КЛАССИКА", "АЛЬТЕРНАТИВА", "АВТОРСКОЕ","ОХЛАЖДАЕТ", "СОГРЕВАЕТ",  "ОСВЕЖАЕТ","Перекусить");
//        for (int i = 0; i <imagePath3.size() ; i++) {
//            writeNewCategory(Integer.toString(i),imagePath3.get(i),title3.get(i));
//        }


    }

    public void writeNewNCaffee(String caffeId,String imagePath, String adress, String time, String contacts){
        CaffeClass caffeClass= new CaffeClass(caffeId,imagePath,adress,time,contacts);
        db.collection("caffe").document("caffeId"+caffeId).set(caffeClass);

//        mDatabase.child("caffe").child("caffeId"+String.valueOf(caffeId)).setValue(caffeClass);
    }
    public void writeNewNews(String newsId,String imagePath, String title, String discription) {
        NewsClass news = new NewsClass(newsId, imagePath, title,  discription);
        db.collection("news").document("newsId"+newsId).set(news);
//        mDatabase.child("News_tabel").child("newsId"+String.valueOf(newsId)).setValue(news);
    }

    public void writeNewItem(String id, int price, String size, String imagePath, String title, String discription, String category,Boolean isPopular) {
        ItemsClass itemsClass= new ItemsClass(id,price,size,imagePath,title,discription,category,isPopular);
//        mDatabase.child("Item_tabel").child("itemId"+String.valueOf(id)).setValue(itemsClass);
        db.collection("items").document(id).set(itemsClass);
    }
    public void writeNewCategory(String id, String imagePath, String title) {
        CategoryClass categoryClass= new CategoryClass(id, imagePath, title);
//        mDatabase.child("Item_tabel").child("itemCategory"+String.valueOf(id)).setValue(categoryClass);
            db.collection("category").document("categoryId"+id).set(categoryClass);
    }
    public void writeNewPrefers(String id, int price, String size, String imagePath, String title, String discription, String category,Boolean isPopular) {
        ItemsClass itemsClass= new ItemsClass(id,price,size,imagePath,title,discription,category,isPopular);
        db.collection("users").document("user1").collection("prefers").document("preferId"+id).set(itemsClass);
//        mDatabase.child("Item_tabel").child("itemId"+String.valueOf(id)).setValue(itemsClass);
    }
}