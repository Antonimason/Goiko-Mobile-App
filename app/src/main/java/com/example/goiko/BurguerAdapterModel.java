package com.example.goiko;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

// Code review from Abhishek (2021). Adapter Tutorial With Example In Android Studio | Abhi Android. [online] AbhiAndroid.com. Available at: https://abhiandroid.com/ui/adapter#gsc.tab=0.
public class BurguerAdapterModel extends BaseAdapter {
    private Context context;
    private List<BurguerModel> burguerList;
    private List<BurguerModel> carrito;
    private TextView totalTextView;

    // Constructor to initialize the adapter with context, list of burgers, and the shopping cart
    public BurguerAdapterModel(Context context, List<BurguerModel> burguerList, List<BurguerModel> carrito) {
        this.context = context;
        this.burguerList = burguerList;
        this.carrito = carrito;
    }

    @Override
    public int getCount() {
        // Returns the number of items in the burger list
        return burguerList.size();
    }

    @Override
    public Object getItem(int position) {
        // Returns the burger at the specified position
        return burguerList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // Returns the position as the ID (not used in this case)
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            // Inflate the layout for each item in the list if it hasn't been created yet
            convertView = LayoutInflater.from(context).inflate(R.layout.burguer_model, parent, false);

            // Find the add and remove buttons
            Button addButton = convertView.findViewById(R.id.burguerOrder);
            Button removeButton = convertView.findViewById(R.id.removeBurguer);

            // Check if it's the cart view or other view
            if (context instanceof CartController) {
                // Hide the add button and show the remove button
                addButton.setVisibility(View.GONE);
                removeButton.setVisibility(View.VISIBLE);
            } else {
                // Show the add button and hide the remove button
                addButton.setVisibility(View.VISIBLE);
                removeButton.setVisibility(View.GONE);
            }
        }

        // Get the current burger item
        BurguerModel burguer = (BurguerModel) getItem(position);

        // Find and set the burger picture
        ImageView imageView = convertView.findViewById(R.id.burguerPicture);
        imageView.setImageResource(burguer.getImageResId());

        // Find and set the burger name
        TextView nameTextView = convertView.findViewById(R.id.burguerName);
        nameTextView.setText(burguer.getName());

        // Find and set the burger description
        TextView descriptionTextView = convertView.findViewById(R.id.burguerIngredients);
        descriptionTextView.setText(burguer.getDescription());

        // Find and set the burger price
        TextView priceTextView = convertView.findViewById(R.id.burguerPrice);
        priceTextView.setText(String.format("€%.2f", burguer.getPrice()));

        // Set click listeners for the add and remove buttons
        Button addButton = convertView.findViewById(R.id.burguerOrder);
        Button removeButton = convertView.findViewById(R.id.removeBurguer);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add the burger to the cart and show a confirmation message
                carrito.add(burguer);
                notifyDataSetChanged();
                Toast.makeText(context, "Burger added to cart", Toast.LENGTH_SHORT).show();
            }
        });

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remove the burger from the cart and show a confirmation message
                carrito.remove(burguer);
                notifyDataSetChanged();
                Toast.makeText(context, "Burger removed from cart", Toast.LENGTH_SHORT).show();
                // Update the total
                if (totalTextView != null) {
                    double total = calculateTotal();
                    totalTextView.setText(String.format("Total: €%.2f", total));
                }

            }
        });

        return convertView;
    }
    // Method to calculate the total price of items in the shopping cart
    public double calculateTotal() {
        double total = 0.0;
        for (BurguerModel burger : carrito) {
            total += burger.getPrice();
        }
        return total;
    }

    // Setter of TotalTextView
    public void setTotalTextView(TextView totalTextView) {
        this.totalTextView = totalTextView;
    }
}
