package de.holhar.java_dev_kb.katas.codewars.puzzles;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class InterfacingTest {

    private Random random;
    private List<String> names;
    private Class<?>[] interfaces;

    @Test
    @Ignore
    public void testCreate() {
        Object o = Interfacing.create(interfaces);

        // Check that the interfaces are implemented
        assertEquals(10, o.getClass().getInterfaces().length);
        assertArrayEquals(interfaces, o.getClass().getInterfaces());

        try {
            // Check that all properties work correctly
            for (Class<?> clazz : interfaces) {
                for (Method method : clazz.getMethods()) {
                    String name = method.getName();
                    if (name.startsWith("set")) {
                        // Get the corresponding getter
                        String getterName = "get" + name.substring(3);
                        Method getter = clazz.getMethod(getterName);

                        int number = random.nextInt();
                        method.invoke(o, number);

                        // Check that we got the right value
                        assertEquals(number, getter.invoke(o));
                    }
                }
            }
        } catch (Exception e) {
            fail(e.toString());
        }

        // Huzzah!
        System.out.println("Success!");
    }

    /* - Preparation - */
    /* - - - - - - - - */

    @Before
    public void prepare()
            throws ClassNotFoundException {
        // Initialize
        random = new Random();
        names = new ArrayList<>();
        interfaces = new Class<?>[10];

        // Some property and class names to work with
        names.addAll(Arrays.asList(new String[]{
                "Test", "Tests", "Name", "FirstName", "LastName",
                "Age", "Gender", "Date", "Time", "Year", "Month",
                "Day", "Hour", "Minute", "Second", "Height", "Width",
                "Make", "Model", "Color", "Weight", "Depth", "Texture",
                "Material", "Status", "State", "Error", "Flag", "None",
                "Flux", "Type", "Vegetable", "Fruit", "Animal",
                "Human", "Country", "Province", "Territory", "Zone",
                "Region", "Direction", "Vector", "Coordinate",
                "Friend", "Ally", "Standing", "Reputation", "Create",
                "Destroy", "Build", "Rebuild", "Number", "Condition",
                "X", "Y", "Z", "A", "B", "C", "D", "E", "F", "G", "H"
        }));

        // Create a random interface list
        for (int i = 0; i < interfaces.length; i++) {
            interfaces[i] = randomInterface();
        }
    }

    protected Class<?> randomInterface()
            throws ClassNotFoundException {
        // Select a random name
        String name = randomName();

        // Give it a random set of properties
        int count = random.nextInt(4) + 1;
        String[] properties = new String[count];
        for (int i = 0; i < count; i++) {
            properties[i] = randomName();
        }

        // Create it, and fetch it from the ClassLoader
        //ClassHelper.createInterface(name, properties);
        return null; //ClassHelper.getClassLoader().findClass(name);
    }

    protected String randomName() {
        // Get and remove a name, randomly from the list
        return names.remove(random.nextInt(names.size()));
    }

}