package edu.itstep.myapplic04.models.abstracts;

import java.text.NumberFormat;

import edu.itstep.myapplic04.utils.Enums;

public abstract class Item {
    private Enums.ItemType _type;
    private String _imageName;
    private String _title;
    private String _description;
    private double _price;
    private boolean isIncluded = false;

    public Enums.ItemType get_type() {
        return _type;
    }

    protected void set_type(Enums.ItemType _category) {
        this._type = _category;
    }

    public String get_imageName() {
        return _imageName;
    }

    protected void set_imageName(String _imageName) {
        this._imageName = _imageName;
    }


    protected void set_title(String value)
    {
        this._title = value;
    }
    public String get_title()
    {
        return this._title;
    }


    protected void set_description(String value)
    {
        this._description = value;
    }
    public String get_description()
    {
        return this._description;
    }

    protected void set_price(double value)
    {
        this._price = value;
    }
    public double get_price()
    {
        return this._price;
    }

    public boolean isIncluded() { return this.isIncluded; }
    public void setIncluded(boolean included) { this.isIncluded = included; }
    public String formatPrice()
    {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(this._price);
    }
    public double get_total()
    {
        return _price;
    }
    @Override
    public String toString()
    {
        return "["+get_type().toString()+", "+get_title()+","+isIncluded()+"]";
    }
}
