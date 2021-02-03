package com.sungsan.gotoeat.interfaces;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

  @GetMapping("/")
  public String helloWorld(){

    return "Hello World!";
  }


}
