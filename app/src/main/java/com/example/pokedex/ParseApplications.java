package com.example.pokedex;

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;
import java.util.ArrayList;
import org.json.JSONArray;

public class ParseApplications {
    private static final String TAG = "ParseApplications";

    private ArrayList<FeedEntry> applications;

    public ParseApplications() {
        applications = new ArrayList<>();
    }

    public ArrayList<FeedEntry> getApplications() {
        return applications;
    }

    public boolean parse(String jsonResponse) {
        FeedEntry entry;
        JSONObject pokemon = null;
        boolean parseSuccess = false;
        String num, name, weight, height, img;
        JSONArray types;

        try {
            JSONObject jObject = new JSONObject(jsonResponse);
            JSONArray pokemons = jObject.getJSONArray("pokemon");

            for (int i=0; i < pokemons.length(); i++)
            {
                try {
                    List<String> pokemonTypes = new ArrayList<>();
                    entry = new FeedEntry();
                    pokemon = pokemons.getJSONObject(i);

                    img = pokemon.getString("img");
                    num = pokemon.getString("num");
                    name = pokemon.getString("name");
                    weight = pokemon.getString("weight");
                    height = pokemon.getString("height");
                    types = pokemon.getJSONArray("type");

                    for (int a=0; a<types.length(); a++) {
                        pokemonTypes.add(types.getString(a));
                    }

                    entry.setImg(img);
                    entry.setNum(num);
                    entry.setName(name);
                    entry.setHeight(height);
                    entry.setWeight(weight);
                    entry.setType(pokemonTypes);
                    
                    applications.add(entry);

                    parseSuccess = true;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception ex) {
            Log.e(TAG, "Erro no parse de pokemons: " + ex.getMessage());
        }

        return parseSuccess;
    }
}