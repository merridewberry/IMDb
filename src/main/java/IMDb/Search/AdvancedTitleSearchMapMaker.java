package IMDb.Search;

import java.util.*;

public class AdvancedTitleSearchMapMaker {

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

    private static List<String> type = new ArrayList<>();
    private static List<String> genre = new ArrayList<>();
    private static List<String> country = new ArrayList<>();
    private static List<String> adult = new ArrayList<>();

    public static Map<String, List<String>> makeMap(){
        fillLists();
        Map allFilters = new HashMap();
        allFilters.put("type", type);
        allFilters.put("genre", genre);
        allFilters.put("country", country);
        allFilters.put("adult", adult);
        return allFilters;
    }

    private static void fillLists() {
        Collections.addAll(type, typeFeature, typeTvMovie, typeTvSeries);
        Collections.addAll(genre, typeFeature, typeTvMovie, typeTvSeries);
        Collections.addAll(country, countryCanada, countryTaiwan);
        Collections.addAll(adult, adultOff, adultOn);
    }

}
