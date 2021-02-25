package com.sungsan.gotoeat.interfaces;

import com.sungsan.gotoeat.application.UnregisteredEmailException;
import com.sungsan.gotoeat.application.WrongPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SessionErrorAdvice {

  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(WrongPasswordException.class)
  public String handleWrongPassword() {
    return "{}";
  }

  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(UnregisteredEmailException.class)
  public String handleUnregisteredEmail() {
    return "{}";
  }

}
