package org.duckdns.terrell;

import java.io.*;
import java.util.Enumeration;
import java.util.Properties;

public class LiveMLBScoreboard {

    public static void main(String[] args) {
        String fileName = "livemlbscoreboard.properties";
        String teamAbbreviation = "COL";
        String teamDivision = "NLW";
        String wildCardDivision = "NLWC";
        String localTimeZone = "MDT";

        System.out.println("Writing properties file.");
        writePropertiesFile(fileName, teamAbbreviation, teamDivision, wildCardDivision, localTimeZone);
        System.out.println("Done.");
        System.out.println();


        System.out.println("Reading ALL properties file.");
        readPropertiesFile(fileName);
        System.out.println();

        System.out.println("Reading ONE property from a file");
        String teamName = readPropertyFromFile(fileName, "teamAbbreviation");
        System.out.print("teamName = " + teamName );

    }

    private static String readPropertyFromFile(String fileName, String key) {
        String returnValue = "";
        try {
            File file = new File(fileName);
            FileInputStream fileInput = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(fileInput);
            fileInput.close();

            String propertyValue = properties.getProperty(key);
            System.out.println("propertyValue : " + propertyValue);
            returnValue = propertyValue;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
        return returnValue;
    }


    public static void writePropertiesFile(String fileName, String teamAbbreviation, String teamDivision, String wildCardDivision, String localTimeZone) {
        try {
            Properties properties = new Properties();
            properties.setProperty("teamAbbreviation", teamAbbreviation);
            properties.setProperty("teamDivision", teamDivision);
            properties.setProperty("wildCardDivision", wildCardDivision);
            properties.setProperty("localTimeZone", localTimeZone);

            File file = new File(fileName);
            FileOutputStream fileOutput = new FileOutputStream(file);
            properties.store(fileOutput, "liveMLBScoreboard Settings");
            fileOutput.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readPropertiesFile(String fileName) {
        try {
            File file = new File(fileName);
            FileInputStream fileInput = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(fileInput);
            fileInput.close();

            Enumeration enuKeys = properties.keys();
            while (enuKeys.hasMoreElements()) {
                String key = (String) enuKeys.nextElement();
                String value = properties.getProperty(key);
                System.out.println(key + ":" + value);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
