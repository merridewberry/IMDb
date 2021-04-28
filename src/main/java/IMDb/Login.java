package IMDb;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

public class Login extends Driver {

    public Login(WebDriver driver) {
        super(driver);
    }

    public void login() {
        driver.navigate().to("https://www.imdb.com/");
        Cookie cookie = new Cookie("at-main", "Atza|IwEBIECFxXywBGeSyyURBDD2RveH6MFn-5tX6b8LgoHHdibEWb1" +
                "a612DojjphaGUpbBHeRuDjjnvUDl9s_zFw5l" +
                "-PQPFUXuUENjhP9w8kqgLWpfnJRN4EZRA66nUuWik4YPZ8wvssYUPJ4fVHnUacufc" +
                "SItMBkrxbL0tAfClJt56VeXBiJkVG_3riaEK574vSEXO9YRhBHY02xKF6JRFRuGeMr7qtnDCcxLNBLDQR0NRl92Tng");
        driver.manage().addCookie(cookie);
//        driver.get("https://www.imdb.com/");
    }
}
