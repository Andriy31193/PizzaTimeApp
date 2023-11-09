package edu.itstep.myapplic04.models.items;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import edu.itstep.myapplic04.models.abstracts.Item;
import edu.itstep.myapplic04.utils.Enums;

public class Pizza extends Item implements Serializable {

    private Enums.PizzaSize _size = Enums.PizzaSize.MEDIUM;
    private ArrayList<Ingredient> _ingredients = new ArrayList<>();
    private ArrayList<Extra> _extras = new ArrayList<>();

    public Pizza(String imageResource, String title, String description, double price) {
        this.set_type(Enums.ItemType.PIZZA);
        this.set_imageName(imageResource);
        this.set_title(title);
        this.set_description(description);
        this.set_price(price);

    }

    public ArrayList<Ingredient> get_ingredients() {
        return this._ingredients;
    }

    public void remove_ingredient(Item item) { this._ingredients.get(this._ingredients.indexOf((Ingredient) item)).setIncluded(false); }
    public void add_ingredient(Item item) { this._ingredients.get(this._ingredients.indexOf((Ingredient) item)).setIncluded(true); }
    public void set_ingredients(ArrayList<Ingredient> _ingredients) { this._ingredients =  _ingredients; }
    public void set_extras(ArrayList<Extra> _extras) {
        this._extras = _extras;
    }

    public void add_extra(Item item)
    {
        getExtra(item).setIncluded(true);
    }
    public void remove_extra(Item item)
    {
        getExtra(item).setIncluded(false);
    }
    public Extra getExtra(Item item) { return this._extras.get(_extras.indexOf((Extra) item)); }
    public Ingredient getIngredient(Item item) { return this._ingredients.get(_ingredients.indexOf((Ingredient) item)); }
    public ArrayList<Extra> get_extras() { return this._extras; }

    public void set_size(Enums.PizzaSize _size) { this._size = _size;}
    public Enums.PizzaSize get_size() { return _size; }
    public String itemsToString(Enums.ItemType itemType)
    {
        ArrayList<Item> filtered = new ArrayList<>(), items = new ArrayList<>();

        switch (itemType)
        {
            case INGREDIENT:
                items.addAll(this._ingredients);
                break;
            case EXTRA:
                items.addAll(this._extras);
                break;
        }

        filtered.addAll(items.stream().filter(x->x.isIncluded()==true).collect(Collectors.toList()));

        StringBuilder stringBuilder = new StringBuilder();


        for (int i = 0; i < filtered.size(); i++)
            stringBuilder.append(filtered.get(i).get_title() + (i>=filtered.size()-1?"":","));


        return stringBuilder.toString();
    }
    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SIZE: "+_size.toString()+"| ");
        stringBuilder.append("ING:[");
        for (int i = 0; i < _ingredients.size(); i++)
            stringBuilder.append(_ingredients.get(i).toString());
        stringBuilder.append("]");
        stringBuilder.append("EXTRAS:[");
        for (int i = 0; i < _extras.size(); i++)
            stringBuilder.append(_extras.get(i).toString());
        stringBuilder.append("]");

        return "["+get_type().toString()+", "+get_title()+stringBuilder;
    }

    @Override
    public String formatPrice() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(this.get_total());
    }

    @Override
    public double get_total() {
        return get_price() + _extras.stream()
                .filter(Extra::isIncluded)
                .mapToDouble(Item::get_price)
                .sum();
    }

}
