package de.holhar.java_dev_kb.katas.ctci.chapters.ch08.three;

import de.holhar.java_dev_kb.katas.ctci.chapters.ch08.three.model.Song;

public interface Jukebox {

    String playSong();

    Song loadRandomSong();

    String stop();
}
