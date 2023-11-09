package edu.itstep.myapplic04.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import edu.itstep.myapplic04.R;
import edu.itstep.myapplic04.models.Basket;
import edu.itstep.myapplic04.models.Order;
import edu.itstep.myapplic04.utils.PizzaUtils;

public class OrderStatusActivity extends AppCompatActivity {

    private Order _order = new Order();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        setContentView(R.layout.activity_order_status);

        getData();
        setData();

    }
    private void init()
    {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    }
    private void getData() {
        Intent intent = getIntent();
        if (intent != null) {
            _order.set_customerName(intent.getStringExtra("customerName"));
            _order.set_customerPhone(intent.getStringExtra("customerPhone"));
        }
        _order.set_customerOrder(Basket.getInstance().get_items());

    }
    private void setData() {
        TextView thankYou = findViewById(R.id.thankYou);
        TextView titleText = findViewById(R.id.titleText);
        TextView order = findViewById(R.id.orderTV);
        AppCompatButton back = findViewById(R.id.backBtn);

        thankYou.setText("THANK YOU, " + _order.get_customerName());
        titleText.setText("We'll notify you on your phone " + _order.get_customerPhone() + " as soon as your order is ready to be delivered :)");
        order.setText(PizzaUtils.itemToString(_order.get_customerOrder()));
        back.setOnClickListener(x->onBack());
    }
    private  void onBack()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings, menu);
        return true;
    }
}
