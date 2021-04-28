package Search;

import IMDb.DriverTest;
import IMDb.Search.AdvancedTitleSearch;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import static IMDb.Values.ADVANCED_SEARCH_URL;
import java.io.IOException;

@Feature("Advanced title search")
@Severity(SeverityLevel.NORMAL)
public class AdvancedTitleSearchTest extends DriverTest {

    @BeforeEach
    public void goToUrl() {
        driver.get(ADVANCED_SEARCH_URL);
    }

    @BeforeAll
    public void makePairs() throws IOException {
        new AdvancedTitleSearch(driver).makePairCsv();
    }

    @Description("Pairwise test for advanced title search")
    @ParameterizedTest
    @CsvFileSource(resources = "/Pairs.csv")
    public void pairwiseTest(String titleType, String genre, String country, String adultRadio) throws IOException {
        new AdvancedTitleSearch(driver)
                .fillIn(titleType, genre, country, adultRadio)
                .clickSearch()
                .checkUrlChanged();
    }

}
