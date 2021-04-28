package IMDb.Search;

import IMDb.Driver;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class FilteredSearch extends Driver {

    public FilteredSearch (WebDriver driver) {
        super(driver);
    }

    @FindBy(name = "q")
    WebElement searchField;

    @FindBy(id = "suggestion-search-button")
    WebElement searchButton;

    @FindBy(xpath = ".//*[1][@class='ipc-icon ipc-icon--arrow-drop-down navbar__flyout__button-pointer']")
    WebElement searchFilterDropdown;

    @FindBy(xpath = ".//span[@id='navbar-search-category-select-contents']/ul/a/span[text()='Titles']/parent::a")
    WebElement filterTitles;

    @FindBy(xpath = ".//span[@id='navbar-search-category-select-contents']/ul/a/span[text()='TV Episodes']/parent::a")
    WebElement filterTvEpisodes;

    @FindBy(xpath = ".//span[@id='navbar-search-category-select-contents']/ul/a/span[text()='Celebs']/parent::a")
    WebElement filterCelebs;

    @FindBy(xpath = ".//span[@id='navbar-search-category-select-contents']/ul/a/span[text()='Companies']/parent::a")
    WebElement filterCompanies;

    @FindBy(xpath = ".//span[@id='navbar-search-category-select-contents']/ul/a/span[text()='Keywords']/parent::a")
    WebElement filterKeywords;

    @FindBy(xpath = ".//div[@id='findSubHeader' and contains(., 'All Titles')]")
    WebElement filteredByTitleResult;

    @FindBy(xpath = ".//div[@id='findSubHeader' and contains(., 'TV Episode Titles')]")
    WebElement filteredByTvEpisodesResult;

    @FindBy(xpath = ".//div[@id='findSubHeader' and contains(., 'All Names')]")
    WebElement filteredByCelebsResult;

    @FindBy(xpath = ".//div[@id='findSubHeader' and contains(., 'All Companies')]")
    WebElement filteredByCompaniesResult;

    @FindBy(xpath = ".//div[@id='findSubHeader' and contains(., 'All Keywords')]")
    WebElement filteredByKeywordsResult;

    @FindBy(xpath = ".//a[text()='Hannah John-Kamen']")
    List<WebElement> hannahJohnKamen;

    @FindBy(xpath = ".//div[text()='John Wick']")
    List<WebElement> johnWick;

    @FindBy(xpath = ".//a[text()='John Carrabino Management']")
    List<WebElement> johnCompany;

    @FindBy(xpath = ".//a[text()='John Abraham']")
    List<WebElement> johnTv;

    @FindBy(xpath = ".//a[text()='johnny-storm-character']")
    List<WebElement> johnKeyword;

    @Step(value = "Input search query in the search field")
    public FilteredSearch inputQuery(String query) {
        searchField.sendKeys(query);
        return this;
    }

    @Step(value = "Click search button")
    public FilteredSearch clickSearchButton() {
        searchButton.click();
        return this;
    }

    @Step(value = "Click on filtered search dropdown menu")
    public FilteredSearch clickFiltersDropdown() {
        searchFilterDropdown.click();
        return this;
    }

    @Step(value = "Click on 'Title' in the filtered search dropdown menu")
    public FilteredSearch filterByTitle() {
        filterTitles.click();
        return this;
    }

    @Step(value = "Click on 'TV Episodes' in the filtered search dropdown menu")
    public FilteredSearch filterByTvEpisodes() {
        filterTvEpisodes.click();
        return this;
    }

    @Step(value = "Click on 'Celebs' in the filtered search dropdown menu")
    public FilteredSearch filterByCelebs() {
        filterCelebs.click();
        return this;
    }

    @Step(value = "Click on 'Companies' in the filtered search dropdown menu")
    public FilteredSearch filterByCompanies() {
        filterCompanies.click();
        return this;
    }

    @Step(value = "Click on 'Keywords' in the filtered search dropdown menu")
    public FilteredSearch filterByKeywords() {
        filterKeywords.click();
        return this;
    }

    @Step(value = "Check that celebrities aren't displayed in the search results")
    public FilteredSearch checkNoHanna() {
        Assertions.assertTrue(hannahJohnKamen.size() < 1);
        return this;
    }

    @Step(value = "Check that movies aren't displayed in the search results")
    public FilteredSearch checkNoJohnWick() {
        Assertions.assertTrue(johnWick.size() < 1);
        return this;
    }

    @Step(value = "Check that TV episodes aren't displayed in the search results")
    public FilteredSearch checkNoTv() {
        Assertions.assertTrue(johnTv.size() < 1);
        return this;
    }

    @Step(value = "Check that companies aren't displayed in the search results")
    public FilteredSearch checkNoCompanies() {
        Assertions.assertTrue(johnCompany.size() < 1);
        return this;
    }

    @Step(value = "Check that keywords aren't displayed in the search results")
    public FilteredSearch checkNoKeywords() {
        Assertions.assertTrue(johnKeyword.size() < 1);
        return this;
    }

    @Step(value = "Check that celebrities are displayed in the search results")
    public FilteredSearch checkHanna() {
        Assertions.assertTrue(hannahJohnKamen.size() > 0);
        return this;
    }

    @Step(value = "Check that movies are displayed in the search results")
    public FilteredSearch checkJohnWick() {
        Assertions.assertTrue(johnWick.size() > 0);
        return this;
    }

    @Step(value = "Check that TV episodes are displayed in the search results")
    public FilteredSearch checkTv() {
        Assertions.assertTrue(johnTv.size() > 0);
        return this;
    }

    @Step(value = "Check that companies are displayed in the search results")
    public FilteredSearch checkCompanies() {
        Assertions.assertTrue(johnCompany.size() > 0);
        return this;
    }

    @Step(value = "Check that keywords are displayed in the search results")
    public FilteredSearch checkKeywords() {
        Assertions.assertTrue(johnKeyword.size() > 0);
        return this;
    }

    @Step(value = "Check if corresponding message is displayed after clicking the search button with 'Keyword' filter")
    public FilteredSearch checkFilteredByKeywords() {
        Assertions.assertTrue(filteredByKeywordsResult.isDisplayed());
        return this;
    }

    @Step(value = "Check if corresponding message is displayed after clicking the search button with 'Companies' filter")
    public FilteredSearch checkFilteredByCompanies() {
        Assertions.assertTrue(filteredByCompaniesResult.isDisplayed());
        return this;
    }

    @Step(value = "Check if corresponding message is displayed after clicking the search button with 'TV Episodes' filter")
    public FilteredSearch checkFilteredByTvEpisodes() {
        Assertions.assertTrue(filteredByTvEpisodesResult.isDisplayed());
        return this;
    }

    @Step(value = "Check if corresponding message is displayed after clicking the search button with 'Celebs' filter")
    public FilteredSearch checkFilteredByCelebs() {
        Assertions.assertTrue(filteredByCelebsResult.isDisplayed());
        return this;
    }

    @Step(value = "Check if corresponding message is displayed after clicking the search button with 'Title' filter")
    public FilteredSearch checkFilteredByTitle() {
        Assertions.assertTrue(filteredByTitleResult.isDisplayed());
        return this;
    }

}
