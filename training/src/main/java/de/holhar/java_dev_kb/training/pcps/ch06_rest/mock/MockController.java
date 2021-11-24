package de.holhar.java_dev_kb.training.pcps.ch06_rest.mock;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MockController {

    @GetMapping("/persons/{id}")
    public Person getPerson(@PathVariable("id") long id) {
        final Person person = new Person();
        person.setId(id);
        person.setName("Peter Parker");
        person.setAge(16);
        person.setHeight(1.7);
        person.setGender(Person.Gender.MALE);
        return person;
    }
}
