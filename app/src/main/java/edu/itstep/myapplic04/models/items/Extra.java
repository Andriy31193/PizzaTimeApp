package edu.itstep.myapplic04.models.items;

import java.io.Serializable;

import edu.itstep.myapplic04.models.abstracts.Item;
import edu.itstep.myapplic04.utils.Enums;

public class Extra extends Item implements Serializable {

    public Extra(String imageResource, String title, double price) {
        this.set_type(Enums.ItemType.EXTRA);
        this.set_imageName(imageResource);
        this.set_title(title);
        this.set_price(price);
    }
}
