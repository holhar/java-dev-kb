package de.holhar.java_dev_kb.training.pcps.ch05_web_mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Q5.1:
 * The @Controller annotation is a specialization of the @Component annotation that have been discussed earlier. It
 * is used to annotate classes that implement web controllers, the C in MVC. Such classes need not extend any
 * particular parent class or implement any particular interfaces.
 */
@Controller
public class WebMvcController {

    /**
     * Q5.3:
     * The {@link RequestMapping} annotation will cause requests using the HTTP method(s) - specified in the optional
     * 'method' element of the annotation - to be mapped to the annotated controller method. If no 'method' element
     * specified in the @RequestMapping annotation, then requests using any HTTP method will be mapped to the
     * annotated method.
     *
     * The @GetMapping annotation is a specialization of the @RequestMapping annotation with
     * 'method = {@link RequestMethod}.GET'. Thus, only requests using the HTTP GET method will be mapped to the
     * method annotated by {@link GetMapping}.
     */
    @GetMapping("/index")
    //@RequestMapping(path = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("title", "Spring Web MVC demo");
        model.addAttribute("message", "Hello Spring Web MVC!");
        return "index";
    }

    /**
     * Q5.4:
     * The @RequestParam annotation indicates that a method parameter should be bound to a web request parameter.
     *
     * Q5.5:
     * The @PathVariable annotation indicates that a method parameter should be bound to a URI template variable.
     */
    @GetMapping("/index2/{title}")
    public String index(
            Model model,
            @PathVariable(name = "title", required = false) String title,
            @RequestParam(name = "message", required = false) String message) {

        title = title != null ? title : "Spring Web MVC demo";
        message = message != null ? message : "Hello Spring Web MVC!";
        model.addAttribute("title", title);
        model.addAttribute("message", message);
        return "index";
    }
}
