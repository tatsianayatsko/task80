package task.helper;

import org.openqa.selenium.WebDriver;

public abstract class AbstractUiArea implements PageAction {
  protected final WebDriver driver;

  protected AbstractUiArea() {
    driver = WebDriverSingleton.getInstance().getWebDriver();
  }
}
