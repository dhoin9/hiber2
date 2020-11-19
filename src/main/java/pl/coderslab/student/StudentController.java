package pl.coderslab.student;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @ModelAttribute("countries")
    public List<String> countries() {
        return Arrays.asList("Poland", "Germany", "France", "Russia", "Denmark");
    }
    @ModelAttribute("skills")
    public List<String> checkOptions() {
        String[] a = new String[] {"java", "php", "ruby", "python"};
        return Arrays.asList(a);}

    @ModelAttribute("hobbies")
    public List<String> hobbies() {
        String[] a = new String[] {"triathlon", "sailing", "running", "reading","boxing", "cooking"};
        return Arrays.asList(a);}


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processForm(Student student) {
        System.out.println(student);
        return "student/form";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showRegistrationForm(Model model) {
        model.addAttribute("student", new Student());
        return "student/form";
    }
}
