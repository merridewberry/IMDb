package IMDb;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class DriverTest {

    public static WebDriver driver;
    public static Actions builder;

//    @BeforeAll
//    public static void setUp() {
//        WebDriverManager.chromedriver().setup();
//    }

    @BeforeAll
    public void prepareTest() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications")
                .addArguments("--disable-popup-blocking");

        driver = new ChromeDriver(options);
        driver.manage().window().setSize(new Dimension(1600, 1000));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        builder = new Actions(driver);
    }

    @AfterAll
    public void quit() {
        driver.quit();
    }

    @AfterEach
    public void log() throws IOException {
        LogEntries browserLogs = driver.manage().logs().get(LogType.BROWSER);
        List<LogEntry> allLogRows = browserLogs.getAll();
        if (allLogRows.size() > 0 ) {
            writeLog(allLogRows);
        }
    }

    private void writeLog(List<LogEntry> allLogRows) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true));
        List<String> logMessages = allLogRows.stream()
                .map(logRow -> logRow.getMessage())
                .collect(Collectors.toList());
        for (int i = 0; i < logMessages.size(); i++) {
            writer.append(logMessages.get(i));
            writer.append("\n");
        }
        writer.append("\n\n\n");
        writer.close();
    }
}
