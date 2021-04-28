package Search;

import IMDb.DriverTest;
import IMDb.Search.FilteredSearch;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static IMDb.Values.QUERY_FOR_FILTERED_SEARCH;
import static IMDb.Values.URL;

@Feature("Filtered search")
@Severity(SeverityLevel.NORMAL)
public class FilteredSearchTest extends DriverTest {

    @BeforeAll
    public static void signIn() {
        new IMDb.Login(driver).login();
    }

    @BeforeEach
    public void goToUrl() {
        DriverTest.driver.get(URL);
    }

    @Description("Testing search filtered by all")
    @Test
    public void filterByAllTest() {
        new FilteredSearch((DriverTest.driver))
                .inputQuery(QUERY_FOR_FILTERED_SEARCH)
                .checkHanna()
                .checkJohnWick()
                .checkNoTv()
                .checkNoCompanies()
                .checkNoKeywords();
    }

    @Description("Testing search filtered by all - all results")
    @Test
    public void filterByAllAllResultsTest() {
        new FilteredSearch((DriverTest.driver))
                .inputQuery(QUERY_FOR_FILTERED_SEARCH)
                .clickSearchButton()
                .checkJohnWick()
                .checkCompanies();
    }

    @Description("Testing search filtered by title")
    @Test
    public void filterByTitleTest() {
        new FilteredSearch(DriverTest.driver)
                .clickFiltersDropdown()
                .filterByTitle()
                .inputQuery(QUERY_FOR_FILTERED_SEARCH)
                .checkNoHanna()
                .checkJohnWick();
    }

    @Description("Testing search filtered by title - all results")
    @Test
    public void filterByTitleAllResultsTest() {
        new FilteredSearch(DriverTest.driver)
                .clickFiltersDropdown()
                .filterByTitle()
                .inputQuery(QUERY_FOR_FILTERED_SEARCH)
                .clickSearchButton()
                .checkFilteredByTitle()
                .checkJohnWick();
    }

    @Description("Testing search filtered by TV episodes")
    @Test
    public void filterByTvEpisodesTest() {
        new FilteredSearch(DriverTest.driver)
                .clickFiltersDropdown()
                .filterByTvEpisodes()
                .inputQuery(QUERY_FOR_FILTERED_SEARCH)
                .checkNoHanna()
                .checkNoJohnWick();
    }

    @Description("Testing search filtered by TV episodes - all results")
    @Test
    public void filterByTvEpisodesAllResultsTest() {
        new FilteredSearch(DriverTest.driver)
                .clickFiltersDropdown()
                .filterByTvEpisodes()
                .inputQuery(QUERY_FOR_FILTERED_SEARCH)
                .clickSearchButton()
                .checkNoHanna()
                .checkNoJohnWick()
                .checkTv()
                .checkNoCompanies()
                .checkNoKeywords();
    }

    @Description("Testing search filtered by celebs")
    @Test
    public void filterByCelebsTest() {
        new FilteredSearch(DriverTest.driver)
                .clickFiltersDropdown()
                .filterByCelebs()
                .inputQuery(QUERY_FOR_FILTERED_SEARCH)
                .checkNoJohnWick()
                .checkHanna();
    }

    @Description("Testing search filtered by celebs - all results")
    @Test
    public void filterByCelebsAllResultsTest() {
        new FilteredSearch(DriverTest.driver)
                .clickFiltersDropdown()
                .filterByCelebs()
                .inputQuery(QUERY_FOR_FILTERED_SEARCH)
                .clickSearchButton()
                .checkHanna()
                .checkNoJohnWick()
                .checkNoTv()
                .checkNoCompanies()
                .checkNoKeywords();
    }

    @Description("Testing search filtered by companies")
    @Test
    public void filterByCompaniesTest() {
        new FilteredSearch(DriverTest.driver)
                .clickFiltersDropdown()
                .filterByCompanies()
                .inputQuery(QUERY_FOR_FILTERED_SEARCH)
                .checkNoHanna()
                .checkNoJohnWick();
    }

    @Description("Testing search filtered by companies - all results")
    @Test
    public void filterByCompaniesAllResultsTest() {
        new FilteredSearch(DriverTest.driver)
                .clickFiltersDropdown()
                .filterByCompanies()
                .inputQuery(QUERY_FOR_FILTERED_SEARCH)
                .clickSearchButton()
                .checkNoHanna()
                .checkNoJohnWick()
                .checkNoTv()
                .checkCompanies()
                .checkNoKeywords();
    }

    @Description("Testing search filtered by keywords")
    @Test
    public void filterByKeywordsTest() {
        new FilteredSearch(DriverTest.driver)
                .clickFiltersDropdown()
                .filterByKeywords()
                .inputQuery(QUERY_FOR_FILTERED_SEARCH)
                .checkNoHanna()
                .checkNoJohnWick();
    }

    @Description("Testing search filtered by keywords - all results")
    @Test
    public void filterByKeywordsAllResultsTest() {
        new FilteredSearch(DriverTest.driver)
                .clickFiltersDropdown()
                .filterByKeywords()
                .inputQuery(QUERY_FOR_FILTERED_SEARCH)
                .clickSearchButton()
                .checkNoHanna()
                .checkNoJohnWick()
                .checkNoTv()
                .checkNoCompanies()
                .checkKeywords();
    }
}
