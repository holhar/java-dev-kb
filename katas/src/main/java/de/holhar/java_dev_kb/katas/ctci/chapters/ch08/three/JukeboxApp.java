package de.holhar.java_dev_kb.katas.ctci.chapters.ch08.three;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JukeboxApp {

    private static final Logger LOGGER = LoggerFactory.getLogger(JukeboxApp.class);

    public static void main(String[] args) throws InterruptedException {
        JukeboxImpl jukeboxImpl = new JukeboxImpl();

        for (int i = 0; i < 5; i++) {
            shuffle(jukeboxImpl);
        }

        LOGGER.debug(jukeboxImpl.stop());
    }

    private static void shuffle(JukeboxImpl jukeboxImpl) throws InterruptedException {
        String nowPlaying = jukeboxImpl.playSong();
        LOGGER.debug(nowPlaying);
        Thread.sleep((int) (Math.random() * 5000));
    }
}
