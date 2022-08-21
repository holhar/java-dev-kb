package de.holhar.java_dev_kb.designpatterns.behavioral.iterator;

public class Channel {

    private final double frequency;
    private final ChannelType type;

    public Channel(double frequency, ChannelType type) {
        this.frequency = frequency;
        this.type = type;
    }

    public double getFrequency() {
        return frequency;
    }

    public ChannelType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "frequency=" + frequency +
                ", type=" + type +
                '}';
    }
}
