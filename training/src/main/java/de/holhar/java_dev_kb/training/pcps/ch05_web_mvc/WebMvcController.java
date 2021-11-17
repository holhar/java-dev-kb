package de.holhar.java_dev_kb.training.pcps.ch05_web_mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.util.concurrent.ListenableFuture;
import java.util.concurrent.Callable;

import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.PushBuilder;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Principal;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/**
 * Q5.1:
 * The @Controller annotation is a specialization of the @Component annotation that have been discussed earlier. It
 * is used to annotate classes that implement web controllers, the C in MVC. Such classes need not extend any
 * particular parent class or implement any particular interfaces.
 */
@Controller
public class WebMvcController {

    private static final Logger logger = LoggerFactory.getLogger(WebMvcController.class);

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

    /**
     * Q5.6.a:
     */
    @GetMapping("/index3")
    public String index(
            WebRequest webRequest,              // Request metadata such as context path, request params, user principal etc.
            NativeWebRequest nativeWebRequest,  // Extension of WebRequest that also allows access to native request and response objects
            ServletRequest servletRequest,      // Request object; the Spring MultipartRequest type can also be used
            ServletResponse servletResponse,    // Response object
            HttpSession httpSession,            // HTTP session object; will ensure that a session object exists and will create one if this is not hte case
            PushBuilder pushBuilder,            // Servlet 4.0 builder used to build push requests
            Principal principal,                // Currently authenticated user principal
            HttpMethod httpMethod,              // HTTP request method
            Locale locale,                      // Locale of the current request
            TimeZone timeZone,                  // also java.time.ZoneId; time zone associated with the current request
            InputStream requestBody,            // also java.io.Reader; request body
            OutputStream responseBody,          // also java.io.Writer; response body
            HttpEntity<Object> request,         // Request headers and body
            RedirectAttributes redirectAttributes,  // Extends the Model interface allowing selection of attributes for redirect scenarios
            Model model,                        // Model that is used in controllers and exposed when the view is rendered
            //Errors errors,                      // Data-binding and validation errors
            //BindingResult bindingResult,        // Extends the Errors interface to allow registration of errors, application of a Validator and binding-specific analysis and model building
            SessionStatus sessionStatus,        // Session processing status; getting and setting
            UriComponentsBuilder uriComponentsBuilder   // Builder for creating URI references; supports URI templates
    ) {
        model.addAttribute("title", "Spring Web MVC demo");
        model.addAttribute("message", "Hello Spring Web MVC!");
        return "index";
    }

    /**
     * Q5.7:
     * Valid return types of a controller method:
     * - {@link HttpEntity}: as a return type from a controller method; represents a response entity consisting of headers and a body
     * - {@link ResponseEntity}: extension of HttpEntity that adds HTTP status code
     * - {@link HttpHeaders}: used when response only consists of HTTP headers, without a body
     * - {@link String}: name of the view to be rendered
     * - {@link View}: an object that renders a view (with given a model, a request, and a response)
     * - {@link Map} and {@link Model}: Mao or Model instance holding attributes to be added to the model
     * - {@link ModelAndView}: object holding both, model and view
     * - {@link DeferredResult} and {@link ListenableFuture}: asynchronously produce the return value of the controller method from another thread.
     * - {@link Callable}: asynchronously produce the return value of the controller method from a Spring MVC managed thread.
     * - {@link ResponseBodyEmitter}: asynchronously process requests allowing objects to be written to the response.
     * - {@link SseEmitter}: specialization of ResponseBodyEmitter that allows for sending Server-Sent Events
     * - {@link StreamingResponseBody}: asynchronously process requests allowing the application to write to the response output stream, bypassing message conversion, without blocking the servlet container thread.
     * - Reactive types: alternative to DeferredResult for use with Reactor, RxJava or similar
     * - Any other type: by default treated as a view name. Simple types are returned unresolved
     * - void: Used when controller method handles responses by writing to an output stream or if the method is
     *       annotated with @ResponseStatus. Used in REST controller methods that do not return a response body. Used in
     *       HTML controller methods selecting the default view name. Returning null from a controller method produces
     *       the same result.
     *
     * Q5.6.b:
     * See method params for possible annotations usable on method parameters
     *
     * curl example:
     * curl -X POST http://localhost:8080/web/params/pathVar;id=bla;this=that?queryParam=foobar -H"X-RequestHeader:customRequestHeader" -H"Cookie:customCookie=customCookieValue" --data "Request body as String"
     */
    @PostMapping("/params/{pathVar}")
    @ResponseBody // Annotation that indicates a method return value should be bound to the web response body
    //@SessionAttributes("todos") // POI: https://www.baeldung.com/spring-mvc-session-attributes (see also @SessionAttribute)
    //@ModelAttribute String modelAttribute, // https://www.baeldung.com/spring-mvc-session-attributes
    public ResponseEntity<String> params(
            Model model,
            @PathVariable("pathVar") String pathVar,
            // Binds a name-value pair in a part of the URL, a path segment, to a handler method argument
            @MatrixVariable Map<String, String> matrixVars, // Need to be activated, see: https://www.baeldung.com/spring-mvc-matrix-variables#configuration
            @RequestParam("queryParam") String queryParam,
            @RequestHeader("X-RequestHeader") String xRequestHeader,
            @CookieValue("customCookie") String customCookieValue,
            @RequestBody String requestBodyAsString
            //@RequestPart Object data, // Binds a part of a 'multipart/form-data' request to a handler method argument
            //@RequestAttribute("requestAttribute") String requestAttribute     // Annotation to bind a method parameter to a request attribute.
    ) {
        logger.info("pathVar: '{}'", pathVar);
        logger.info("matrixVars:");
        matrixVars.forEach((k, v) -> logger.info("-- {}={}", k, v));
        logger.info("queryParam: '{}'", queryParam);
        logger.info("xRequestHeader: '{}'", xRequestHeader);
        logger.info("customCookieValue: '{}'", customCookieValue);
        logger.info("requestBodyAsString: '{}'", requestBodyAsString);
        return ResponseEntity.ok("SUCCESS");
    }
}
