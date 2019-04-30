package com.company.DataStorage;

import com.sun.deploy.util.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class PokemonData {
    private String name;
    private String height;
    private String weight;
    private String image;
    private int num;

    private ArrayList<String> abilities;
    private ArrayList<String> types;

    private JSONObject jsonObject;
    private JSONArray jsonArray;

    public PokemonData(String data) {
        jsonObject = new JSONObject(data);

        setName(jsonObject.getString("name"));
        setNum(jsonObject.getInt("number"));
        setHeight(jsonObject.getString("height"));
        setWeight(jsonObject.getString("weight"));
        setImage(jsonObject.getString("picture"));

        abilities = new ArrayList<String>();
        setAbilities();

        types = new ArrayList<String>();
        setTypes();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setTypes() {
        jsonArray = jsonObject.getJSONObject("types").getJSONArray("values");

        for(int i = 0; i < jsonArray.length(); i++)
            types.add(jsonArray.get(i).toString());
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setAbilities() {
        jsonArray = jsonObject.getJSONObject("abilities").getJSONArray("values");

        for(int i = 0; i < jsonArray.length(); i++)
            abilities.add(jsonArray.get(i).toString());
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public int getNum() {
        return num;
    }

    public String getTypes() {
        return StringUtils.join(types, ",\n");
    }

    public String getHeight() {
        return height;
    }

    public String getWeight() {
        return weight;
    }

    public String getAbilities() {
        return StringUtils.join(abilities, ",\n");
    }

    public String getImage() {
        return image;
    }
}
