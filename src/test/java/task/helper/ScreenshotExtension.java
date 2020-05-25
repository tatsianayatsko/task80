package task.helper;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotExtension implements TestExecutionExceptionHandler {

  @Attachment(type = "image/png")
  public byte[] captureScreen() {
    return ((TakesScreenshot) WebDriverSingleton.getInstance().getWebDriver()).getScreenshotAs(OutputType.BYTES);
  }

  @Override
  public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
    captureScreen();
    Allure.addAttachment("Info", "text/plain",
        "Browser: " + getBrowser() + "\nPlatform: " + getPlatform()+ "\nDate and time: " + getDateAndTime());

    throw throwable;
  }

  private String getBrowser(){
    return System.getProperty("browser") != null ? System.getProperty("browser") : "chrome";
  }

  private String getDateAndTime(){
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    return (dateFormat.format( new Date()));
  }

  private String getPlatform() {
    return System.getProperty("os.name");
  }
}
