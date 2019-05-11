package org.duckdns.terrell;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

import static org.duckdns.terrell.weatherFunctions.isWeatherGovAlive;
import static org.duckdns.terrell.weatherFunctions.printWeatherGovGlossary;

/**
 * Hello world!
 */
public class divisionStandings {
    public static void main(String[] args) throws IOException {

        if (!isWeatherGovAlive()) {
            System.out.println("https://api.weather.gov/ is available.");
        } else {
            System.out.println("DOWN!");
        }

        /*Validate.isTrue(args.length == 1, "usage: supply url to fetch");
        String url = args[0];*/
        String url = "https://en.wikipedia.org/wiki/Template:2019_NL_West_standings";
        print("Fetching %s...", url);

        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("a[href]");
        Elements media = doc.select("[src]");
        Elements imports = doc.select("link[href]");

        Element table = doc.select("table.wikitable").get(0); //Select the first table
        Elements rows = table.select("tr");

        System.out.print(StringUtils.center("TEAM NAME", 29, "-") + "\t");
        System.out.print(StringUtils.center("W", 6, "-") + "\t");
        System.out.print(StringUtils.center("L", 6, "-") + "\t");
        System.out.print(StringUtils.center("PCT", 6, "-") + "\t");
        System.out.print(StringUtils.center("GB", 6, "-") + "\t");
        System.out.print(StringUtils.center("HOME", 6, "-") + "\t");
        System.out.println(StringUtils.center("ROAD", 6, "-"));

        for (int i = 1; i < rows.size(); i++) { // Skip first row
            Element row = rows.get(i);
            Elements cols = row.select("td");

            String teamName = StringUtils.center(cols.get(0).text(), 29);
            String teamWins = StringUtils.center(cols.get(1).text(), 6);
            String teamLosses = StringUtils.center(cols.get(2).text(), 6);
            String teamPct = StringUtils.center(cols.get(3).text(), 6);
            String teamGB = StringUtils.center(cols.get(4).text(), 6);
            String teamHome = StringUtils.center(cols.get(5).text(), 6);
            String teamAway = StringUtils.center(cols.get(6).text(), 6);

            System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\t%s\n", teamName, teamWins, teamLosses, teamPct, teamGB, teamHome, teamAway);

        }
        printWeatherGovGlossary();

    }

    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width - 1) + ".";
        else
            return s;
    }

/*
public static void main( String[] args )
{
System.out.println( "Hello World!" );
}
*/

}
