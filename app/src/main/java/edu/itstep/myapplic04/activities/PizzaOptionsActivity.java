package edu.itstep.myapplic04.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import edu.itstep.myapplic04.R;
import edu.itstep.myapplic04.adapters.PizzaOptionsAdapter;
import edu.itstep.myapplic04.models.Basket;
import edu.itstep.myapplic04.models.abstracts.Item;
import edu.itstep.myapplic04.models.items.Pizza;
import edu.itstep.myapplic04.utils.Enums;
import edu.itstep.myapplic04.utils.PizzaUtils;

public class PizzaOptionsActivity extends AppCompatActivity {

    private Pizza _currentPizza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideBar();
        setContentView(R.layout.activity_pizza_options);

        init();
        setListeners();

    }

    private void hideBar()
    {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

    }
    private void init()
    {
        onSetPizzaSize(Enums.PizzaSize.MEDIUM);
        int basketItemId = -1;

        Intent intent = getIntent();
        if (intent != null) {
            basketItemId = intent.getIntExtra("basket_item_id", -1);
            if(basketItemId == -1)
                return;
        }

        _currentPizza = (Pizza)Basket.getInstance().get_items().get(basketItemId);

        TextView titleText = findViewById(R.id.textTitle);
        TextView descriptionText = findViewById(R.id.textDescription);
        ImageView image = findViewById(R.id.imagePizza);

        titleText.setText(_currentPizza.get_title());
        descriptionText.setText(_currentPizza.get_description());
        image.setImageResource(getImageResourceByName(_currentPizza.get_imageName()));

        ArrayList<Item> ingredients = new ArrayList<>(), extras = new ArrayList<>();

        ingredients.addAll(_currentPizza.get_ingredients());
        extras.addAll(_currentPizza.get_extras());

        RecyclerView listView = findViewById(R.id.listViewIng);
        RecyclerView listViewExtras = findViewById(R.id.listViewExtras);

        LinearLayoutManager layoutManager= new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManagerExtras = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        PizzaOptionsAdapter adapter_ingredients = new PizzaOptionsAdapter(Enums.ItemType.INGREDIENT, this, ingredients, _currentPizza);
        PizzaOptionsAdapter adapter_extras = new PizzaOptionsAdapter(Enums.ItemType.EXTRA, this, extras, _currentPizza);

        listView.setLayoutManager(layoutManager);
        listViewExtras.setLayoutManager(layoutManagerExtras);

        listView.setAdapter(adapter_ingredients);
        listViewExtras.setAdapter(adapter_extras);
    }
    private void setListeners()
    {

        AppCompatButton editIngBtn = findViewById(R.id.editIngredientsBtn);

        for (int i = 0; i < 3; i++) {
            final int index = i;
            int id = getResources().getIdentifier("select_size_btn_" + (index+1), "id", getPackageName());

            AppCompatButton btn = findViewById(id);
            btn.setOnClickListener(x -> onSetPizzaSize(Enums.PizzaSize.values()[index]));
        }

        editIngBtn.setOnClickListener(x->onEditIngredients(editIngBtn));
        // PIZZA SIZES //

        AppCompatButton btnDone = findViewById(R.id.buttonNext);
        btnDone.setOnClickListener(x -> onEditDone());
    }
    private void onSetPizzaSize(Enums.PizzaSize pizzaSize)
    {
        for (int i = 0; i < 3; i++) {
            int id = getResources().getIdentifier("select_size_btn_" + (i + 1), "id", getPackageName());
            AppCompatButton btn = findViewById(id);

            if(btn == null)
                continue;

            btn.setEnabled(true);
            btn.setTextColor(Color.WHITE);
            btn.setBackgroundColor(Color.rgb(255, 183, 3));
            btn.setText("SELECT");
        }

        int id = getResources().getIdentifier("select_size_btn_" + (pizzaSize.ordinal()+1), "id", getPackageName());
        AppCompatButton btn = findViewById(id);
        btn.setEnabled(false);
        btn.setTextColor(Color.GREEN);
        btn.setBackgroundColor(Color.rgb(255, 133, 3));
        btn.setText("✔");

        if(_currentPizza != null)
            _currentPizza.set_size(pizzaSize);
    }
    private void onEditDone()
    {
        Log.d("BASKET", Basket.getInstance().toString());

        Intent intent = new Intent(this, BasketActivity.class);
        startActivity(intent);
    }
    private void onEditIngredients(AppCompatButton btn) {

        btn.setVisibility(View.GONE);
        LinearLayout el = findViewById(R.id.editIngredientsLayout);
        el.setVisibility(View.VISIBLE);

    }
    private int getImageResourceByName(String resourceName) {
        return getResources().getIdentifier(resourceName, "drawable", getPackageName());
    }
}