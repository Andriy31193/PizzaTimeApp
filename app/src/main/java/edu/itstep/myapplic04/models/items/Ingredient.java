package edu.itstep.myapplic04.models.items;

import java.io.Serializable;

import edu.itstep.myapplic04.models.abstracts.Item;
import edu.itstep.myapplic04.utils.Enums;

public class Ingredient extends Item implements Serializable {
    public Ingredient(String title, String image_src) {
        this.set_type(Enums.ItemType.INGREDIENT);
        this.set_title(title);
        this.set_imageName(image_src);
    }
}
