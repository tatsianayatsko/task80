package task.po;

import org.openqa.selenium.By;
import task.helper.AbstractUiArea;

public class LoginFormPage extends AbstractUiArea {

  private static final By USERNAME_INPUT = By.id("passp-field-login");
  private static final By PASSWORD_INPUT = By.id("passp-field-passwd");
  private static final By VOITI_BUTTON = By.cssSelector("button[type='submit']");
  private static final By ANOTHER_ACCOUNT_LINK =
      By.cssSelector("[class*='control link link_theme_normal passp-auth-header-link passp-auth-header-link_visible']");
  private static final By SIGN_IN_WITH_ANOTHER_ACCOUNT_LINK = By.cssSelector("a [class*='passp-account-list__sign-in-button']");

  public InBoxPage login(String name, String password) {

    if (driver.findElements(USERNAME_INPUT).size() == 0) {
      if (driver.findElements(PASSWORD_INPUT).size() != 0){
        click(ANOTHER_ACCOUNT_LINK);
      }
      if (driver.findElements(USERNAME_INPUT).size() == 0){
        click(SIGN_IN_WITH_ANOTHER_ACCOUNT_LINK);
      }
    }
    type(USERNAME_INPUT, name);
    click(VOITI_BUTTON);
    type(PASSWORD_INPUT, password);
    click(VOITI_BUTTON);

    return new InBoxPage();
  }
}
