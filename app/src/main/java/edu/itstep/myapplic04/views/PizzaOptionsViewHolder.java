package edu.itstep.myapplic04.views;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import edu.itstep.myapplic04.R;

public class PizzaOptionsViewHolder extends RecyclerView.ViewHolder {

    private TextView textDescription;
    private ImageView imagePizza;
    private CheckBox checkBox;

    public PizzaOptionsViewHolder(View itemView) {
        super(itemView);
        imagePizza = itemView.findViewById(R.id.ingredient_image);
        checkBox = itemView.findViewById(R.id.ingredient_checkBox);
        textDescription = itemView.findViewById(R.id.ingredient_text);
    }

    public ImageView getImagePizza() {
        return imagePizza;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public TextView getTextDescription() {
        return textDescription;
    }

}
