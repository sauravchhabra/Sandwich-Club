package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.SocketAddress;
import java.util.ArrayList;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        //Name of the sandwich
        final String MAIN_NAME = "mainName";

        //Sandwich also known as
        final String ALSO_KNOWN_AS = "alsoKnownAs";

        //Place of origin of sandwich
        final String ORIGIN = "placeOfOrigin";

        //Description of Sandwich
        final String DESCRIPTION = "description";

        //Ingredients list
        final String INGREDIENTS = "ingredients";

        //Image URL
        final String IMAGE_URL = "image";

        String mainName = null;
        ArrayList<String> alsoKnownAs = new ArrayList<>();
        String origin = null;
        String description = null;
        String imageUrl = null;
        ArrayList<String> ingredientsList = new ArrayList<>();

        Sandwich sandwich = new Sandwich();

        try {
            JSONObject sandwichJson = new JSONObject(json);
            JSONObject nameJson = sandwichJson.getJSONObject("name");

            mainName = nameJson.optString(MAIN_NAME);
            JSONArray alsoKnownAsJson = nameJson.getJSONArray(ALSO_KNOWN_AS);

            origin = sandwichJson.optString(ORIGIN);
            description = sandwichJson.optString(DESCRIPTION);
            imageUrl = sandwichJson.optString(IMAGE_URL);

            JSONArray ingredientsListJson = sandwichJson.getJSONArray(INGREDIENTS);


            for (int i = 0; i < alsoKnownAsJson.length(); i++) {
                alsoKnownAs.add(alsoKnownAsJson.getString(i));
            }


            for (int i = 0; i < ingredientsListJson.length(); i++) {
                ingredientsList.add(ingredientsListJson.getString(i));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        //Set the text to Sandwich object
        sandwich.setMainName(mainName);
        sandwich.setAlsoKnownAs(alsoKnownAs);
        sandwich.setDescription(description);
        sandwich.setImage(imageUrl);
        sandwich.setIngredients(ingredientsList);
        sandwich.setPlaceOfOrigin(origin);


        return new Sandwich(mainName, alsoKnownAs, origin, description, imageUrl, ingredientsList);

    }
}
