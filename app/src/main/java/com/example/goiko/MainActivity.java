package com.example.goiko;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

//
public class MainActivity extends AppCompatActivity {

    private ArrayList<BurguerModel> productList = new ArrayList<>();
    private ArrayList<BurguerModel> offerList = new ArrayList<>();
    private ArrayList<BurguerModel> shoppingCart = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BurguerModel kevinBacon = new BurguerModel("Kevin Bacon","200gr beef, cheese, crispy onions, bacon bits",R.drawable.kevinbacon,14.50);
        BurguerModel yankee = new BurguerModel("Yankee","200gr beef, ribs, cheese, grilled onions, lettuce, BBQ sauce",R.drawable.yankee,18.50);
        BurguerModel pigma = new BurguerModel("Pigma","200gr beef, cheese, bacon, egg, mayo sauce",R.drawable.pigma,14.00);
        BurguerModel m30 = new BurguerModel("M-30","200gr beef, goat's cheese, caramelized onions",R.drawable.m30,13.50);
        BurguerModel chipotle = new BurguerModel("Chipotle","200gr beef, guacamole, chipotle sauce",R.drawable.chipotle,10.50);
        BurguerModel smashed = new BurguerModel("Smashed","x2 120gr beef, cheese, bacon, grilled onions",R.drawable.smashed,11.00);
        BurguerModel kiki = new BurguerModel("Kiki","x2 fried chicken, cheese, bacon, iceberg lettuce, mayo sauce",R.drawable.kiki,12.30);
        BurguerModel retro = new BurguerModel("Retro","200gr beef, cheese, bacon, tomato, pickles, lettuce, BQQ sauce, mayo sauce",R.drawable.retro,13.00);

        productList.add(kevinBacon);
        productList.add(yankee);
        productList.add(pigma);
        productList.add(m30);

        offerList.add(chipotle);
        offerList.add(smashed);
        offerList.add(kiki);
        offerList.add(retro);

        Intent intent = new Intent(MainActivity.this, HomeController.class);
        intent.putExtra("productList", productList);
        intent.putExtra("offerList", offerList);
        intent.putExtra("shoppingCart", shoppingCart);
        startActivity(intent);

    }
}
