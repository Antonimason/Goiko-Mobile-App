package com.example.goiko;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import android.widget.Toast;

public class CartController extends AppCompatActivity {
    // Declaring UI elements and lists
    private Button buttonHome;
    private Button buttonOffers;
    private Button buttonProducts;
    private Button buttonFeedback;
    private Button orderButton;
    private TextView totalTextView;
    private ArrayList<BurguerModel> shoppingCart;
    private ArrayList<BurguerModel> productList;
    private ArrayList<BurguerModel> offerList;
    private BurguerAdapterModel cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_cart);

        // Get the intent and retrieve the data passed from the previous activity
        Intent intent = getIntent();
        offerList = (ArrayList<BurguerModel>) intent.getSerializableExtra("offerList");
        shoppingCart = (ArrayList<BurguerModel>) intent.getSerializableExtra("shoppingCart");
        productList = (ArrayList<BurguerModel>) getIntent().getSerializableExtra("productList");

        // Set up the ListView and its adapter
        ListView cartListView = findViewById(R.id.shoppingList);
        cartAdapter = new BurguerAdapterModel(this, shoppingCart, shoppingCart);
        cartListView.setAdapter(cartAdapter);
        // Set up the totalTextView and calculate the total price
        totalTextView = findViewById(R.id.totalTextView);
        cartAdapter.setTotalTextView(totalTextView);
        updateTotal();

        // Initialize buttons
        buttonHome = findViewById(R.id.home);
        buttonOffers = findViewById(R.id.offers);
        buttonProducts = findViewById(R.id.products);
        buttonFeedback = findViewById(R.id.feedback);
        orderButton = findViewById(R.id.orderButton);

        // Set up the onClick listener for the order button
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shoppingCart.isEmpty()) {
                    // Show a message if the cart is empty
                    Toast.makeText(CartController.this, "Your cart is empty. Please add items to order.", Toast.LENGTH_LONG).show();
                } else {
                    // Show a success message if the order is placed
                    Toast.makeText(CartController.this, "Your order has been placed successfully!", Toast.LENGTH_LONG).show();
                    // Clear the shopping cart after a successful order
                    shoppingCart.clear();
                    cartAdapter.notifyDataSetChanged();
                    updateTotal();
                }
            }
        });

        // Set up the onClick listener for the offers button
        buttonOffers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartController.this, OffersController.class);
                intent.putExtra("shoppingCart", shoppingCart);
                intent.putExtra("offerList", offerList);
                intent.putExtra("productList", productList);
                startActivity(intent);
            }
        });

        // Set up the onClick listener for the products button
        buttonProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartController.this, ProductsController.class);
                intent.putExtra("shoppingCart", shoppingCart);
                intent.putExtra("offerList", offerList);
                intent.putExtra("productList", productList);
                startActivity(intent);
            }
        });

        // Set up the onClick listener for the home button
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartController.this, HomeController.class);
                intent.putExtra("shoppingCart", shoppingCart);
                intent.putExtra("offerList", offerList);
                intent.putExtra("productList", productList);
                startActivity(intent);
            }
        });

        // Set up the onClick listener for the feedback button
        buttonFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartController.this, FeedbackController.class);
                intent.putExtra("shoppingCart", shoppingCart);
                intent.putExtra("offerList", offerList);
                intent.putExtra("productList", productList);
                startActivity(intent);
            }
        });
    }

    // Method to update the total price displayed
    private void updateTotal() {
        double total = cartAdapter.calculateTotal();
        totalTextView.setText(String.format("Total: €%.2f", total));
    }
}

