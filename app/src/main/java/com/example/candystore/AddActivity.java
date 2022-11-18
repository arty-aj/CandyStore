package com.example.candystore;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.candystore.databinding.ActivityMainBinding;

public class AddActivity extends AppCompatActivity {

    private DatabaseManager dbManager;

    //private AppBarConfiguration appBarConfiguration;
    //private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        dbManager = new DatabaseManager(this);
    }

    public void add(View v){
        EditText priceET = findViewById(R.id.input_price);
        String priceString = priceET.getText().toString();

        EditText nameET = findViewById(R.id.input_name);
        String nameString = nameET.getText().toString();

        try {
            double price = Double.parseDouble(priceString);
            Candy candy = new Candy(0, nameString, price);
            dbManager.insertCandy(candy);
            Toast.makeText(this, nameString + " has been inserted to dbTable", Toast.LENGTH_LONG).show();
        }catch (NumberFormatException ne){
            Toast.makeText(this, "Price Error", Toast.LENGTH_LONG).show();
        }

        //clearEdit Text
        priceET.setText("");
        nameET.setText("");

    }

    public void goBack(View v){
        this.finish();
    }

}