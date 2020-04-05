package task.pe;

import org.openqa.selenium.By;
import task.helper.AbstractUiArea;
import task.po.InBoxDeletedPage;
import task.po.InBoxSentPage;
import task.po.NewLetterWindow;
import task.po.YandexPage;

public class MainMenu extends AbstractUiArea {
  private static final By TRASH_LINK = By.cssSelector("[href='#trash']");
  private static final By SENT_LINK = By.cssSelector("[href='#sent']");
  private static final By INBOX_LINK = By.cssSelector("[href='#inbox']");
  private static final By USER_NAME_TEXT = By.className("mail-User-Name");
  private static final By SIGN_OUT_LINK = By.cssSelector("[data-metric='Sign out of Yandex services']");
  private static final By COMPOSE_LINK = By.cssSelector("[href='#compose']");

  public NewLetterWindow clickWriteButton() {
    click(COMPOSE_LINK);

    return new NewLetterWindow();
  }

  public Boolean isUserNameDisplayed(String userName) {
    return userName.equals(driver.findElement(USER_NAME_TEXT).getText());
  }

  private void openUserDropDown() {
    click(USER_NAME_TEXT);
  }

  public YandexPage logout() {
    waitHack();
    openUserDropDown();
    click(SIGN_OUT_LINK);
    driver.close();
    switchToWindow(1);

    return new YandexPage();
  }

  public InBoxSentPage openInBoxSentPage() {
    click(SENT_LINK);

    return new InBoxSentPage();
  }

  public InBoxDeletedPage openDeletedMailPage() {
    click(TRASH_LINK);

    return new InBoxDeletedPage();
  }

  public InBoxSentPage openInBoxPage() {
    click(INBOX_LINK);

    return new InBoxSentPage();
  }
}
