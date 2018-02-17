package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String NAME_KEY = "name";
    private static final String MAIN_NAME_KEY = "mainName";
    private static final String AKA_KEY = "alsoKnownAs";
    private static final String PLACE_OF_ORIGIN_KEY = "placeOfOrigin";
    private static final String DESCRIPTION_KEY = "description";
    private static final String IMAGE_KEY = "image";
    private static final String INGREDIENTS_KEY = "ingredients";

    public static Sandwich parseSandwichJson(String json) throws JSONException {
        JSONObject sandwichJson = new JSONObject(json);

        JSONObject nameJson = sandwichJson.getJSONObject(NAME_KEY);
        // get the main name
        String mainName = nameJson.getString(MAIN_NAME_KEY);
        // get the list of aliases
        List<String> akaNamesList = new ArrayList<>();
        JSONArray akaNamesJsonArray = nameJson.getJSONArray(AKA_KEY);
        for (int i = 0; i < akaNamesJsonArray.length(); i++) {
            akaNamesList.add(akaNamesJsonArray.getString(i));
        }
        // get place of origin
        String placeOfOrigin = sandwichJson.getString(PLACE_OF_ORIGIN_KEY);
        // get description
        String description = sandwichJson.getString(DESCRIPTION_KEY);
        // get image
        String imageUrl = sandwichJson.getString(IMAGE_KEY);
        // get ingredients
        List<String> ingredientsList = new ArrayList<>();
        JSONArray ingredientsJsonArray = sandwichJson.getJSONArray(INGREDIENTS_KEY);
        for (int i = 0; i < ingredientsJsonArray.length(); i++) {
            ingredientsList.add(ingredientsJsonArray.getString(i));
        }

        return new Sandwich(mainName, akaNamesList, placeOfOrigin, description, imageUrl, ingredientsList);
    }
}
