package de.holhar.java_dev_kb.katas.ctci.chapters.ch08.three;

import de.holhar.java_dev_kb.katas.ctci.chapters.ch08.three.model.Song;
import de.holhar.java_dev_kb.katas.ctci.chapters.ch08.three.utils.SongUtils;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class JukeboxImpl implements Jukebox {

    private List<Song> songs;

    public JukeboxImpl() {
        this.songs = SongUtils.loadSongs();
    }


    @Override
    public String playSong() {
        Song song = loadRandomSong();
        return "Now Playing: " + song.getArtist() + " with " + song.getSongName();
    }

    // TODO should be private
    @Override
    public Song loadRandomSong() {
        int randomNum = ThreadLocalRandom.current().nextInt(0, songs.size());
        return songs.get(randomNum);
    }

    @Override
    public String stop() {
        return "Stopping the jukebox...";
    }
}
