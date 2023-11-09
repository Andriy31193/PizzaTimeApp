package edu.itstep.myapplic04.listeners;

import java.util.ArrayList;

import edu.itstep.myapplic04.models.abstracts.Item;
import edu.itstep.myapplic04.utils.Enums;

public interface BasketListener {
    void onBasketUpdated(Enums.EventType type, Item item);
}
