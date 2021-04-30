package IMDb.Search;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AdvancedTitleSearchCsvMaker {

    private static File advSearchCsv = new File("src/test/resources/AdvSearch.csv");

    /* I used only a few of the filters from the advanced title search page, because there are, like, thousand of them,
     * and I mainly just wanted to try out this pairwise library I found. */

    //    Title type
    private static String typeFeature = ".//input[@value='feature']";
    private static String typeTvMovie = ".//input[@value='tv_movie']";
    private static String typeTvSeries = ".//input[@value='tv_series']";

    //    Genre
    private static String genreAction = ".//input[@value='action']";
    private static String genreAdventure = ".//input[@value='adventure']";
    private static String genreAnimation = ".//input[@value='animation']";

    //    Country
    private static String countryCanada = "\".//option[contains(., 'Canada')]\"";
    private static String countryTaiwan = "\".//option[contains(., 'Taiwan')]\"";

    //    Adult content radio button
    private static String adultOff = ".//*[@id='adult']";
    private static String adultOn = ".//*[@id='adult|include']";


    private static String[] type = {typeFeature, typeTvMovie, typeTvSeries};
    private static String[] genre = {genreAction, genreAdventure, genreAnimation};
    private static String[] country = {countryCanada, countryTaiwan};
    private static String[] adult = {adultOff, adultOn};

    public static String[][] allTheFilters = {type, genre, country, adult};

    public static void makeCsv() throws IOException {
        advSearchCsv.delete();
        for (int i = 0; i < allTheFilters.length; i++) {
            write(allTheFilters[i]);
        }
    }

    private static void write(String[] filtersLine) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(advSearchCsv, true));
        for (int i = 0; i < filtersLine.length; i++) {
            writer.append(filtersLine[i]);
            if (i < filtersLine.length - 1) {
                writer.append("#");
            }
        }
        writer.append("\n");
        writer.close();
    }
}
