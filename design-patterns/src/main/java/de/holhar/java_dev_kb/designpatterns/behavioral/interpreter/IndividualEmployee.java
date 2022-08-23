package de.holhar.java_dev_kb.designpatterns.behavioral.interpreter;

public class IndividualEmployee implements Employee {

    private final int yearsOfExperience;
    private final String currentGrade;

    public IndividualEmployee(int yearsOfExperience, String currentGrade) {
        this.yearsOfExperience = yearsOfExperience;
        this.currentGrade = currentGrade;
    }

    @Override
    public boolean interpret(Context context) {
        return this.yearsOfExperience >= context.getYearsOfExperience()
            && context.getPermissibleGrades().contains(this.currentGrade);
    }
}
