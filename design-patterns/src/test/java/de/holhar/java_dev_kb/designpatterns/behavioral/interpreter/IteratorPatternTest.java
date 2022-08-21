package de.holhar.java_dev_kb.designpatterns.behavioral.interpreter;

import de.holhar.java_dev_kb.designpatterns.behavioral.iterator.Channel;
import de.holhar.java_dev_kb.designpatterns.behavioral.iterator.ChannelCollection;
import de.holhar.java_dev_kb.designpatterns.behavioral.iterator.ChannelCollectionImpl;
import de.holhar.java_dev_kb.designpatterns.behavioral.iterator.ChannelIterator;
import de.holhar.java_dev_kb.designpatterns.behavioral.iterator.ChannelType;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

class IteratorPatternTest {

    private static final Logger logger = LoggerFactory.getLogger(IteratorPatternTest.class);

    @Test
    void iteratorPattern() {
        ChannelCollection channels = populateChannels();

        int outputCount = 0;
        ChannelIterator iterator = channels.iterator(ChannelType.ALL);
        while (iterator.hasNext()) {
            outputCount++;
            Channel c = iterator.next();
            logger.info("{}", c);
        }
        assertEquals(9, outputCount);

        outputCount = 0;
        iterator = channels.iterator(ChannelType.ENGLISH);
        while (iterator.hasNext()) {
            outputCount++;
            Channel c = iterator.next();
            logger.info("{}", c);
        }
        assertEquals(3, outputCount);
    }

    private ChannelCollection populateChannels() {
        ChannelCollectionImpl channels = new ChannelCollectionImpl();
        channels.addChannel(new Channel(98.5, ChannelType.ENGLISH));
        channels.addChannel(new Channel(99.5, ChannelType.HINDI));
        channels.addChannel(new Channel(100.5, ChannelType.FRENCH));
        channels.addChannel(new Channel(101.5, ChannelType.ENGLISH));
        channels.addChannel(new Channel(102.5, ChannelType.HINDI));
        channels.addChannel(new Channel(103.5, ChannelType.FRENCH));
        channels.addChannel(new Channel(104.5, ChannelType.ENGLISH));
        channels.addChannel(new Channel(105.5, ChannelType.HINDI));
        channels.addChannel(new Channel(106.5, ChannelType.FRENCH));
        return channels;
    }

}
