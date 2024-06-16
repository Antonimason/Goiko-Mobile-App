package com.example.goiko;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Toast;
import java.util.ArrayList;

public class FeedbackController extends AppCompatActivity {
    // UI elements
    private Button buttonHome;
    private Button buttonOffers;
    private Button buttonProducts;
    private ImageButton buttonCart;
    private EditText etFirstName;
    private EditText etLastName;
    private RatingBar ratingBar;
    private EditText etComment;
    private Button btnSubmit;
    // Lists for passing data
    private ArrayList<BurguerModel> offerList;
    private ArrayList<BurguerModel> shoppingCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);

        // Retrieving data from the intent
        Intent intent = getIntent();
        offerList = (ArrayList<BurguerModel>) intent.getSerializableExtra("offerList");
        shoppingCart = (ArrayList<BurguerModel>) intent.getSerializableExtra("shoppingCart");

        // Finding UI elements
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        ratingBar = findViewById(R.id.ratingBar);
        etComment = findViewById(R.id.etComment);
        btnSubmit = findViewById(R.id.btnSubmit);

        // Button click listener for submitting feedback
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve input values
                String firstName = etFirstName.getText().toString().trim();
                String lastName = etLastName.getText().toString().trim();
                float rating = ratingBar.getRating();
                String comment = etComment.getText().toString().trim();

                // Validate input
                if (firstName.isEmpty() || lastName.isEmpty() || comment.isEmpty()) {
                    Toast.makeText(FeedbackController.this, "Please, Complete all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Handle feedback submission (e.g., save to database or send to server)
                    Toast.makeText(FeedbackController.this, "Thank you for your feedback", Toast.LENGTH_SHORT).show();

                    // Clear input fields after submission
                    etFirstName.setText("");
                    etLastName.setText("");
                    ratingBar.setRating(0);
                    etComment.setText("");
                }
            }
        });

        // Finding other UI elements and setting their click listeners
        buttonHome = findViewById(R.id.home);
        buttonOffers = findViewById(R.id.offers);
        buttonProducts = findViewById(R.id.products);
        buttonCart = findViewById(R.id.cart);

        // Click listener for the cart button to navigate to the cart activity
        buttonCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FeedbackController.this, CartController.class);
                intent.putExtra("shoppingCart", shoppingCart);
                intent.putExtra("offerList", offerList);
                intent.putExtra("productList", (ArrayList<BurguerModel>) getIntent().getSerializableExtra("productList"));
                startActivity(intent);
            }
        });

        // Click listener for the offers button to navigate to the offers activity
        buttonOffers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FeedbackController.this, OffersController.class);
                intent.putExtra("offerList", offerList);
                intent.putExtra("shoppingCart", shoppingCart);
                intent.putExtra("productList", (ArrayList<BurguerModel>) getIntent().getSerializableExtra("productList"));
                startActivity(intent);
            }
        });

        // Click listener for the products button to navigate to the products activity
        buttonProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FeedbackController.this, ProductsController.class);
                intent.putExtra("productList", (ArrayList<BurguerModel>) getIntent().getSerializableExtra("productList"));
                intent.putExtra("shoppingCart", shoppingCart);
                intent.putExtra("offerList", offerList);
                startActivity(intent);
            }
        });

        // Click listener for the home button to navigate to the main activity
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FeedbackController.this, HomeController.class);
                intent.putExtra("productList", (ArrayList<BurguerModel>) getIntent().getSerializableExtra("productList"));
                intent.putExtra("shoppingCart", shoppingCart);
                intent.putExtra("offerList", offerList);
                startActivity(intent);
            }
        });
    }
}

