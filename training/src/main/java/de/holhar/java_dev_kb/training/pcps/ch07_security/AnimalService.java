package de.holhar.java_dev_kb.training.pcps.ch07_security;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public Animal getAnimal(long id) {
        return animalRepository.findOne(id);
    }

    public List<Animal> getAnimals() {
        return animalRepository.findAll();
    }

    public void addAnimal(Animal animal) {
        animalRepository.put(animal);
    }

    public void delete(long id) {
        animalRepository.delete(id);
    }
}
