package task.po;

import org.openqa.selenium.By;
import task.helper.AbstractUiArea;
import task.model.Email;

public class NewLetterWindow extends AbstractUiArea {

  private static final By TO_TEXT_AREA = By.cssSelector("[class*='tst-field-to'] div[contenteditable]");
  private static final By CONTENT_TEXT_AREA = By.cssSelector("div[id='cke_1_contents'] div");
  private static final By SUBJECT_TEXT_AREA = By.cssSelector("input[name='subject']");
  private static final By SEND_BUTTON = By.xpath("//div[@class='ComposeSendButton-Text']/ancestor::button");

  private void typeWhom(String value){
    type(TO_TEXT_AREA, value);
  }

  private void typeLetterText(String value){
    type(CONTENT_TEXT_AREA, value);
  }

  private void typeSubject(String value){
    type(SUBJECT_TEXT_AREA, value);
  }

  private void clickSendLetterButton(){
    click(SEND_BUTTON);
  }

  public ConfirmationWindow sendLetter(Email email){
    typeWhom(email.getTo());
    typeSubject(email.getSubject());
    typeLetterText(email.getContent());
    clickSendLetterButton();

    return new ConfirmationWindow();
  }
}
