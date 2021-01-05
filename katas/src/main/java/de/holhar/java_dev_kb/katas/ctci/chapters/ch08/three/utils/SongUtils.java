package de.holhar.java_dev_kb.katas.ctci.chapters.ch08.three.utils;

import de.holhar.java_dev_kb.katas.ctci.chapters.ch08.three.model.Song;

import java.util.ArrayList;
import java.util.List;

public class SongUtils {

    public SongUtils() {}

    public static List<Song> loadSongs() {
        List<Song> songs = new ArrayList<>();
        songs.add(new Song("QotSA", "Little Sister"));
        songs.add(new Song("NIN", "The Collector"));
        songs.add(new Song("Foot Fighters", "Learn to Fly"));
        songs.add(new Song("Nicolas Jaar", "Killing Time"));
        songs.add(new Song("Villagers", "Home"));
        songs.add(new Song("Grizzly Bear", "Knife"));
        songs.add(new Song("The Doors", "Light my Fire"));
        songs.add(new Song("Weval", "Gimme Some"));
        songs.add(new Song("Chemical Brothers", "Hey Boy, Hey Girl"));
        songs.add(new Song("Bonobo", "A Carlf born in Winter"));
        return songs;
    }
}
