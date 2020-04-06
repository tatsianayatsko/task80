import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import org.apache.http.HttpResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.File;

@Epic("Task100")
@ExtendWith(ScreenshotExtension.class)
public class Tests {
  UploadPage uploadPage;
  File file;
  private static final File FILE_PATH = new File("src/test/resources/FileTemplate.xlsx");
  private static final String DOWNLOADED_FILE_PATH = "src/test/resources/test/";

  @Test
  @Feature("Upload")
  @Issue("DARWIN-18003")
  @Description("Upload file")
  protected void uploadTest(){
    uploadPage = new UploadPage()
        .goToUploadPage()
        .uploadFile(FILE_PATH.getAbsolutePath());
    Assertions.assertEquals(uploadPage.getUploadedFile(),FILE_PATH.getName());
  }

  @Test
  @Feature("Upload")
  @Issue("DARWIN-18002")
  @Description("Upload file via hot keys")
  protected void uploadWithRobotTest(){
    uploadPage = new UploadPage()
        .goToUploadPage()
        .uploadFileWithRobot(FILE_PATH.getAbsolutePath());
    Assertions.assertEquals(uploadPage.getUploadedFile(),FILE_PATH.getName());
  }

  @Test
  @Feature("Download")
  @Issue("DARWIN-18001")
  @Description("Download file")
  public void downloadTest(){
    String fileName = new DownloadPage().goToDownloadPage().clickFileLink();
    file = new File(DOWNLOADED_FILE_PATH + fileName);
    Assertions.assertTrue(file.exists());
    file.delete();
  }

  @Test
  @Feature("Download")
  @Issue("DARWIN-18000")
  @Description("Check if it is possible to download file via http request")
  public void downloadFileThroughHTTPRequestTest() {
    HttpResponse response = new DownloadPage().goToDownloadPage().getHttpResponse();
    String contentType = response.getFirstHeader("Content-Type").getValue();
    int contentLength = Integer.parseInt(response.getFirstHeader("Content-Length").getValue());

    Assertions.assertEquals(contentType, "application/octet-stream");
    Assertions.assertNotEquals(contentLength, 0);
  }

  @AfterEach
  public void tearDown() {
    WebDriverSingleton.getInstance().closeWebDriver();
  }
}