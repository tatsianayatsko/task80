package task.po;

import lombok.Getter;
import task.helper.AbstractUiArea;
import task.pe.MailsTable;
import task.pe.MainMenu;

public class PageBase extends AbstractUiArea {
  @Getter
  protected final MailsTable mailsTable;
  @Getter
  protected final MainMenu mainMenu;

  public PageBase() {
    mailsTable = new MailsTable();
    mainMenu = new MainMenu();
  }
}
