package com.example.candystore;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.candystore.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id){
            case R.id.action_add:
                Intent addActivity = new Intent(this, AddActivity.class);
                this.startActivity(addActivity);
                return true;
            case R.id.action_delete:
                Intent deleteActivity = new Intent(this, DeleteActivity.class);
                this.startActivity(deleteActivity);
                return true;
            case R.id.action_update:
                Intent updateActivity = new Intent(this, UpdateActivity.class);
                this.startActivity(updateActivity);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
        /*
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            //used to connect this activity to AddActivity class
            Intent addIntent = new Intent(this, AddActivity.class);
            this.startActivity(addIntent);
            return true;
        }
        if (id == R.id.action_delete) {
            //used to connect this activity to AddActivity class
            Intent deleteIntent = new Intent(this, DeleteActivity.class);
            this.startActivity(deleteIntent);
            return true;
        }
        if (id == R.id.action_update){
            Intent updateIntent = new Intent(this, UpdateActivity.class);
            this.startActivity(updateIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
        */
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}