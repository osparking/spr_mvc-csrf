package space.bum.security04.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {

  @GetMapping("/addStudent")
  public String addStudentForm() {
    return "addStudent";
  }

  @PostMapping("/addStudent")
  public String addStudentAct() {
    return "home";
  }

}
