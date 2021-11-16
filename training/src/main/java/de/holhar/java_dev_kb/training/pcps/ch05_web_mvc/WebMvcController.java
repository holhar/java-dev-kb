package de.holhar.java_dev_kb.training.pcps.ch05_web_mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Q5.1:
 * The @Controller annotation is a specialization of the @Component annotation that have been discussed earlier. It
 * is used to annotate classes that implement web controllers, the C in MVC. Such classes need not extend any
 * particular parent class or implement any particular interfaces.
 */
@Controller
public class WebMvcController {

    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("title", "Spring Web MVC demo");
        model.addAttribute("message", "Hello Spring Web MVC!");
        return "index";
    }
}
