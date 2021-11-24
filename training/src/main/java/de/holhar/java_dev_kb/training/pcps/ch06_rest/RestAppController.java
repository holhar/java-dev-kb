package de.holhar.java_dev_kb.training.pcps.ch06_rest;

import de.holhar.java_dev_kb.training.pcps.ch06_rest.mock.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.HttpInputMessage;

import java.util.Map;

import de.holhar.java_dev_kb.training.pcps.ch05_web_mvc.WebMvcController;

/**
 * Q6.1:
 * REST stands for REpresentational State Transfer and is an architectural style which allows clients to access and
 * manipulate textual representations of web resources given a set of constraints. If the application does observe
 * the constraints, certain benefits are gained. Examples of such benefits are scalability, simplicity, portability
 * and reliability.
 *
 * Q6.2:
 * What is a resource? To quote Roy Fielding:
 * "Any information that can be named can be a resource: a document or image, a temporal service (e.g. "today's
 * weather in Los Angeles"), a collection of other resources, a non-virtual object (e.g. a person), and so on. In
 * other words, any concept that might be the target of an author's hypertext reference must fit within the
 * definition of a resource. A resource is a conceptual mapping to a set of entities, not the entity that corresponds
 * to the mapping at any particular point in time."
 *
 * Q6.2.a:
 * The CRUD acronym stands for Create, Read, Update and Delete. These are the basic operations on REST resources, as
 * well as persistent storage.
 *
 * Q6.3:
 * The REST architectural style in itself does not stipulate any particular security solution but suggests using a
 * layered system style using one or more intermediaries. Security may be a responsibility of
 * one type of intermediary.
 * This maps very well to what Spring has to offer when it comes to developing REST web services: A REST web service
 * can be developed using Spring without having to consider security at all. Security can later be added to the REST
 * web service using Spring Security.
 * While security, such as basic authentication, will make a REST service unavailable to everyone except those who
 * know the login information, the messages passed between clients and the service will still be readable by anyone
 * able to intercept them. To protect the messages in transit, encryption can be used such as with the HTTPS protocol.
 *
 * Q6.4:
 * Yes, REST web services are both scalable and interoperable.
 *
 * Scalability:
 * The statelessness, the cacheability and the layered system constraints of the REST architectural style allows for
 * scaling a REST web service.
 * Statelessness ensures that requests can be processed by any node in a cluster of services without having to
 * consider server-side state.
 * Cacheability allows for creating responses from cached information without the request having to proceed to the
 * actual service, which improves network efficiency and reduces the load on the service.
 * A layered system allows for introducing intermediaries such as a load balancer without clients having to modify
 * their behavior as far as sending requests to the service is concerned. The load balancer can then distribute the
 * requests between multiple instances of the service in order to increase the request-processing capacity of the
 * service.
 *
 * SIDE NOTE: Is REST normally stateless?
 * Statelessness of a REST service is one of the fundamental constraints of the REST architectural style. Thus REST
 * is always stateless or else it is no longer REST. That said, a REST client may hold state of some kind and
 * enclose data being part of this state in requests to a REST service. The server however is not aware of the data
 * being part of some state and the server will not retain any such data between requests.
 *
 * Interoperability:
 * The following elements of the REST architectural style increases interoperability:
 * - a REST service can support different formats for the resource representation transferred to clients and allow
 * for clients to specify which format it wants to receive data in. Common formats are XML, JSON, HTML which are all
 * formats that facilitate interoperability.
 * - REST resources are commonly identified using URIs, which do not depend on any particular language or
 * implementation.
 * - The REST architectural style allows for a fixed set of operations on resources.
 *
 * Q6.5:
 * Mapping the CRUD (create, read, update and delete) operations onto HTTP verbs yields the following table,
 * which shows the HTTP methods commonly used in connection to REST web services:
 * Create -> POST
 * Read -> GET
 * Update -> PUT
 * Delete -> DELETE
 *
 * Q6.6:
 * The {@link HttpMessageConverter} interface specifies the properties of a converter that can perform the following
 *
 * conversions:
 * - convert a {@link HttpInputMessage} to an object of specified type.
 * - convert an object to a {@link org.springframework.http.HttpOutputMessage}.
 *
 * There are many implementations of this interface that performs specific conversions. A few examples are:
 * - {@link org.springframework.http.converter.feed.AtomFeedHttpMessageConverter}: converts to/from Atom feeds
 * - {@link org.springframework.http.converter.ByteArrayHttpMessageConverter}: converts to/from byte arrays
 * - {@link org.springframework.http.converter.FormHttpMessageConverter}: converts to/from HTML forms
 * - {@link org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter}: Reads classes annotated with
 *   the JAXB2 annotations @XmlRootElement and @XmlType and writes classes annotated with @XmlRootElement.
 * - {@link org.springframework.http.converter.json.MappingJackson2HttpMessageConverter}: Converts to/from JSON using Jackson 2.x.
 *
 * A HttpMessageConverter converts a HttpInputMessage created from the request to the parameter type of the
 * controller method that is to process the request. When the controller method has finished, a HttpMessageConverter
 * converts the object returned from the controller method to a HttpOutputMessage.
 *
 * Q6.7:
 * The @Controller annotation is a stereotype annotation. In addition, it is located in the
 * org.springframework.stereotype package. The @RestController annotation is a stereotype annotation, given that its
 * declaration is annotated with the @Controller annotation.
 *
 * Stereotype annotations are annotations that are applied to classes that fulfill a certain, distinct, role. The
 * (core) Spring Framework supplies the following stereotype annotations, all found in the org.springframework.stereotype package:
 * - @Component
 * - @Controller
 * - @Indexed
 * - @Repository
 * - @Service
 * Other Spring projects do provide additional stereotype annotations.
 *
 * Q6.8:
 * The @RestController annotation is an annotation that is annotated with the @Controller and the @ResponseBody
 * annotations. That is, annotating a class with the @RestController annotation is identical to annotating the class
 * with the @Controller and @ResponseBody annotations. When applied at class level, the @ResponseBody annotation
 * applies to all the methods in the controller that handles web requests.
 * Please refer to the next section for an explanation on the @ResponseBody annotation.
 *
 * Q6.9:
 * The {@link ResponseBody}  annotation can be applied to either a controller class or a controller handler method.
 * It causes the response to be created from the serialized result of the controller method result processed by a
 * {@link HttpMessageConverter}. This is useful when you want the web response body to contain the result produced by
 * the controller method, as is the case when implementing a backend service, for example a REST service. The
 * @ResponseBody annotation is not needed if the controller class is annotated with @RestController.
 *
 * Q6.10:
 * HTTP response status codes are three-digit integer codes where the first digit determines the class of the
 * response. There are five different classes of HTTP response codes:
 * - 1xx: Informational; The request has been received and processing of it continues
 * - 2xx: Successful; The request has been successfully received, understood and accepted
 * - 3xx: Redirection; Further action is needed to complete the request
 * - 4xx: Client error; The request is incorrect or cannot be processed
 * - 5xx: Server error; The server failed to process what appears to be a valid request
 *
 * The following HTTP response status codes are of the successful class:
 * 200 -> OK
 * 201 -> Created
 * 202 -> Accepted
 * 203 -> Non-Authoritative Information
 * 204 -> No Content
 * 205 -> Reset Content
 * 206 -> Partial Content
 *
 * Q6.12:
 * @ResponseBody - as mentioned above, the @ResponseBody annotation is an annotation that can be applied to controller
 * classes or controller handler methods. It is used when the web response body is to contain the result produced by
 * the controller method(s).
 *
 * @RequestBody - the @RequestBody annotation can only be applied to parameters of methods. More specific, parameters
 * of controller handler methods. It is used when the web request body is to be bound to a parameter of the
 * controller handler method. That is, the annotated method parameter will contain the web request body when there is
 * a request to be handled by the controller handler method with the annotated parameter. See
 * {@link WebMvcController#params(Model, String, Map, String, String, String, String)} for an example.
 */
@RestController
public class RestAppController {

    private final RestTemplateService restTemplateService;

    public RestAppController(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    /**
     * The @RequestMapping annotation marks a method in a controller as a method that will be invoked
     * to handle requests that match the configuration in the @RequestMapping annotation.
     *
     * Following RequestMapping configuration yields requests similar to this curl call:
     * curl -X POST "http://localhost:8080/hello?myParam=myValue" -H"My-Header:myValue" -H"Content-Type:application/json" --data '{"foo":"bar"}'
     */
    @RequestMapping(
            path = "/hello",              // Path mapping URL
            consumes = "application/json",  // Consumable media types
            produces = "application/json",  // Media type(s) that can be produced
            method = RequestMethod.POST,    // HTTP method
            params = "myParam=myValue",     // parameters and values of the method
            headers = "My-Header=myValue"   // Header(s)
    )
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello dude!");
    }

    @GetMapping("/test")
    public Person get() {
        return restTemplateService.getPerson(1);
    }
}
