package de.holhar.java_dev_kb.designpatterns.structural.decorator;

public abstract class AbstractDecorator extends Component {

    protected Component component;

    public void setComponent(Component component) {
        this.component = component;
    }

    public void makeHouse() {
        // Delegate the task
        if (component != null) {
            component.makeHouse();
        }
    }
}
