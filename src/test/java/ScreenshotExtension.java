import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class ScreenshotExtension implements TestExecutionExceptionHandler {

  @Attachment(type = "image/png")
  public byte[] captureScreen() {
    return ((TakesScreenshot) WebDriverSingleton.getInstance().getWebDriver()).getScreenshotAs(OutputType.BYTES);
  }

  @Override
  public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
    captureScreen();
    Allure.addAttachment("Info", "text/plain", "Current Url: " + WebDriverSingleton.getInstance().getWebDriver().getCurrentUrl() );

    throw throwable;
  }
}
