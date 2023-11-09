package edu.itstep.myapplic04.utils;

import android.content.Intent;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import edu.itstep.myapplic04.R;
import edu.itstep.myapplic04.models.abstracts.Item;
import edu.itstep.myapplic04.models.items.Extra;
import edu.itstep.myapplic04.models.items.Ingredient;
import edu.itstep.myapplic04.models.items.Pizza;

public class PizzaUtils {
    public static String getFileData(InputStream ip) {
        try {
            InputStream inputStream = ip;

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder jsonString = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }

            return jsonString.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    public static ArrayList<Extra> getExtrasFromJson(String data) {
        ArrayList<Extra> extras = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray extrasArray = jsonObject.optJSONArray("extras");

            if (extrasArray != null) {
                for (int i = 0; i < extrasArray.length(); i++) {
                    JSONObject extraObject = extrasArray.getJSONObject(i);
                    String title = extraObject.optString("title", "Unknown Extra");
                    String image = extraObject.optString("image", "cheese");
                    double price = extraObject.optDouble("price", 0);

                    Extra extra = new Extra(image, title, price);
                    extras.add(extra);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return extras;
    }

    public static ArrayList<Pizza> getFromJson(String data) {
        ArrayList<Pizza> items = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray pizzaArray = jsonObject.optJSONArray("pizzas");

            if (pizzaArray != null) {
                for (int i = 0; i < pizzaArray.length(); i++) {
                    JSONObject pizzaObject = pizzaArray.getJSONObject(i);
                    String imageResource = pizzaObject.optString("image_resource", "pizza");
                    String title = pizzaObject.optString("title", "Unknown Pizza");
                    String description = pizzaObject.optString("description", "No description available");
                    double price = pizzaObject.optDouble("price", 0);

                    JSONArray ingredientsArray = pizzaObject.optJSONArray("ingredients");
                    ArrayList<Ingredient> ingredients = new ArrayList<>();

                    if (ingredientsArray != null) {
                        for (int j = 0; j < ingredientsArray.length(); j++) {
                            JSONObject ingredientObject = ingredientsArray.getJSONObject(j);
                            String ingredientTitle = ingredientObject.optString("title", "Unknown Ingredient");
                            String ingredientImage = ingredientObject.optString("image", "default_image_name");

                            Ingredient ingredient = new Ingredient(ingredientTitle, ingredientImage);
                            ingredient.setIncluded(true);
                            ingredients.add(ingredient);
                        }
                    }

                    Pizza pizza = new Pizza(imageResource, title, description, price);
                    pizza.set_ingredients(ingredients);
                    pizza.set_extras(getExtrasFromJson(data));
                    items.add(pizza);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return items;
    }
    public static String itemToString(ArrayList<Item> items) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < items.size(); i++)
            stringBuilder.append(items.get(i).get_title() + (i >= items.size() - 1 ? "" : ","));

        return stringBuilder.toString();
    }
}

