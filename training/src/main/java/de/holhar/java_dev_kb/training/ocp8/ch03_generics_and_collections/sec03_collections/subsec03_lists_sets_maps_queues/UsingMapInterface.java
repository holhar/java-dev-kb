package de.holhar.java_dev_kb.training.ocp8.ch03_generics_and_collections.sec03_collections.subsec03_lists_sets_maps_queues;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.TreeMap;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public class UsingMapInterface {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsingMapInterface.class);

    public static void main(String[] args) {
        Map<String, String> map = new TreeMap<>();
        map.put("koala", "bamboo");
        map.put("lion", "meat");
        map.put("giraffe", "leaf");

        for (String key : map.keySet())
            LOGGER.debug("{}", key); // sorted: giraffe, koala, lion

        println(map.isEmpty());
        println(map.size());
        println(map.get("koala"));
        println(map.remove("koala"));
        println(map.containsKey("lion"));
        println(map.containsValue("leaf"));
        println(map.keySet());
        println(map.values());
        map.clear(); // void
    }
}
