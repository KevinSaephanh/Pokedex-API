package com.company.DataStorage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PokeAPIConnection {
    private final static String API_URL = "https://37cci3dcea.execute-api.us-east-2.amazonaws.com/Dev/";

    private URL url;
    private HttpURLConnection httpCon;

    //Set up connection to api
    private void getHTTPConnection() {
        try {
            httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setRequestProperty("User-Agent", "Mozilla 5.0 (Windows; U; "
                                       + "Windows NT 5.1; en-US; rv:1.8.0.11) ");
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    //Retrieve data from api
    public String getPokemonData(String pokeURL) {
        StringBuilder content = new StringBuilder();
        String line;

        try {
            url = new URL(API_URL + pokeURL.toLowerCase());
            getHTTPConnection();

            //Wrap http connection in bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));

            //Read from http connection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null)
                content.append(line).append("\n");

            bufferedReader.close();
        } catch(IOException e) {
            e.printStackTrace();
        }

        return content.toString();
    }
}
