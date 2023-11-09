package edu.itstep.myapplic04.adapters;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import edu.itstep.myapplic04.R;
import edu.itstep.myapplic04.models.Basket;
import edu.itstep.myapplic04.models.items.Pizza;

public class PizzaAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Pizza> pizzaList; // Replace 'Pizza' with your data model class

    public PizzaAdapter(Context context, ArrayList<Pizza> pizzaList) {
        this.context = context;
        this.pizzaList = pizzaList;
    }

    @Override
    public int getCount() {
        return pizzaList.size();
    }

    @Override
    public Object getItem(int position) {
        return pizzaList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_pizza_item, parent, false);
        }

        ImageView imagePizza = convertView.findViewById(R.id.imagePizza);
        TextView textTitle = convertView.findViewById(R.id.textTitle);
        TextView textDescription = convertView.findViewById(R.id.textDescription);
        TextView price = convertView.findViewById(R.id.price);
        Button buttonAdd = convertView.findViewById(R.id.buttonAdd);

        Pizza pizza = pizzaList.get(position);

        imagePizza.setImageResource(getImageResourceByName(pizza.get_imageName()));
        textTitle.setText(pizza.get_title());
        textDescription.setText(pizza.get_description());
        price.setText(String.valueOf(pizza.formatPrice()));

        buttonAdd.setOnClickListener(v -> onPizzaAdd(pizza));

        return convertView;
    }

    private void onPizzaAdd(Pizza pizza)
    {
        Basket.getInstance().addItem(pizza);

        Log.v(TAG, Arrays.toString(Basket.getInstance().get_items().stream().map(x->x.get_title()).toArray()));
    }
    private int getImageResourceByName(String resourceName) {
        return context.getResources().getIdentifier(resourceName, "drawable", context.getPackageName());
    }
}
