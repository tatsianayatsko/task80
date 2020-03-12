import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;

import java.io.IOException;

public class DownloadPage extends PageBase {
  private final static By FIRST_DOWNLOAD_LINK = By.cssSelector("#content a");

  public DownloadPage goToDownloadPage() {
    driver.get("https://the-internet.herokuapp.com/download");

    return this;
  }

  public String clickFileLink() {
    String fileName = driver.findElement(FIRST_DOWNLOAD_LINK).getText();
    driver.findElement(FIRST_DOWNLOAD_LINK).click();
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    return fileName;
  }

  public HttpResponse getHttpResponse() {
    String link = driver.findElement(FIRST_DOWNLOAD_LINK).getAttribute("href");

    HttpClient httpClient = HttpClientBuilder.create().build();
    HttpHead request = new HttpHead(link);
    HttpResponse response = null;
    try {
      response = httpClient.execute(request);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return response;
  }

}
