package com.sungsan.gotoeat.application;

public class WrongPasswordException extends RuntimeException{

  WrongPasswordException() {
    super("Wrong Password!");
  }

}
