package edu.itstep.myapplic04.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;

import java.util.ArrayList;

import edu.itstep.myapplic04.R;
import edu.itstep.myapplic04.models.Basket;
import edu.itstep.myapplic04.models.abstracts.Item;
import edu.itstep.myapplic04.models.items.Pizza;
import edu.itstep.myapplic04.utils.Enums;

public class BasketAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Item> _list;

    public BasketAdapter(Context context, ArrayList<Item> list) {
        this.context = context;
        this._list = list;
    }

    @Override
    public int getCount() {
        return this._list.size();
    }

    @Override
    public Object getItem(int position) {
        return this._list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_basket_item, parent, false);
        }

        ImageView imagePizza = convertView.findViewById(R.id.imageItem);
        TextView textTitle = convertView.findViewById(R.id.textTitle);
        TextView textDescription = convertView.findViewById(R.id.textDescription);
        TextView extras = convertView.findViewById(R.id.extras);
        TextView price = convertView.findViewById(R.id.price);
        AppCompatButton buttonRemove = convertView.findViewById(R.id.buttonRemove);
        AppCompatButton editBtn = convertView.findViewById(R.id.buttonEdit);

        Item item = this._list.get(position);

        imagePizza.setImageResource(getImageResourceByName(item.get_imageName()));
        textTitle.setText(item.get_title() + " (" + ((Pizza)item).get_size().toString()+")");
        textDescription.setText(((Pizza)item).itemsToString(Enums.ItemType.INGREDIENT));
        price.setText(String.valueOf(item.formatPrice()));

        switch (item.get_type()) {
            case PIZZA:
                extras.setText(((Pizza)item).itemsToString(Enums.ItemType.EXTRA));
            break;
        }

        buttonRemove.setOnClickListener(v -> onRemove(item));
        editBtn.setOnClickListener(x-> onEdit(item));

        return convertView;
    }
    private void onEdit(Item item)
    {
        Basket.getInstance().editItem(item);
    }
    private void onRemove(Item item)
    {
        Basket.getInstance().removeItem(item);

    }
    private int getImageResourceByName(String resourceName) {
        return context.getResources().getIdentifier(resourceName, "drawable", context.getPackageName());
    }
}

