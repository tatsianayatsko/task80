import org.openqa.selenium.WebDriver;

public class PageBase {
  protected final WebDriver driver;

  public PageBase() {
    this.driver = WebDriverSingleton.getInstance().getWebDriver();
  }
}
