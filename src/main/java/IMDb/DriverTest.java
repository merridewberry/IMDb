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
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class DriverTest {

    public static WebDriver driver;
    public static Actions builder;
    private static String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());


    /* I can see why it may be viable to restart driver before each test, but IMDb is just to laggy and slow,
    and I don't have patience for that.
    Also right now I wrote tests only for search and I don't see how cookies or cache can interfere with test results
    in this case. */
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
        BufferedWriter writer = new BufferedWriter(new FileWriter("browserLogs/log" + timestamp + ".txt", true));
        List<String> logMessages = allLogRows.stream()
                .map(LogEntry::getMessage)
                .collect(Collectors.toList());
        for (String logMessage : logMessages) {
            writer.append(logMessage);
            writer.append("\n");
        }
        writer.append("\n\n\n");
        writer.close();
    }
}
