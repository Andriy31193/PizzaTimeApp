package edu.itstep.myapplic04.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;

import edu.itstep.myapplic04.R;
import edu.itstep.myapplic04.models.Basket;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Basket(new ArrayList<>());


        Intent intent = new Intent(this, OrderPizzaActivity.class);
        startActivity(intent);
    }
}