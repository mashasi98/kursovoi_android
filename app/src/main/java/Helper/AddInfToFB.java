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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;


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

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        FirebaseApp.initializeApp(this);
//        FirebaseAppCheck firebaseAppCheck = FirebaseAppCheck.getInstance();
//        firebaseAppCheck.installAppCheckProviderFactory(
//                SafetyNetAppCheckProviderFactory.getInstance());
//
//        setContentView(R.layout.activity_add_inf_to_fb);
//        db=FirebaseFirestore.getInstance();
////        mDatabase = FirebaseDatabase.getInstance().getReference();
//        System.out.println("ADD TO FIRESTORE__________________");


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
//        List<String> imagePath2 = Arrays.asList("https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/Menu_classic_image%2Famericano.jpg?alt=media&token=eecb71d0-cf75-4b7e-a08e-55f11ee6093d"
//                ,"https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/Menu_classic_image%2Fcappuchino.jpeg?alt=media&token=fe52aa96-016c-455a-81ae-b87ffd918333"
//                ,"https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/Menu_classic_image%2Flatte.jpg?alt=media&token=e4a0f7b6-1d43-4614-9874-c495ffa6309e"
//                ,"https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/Menu_classic_image%2Ffalt_white.jpeg?alt=media&token=5140151d-0a48-4b57-801f-30ffad27a1a5",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/Menu_classic_image%2Fraf.jpg?alt=media&tokenS=f9379dc2-c57d-4c9f-8016-747a8ceb466b"
//                ,"https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/Menu_classic_image%2Flatte.jpg?alt=media&token=e4a0f7b6-1d43-4614-9874-c495ffa6309e");
//        List<Integer> price = Arrays.asList(149,139,229,179,269,259);
//        List<String> title = Arrays.asList("Американо", "Капучино", "Латте", "Флэт уайт", "Раф классика", "Мокко");
//        List<String> discription = Arrays.asList("Классический черный кофе",
//                "Насыщенный кофейно-молочный напиток с плотной молочной пеной",
//                "Молочный напиток на основе кофе с очень плотной молочной пеной",
//                "Молочный напиток на основе эспрессо.",
//                "Кофейный напиток со сливочно-ванильным вкусом с очень нежной и плотной текстурой.",
//                "Классический шоколадный кофе с шоколадной крошкой.");
//        List<String> sizeItem = Arrays.asList("250","400","400","250","400","300");
//        List<String> category = Arrays.asList("Классика");
//        for (int i = 0; i <imagePath2.size() ; i++) {
//            writeNewPrefers(Integer.toString(i),price.get(i),sizeItem.get(i),imagePath2.get(i),title.get(i),discription.get(i),category.get(i));
//        }
        //классика
//        List<String> imagePath2 = Arrays.asList("https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/Menu_classic_image%2Famericano.jpg?alt=media&token=eecb71d0-cf75-4b7e-a08e-55f11ee6093d"
//                ,"https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/Menu_classic_image%2Fcappuchino.jpeg?alt=media&token=fe52aa96-016c-455a-81ae-b87ffd918333"
//                ,"https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/Menu_classic_image%2Flatte.jpg?alt=media&token=e4a0f7b6-1d43-4614-9874-c495ffa6309e"
//                ,"https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/Menu_classic_image%2Ffalt_white.jpeg?alt=media&token=5140151d-0a48-4b57-801f-30ffad27a1a5",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/Menu_classic_image%2Fraf.jpg?alt=media&tokenS=f9379dc2-c57d-4c9f-8016-747a8ceb466b"
//                ,"https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/Menu_classic_image%2Flatte.jpg?alt=media&token=e4a0f7b6-1d43-4614-9874-c495ffa6309e");
//        List<Integer> price = Arrays.asList(149,139,229,179,269,259);
//        List<String> title = Arrays.asList("Американо", "Капучино", "Латте", "Флэт уайт", "Раф классика", "Мокко");
//        List<String> discription = Arrays.asList("Классический черный кофе",
//                "Насыщенный кофейно-молочный напиток с плотной молочной пеной",
//                "Молочный напиток на основе кофе с очень плотной молочной пеной",
//                "Молочный напиток на основе эспрессо.",
//                "Кофейный напиток со сливочно-ванильным вкусом с очень нежной и плотной текстурой.",
//                "Классический шоколадный кофе с шоколадной крошкой.");
//        List<String> sizeItem = Arrays.asList("250","400","400","250","400","300");
//        List<String> category = Arrays.asList("Классика");

//        //альтернатива
//        List<String> imagePath2 = Arrays.asList("https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/Menu_alter_img%2F995d73ce654fffbcdd76383d9f535969.jpg?alt=media&token=e395218d-4df0-4dca-8061-8814d89ebec6",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/Menu_alter_img%2F2f5228ba3b1acf99f8c8e007380760b9.jpg?alt=media&token=2a450b13-af60-40c6-9ae8-1454b4ce34f1",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/Menu_alter_img%2Fdf923a351f336f2b0baa2342e4d9ca52.jpg?alt=media&token=ec7caaf5-fc66-41c7-aa25-d5c001695d23",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/Menu_alter_img%2F01d4c36ef82245538e8f279a22b360dd.jpg?alt=media&token=b152e7ec-0539-4f1c-8e1a-9649f66ee9b2",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/Menu_alter_img%2F8cbdc6265806d75f9b09b9f9309501ef.jpg?alt=media&token=7cc42352-af64-43d4-99e6-3be390e21293");
//        List<Integer> price = Arrays.asList(189,250,189,169,189);
//        List<String> title = Arrays.asList( "Френч-пресс","Кемекс", "Воронка V60", "Фильтр", "Аэро-пресс");
//        List<String> discription = Arrays.asList("",
//                "",
//                "",
//                "",
//                "");
//        List<String> sizeItem = Arrays.asList("","","","","","");
//        List<String> category = Arrays.asList("Альтернатива");

//        //authors
//        List<String> imagePath2 = Arrays.asList("https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/menu_auth_img%2Fb16eecc51f809a5f11f6c3a29ac21767.jpg?alt=media&token=a251ffbe-d406-4411-83d1-d2edca9c3b76",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/menu_auth_img%2F6fa4ea9ab52426dbb0b105746f49e4e2.jpg?alt=media&token=69679f7c-b43c-4976-bca6-456c0f555bd7",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/menu_auth_img%2F787cef72d1441ba61bd422837875eb6a.jpg?alt=media&token=725100a9-53ff-45a9-a04f-b0313799ed52",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/menu_auth_img%2Fergws.jpg?alt=media&token=6723a857-0721-497a-aa73-87aae1b6ec4c",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/menu_auth_img%2FIMG_7892.jpg?alt=media&token=4401db95-6c7f-407d-81ec-88eb1d397640",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/menu_auth_img%2FSyrnyj-latte.jpg?alt=media&token=f3671f0e-adeb-4867-88b5-318c45400544",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/menu_auth_img%2F0dc791b66a0063c309b084e35ef4a390-680x500.jpg?alt=media&token=44f2fa87-da70-44ed-87ba-fd575c360ca4",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/menu_auth_img%2Faraxisovyj-raf.jpg?alt=media&token=5aed0ddd-03c9-4158-984e-b503183f0e8c",
//               "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/menu_auth_img%2F1b326ddeec43eb831de39cb10b6a3a3c.jpg?alt=media&token=c02afcee-7da6-4e87-b82b-bfa066d52b69",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/menu_auth_img%2F9749216357e57faefc2be3e2a022b380.jpg?alt=media&token=d3721928-a33d-4d46-9d3c-cdd49c382e6e",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/menu_auth_img%2F51005_3000x2000.jpg?alt=media&token=2801f148-5abb-4410-8133-1563e20c0a8b");
//        List<Integer> price = Arrays.asList(249,229,249,219,249,219,219,229,249,219,229);
//        List<String> title = Arrays.asList( "Рафф Баунти"
//                ,"Испанский латте"
//                , "Рафф Нутелла"
//                , "Капучино ореховый бум"
//                ,"Рафф лимонное печенье"
//                ,"Латте сырный"
//                , "Латте халва"
//                , "Латте арахисовый"
//                ,"Рафф медовая груша"
//                , "Латте инжир-лаванда"
//                ,"Горячий бамбл");
//        List<String> discription = Arrays.asList("",
//                "",
//                "",
//                "",
//                "");
//        List<String> sizeItem = Arrays.asList("","","","","","");
//        List<String> category = Arrays.asList("Авторское");
////

////        охлаждает
//        List<String> imagePath2 = Arrays.asList("https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/menu_iced_img%2F27d082e0067e51cc3efc8129fcdc5f73.jpg?alt=media&token=7f7c2e27-3a3e-49a3-add6-dba14421f738",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/menu_iced_img%2Ffe1d56ab50cac8b2e67f4a34b8bd1f20.jpg?alt=media&token=e2c0e251-9665-4351-ad82-4328ed52e48e",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/menu_iced_img%2F43850229388cea1d424b774370b27836.jpg?alt=media&token=c3aaf3ce-b133-4d94-b2b9-3559ed2db1f0",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/menu_iced_img%2Fd48df57da59288e26603fefbf5076322.jpg?alt=media&token=2f194083-bba0-4a7e-8632-0f422469ec96",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/menu_iced_img%2F64064103af076323b16d2c972b172ee8.jpg?alt=media&token=e271dfb5-25a2-4143-802d-34486931fa03",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/menu_iced_img%2F5e15b613bad7019abbcacf5037802249.jpg?alt=media&token=61cf3f33-6939-4cde-9c7e-37b91356eeed",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/menu_iced_img%2F64230b1825c5982bf890c4e259dd52ec.jpg?alt=media&token=25bfeb49-3fc9-4cdb-81ce-e35aead11ebf");
//        List<Integer> price = Arrays.asList(209,219,219,209,209,209,209);
//        List<String> title = Arrays.asList("Шоко-минт",
//                "Aйс матча-латте",
//                "Испанский айс-латте",
//                "Бамбл",
//                "Айс-латте",
//                "Эспрессо-тоник",
//                "Фраппе");
//        List<String> discription = Arrays.asList("");
//        List<String> sizeItem = Arrays.asList("","","","","","");
//        List<String> category = Arrays.asList("Охлаждает");
//        согревает
//        List<String> imagePath2 = Arrays.asList("https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/menu_warmed_img%2Fmain.jpg?alt=media&token=e122d96f-49d1-4812-9366-8e9d603b1ed6",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/menu_warmed_img%2F4136607d9f9e80bfbe2f505bb6f87c02.jpg?alt=media&token=e683a5d9-ba5d-46d2-a538-ff30c7a20d84",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/menu_warmed_img%2Fa9262a21d127d469a621c076067e1092.jpg?alt=media&token=537933dd-0b9f-4654-90e1-e675832fb503",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/menu_warmed_img%2Fcfbc748a453448e52d923b395284835a.jpg?alt=media&token=ae1cb668-1dae-4b29-bb5b-7944462aae8e",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/menu_warmed_img%2F01.jpg?alt=media&token=1193dc43-13ae-41ca-9f43-8115c536031d",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/menu_warmed_img%2Fchai_s_kornem_imbirya-412467.jpg?alt=media&token=52e571b1-1e1f-4cc2-b85b-23701afd61b2",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/menu_warmed_img%2Fchay-4.jpg?alt=media&token=44de48fc-448c-48cf-aa49-a62f2d76b06a",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/menu_warmed_img%2Foblepiha-2.jpg?alt=media&token=24559857-d5aa-4892-a005-72f1d3e078d9",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/menu_warmed_img%2FImbirno-oblepihovyj-chaj-500x350-1200x800.jpg?alt=media&token=cc312e18-d815-4094-96b3-8e28aaa2b842",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/menu_warmed_img%2F%D0%9C%D0%B0%D0%BB%D0%B8%D0%BD%D0%BE%D0%B2%D1%8B%D0%B9-%D1%81%D0%BE%D1%80%D0%B1%D0%B5%D1%82-8.png?alt=media&token=983a998d-b9a0-4289-9859-7084bbf45599",
//                 "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/menu_warmed_img%2F6bedfa7cadbefa8190c350347460a101.jpg?alt=media&token=bba09bf1-8310-49f8-a706-d9378544bbc0",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/menu_warmed_img%2Fc74e58011f23daac6f618847de079576.jpg?alt=media&token=d3526de5-c467-4b18-9d6d-0e19e68e736e",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/menu_warmed_img%2F4bfda0ec9160f01a78c9be1755a1f8b9.jpg?alt=media&token=793c60cc-2da7-497d-808c-a8a0a16164f3");
//        List<Integer> price = Arrays.asList(189,199,189,129,120,199,209,195,209,209,209,189,199);
//        List<String> title = Arrays.asList("Какао",
//                "Пряный какао ",
//                "Горячий шоколад",
//                "Листовой чай",
//                "Гречишный чай",
//                "Имбирный чай",
//                "Чай ягодный микс",
//                "Облепиховый чай",
//                "Облепихово-имбирный чай",
//                "Малиновый чай",
//                "Клубничный чай",
//                "Пряный чай-латте",
//                "Матча-латте");
//        List<String> discription = Arrays.asList("");
//        List<String> sizeItem = Arrays.asList("","","","","","");
//        List<String> category = Arrays.asList("Согревает");

////     Освежает
//        List<String> imagePath2 = Arrays.asList("https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/fresh_img%2F5730cb4b335ba501a5273605815a6ca5.jpg?alt=media&token=900ab51d-7ffc-41bf-9a9e-a84d2480dd05",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/fresh_img%2Fgkl.jpg?alt=media&token=bc1aea16-0ccd-4b2a-9f7a-f7f8a981100c",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/fresh_img%2Fcocktail-or-lemonade-with-cherries-lime-and-rosemary-summer-refreshment-drink_132278-2620.jpg?alt=media&token=e5ae4c97-f472-4378-98ca-359fb8e4dfd7",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/fresh_img%2Flychee-jelly-seasonal-fruit-and-beautifully-decorated-thai-dessert-concept_1150-23456.jpg?alt=media&token=9a01dcee-b1fe-4e2f-ae8e-b0c3ffa17864",
//               "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/fresh_img%2F2745081_81297-710x550x.jpg?alt=media&token=872c054d-bbdd-4ce8-a7ac-2ce41f6118a8",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/fresh_img%2Fimages%20(1).jpeg?alt=media&token=ecd2e74d-0769-438d-86ab-20942d682899",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/fresh_img%2Fmatcha-tonic-cocktail-moya%20(2).jpg?alt=media&token=28c481dc-6542-4197-b5b7-8b0e43c1170d",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/fresh_img%2Fbd403537.jpg?alt=media&token=d143426e-43b5-4c80-b2c9-6566b6400aa9",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/fresh_img%2F%D0%BA%D1%80%D0%B0%D0%BD%D0%B1%D0%B5%D1%80%D1%80%D0%B8-%D0%BA%D0%BE%D0%BA%D1%82%D0%B5%D0%B9%D0%BB%D1%8C-%D0%BA%D0%BE%D0%B4%D0%B5%D1%80-%D1%81%D0%BE-%D0%BB%D1%8C%D0%B4%D0%BE%D0%BC-%D1%80%D0%BE%D0%B7%D0%BC%D0%B0%D1%80%D0%B8-%D0%B8-%D1%8F%D0%B3%D0%BE%D0%B4%D1%8B-%D0%B1%D0%B0%D1%80-%D0%B8%D0%BD%D1%81%D1%82%D1%80%D1%83%D0%BC%D0%B5%D0%BD%D1%82%D1%8B-%D1%84%D0%BE%D0%BD-163175039.jpg?alt=media&token=28d159b4-9740-41d4-a598-4d11a26652d2",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/fresh_img%2F94b7b6.jpg?alt=media&token=79cd335a-a2d2-4474-8f21-279f5b2f848a",
//                 "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/fresh_img%2Fingredients-preparation-green-smoothies-apple-2536125.jpg?alt=media&token=afbce1ea-93d4-49ed-a486-b562445eb9fb",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/fresh_img%2F153bae56f0059f2a389d654e42d6bb9e.jpg?alt=media&token=d1426660-153a-4a20-b012-d7f33d76f46f",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/fresh_img%2Fimages%20(2).jpeg?alt=media&token=4736d2a7-e6d2-4541-91d2-3fc5ea197042",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/fresh_img%2Fmolochnii_vanilnii_kokteil-405516.jpg?alt=media&token=80299c00-9d79-46a9-8166-fde1f1738698",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/fresh_img%2Fb36fc8ea899ade39df259bf323b8c388-0x0.webp?alt=media&token=c818d429-fa29-44e7-b44c-0eceb155c7c3",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/fresh_img%2Fu-0e1b7d921fbcc67f139555e0c151eb54.jpg?alt=media&token=eb033d32-69a6-4e3f-b669-632c6bbb123c",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/fresh_img%2Fimages%20(3).jpeg?alt=media&token=4c3a284f-7494-4cc2-975b-3b3a719e08d0",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/fresh_img%2Fmqdefault.jpg?alt=media&token=62ab6fdf-8d9b-4dca-9b02-51f5c37f5f8a"
//                );
//
//        List<Integer> price = Arrays.asList( 199,239,229,259,239,229,259,239,199,239,239,239,239,189,209,209,269,269);
//        List<String> title = Arrays.asList("Классический лимонад",
//                "Лимонад облепиха-маракуя",
//                "Лимонад вишня-розмарин",
//                "Лимонад личи-гуава",
//                "Лимонад клубника",
//                "Матча-тоник",
//                "Матча-тоник личи-лайм",
//                "Мохито",
//                "Ягодный пунш",
//                "Смузи клубника-банан",
//                "Смузи яблоко-киви-базилик",
//                "Смузи малина",
//                "Смузи смородинан",
//                "Милкшейк ваниль",
//                "Милкшейк клубника",
//                "Милкшейк смородина",
//                "Милкшейк орео",
//                "Милкшейк сникерс"
//                );
//
//        List<String> discription = Arrays.asList("");
//        List<String> sizeItem = Arrays.asList("","","","","","");
//        List<String> category = Arrays.asList("Освежает");
//     перекусить
//        List<String> imagePath2 = Arrays.asList("https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/eat_img%2F083ca5f61d1def2bed653345ea3c0e2f-680x500.jpeg?alt=media&token=04c50781-8390-4c6c-a2b1-75c1a6b219a7",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/eat_img%2Fimg_000000518.jpg?alt=media&token=f578747f-6033-486b-b0f1-30793a120aca",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/eat_img%2F6071845474.jpg?alt=media&token=a8c8a954-e7d3-4b9d-8065-6617f361436f",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/eat_img%2F728x546_1_86949c08d0d2e70d533e573e16b30b10%401706x1280_0xac120003_10302427471639650675.jpeg?alt=media&token=54242099-5d04-4af2-ab26-d6efdf1f345a",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/eat_img%2F5ef97b8425b1c_w900_q95.jpg?alt=media&token=f7bd8856-4c80-4337-a406-935fe64a9ee7",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/eat_img%2F226670457%20(1).jpeg?alt=media&token=7d0ec01e-a83a-4299-b92c-b097b187fc6a",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/eat_img%2Fmorkovnyy-tort-recepty-960-38359.jpg?alt=media&token=ea70200a-f7f4-492e-9899-9fd56799974d",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/eat_img%2Fp4103262-scaled.jpg?alt=media&token=50427c92-1aa1-4048-b584-3b6f6ccc9776",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/eat_img%2Ftort-krasnyy-barhat5.jpg?alt=media&token=6cde7904-1a4b-4655-9ba1-ce16d2224b65",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/eat_img%2FDepositphotos_137624948_XL_1635865277-scaled-e1635865324489-1280x640.jpg?alt=media&token=070118a5-d71d-4921-aefd-83725920aea2",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/eat_img%2F%D0%A7%D0%B8%D0%B7%D0%BA%D0%B5%D0%B9%D0%BA%20%D0%9C%D0%B0%D0%BD%D0%B3%D0%BE-%D0%9C%D0%B0%D1%80%D0%B0%D0%BA%D1%83%D0%B9%D1%8F%20%D0%9C%203.jpg?alt=media&token=7983dd15-0906-47b2-aef6-1b289b1cd421",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/eat_img%2Fimages%20(4).jpeg?alt=media&token=717521c9-eb3a-443d-9ee8-c19eee06905b",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/eat_img%2F60060d08b1b731.01641976.jpg?alt=media&token=943a5888-c8e2-4717-bc6d-dbb45f07e3b7",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/eat_img%2Fimages%20(6).jpeg?alt=media&token=e42725e4-a594-4957-88a0-c305c2f0f1e7",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/eat_img%2Fec310ce865d10da3eabadd30f6affc3c.jpg?alt=media&token=4fb44e12-2974-47f7-802f-68de5a54f29f",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/eat_img%2Fe1c947f4-c0a0-4181-a0e3-ec034169baf1.jpg?alt=media&token=e800bf16-0ad4-4d5f-9f8f-2991bd7237cf",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/eat_img%2Fcach-lam-dau-say-deo.jpg?alt=media&token=5c5c6cb9-3941-46a3-a37f-95ec6814c131",
//                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/eat_img%2F755650190202836.jpg?alt=media&token=9a33cd6f-591f-4a4a-b837-f22ec56540af"
//        );
//
//        List<Integer> price = Arrays.asList(219,209,249,159,200,79,189,209,209,209,219,69,99,129,119,219,199,179);
//        List<String> title = Arrays.asList("Сэндвич Цезарь с курицей",
//                "Сэндвич с ветчиной",
//                "Сэндвич с семгой",
//                "Сырники 2 шт.",
//                "Онигири",
//                "Шоколадный маффин",
//                "Морковный торт",
//                "Творожный медовик",
//                "Красный бархат",
//                "Классический наполеон",
//
//                "Чизкейк маракуйя",
//                "ПП конфета",
//                "Бельгийский куки",
//                "ПП батончик",
//                "Макаронс",
//                "Сушенный манго ",
//                "Сушенная клубника",
//                "Ассорти орешков"
//        );
//
//        List<String> discription = Arrays.asList("",
//                "Грибы, томленые в сливках, с ветчиной и моцареллой, салат латук на тостах тар-тар.",
//                "Нежный творожный крем в сочетании с семгой слабой соли на ржаном тосте со свежим огурцом и салатом.",
//                "",
//                "Традиционные японские рисовые конвертики",
//                "Классический шоколадный кекс с кусочками темного шоколада",
//                "Морковный бисквит со сметанным кремом и кусочками моркови.",
//                "Классический рыжик, только с творожным кремом.",
//                " ",
//                "Воздушный классический торт с заварным кремом.",
//                "Воздушный десерт из творожного сыра с прослойкой из пюре маракуйя.",
//                "Из смеси фруктов и ягод с орехами или шоколадом.",
//                "Классическое американское печенье с кусочками бельгийского шоколада.",
//                "Из смеси шоколадного ганаша и орехов.",
//                "Французское пирожное из миндальной муки и белого шоколада.",
//                "Сушенный фрукт без сахара и добавок.",
//                "Сушенная ягода без сахара и добавок.",
//                "Миндаль, кешью, фундук, макадамия и грецкий."
//                );
//        List<String> sizeItem = Arrays.asList("190г.","190г.","190г.","140г.","140г."
//                ,"145г.","140г.","110г.", "140г.", "140г.","20г.","80г.","20г.","35г.","70г.","70г.","75г.");
//        List<String> category = Arrays.asList("Перекусить");
//
////ТОВАР
////        ArrayList<ItemsClass> itemsClassesAr=new ArrayList<>();
//        String p ="categoryId6";
//        p="category/"+p;
//        DocumentReference document = db.document(p);
//        for (int i = 0; i <imagePath2.size() ; i++) {
//
//            writeNewItem(Integer.toString(i),price.get(i),sizeItem.get(0),imagePath2.get(i),title.get(i),discription.get(i),category.get(0),false,document);
//
//        }
//
//        //        Категория
////        List<String> imagePath3 = Arrays.asList("https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/Category_images%2Fcategory_classic.jpg?alt=media&token=74cce5fa-b9a0-42d5-8bbc-8199519b2095"
////                ,"https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/Category_images%2Fcategory_aliter.jpeg?alt=media&token=41b74273-ba85-415f-93e0-d607c2a1dff3"
////                ,"https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/Category_images%2Fcategory_author.jpg?alt=media&token=c3d21c2e-bddb-4fd8-b6f7-1a89cf91999e"
////                ,"https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/Category_images%2Fcategory_iced.png?alt=media&token=9fbc1aab-0df2-4adb-ba54-b4d2ee15d601",
////                "https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/Category_images%2Fcategory_warming.jpg?alt=media&token=e98c69f1-5c8e-4026-8433-46601ea1d122"
////                ,"https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/Category_images%2Fcategory_fresh.jpeg?alt=media&token=071a183a-7d69-4c5a-b74e-880f4a8b4917"
////                ,"https://firebasestorage.googleapis.com/v0/b/zatsepicoffee-a8b39.appspot.com/o/Category_images%2Fcategory_meal.jpeg?alt=media&token=fb1b56b4-c3df-4cf1-9c0f-5fe6e37791b5");
////
////        List<String> title3 = Arrays.asList("КЛАССИКА", "АЛЬТЕРНАТИВА", "АВТОРСКОЕ","ОХЛАЖДАЕТ", "СОГРЕВАЕТ",  "ОСВЕЖАЕТ","Перекусить");
////        for (int i = 0; i <imagePath3.size() ; i++) {
////            writeNewCategory(Integer.toString(i),imagePath3.get(i),title3.get(i));
////        }
//
//
//    }
//
//    public void writeNewNCaffee(String caffeId,String imagePath, String adress, String time, String contacts){
//        CaffeClass caffeClass= new CaffeClass(caffeId,imagePath,adress,time,contacts);
//        db.collection("caffe").document("caffeId"+caffeId).set(caffeClass);
//
////        mDatabase.child("caffe").child("caffeId"+String.valueOf(caffeId)).setValue(caffeClass);
//    }
//    public void writeNewNews(String newsId,String imagePath, String title, String discription) {
//        NewsClass news = new NewsClass(newsId, imagePath, title,  discription);
//        db.collection("news").document("newsId"+newsId).set(news);
////        mDatabase.child("News_tabel").child("newsId"+String.valueOf(newsId)).setValue(news);
//    }
//
//    public void writeNewItem(String id, int price, String size, String imagePath, String title, String discription, String category, Boolean isPopular, DocumentReference docPath) {
//        ItemsClass itemsClass= new ItemsClass(id,price,size,imagePath,title,discription,category,isPopular);
////        mDatabase.child("Item_tabel").child("itemId"+String.valueOf(id)).setValue(itemsClass);
////        db.collection("items").document(id).set(itemsClass);
//        docPath.collection("items").document("itemId"+id).set(itemsClass);;
//    }
//    public void writeNewCategory(String id, String imagePath, String title) {
//        CategoryClass categoryClass= new CategoryClass(id, imagePath, title);
////        mDatabase.child("Item_tabel").child("itemCategory"+String.valueOf(id)).setValue(categoryClass);
////            db.collection("category").document("categoryId"+id).set(categoryClass);
//
//    }
//    public void writeNewPrefers(String id, int price, String size, String imagePath, String title, String discription, String category,Boolean isPopular) {
//        ItemsClass itemsClass= new ItemsClass(id,price,size,imagePath,title,discription,category,isPopular);
//        db.collection("users").document("user1").collection("prefers").document("preferId"+id).set(itemsClass);
////        mDatabase.child("Item_tabel").child("itemId"+String.valueOf(id)).setValue(itemsClass);
    }
