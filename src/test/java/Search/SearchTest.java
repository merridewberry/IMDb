package Search;

import IMDb.DriverTest;
import IMDb.Search.Search;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static IMDb.Values.*;

@Feature("Basic search")
@Severity(SeverityLevel.CRITICAL)
public class SearchTest extends DriverTest {

    @BeforeAll
    public static void signIn() {
        new IMDb.Login(driver).login();
    }

    @BeforeEach
    public void goToUrl() {
        DriverTest.driver.get(URL);
    }

    @Description("Basic search test")
    @ParameterizedTest
    @CsvFileSource(resources = "/Movies.csv")
    public void positiveSearchTest(String title, String movieID) {
        new Search(DriverTest.driver).inputQuery(title)
                .clickSearchedMovie(movieID + MOVIE_URL_TAIL)
                .checkUrl(URL + movieID + "/" + MOVIE_URL_TAIL);
    }

    @Description("Testing searching the movie which title has been changed")
    @Test
    public void changedNameSearchTest() {
        new Search(DriverTest.driver)
                .inputQuery(MOVIE_CHNG[0])
                .clickSearchedMovie(MOVIE_CHNG[1] + MOVIE_URL_TAIL)
                .checkUrl(URL + MOVIE_CHNG[1] + "/" + MOVIE_URL_TAIL);
    }

    @Description("Testing searching for invalid title")
    @Test
    public void noResultsAlertTest() {
        new Search(DriverTest.driver)
                .inputQuery(INVALID_TITLE)
                .checkNoResults();
    }

    @Description("Testing searching for invalid title with pressing search button")
    @Test
    public void noResultsPageTest() {
        new Search(DriverTest.driver)
                .inputQuery(INVALID_TITLE)
                .clickSearchButton()
                .checkNoResultsPageMessage();
    }

    @Description("Testing 'all results' link")
    @ParameterizedTest
    @CsvFileSource(resources = "/Movies.csv")
    public void allResultsTest(String title, String movieID) {
        new Search(DriverTest.driver)
                .inputQuery(title)
                .clickAllResults()
                .clickSearchedMovie(movieID + MOVIE_URL_TAIL_II)
                .checkUrl(URL + movieID + MOVIE_URL_TAIL_II);
    }

    @Description("Testing search button")
    @ParameterizedTest
    @CsvFileSource(resources = "/Movies.csv")
    public void clickSearchButtonTest(String title, String movieID) {
        new Search(DriverTest.driver)
                .inputQuery(title)
                .clickSearchButton()
                .clickSearchedMovie(movieID + MOVIE_URL_TAIL_II)
                .checkUrl(URL + movieID + MOVIE_URL_TAIL_II);
    }

    @Description("Testing incomplete queries and queries with typos")
    @ParameterizedTest
    @CsvFileSource (resources = "/Movies_typo.csv")
    public void typoSearchTest(String title, String movieID) {
        new Search(DriverTest.driver)
                .inputQuery(title)
                .clickSearchedMovie(movieID + MOVIE_URL_TAIL)
                .checkUrl(URL + movieID + "/" + MOVIE_URL_TAIL);
    }

    @Description("Testing searching with empty query")
    @Test
    public void emptySearchTest() {
        new Search(DriverTest.driver)
                .clickSearchButton()
                .checkEmptySearch();
    }


}
