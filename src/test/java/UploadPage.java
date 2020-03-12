import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;


public class UploadPage extends PageBase {
  private static final By CHOOSE_FILE = By.id("file-upload");
  private static final By UPLOAD_BUTTON = By.id("file-submit");
  private static final By UPLOADED_FILES = By.id("uploaded-files");

  public UploadPage goToUploadPage() {
    driver.get("https://the-internet.herokuapp.com/upload");

    return this;
  }
  public UploadPage uploadFile(String filePath) {
    driver.findElement(CHOOSE_FILE).sendKeys(filePath);
    driver.findElement(UPLOAD_BUTTON).click();
    return this;
  }

  public String getUploadedFile(){
    return driver.findElement(UPLOADED_FILES).getText();
  }

  public static void setClipboardData(String string) {
    StringSelection stringSelection = new StringSelection(string);
    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
  }

  public UploadPage uploadFileWithRobot(String path) {
    setClipboardData(path);
    Actions act = new Actions(driver);
    act.moveToElement(driver.findElement(CHOOSE_FILE)).click().build().perform();
    pasteFromClipboardByRobot();
    driver.findElement(UPLOAD_BUTTON).click();

    return this;
  }

  private void pasteFromClipboardByRobot() {
    Robot robot = null;

    try {
      robot = new Robot();
    } catch (AWTException e) {
      e.printStackTrace();
    }

    robot.delay(500);
    robot.keyPress(KeyEvent.VK_ENTER);
    robot.keyRelease(KeyEvent.VK_ENTER);
    robot.keyPress(KeyEvent.VK_CONTROL);
    robot.keyPress(KeyEvent.VK_V);
    robot.keyRelease(KeyEvent.VK_V);
    robot.keyRelease(KeyEvent.VK_CONTROL);
    robot.delay(150);
    robot.keyPress(KeyEvent.VK_ENTER);
    robot.delay(150);
    robot.keyRelease(KeyEvent.VK_ENTER);
  }
}
