package org.duckdns.terrell;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import java.net.URL;
import java.nio.charset.Charset;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class weatherFunctions {

    public static boolean isWeatherGovAlive() throws IOException {
        JSONObject results;
        String url = "https://api.weather.gov";
        results = getJSONObjectFromUrl(url);

        if ( results.getString("status").equals("OK") ) {
            return FALSE;
        } else {
            return TRUE;
        }
    }

    public static void printWeatherGovGlossary() throws IOException {
        JSONObject results;
        String url = "https://api.weather.gov/glossary";
        results = getJSONObjectFromUrl(url);

        System.out.println(results.toString(4));
    }


    public static JSONObject getJSONObjectFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1 ) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}
