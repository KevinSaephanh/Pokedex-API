package com.company.DataStorage;

import com.sun.deploy.util.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class PokemonData {
    private String name;
    private String height;
    private String weight;
    private String ability;
    private String image;
    private int num;

    private ArrayList<String> type;
    private ArrayList<String> weakness;

    private JSONObject jsonObject;
    private JSONArray jsonArray;

    public PokemonData(String data) {
        jsonObject = new JSONObject(data);

        setName(jsonObject.getString("name"));
        setNum(jsonObject.getInt("number"));
        setHeight(jsonObject.getString("height"));
        setWeight(jsonObject.getString("weight"));
        setImage(jsonObject.getString("picture"));
        setAbility(jsonObject.getString("ability"));

        type = new ArrayList<String>();
        setType();

        weakness = new ArrayList<String>();
        setWeakness();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setType() {
        jsonArray = jsonObject.getJSONArray("type");

        for (int i = 0; i < jsonArray.length(); i++)
            type.add(jsonArray.optString(i));
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public void setWeakness() {
        jsonArray = jsonObject.getJSONArray("weakness");

        for(int i = 0; i < jsonArray.length(); i++)
                weakness.add(jsonArray.get(i).toString());
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

    public String getType() {
        return StringUtils.join(type, ",");
    }

    public String getHeight() {
        return height;
    }

    public String getWeight() {
        return weight;
    }

    public String getAbility() {
        return ability;
    }

    public String getWeakness() {
        return StringUtils.join(weakness, ",");
    }

    public String getImage() {
        return image;
    }
}
