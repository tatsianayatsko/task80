package task.pe;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import task.helper.AbstractUiArea;

import java.util.List;
import java.util.stream.Collectors;

public class MailsTable extends AbstractUiArea {
  private static final String MAIL_SUBJECT_POINTER = "//span[contains(@title,'%s')]/ancestor::div[@class='mail-MessageSnippet-Content']//label";
  private static final By DELETE_BUTTON = By.cssSelector("[title*='Delete']");
  private static final By SELECT_ALL_CHECK_BOX = By.cssSelector("div[class*='-select-all'] label");
  private static final By MAIL_SUBJECT_TEXT = By.xpath("//span[contains(@class,'Item_subject')]/span[@title]");
  private static final By MAILS_CHECK_BOX_INPUT = By.className("_nb-checkbox-input");

  public List<String> getListOfMails(){
    return driver.findElements(MAIL_SUBJECT_TEXT).stream()
        .map(WebElement::getText)
        .collect(Collectors.toList());
  }

  private void clickDeleteButton(){
    click(DELETE_BUTTON);
  }

  private void selectMail(String subject){
    click(By.xpath(String.format(MAIL_SUBJECT_POINTER, subject)));
  }

  private void selectAllMails(){
    click(SELECT_ALL_CHECK_BOX);
  }

  public void deleteAllMails(){
    if (areMailsExist()) {
      selectAllMails();
      clickDeleteButton();
    }
  }

  public void deleteMail(String subject){
    selectMail(subject);
    clickDeleteButton();
  }

  private Boolean areMailsExist(){
    return driver.findElement(MAILS_CHECK_BOX_INPUT).isDisplayed();
  }
}
