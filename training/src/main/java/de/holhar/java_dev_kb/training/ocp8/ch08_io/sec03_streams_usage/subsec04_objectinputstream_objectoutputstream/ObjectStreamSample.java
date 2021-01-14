package de.holhar.java_dev_kb.training.ocp8.ch08_io.sec03_streams_usage.subsec04_objectinputstream_objectoutputstream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.getIoHome;
import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public class ObjectStreamSample {

    public static List<Animal> getAnimals(File dataFile) throws IOException, ClassNotFoundException {
        List<Animal> animals = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(dataFile)))) {
            while (true) {
                Object object = in.readObject();
                if (object instanceof Animal)
                    animals.add((Animal) object);
            }
        } catch (EOFException e) {
            // File end reached
        }
        return animals;
    }

    public static void createAnimalsFile(File destination, List<Animal> animals) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(destination)))) {
            for (Animal a : animals) {
                out.writeObject(a);
            }
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Tommy Tiger", 5, 'T'));
        animals.add(new Animal("Peter Penguin", 8, 'P'));

        File dataFile = new File(getIoHome() + "animal.data");
        createAnimalsFile(dataFile, animals);
        println(getAnimals(dataFile));
    }
}
