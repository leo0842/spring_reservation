package com.sungsan.gotoeat.application;

public class UnregisteredEmailException extends RuntimeException{

  UnregisteredEmailException(String email) {
    super("This email is unregistered: " + email);
  }

}
