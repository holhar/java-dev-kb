package de.holhar.java_dev_kb.training.effectivejava.item29;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TypeToken {

    private static final Logger LOGGER = LoggerFactory.getLogger(TypeToken.class);

    public static void main(String[] args) {
        Favorites f = new Favorites();
        f.putFavorite(String.class, "Java");
        f.putFavorite(Integer.class, 0xcafebabe);
        f.putFavorite(Class.class, Favorites.class);

        String favoriteString = f.getFavorite(String.class);
        int favoriteInteger = f.getFavorite(Integer.class);
        Class<?> favoriteClass = f.getFavorite(Class.class);
        LOGGER.debug("{} {} {}\n", favoriteString, favoriteInteger, favoriteClass.getName());
    }
}
