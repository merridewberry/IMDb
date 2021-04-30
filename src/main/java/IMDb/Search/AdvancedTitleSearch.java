package IMDb.Search;

import IMDb.Driver;
import com.abslab.lib.pairwise.gen.PairwiseGenerator;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static IMDb.Search.AdvancedTitleSearchMapMaker.makeMap;
import static IMDb.Values.ADVANCED_SEARCH_URL;
import java.io.*;
import java.util.*;

public class AdvancedTitleSearch extends Driver {

    public AdvancedTitleSearch(WebDriver driver) throws IOException {
        super(driver);
    }

    @FindBy(xpath = ".//button[text()='Search']")
    public WebElement searchButton;

    private Map<String, List<String>> parameters = makeMap();

    private File pairsCsv = new File("src/test/resources/Pairs.csv");

    @Step(value =
            "Fill in search filters with following data (by xpath): title type '{titleType}', genre '{genre}'," +
                    "country '{country}', adult content '{adultRadio}'")
    public AdvancedTitleSearch fillIn(String titleType, String genre, String country, String adultRadio) {
        driver.findElement(By.xpath(titleType)).click();
        driver.findElement(By.xpath(genre)).click();
        driver.findElement(By.xpath(country)).click();
        driver.findElement(By.xpath(adultRadio)).click();
        return this;
    }

    @Step(value = "Click search button")
    public AdvancedTitleSearch clickSearch() {
        searchButton.click();
        return this;
    }

    @Step(value = "Check for redirect")
    public AdvancedTitleSearch checkUrlChanged() {
        Assertions.assertNotEquals(driver.getCurrentUrl(), ADVANCED_SEARCH_URL);
        return this;
    }

    public void makePairCsv() {
        pairsCsv.delete();
        PairwiseGenerator<String, String> gen = new PairwiseGenerator<>(parameters);
        gen.stream().forEach(line -> {
            try {
                write(line);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void write(List<String> pw) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(pairsCsv, true));
        for (int i = 0; i < pw.size(); i++) {
            writer.append(pw.get(i));
            if (i < pw.size() - 1) {
                writer.append(",");
            }
        }
        writer.append("\n");
        writer.close();
    }

}
