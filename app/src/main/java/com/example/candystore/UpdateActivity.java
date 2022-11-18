package com.example.candystore;

import android.graphics.Point;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {
    DatabaseManager dbManager;

    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        dbManager = new DatabaseManager(this);
        updateView();
    }

    //build view with the candies
    public void updateView() {
        ArrayList<Candy> candies = dbManager.selectAll();
        RelativeLayout layout = new RelativeLayout(this);

        if(candies.size() > 0){
            //create the rows and grid view
            ScrollView scrollView = new ScrollView(this);
            GridLayout grid = new GridLayout(this);
            grid.setRowCount(candies.size() + 1);
            grid.setColumnCount(4);

            //create arrays of components
            TextView [] ids = new TextView[candies.size()];
            EditText [][] namesAndPrices = new EditText[candies.size()][2];
            Button [] buttons = new Button[candies.size()];
            ButtonHandler bh = new ButtonHandler();

            //retrieves width of screen
            Point size = new Point();
            getWindowManager().getDefaultDisplay().getSize(size);
            int width = size.x;

            int i = 0;

            for(Candy candy : candies){
                //create textview for candy id
                ids[i] = new TextView(this);
                ids[i].setGravity(Gravity.CENTER);
                ids[i].setText("" + candy.getId());

                //create two edittext for the candy's name and price
                namesAndPrices[i][0] = new EditText(this);
                namesAndPrices[i][1] = new EditText(this);
                namesAndPrices[i][0].setText(candy.getName());
                namesAndPrices[i][1].setText("" +candy.getPrice());
                namesAndPrices[i][1].setInputType(InputType.TYPE_CLASS_NUMBER);
                //play with these numbers, idk why we multiply by 10
                namesAndPrices[i][0].setId(10 * candy.getId());
                namesAndPrices[i][1].setId(10 * candy.getId() + 1);

                //create buttons
                buttons[i] = new Button(this);
                buttons[i].setText("Update");
                buttons[i].setId(candy.getId());

                // set up event handling
                buttons[i].setOnClickListener(bh);

                //add elements to the grid
                grid.addView(ids[i], width/10,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(namesAndPrices[i][0], (int) (width * .4),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(namesAndPrices[i][1], (int) (width * .15),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(buttons[i], (int) (width * .35),
                        ViewGroup.LayoutParams.WRAP_CONTENT);

                i++;
            }
            // create a back button
            Button backButton = new Button(this);
            backButton.setText(R.string.button_back);

            backButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    UpdateActivity.this.finish();
                }
            });

            scrollView.addView(grid);
            layout.addView(scrollView);

            // add back button at bottom
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            params.addRule(RelativeLayout.CENTER_HORIZONTAL);
            params.setMargins(0, 0, 0, 50);
            layout.addView(backButton, params);

            setContentView(layout);
        }
    }

    private class ButtonHandler implements View.OnClickListener{
        public void onClick(View v){
            //retrieve name and price of candy
            int candyId = v.getId();
            EditText nameET = (EditText) findViewById(10 * candyId);
            EditText priceET = (EditText) findViewById(10 * candyId + 1);
            String name = nameET.getText().toString();
            String priceString = priceET.getText().toString();

            //update candy in database
            try{
                double price = Double.parseDouble(priceString);
                dbManager.updateById(name, candyId, price);
                Toast.makeText(UpdateActivity.this, "Candy updated",
                        Toast.LENGTH_SHORT).show();
            } catch (NumberFormatException nfe){
                Toast.makeText(UpdateActivity.this, "Price error",
                        Toast.LENGTH_LONG).show();
            }

        }
    }

}
