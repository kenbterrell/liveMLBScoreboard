package org.duckdns.terrell;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class weatherFunctions {

    public static void isWeatherGovAlive() {

        try {
            URL weatherGovStatusUrl = new URL("https://api.weather.gov/");
            URLConnection urlConnection = weatherGovStatusUrl.openConnection();
            BufferedReader urlConnectionInput = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String urlConnectionInputLine;
            while ((urlConnectionInputLine = urlConnectionInput.readLine()) != null) {
                System.out.println(urlConnectionInputLine);
            }
        } catch (MalformedURLException e) {
            System.out.println("Exception Caught:");
            e.printStackTrace();
        } catch(FileNotFoundException e) {
            System.out.println("Exception Caught:");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Exception Caught:");
            e.printStackTrace();
        }
    }
}
