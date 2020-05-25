package task.tests;

import task.model.Email;

import java.util.UUID;

public final class TestDataFactory {

  public static Email getEmailForTest(String userEmail){
    return Email.builder()
        .to(userEmail)
        .subject("Email " + UUID.randomUUID())
        .content("text " + UUID.randomUUID())
        .build();
  }
}
