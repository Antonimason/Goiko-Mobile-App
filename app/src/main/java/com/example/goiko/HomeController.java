package com.example.goiko;
import android.content.Intent;
import android.os.Bundle;
import android.net.Uri;
import android.widget.ImageButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class HomeController extends AppCompatActivity{
    // Declaring UI elements and lists
    private Button buttonOffers; // Declare a Button variable for Offers button
    private Button buttonProducts; // Declare a Button variable for Products button
    private Button buttonFeedback; // Declare a Button variable for Feedback button
    private ImageButton buttonCart; // Declare a ImageButton variable for shopping cart
    private ImageView google;
    private ArrayList<BurguerModel> productList = new ArrayList<>();
    private ArrayList<BurguerModel> offerList = new ArrayList<>();
    private ArrayList<BurguerModel> shoppingCart = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Retrieving data from the intent
        Intent intent = getIntent();
        productList = (ArrayList<BurguerModel>) intent.getSerializableExtra("productList");
        shoppingCart = (ArrayList<BurguerModel>) intent.getSerializableExtra("shoppingCart");
        offerList = (ArrayList<BurguerModel>) intent.getSerializableExtra("offerList");

        // Finding UI buttons and setting their click listeners
        google = findViewById(R.id.google);
        buttonOffers = findViewById(R.id.offers);
        buttonProducts = findViewById(R.id.products);
        buttonFeedback = findViewById(R.id.feedback);
        buttonCart = findViewById(R.id.cart);

        // Click listener for Google Map
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://maps.google.com";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
        // Click listener for the cart button to navigate to the cart activity
        buttonCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeController.this, CartController.class);
                intent.putExtra("productList", productList);
                intent.putExtra("offerList", offerList);
                intent.putExtra("shoppingCart", shoppingCart);
                startActivity(intent);
            }
        });
        // Initialize buttonOffer and set click listener to start OfferView activity
        buttonOffers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeController.this, OffersController.class);
                intent.putExtra("productList", productList);
                intent.putExtra("offerList", offerList);
                intent.putExtra("shoppingCart", shoppingCart);
                startActivity(intent);
            }
        });

        // Initialize buttonProducts and set click listener to start ProductsView activity
        buttonProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeController.this, ProductsController.class);
                intent.putExtra("productList", productList);
                intent.putExtra("offerList", offerList);
                intent.putExtra("shoppingCart", shoppingCart);
                startActivity(intent);
            }
        });

        // Initialize buttonFeedback and set click listener to start FeedbackView activity
        buttonFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeController.this, FeedbackController.class);
                intent.putExtra("productList", productList);
                intent.putExtra("offerList", offerList);
                intent.putExtra("shoppingCart", shoppingCart);
                startActivity(intent);
            }
        });
    }
}
