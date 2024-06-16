package com.example.goiko;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Button;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class OffersController extends AppCompatActivity{
    // Declaring UI elements and lists
    private Button buttonHome;
    private Button buttonProducts;
    private Button buttonFeedback;
    private ImageButton buttonCart;
    private ArrayList<BurguerModel> offerList;
    private ArrayList<BurguerModel> shoppingCart;
    private BurguerAdapterModel offersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.offers);

        // Retrieving data from the intent
        Intent intent = getIntent();
        offerList = (ArrayList<BurguerModel>) intent.getSerializableExtra("offerList");
        shoppingCart = (ArrayList<BurguerModel>) intent.getSerializableExtra("shoppingCart");

        // Setting up the ListView and its adapter
        ListView offersListView = findViewById(R.id.offersList);
        offersAdapter = new BurguerAdapterModel(this, offerList, shoppingCart);
        offersListView.setAdapter(offersAdapter);

        // Finding UI buttons and setting their click listeners
        buttonHome = findViewById(R.id.home);
        buttonProducts = findViewById(R.id.products);
        buttonFeedback = findViewById(R.id.feedback);
        buttonCart = findViewById(R.id.cart);

        // Click listener for the cart button to navigate to the cart activity
        buttonCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OffersController.this, CartController.class);
                intent.putExtra("offerList", offerList);
                intent.putExtra("productList", (ArrayList<BurguerModel>) getIntent().getSerializableExtra("productList"));
                intent.putExtra("shoppingCart", shoppingCart);
                startActivity(intent);
            }
        });

        // Click listener for the products button to navigate to the products activity
        buttonProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OffersController.this, ProductsController.class);
                intent.putExtra("offerList", offerList);
                intent.putExtra("productList", (ArrayList<BurguerModel>) getIntent().getSerializableExtra("productList"));
                intent.putExtra("shoppingCart", shoppingCart);
                startActivity(intent);
            }
        });

        // Click listener for the home button to navigate to the main activity
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OffersController.this, HomeController.class);
                intent.putExtra("offerList", offerList);
                intent.putExtra("productList", (ArrayList<BurguerModel>) getIntent().getSerializableExtra("productList"));
                intent.putExtra("shoppingCart", shoppingCart);
                startActivity(intent);
            }
        });

        // Click listener for the feedback button to navigate to the feedback activity
        buttonFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OffersController.this, FeedbackController.class);
                intent.putExtra("offerList", offerList);
                intent.putExtra("productList", (ArrayList<BurguerModel>) getIntent().getSerializableExtra("productList"));
                intent.putExtra("shoppingCart", shoppingCart);
                startActivity(intent);
            }
        });
    }
}
