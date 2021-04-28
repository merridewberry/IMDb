package IMDb.Search;

import IMDb.Driver;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class Search extends Driver {

    @FindBy(name = "q")
    WebElement searchField;

    @FindBy(xpath = ".//div[@class='ipc-error-message ipc-error-message--on-baseAlt']/" +
            "div[contains(., 'No results found.')]")
    WebElement noResultsAlert;

    @FindBy(partialLinkText = "See all results for ")
    WebElement allResults;

    @FindBy(id = "suggestion-search-button")
    WebElement searchButton;

    @FindBy(xpath = ".//p[text()='IMDb.Search.Search IMDb by typing a word or phrase in the search box at the top of this page.']")
    WebElement emptySearchResult;

    @FindBy(xpath = ".//h1[text()='No results found for ']")
    WebElement noResultPageMessage;

    public Search(WebDriver driver) {
        super(driver);
    }

    @Step(value = "Input search query into search field")
    public Search inputQuery(String query) {
        searchField.sendKeys(query);
        return this;
    }

    @Step(value = "Click on the searched movie in the dropdown search results")
    public Search clickSearchedMovie(String query) {
        getSearchedMovie(query).click();
        return this;
    }

    private WebElement getSearchedMovie(String url) {
        List<WebElement> searchedMovieList = driver.findElements(By.xpath(".//a[@href='" + url + "']"));
        Assumptions.assumeTrue(searchedMovieList.size() > 0);
        return searchedMovieList.get(0);
    }

    @Step(value = "Click on 'Show all results for...' link in the dropdown search results")
    public Search clickAllResults() {
        allResults.click();
        return this;
    }

    @Step(value = "Click on the search button")
    public Search clickSearchButton() {
        searchButton.click();
        return this;
    }

    @Step(value = "Check if actual searched movie URL is equal to expected")
    public void checkUrl(String url) {
        Assertions.assertEquals(driver.getCurrentUrl(), url);
    }

    @Step(value = "Check if 'No results found' message is displayed when nothing was found")
    public void checkNoResults() {
        Assertions.assertTrue(noResultsAlert.isDisplayed());
    }

    @Step(value = "Check if corresponding message is displayed after clicking the search button with invalid search query")
    public void checkNoResultsPageMessage() {
        Assertions.assertTrue(noResultPageMessage.isDisplayed());
    }

    @Step(value = "Check if corresponding message is displayed after clicking the search button with empty search query")
    public void checkEmptySearch() {
        Assertions.assertTrue(emptySearchResult.isDisplayed());
    }


}
