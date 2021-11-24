package de.holhar.java_dev_kb.training.pcps.ch06_rest;

import de.holhar.java_dev_kb.training.pcps.ch06_rest.mock.Person;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTemplateService {

    /**
     * Q6.15:
     * The RestTemplate is similar to the JdbcTemplate and the JmsTemplate in that it also implement the Template
     * Method design pattern. The RestTemplate implements a synchronous HTTP client that simplifies sending requests
     * and also enforces RESTful principles. Some advantages of the RestTemplate are:
     *
     * - Provides a higher-level API to perform HTTP requests compared to traditional HTTP client libraries
     * - Allows for sending HTTP requests with a minimum of code
     * - Allows for easy selection of which underlying HTTP client library to use
     * - Supports URI templates
     * - Automatically encodes URI templates (For example, a space character in an URI will be replaced with %20 using
     *   percent-encoding)
     * - Supports automatic detection of content type
     * - Supports automatic conversion between objects and HTTP messages
     * - Supports easy customization of the HTTP message converters available to detect content type and handle
     *   conversion between objects and HTTP messages
     * - Allows for easy customization of response errors (a custom ResponseErrorHandler can be registered on the RestTemplate)
     * - Allows for easy customization of URI template handling (This is the process of creating a URI from a URI template)
     * - Provides methods for conveniently sending common HTTP request types and also provides methods that allow for
     *   increased detail when sending requests
     * - Examples of the former method type are: delete, getForObject, getForEntity, headForHeaders, postForObject
     *   and put
     * - The latter type of methods are all called execute but have different parameters
     */
    private final RestTemplate restTemplate;

    public RestTemplateService(RestTemplateResponseErrorHandler restTemplateResponseErrorHandler) {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(10000);
        requestFactory.setReadTimeout(10000);
        this.restTemplate = new RestTemplate(requestFactory);
        this.restTemplate.setErrorHandler(restTemplateResponseErrorHandler);
    }

    public Person getPerson(long id) {
        final URI requestUri = UriComponentsBuilder
                .fromUriString("http://localhost:8080/persons/{id}")
                .build(id);

        final RequestEntity<Void> requestEntity = RequestEntity
                .get(requestUri)
                .accept(MediaType.APPLICATION_JSON)
                .build();

        final ResponseEntity<Person> response = restTemplate.exchange(requestEntity, Person.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new RuntimeException("Shouldn't happen");
        }
    }
}
