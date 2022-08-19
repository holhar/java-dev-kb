package de.holhar.java_dev_kb.designpatterns.structural.adapter;

/*
 * Object Adapter - uses object composition and the adapter contains an instance of the source object.
 */
public class SocketObjectAdapterImpl implements SocketAdapter {

    private final Socket socket = new Socket();

    @Override
    public Volt get120Volt() {
        return socket.getVolt();
    }

    @Override
    public Volt get12Volt() {
        Volt volt = socket.getVolt();
        return convertVolt(volt, 10);
    }

    @Override
    public Volt get3Volt() {
        Volt volt = socket.getVolt();
        return convertVolt(volt, 40);
    }

    private Volt convertVolt(Volt v, int i) {
        return new Volt(v.getVolts()/i);
    }
}
