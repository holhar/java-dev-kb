package de.holhar.java_dev_kb.designpatterns.behavioral.interpreter;

import java.util.ArrayList;
import java.util.List;

public class Context {

    private final int yearsOfExperience;
    private final List<String> permissibleGrades;

    public Context(int yearsOfExperience, List<String> permissibleGrades) {
        this.yearsOfExperience = yearsOfExperience;
        this.permissibleGrades = permissibleGrades;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public List<String> getPermissibleGrades() {
        return new ArrayList<>(permissibleGrades);
    }
}
