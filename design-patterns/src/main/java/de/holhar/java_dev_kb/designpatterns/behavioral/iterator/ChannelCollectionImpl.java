package de.holhar.java_dev_kb.designpatterns.behavioral.iterator;

import java.util.ArrayList;
import java.util.List;

public class ChannelCollectionImpl implements ChannelCollection {

    private final List<Channel> channelList;

    public ChannelCollectionImpl() {
        this.channelList = new ArrayList<>();
    }

    @Override
    public void addChannel(Channel c) {
        this.channelList.add(c);
    }

    @Override
    public void removeChannel(Channel c) {
        this.channelList.remove(c);
    }

    @Override
    public ChannelIterator iterator(ChannelType type) {
        return new ChannelIteratorImpl(type, channelList);
    }

    private static class ChannelIteratorImpl implements ChannelIterator {

        private final ChannelType type;
        private final List<Channel> channels;
        private int position;

        public ChannelIteratorImpl(ChannelType type, List<Channel> channels) {
            this.type = type;
            this.channels = channels;
        }

        @Override
        public boolean hasNext() {
            while (position < channels.size()) {
                Channel c = channels.get(position);
                if (c.getType().equals(type) || type.equals(ChannelType.ALL)) {
                    return true;
                } else {
                    position++;
                }
            }
            return false;
        }

        @Override
        public Channel next() {
            Channel channel = channels.get(position);
            position++;
            return channel;
        }
    }
}
