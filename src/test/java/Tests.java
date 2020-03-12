import org.apache.http.HttpResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

public class Tests {
  UploadPage uploadPage;
  File file;
  private static final File FILE_PATH = new File("src/test/resources/FileTemplate.xlsx");
  private static final String DOWNLOADED_FILE_PATH = "src/test/resources/test/";

  @Test
  protected void uploadTest(){
    uploadPage = new UploadPage()
        .goToUploadPage()
        .uploadFile(FILE_PATH.getAbsolutePath());
    Assertions.assertEquals(uploadPage.getUploadedFile(),FILE_PATH.getName());
  }

  @Test
  protected void uploadWithRobotTest(){
    uploadPage = new UploadPage()
        .goToUploadPage()
        .uploadFileWithRobot(FILE_PATH.getAbsolutePath());
    Assertions.assertEquals(uploadPage.getUploadedFile(),FILE_PATH.getName());
  }

 @Test
  public void downloadTest(){
    String fileName = new DownloadPage().goToDownloadPage().clickFileLink();
    file = new File(DOWNLOADED_FILE_PATH + fileName);
    Assertions.assertTrue(file.exists());
    file.delete();
  }

  @Test
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

