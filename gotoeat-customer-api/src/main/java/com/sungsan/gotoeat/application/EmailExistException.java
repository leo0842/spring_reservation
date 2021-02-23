package com.sungsan.gotoeat.application;

public class EmailExistException extends RuntimeException {

  EmailExistException(String email) {

    super("This e-mail is already registered: " + email);

  }

}
