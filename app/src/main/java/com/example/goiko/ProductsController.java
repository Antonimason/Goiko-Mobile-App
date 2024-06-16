package com.example.goiko;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ProductsController extends AppCompatActivity {

    // Declaring UI elements and lists
    private Button buttonHome;
    private Button buttonOffers;
    private Button buttonFeedback;
    private ImageButton buttonCart;
    private ArrayList<BurguerModel> productList;
    private ArrayList<BurguerModel> shoppingCart;
    private ArrayList<BurguerModel> offerList;
    private BurguerAdapterModel productsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.products);

        // Retrieving data from the intent
        Intent intent = getIntent();
        productList = (ArrayList<BurguerModel>) intent.getSerializableExtra("productList");
        shoppingCart = (ArrayList<BurguerModel>) intent.getSerializableExtra("shoppingCart");
        offerList = (ArrayList<BurguerModel>) intent.getSerializableExtra("offerList");

        // Setting up the ListView and its adapter
        ListView productsListView = findViewById(R.id.productsList);
        productsAdapter = new BurguerAdapterModel(this, productList, shoppingCart);
        productsListView.setAdapter(productsAdapter);

        // Finding UI buttons and setting their click listeners
        buttonHome = findViewById(R.id.home);
        buttonOffers = findViewById(R.id.offers);
        buttonFeedback = findViewById(R.id.feedback);
        buttonCart = findViewById(R.id.cart);

        // Click listener for the cart button to navigate to the cart activity
        buttonCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductsController.this, CartController.class);
                intent.putExtra("shoppingCart", shoppingCart);
                intent.putExtra("offerList", offerList);
                intent.putExtra("productList", productList);
                startActivity(intent);
            }
        });

        // Click listener for the offers button to navigate to the offers activity
        buttonOffers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductsController.this, OffersController.class);
                intent.putExtra("offerList", offerList);
                intent.putExtra("shoppingCart", shoppingCart);
                intent.putExtra("productList", productList);
                startActivity(intent);
            }
        });

        // Click listener for the home button to navigate to the main activity
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductsController.this, HomeController.class);
                intent.putExtra("shoppingCart", shoppingCart);
                intent.putExtra("offerList", offerList);
                intent.putExtra("productList", productList);
                startActivity(intent);
            }
        });

        // Click listener for the feedback button to navigate to the feedback activity
        buttonFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductsController.this, FeedbackController.class);
                intent.putExtra("shoppingCart", shoppingCart);
                intent.putExtra("offerList", offerList);
                intent.putExtra("productList", productList);
                startActivity(intent);
            }
        });
    }
}

