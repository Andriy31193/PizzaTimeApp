package edu.itstep.myapplic04.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import edu.itstep.myapplic04.R;
import edu.itstep.myapplic04.adapters.BasketAdapter;
import edu.itstep.myapplic04.listeners.BasketListener;
import edu.itstep.myapplic04.models.Basket;
import edu.itstep.myapplic04.models.abstracts.Item;
import edu.itstep.myapplic04.utils.Enums;

public class BasketActivity extends AppCompatActivity implements BasketListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        setContentView(R.layout.activity_basket);

        setListeners();
        displayItems();
    }
    private void init()
    {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    }
    private void setListeners()
    {
        Basket.getInstance().addBasketListener(this);

        Button backBtn = findViewById(R.id.buttonBack);
        backBtn.setOnClickListener(x-> onGoBack());

        AppCompatButton checkoutBtn = findViewById(R.id.buttonOrder);
        checkoutBtn.setOnClickListener(x -> onOrder());

    }
    @SuppressLint("SetTextI18n")
    private void displayItems() {
        ArrayList<Item> items = Basket.getInstance().get_items();

        ListView listView = findViewById(R.id.listView);
        BasketAdapter adapter = new BasketAdapter(this, items);
        listView.setAdapter(adapter);

        TextView titleText = findViewById(R.id.textViewTitle);
        titleText.setText(items.isEmpty() ? getString(R.string.nothing_s_here) : getString(R.string.your_basket_has) + items.size() + " " + getString(R.string.items)+":");


        Button btnOrder = findViewById(R.id.buttonOrder);
        LinearLayout checkoutPanel = findViewById(R.id.checkoutPanel);
        btnOrder.setText(getString(R.string.go_to_checkout)+" " + Basket.getInstance().formatTotal());
        checkoutPanel.setVisibility(items.isEmpty() ? View.INVISIBLE : View.VISIBLE);
        btnOrder.setOnClickListener(x -> onOrder());
    }
    private void onOrder()
    {
        EditText name = findViewById(R.id.editTextName);
        EditText phone = findViewById(R.id.editTextPhoneNumber);


        Intent intent = new Intent(this, OrderStatusActivity.class);

        intent.putExtra("customerName", name.getText().toString());
        intent.putExtra("customerPhone", phone.getText().toString());
        startActivity(intent);
    }
    private void onGoBack()
    {
        Intent intent = new Intent(this, OrderPizzaActivity.class);
        startActivity(intent);
    }
    @Override
    public void onBasketUpdated(Enums.EventType type, Item item) {
        displayItems();
    }


}
