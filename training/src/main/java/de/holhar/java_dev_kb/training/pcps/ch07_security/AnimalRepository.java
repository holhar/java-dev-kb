package de.holhar.java_dev_kb.training.pcps.ch07_security;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AnimalRepository {

    private final Map<Long, Animal> animalMap = new HashMap<>();

    public AnimalRepository() {
        animalMap.put(1L, new Animal("Cat", "Larry", 5.54));
        animalMap.put(2L, new Animal("Dog", "Judy", 14.90));
        animalMap.put(3L, new Animal("Bird", "Rambo", 0.31));
    }

    public Animal findOne(long id) {
        if (animalMap.containsKey(id)) {
            return animalMap.get(id);
        } else {
            throw new NotFoundException("Animal not found for id " + id);
        }
    }

    public List<Animal> findAll() {
        return new ArrayList<>(animalMap.values());
    }

    public void put(Animal animal) {
        Long max = animalMap.keySet().stream().max(Long::compareTo).orElse(1L);
        animalMap.put(max + 1L, animal);
    }

    public void delete(long id) {
        animalMap.remove(id);
    }
}
