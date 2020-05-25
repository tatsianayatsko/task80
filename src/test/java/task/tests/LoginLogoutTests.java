package task.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import task.po.InBoxPage;

@DisplayName("Login and Logout Tests")
@Epic("FinalTask")
public class LoginLogoutTests extends TestBase {
  private static final String USER_NAME_1 = "seleniumteststanya1";
  private static final String PASSWORD = "Qwerty123";
  private static final String USER_NAME_2 = "seleniumteststanya2";
  private static final String USER_NAME_3 = "seleniumteststanya3";

  @DisplayName("Login to Yandex Mail")
  @Description("Login and verify that UserName is displayed in the MailBox mailsTable")
  @ParameterizedTest(name="{displayName} {arguments}")
  @ValueSource(strings = {USER_NAME_1, USER_NAME_2, USER_NAME_3})
  public void login(String userName) {
    InBoxPage inBoxPage = yandexPage.openLoginWindow().login(userName, PASSWORD);
    Boolean userNameDisplayed = inBoxPage.getMainMenu().isUserNameDisplayed(userName);
    inBoxPage.getMainMenu().logout();

    Assertions.assertTrue(userNameDisplayed);
  }

  @DisplayName("Logout from Yandex Mail")
  @Description("Logout and verify that login link appeared on the page")
  @ParameterizedTest(name="{displayName} {arguments}")
  @ValueSource(strings = {USER_NAME_1, USER_NAME_2, USER_NAME_3})
  public void logout(String userName) {
    yandexPage.openLoginWindow()
        .login(userName, PASSWORD).getMainMenu()
        .logout();
    yandexPage.pageRefresh();

    Assertions.assertTrue(yandexPage.isUserLogout(), "Login link is not appeared on the page");
  }
}

