package task.helper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

public interface PageAction {

  default void click(By locator) {
    WebDriverSingleton.getInstance().getWebDriver().findElement(locator).click();
  }

  default void switchToWindow(int index) {
    waitHack();
    WebDriver webDriver = WebDriverSingleton.getInstance().getWebDriver();
    ArrayList<String> windows = new ArrayList<>(webDriver.getWindowHandles());
    webDriver.switchTo().window(windows.get(index - 1));
  }

  default void waitHack() {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  default void pageRefresh() {
    WebDriverSingleton.getInstance().getWebDriver().navigate().refresh();
  }

  default void type(By locator, String value){
    WebDriverSingleton.getInstance().getWebDriver().findElement(locator).sendKeys(value);
  }
}
