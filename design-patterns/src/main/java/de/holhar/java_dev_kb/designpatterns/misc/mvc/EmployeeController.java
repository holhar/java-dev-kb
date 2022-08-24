package de.holhar.java_dev_kb.designpatterns.misc.mvc;

public class EmployeeController {

    private final Employee employee;
    private final EmployeeView employeeView;

    public EmployeeController(Employee employee, EmployeeView employeeView) {
        this.employee = employee;
        this.employeeView = employeeView;
    }
    
    public void setEmployeeId (int id) {
        employee.setId(id);
        updateView();
    }

    public int getEmployeeId () {
        return employee.getId();
    }

    public void setEmployeeName (String name) {
        employee.setName(name);
        updateView();
    }

    public String getEmployeeName () {
        return employee.getName();
    }

    public void setEmployeeJob (String job) {
        employee.setJob(job);
        updateView();
    }

    public String getEmployeeJob() {
        return employee.getJob();
    }

    public void updateView(){
        employeeView.printEmployeeInformation(employee);
    }
}
