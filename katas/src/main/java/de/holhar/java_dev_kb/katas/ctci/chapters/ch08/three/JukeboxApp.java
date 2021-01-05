package de.holhar.java_dev_kb.katas.ctci.chapters.ch08.three;

public class JukeboxApp {

    public static void main(String[] args) throws InterruptedException {
        JukeboxImpl jukeboxImpl = new JukeboxImpl();

        for (int i = 0; i < 5; i++) {
            shuffle(jukeboxImpl);
        }

        System.out.println(jukeboxImpl.stop());
    }

    private static void shuffle(JukeboxImpl jukeboxImpl) throws InterruptedException {
        String nowPlaying = jukeboxImpl.playSong();
        System.out.println(nowPlaying);
        Thread.sleep((int) (Math.random() * 5000));
    }
}
