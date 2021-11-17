package de.holhar.java_dev_kb.training.pcps.ch06_rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;

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
 */
@RestController
public class RestAppController {

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello dude!");
    }
}
