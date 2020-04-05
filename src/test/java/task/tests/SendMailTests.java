package task.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import task.helper.ScreenshotExtension;
import task.model.Email;
import task.po.InBoxDeletedPage;
import task.po.InBoxPage;
import task.po.InBoxSentPage;

@DisplayName("Sent mails tests")
@Epic("FinalTask")
@ExtendWith(ScreenshotExtension.class)
public class SendMailTests extends TestBase {
  private static final String USER_NAME_1 = "seleniumteststanya1";
  private static final String PASSWORD = "Qwerty123";
  private static final String USER_NAME_2 = "seleniumteststanya2";
  private static final String USER_EMAIL_1 = "seleniumteststanya1@yandex.ru";
  private static final String USER_EMAIL_2 = "seleniumteststanya2@yandex.by";
  private InBoxPage inBoxPage;

  @BeforeEach
  public void login(){
   inBoxPage = yandexPage.openLoginWindow().login(USER_NAME_1, PASSWORD);
  }

  @Test
  @DisplayName("Sent Mail appears in Sent Mail Folder")
  @Description("Verify that sent email appears in Sent Mail folder")
  public void sentEmailIsSavedInSentMails() {
    Email email = TestDataFactory.getEmailForTest(USER_EMAIL_2);
    InBoxSentPage inBoxSentPage = inBoxPage.getMainMenu().clickWriteButton()
        .sendLetter(email)
        .returnToInBoxPage()
        .getMainMenu().openInBoxSentPage();
    inBoxSentPage.pageRefresh();
    boolean isEmailInTheList = inBoxSentPage.getMailsTable().getListOfMails().contains(email.getSubject());
    inBoxSentPage.getMainMenu().openInBoxPage();

    Assertions.assertTrue(isEmailInTheList, String.format("No email with such subject '%s' displayed", email.getSubject()));
  }

  @Test
  @DisplayName("Deleted Mail appears on Deleted Mail Page")
  @Description("Verify that deleted email is listed in Trash")
  public void deletedEmailIsDisplayedInDeletedMails() {
    Email email = TestDataFactory.getEmailForTest(USER_EMAIL_1);

    inBoxPage.getMainMenu().clickWriteButton()
        .sendLetter(email)
        .returnToInBoxPage()
        .pageRefresh();
    inBoxPage.getMailsTable().deleteMail(email.getSubject());

    InBoxDeletedPage inBoxDeletedPage =  inBoxPage.getMainMenu().openDeletedMailPage();
    inBoxDeletedPage.pageRefresh();

    boolean isEmailInTheList = inBoxDeletedPage.getMailsTable().getListOfMails().contains(email.getSubject());
    inBoxDeletedPage.getMainMenu().openInBoxPage();

    Assertions.assertTrue(isEmailInTheList, String.format("Email with such subject '%s' is not displayed", email.getSubject()));
  }

  @Test
  @DisplayName("Send Mail")
  @Description("Verify the ability to send emails")
  public void emailIsReceived() {
    Email email = TestDataFactory.getEmailForTest(USER_EMAIL_2);
    inBoxPage.getMainMenu().clickWriteButton()
        .sendLetter(email)
        .returnToInBoxPage().getMainMenu().logout();

        yandexPage.openLoginWindow()
        .login(USER_NAME_2, PASSWORD);

    Assertions.assertTrue(inBoxPage.getMailsTable().getListOfMails().contains(email.getSubject()), String
        .format("No email with such subject '%s' is received", email.getSubject()));
  }

  @AfterEach
  public void returnToFirstTab() {
    inBoxPage.getMainMenu().logout();
  }

  @AfterAll
  public static void cleanup(){
    yandexPage.openLoginWindow()
        .login(USER_NAME_2, PASSWORD)
        .getMailsTable()
        .deleteAllMails();
  }
}