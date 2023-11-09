package edu.itstep.myapplic04.models;

import java.util.ArrayList;

import edu.itstep.myapplic04.models.abstracts.Item;

public class Order {
    private String _customerName, _customerPhone;
    private ArrayList<Item> _customerOrder;

    public Order(){}
    public Order(String name, String phone, ArrayList<Item> items)
    {
        this._customerName = name;
        this._customerPhone = phone;
        this._customerOrder = items;
    }

    public String get_customerName() {
        return _customerName;
    }

    public void set_customerName(String _customerName) {
        this._customerName = _customerName;
    }


    public String get_customerPhone() {
        return _customerPhone;
    }

    public void set_customerPhone(String _customerPhone) {
        this._customerPhone = _customerPhone;
    }


    public ArrayList<Item> get_customerOrder() {
        return _customerOrder;
    }

    public void set_customerOrder(ArrayList<Item> _customerOrder) {
        this._customerOrder = _customerOrder;
    }
}
