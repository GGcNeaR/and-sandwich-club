package com.udacity.sandwichclub.utils;

import android.text.TextUtils;

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
        Sandwich result = null;
        JSONObject sandwichJson = new JSONObject(json);

        if (sandwichJson.has(NAME_KEY)) {
            String mainName;
            List<String> akaNamesList = new ArrayList<>();

            JSONObject nameJson = sandwichJson.getJSONObject(NAME_KEY);
            // get the main name
            if (nameJson.has(MAIN_NAME_KEY)) {
                mainName = nameJson.optString(MAIN_NAME_KEY);

                // get the list of aliases
                JSONArray akaNamesJsonArray = nameJson.getJSONArray(AKA_KEY);
                jsonArrayToList(akaNamesJsonArray, akaNamesList);

                // get place of origin
                String placeOfOrigin = sandwichJson.optString(PLACE_OF_ORIGIN_KEY);
                // get description
                String description = sandwichJson.optString(DESCRIPTION_KEY);
                // get image
                String imageUrl = sandwichJson.optString(IMAGE_KEY);
                // get ingredients
                List<String> ingredientsList = new ArrayList<>();
                JSONArray ingredientsJsonArray = sandwichJson.getJSONArray(INGREDIENTS_KEY);
                jsonArrayToList(ingredientsJsonArray, ingredientsList);

                result = new Sandwich(mainName, akaNamesList, placeOfOrigin, description, imageUrl, ingredientsList);
            }
        }

        return result;
    }

    private static void jsonArrayToList(JSONArray array, List<String> list) {
        for (int i = 0; i < array.length(); i++) {
            String item = array.optString(i);
            if (!TextUtils.isEmpty(item)) {
                list.add(item);
            }
        }
    }
}
