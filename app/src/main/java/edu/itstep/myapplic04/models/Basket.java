package edu.itstep.myapplic04.models;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;

import edu.itstep.myapplic04.listeners.BasketListener;
import edu.itstep.myapplic04.models.abstracts.Item;
import edu.itstep.myapplic04.models.items.Extra;
import edu.itstep.myapplic04.models.items.Ingredient;
import edu.itstep.myapplic04.utils.Enums;

public class Basket implements Serializable {

    private static Basket Instance;

    private ArrayList<BasketListener> _listeners = new ArrayList<>();
    private ArrayList<Item> _items;

    public Basket(ArrayList<Item> items) {
        Instance = this;
        this._items = items;
    }

    public static Basket getInstance() {
        return Instance;
    }

    public void addItem(Item item) {
        _items.add(item);
        notifyListeners(Enums.EventType.ADD, item);
    }

    public void removeItem(Item item) {
        _items.remove(item);
        notifyListeners(Enums.EventType.REMOVE, item);
    }

    public void editItem(Item item) {
        notifyListeners(Enums.EventType.EDIT, item);
    }

    public Item getItem(Item item) {
        int idx = this._items.indexOf(item);
        if(idx == -1)
            return null;

        return this._items.get(idx);
    }
    public ArrayList<Item> get_items() {
        return _items;
    }

    public void addBasketListener(BasketListener listener) {
        this._listeners.add(listener);
    }

    private void notifyListeners(Enums.EventType type, Item item) {

        if (this._listeners.isEmpty() || item == null)
            return;

        for (BasketListener listener : this._listeners)
            listener.onBasketUpdated(type, item);
    }
    public String formatTotal()
    {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        double total = this._items.stream().mapToDouble(x->x.get_total()).sum();
        return formatter.format(total);
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < _items.size(); i++)
            stringBuilder.append(_items.get(i).toString()+", ");
        return stringBuilder.toString();
    }
}
