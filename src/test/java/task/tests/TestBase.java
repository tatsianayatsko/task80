package task.tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import task.helper.ScreenshotExtension;
import task.helper.WebDriverSingleton;
import task.po.YandexPage;


@ExtendWith(ScreenshotExtension.class)
public class TestBase {
  protected static YandexPage yandexPage;

  @BeforeAll
  public static void openYandexPage() {
    yandexPage = new YandexPage().openPage();
  }

  @AfterAll
  public static void tearDown() {
    WebDriverSingleton.getInstance().closeWebDriver();
  }
}
