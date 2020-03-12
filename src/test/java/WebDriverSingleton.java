import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class WebDriverSingleton {

  private static WebDriverSingleton instance;
  private WebDriver driver;

  private WebDriverSingleton() {
    driver = createChromeWithCustomDownloadFolder();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
  }

  public static WebDriverSingleton getInstance() {
    if (instance == null) {
      synchronized (WebDriverSingleton.class) {
        if (instance == null) {
          instance = new WebDriverSingleton();
        }
      }
    }

    return instance;
  }

  public WebDriver getWebDriver() {
    return driver;
  }

  public void closeWebDriver() {
    if (instance != null) {
      driver.quit();
      instance = null;
    }
  }

  private WebDriver createChromeWithCustomDownloadFolder() {
    HashMap<String, Object> chromePrefs = new HashMap<>();
    chromePrefs.put("profile.default_content_settings.popups", 0);
    chromePrefs.put("download.default_directory", getDownloadFilePath());

    ChromeOptions options = new ChromeOptions();
    options.setExperimentalOption("prefs", chromePrefs);

    return new ChromeDriver(options);
  }

  private String getDownloadFilePath() {
    File filePath = new File("src/test/resources/test");
    return filePath.getAbsolutePath();
  }
}

