package task.helper;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class WebDriverSingleton {

  private static final String USER_NAME = "tanyayatic";
  private static final String ACCESS_KEY = "08cc64e7-b485-4eaa-af15-d91d6348110a";
  private static WebDriverSingleton instance;
  private WebDriver driver;

  private WebDriverSingleton() {
    String browser = System.getProperty("browser") != null ? System.getProperty("browser") : "chrome";
    boolean useGrid = System.getProperty("grid") != null;

    if (useGrid) {
      initializeRemoteWebDriver(browser);
    } else {
      initializeLocalWebDriver(browser);
    }

    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
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

  private void initializeRemoteWebDriver(String browser) {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setBrowserName(browser);

    String host = System.getProperty("host");
    String port = System.getProperty("port");

    String value;
    if (host != null ^ port != null || host == null) {
      value = ("http://127.0.0.1:4444") + "/wd/hub";
    } else
      if (System.getProperty("grid").equals("saucelab")) {
        capabilities.setPlatform(Platform.WIN10);
        capabilities.setCapability("username", USER_NAME);
        capabilities.setCapability("accessKey", ACCESS_KEY);
        capabilities.setCapability("version", "latest");
        value = String.format("%s:%s/wd/hub", host, port);
      } else
        value = String.format("http://%s:%s/wd/hub", host, port);

    try {
      URL gridUrl = new URL(value);
      driver = new RemoteWebDriver(gridUrl, capabilities);

    } catch (MalformedURLException e) {
      e.printStackTrace();
      throw new RuntimeException("Can't connect to remote webdriver");
    }
  }

  private void initializeLocalWebDriver(String browser) {
    if (BrowserType.FIREFOX.equals(browser)) {
      driver = new FirefoxDriver();
    } else {
      driver = new ChromeDriver();
    }
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
}

