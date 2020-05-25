package task.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Email {

  private String to;
  private String subject;
  private String content;
}
