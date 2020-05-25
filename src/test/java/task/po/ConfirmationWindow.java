package task.po;

import org.openqa.selenium.By;
import task.helper.AbstractUiArea;

public class ConfirmationWindow extends AbstractUiArea {
  private static final By INBOX_LINK = By.xpath("//div[@class='ComposeDoneScreen-Wrapper']//a[@href='#inbox']");

  public InBoxPage returnToInBoxPage() {
    click(INBOX_LINK);

    return new InBoxPage();
  }
}
