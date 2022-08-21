package de.holhar.java_dev_kb.designpatterns.behavioral.iterator;

public interface ChannelCollection {
    void addChannel(Channel c);
    void removeChannel(Channel c);
    ChannelIterator iterator(ChannelType type);
}
