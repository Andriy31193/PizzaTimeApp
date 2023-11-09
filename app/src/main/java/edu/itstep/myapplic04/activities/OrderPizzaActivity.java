package edu.itstep.myapplic04.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import edu.itstep.myapplic04.R;
import edu.itstep.myapplic04.adapters.PizzaAdapter;
import edu.itstep.myapplic04.listeners.BasketListener;
import edu.itstep.myapplic04.models.Basket;
import edu.itstep.myapplic04.models.abstracts.Item;
import edu.itstep.myapplic04.models.items.Pizza;
import edu.itstep.myapplic04.utils.Enums;
import edu.itstep.myapplic04.utils.PizzaUtils;

public class OrderPizzaActivity extends AppCompatActivity implements BasketListener {

    private TextView _basketCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        setContentView(R.layout.activity_order_pizza);

        getViews();
        setListeners();
        displayPizzas();
    }

    private void getViews()
    {
        _basketCount = findViewById(R.id.basketCount);
    }
    private void setListeners()
    {
        Basket.getInstance().addBasketListener(this);

        AppCompatButton btn = findViewById(R.id.basketBtn);
        btn.setOnClickListener(x -> showBasket());

        Button myBasketBtn = findViewById(R.id.buttonOrder);
        myBasketBtn.setOnClickListener(x->showBasket());
    }
    private void displayPizzas()
    {
        updateBasket();

        ArrayList<Pizza> pizzaList;

        Intent intent = getIntent();
        if (intent != null)
            pizzaList = PizzaUtils.getFromJson(PizzaUtils.getFileData(getResources().openRawResource(R.raw.data)));
        else {
            Toast.makeText(this, "Unknown error.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (pizzaList.isEmpty()) {
            Toast.makeText(this, "No pizzas found.", Toast.LENGTH_SHORT).show();
        } else {
            ListView listView = findViewById(R.id.listViewPizzaChoices);
            PizzaAdapter adapter = new PizzaAdapter(this, pizzaList);
            listView.setAdapter(adapter);
        }
    }
    private void editItem(Item item)
    {
        Intent intent = new Intent(this, PizzaOptionsActivity.class);
        intent.putExtra("basket_item_id", Basket.getInstance().get_items().indexOf(item));
        startActivity(intent);
    }
    private void updateBasket() { _basketCount.setText((String.valueOf(Basket.getInstance().get_items().size())));}
    @Override
    public void onBasketUpdated(Enums.EventType type, Item item) {

        switch (type) {
            case ADD:
                case REMOVE:
                updateBasket();
                break;
            case EDIT:
                editItem(item);
                break;
        }
    }
    private  void showBasket()
    {
        Intent intent = new Intent(this, BasketActivity.class);
        startActivity(intent);
    }
}