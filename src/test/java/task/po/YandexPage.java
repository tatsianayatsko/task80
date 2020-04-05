package task.po;

import org.openqa.selenium.By;
import task.helper.AbstractUiArea;

public class YandexPage extends AbstractUiArea {
  private static final By LOGIN_LINK = By.cssSelector("[href*='https://passport.yandex.by/auth']");
  private static final String YANDEX_BY_URL = "https://yandex.by";

  public YandexPage openPage(){
    driver.get(YANDEX_BY_URL);

    return this;
  }

    public Boolean isUserLogout () {
      return driver.findElement(LOGIN_LINK).isDisplayed();
    }

    public LoginFormPage openLoginWindow () {
      click(LOGIN_LINK);
      switchToWindow(2);

      return new LoginFormPage();
    }
  }
