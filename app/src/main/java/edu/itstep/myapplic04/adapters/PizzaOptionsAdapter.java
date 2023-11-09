package edu.itstep.myapplic04.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.stream.Collectors;

import edu.itstep.myapplic04.R;
import edu.itstep.myapplic04.models.Basket;
import edu.itstep.myapplic04.models.abstracts.Item;
import edu.itstep.myapplic04.models.items.Extra;
import edu.itstep.myapplic04.models.items.Ingredient;
import edu.itstep.myapplic04.models.items.Pizza;
import edu.itstep.myapplic04.utils.Enums;
import edu.itstep.myapplic04.views.PizzaOptionsViewHolder;

public class PizzaOptionsAdapter extends RecyclerView.Adapter<PizzaOptionsViewHolder> {

    private Pizza _currentPizzaItem;
    private Enums.ItemType _itemsType;
    private Context context;
    private ArrayList<Item> _items;

    public PizzaOptionsAdapter(Enums.ItemType type, Context context, ArrayList<Item> items, Pizza pizza) {
        this.context = context;
        this._items = items;
        this._currentPizzaItem = pizza;
        this._itemsType = type;
    }

    @Override
    public PizzaOptionsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_pizza_option, parent, false);

        return new PizzaOptionsViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(PizzaOptionsViewHolder holder, int position) {
        Item item = this._items.get(position);

        int imageResource = getImageResourceByName(item.get_imageName());

        holder.getImagePizza().setImageResource(imageResource);
        holder.getTextDescription().setText(item.get_title() + (item.get_type() == Enums.ItemType.EXTRA ? "("+item.formatPrice()+")" : ""));

        holder.getCheckBox().setChecked(item.isIncluded());
        holder.getCheckBox().setOnClickListener(v -> onSelectItem(item));


    }

    @Override
    public int getItemCount() {
        return this._items.size();
    }

    private void onSelectItem(Item item) {

        switch (_itemsType) {
            case INGREDIENT:
            if (_currentPizzaItem.getIngredient(item).isIncluded()) {
                _currentPizzaItem.remove_ingredient(item);
            } else {
                _currentPizzaItem.add_ingredient(item);
            }
            break;
            case EXTRA:
                if (_currentPizzaItem.getExtra(item).isIncluded()) {
                    _currentPizzaItem.remove_extra(item);
                } else {
                    _currentPizzaItem.add_extra(item);
                }
                break;
        }
    }

    private int getImageResourceByName(String resourceName) {
        return context.getResources().getIdentifier(resourceName, "drawable", context.getPackageName());
    }
}
