package de.holhar.java_dev_kb.katas.ctci.chapters.ch08.three.model;

public class Song {

    private final String artist;
    private final String songName;

    public Song(String artist, String songName) {
        this.artist = artist;
        this.songName = songName;
    }

    public String getArtist() {
        return artist;
    }

    public String getSongName() {
        return songName;
    }

    @Override
    public String toString() {
        return "Song{" +
                "artist='" + artist + '\'' +
                ", songname='" + songName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Song song = (Song) o;

        if (artist != null ? !artist.equals(song.artist) : song.artist != null) return false;
        return songName != null ? songName.equals(song.songName) : song.songName == null;
    }

    @Override
    public int hashCode() {
        int result = artist != null ? artist.hashCode() : 0;
        result = 31 * result + (songName != null ? songName.hashCode() : 0);
        return result;
    }
}
