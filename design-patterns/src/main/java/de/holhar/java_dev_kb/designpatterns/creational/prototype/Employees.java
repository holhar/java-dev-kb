package de.holhar.java_dev_kb.designpatterns.creational.prototype;

import java.util.ArrayList;
import java.util.List;

public class Employees implements Cloneable {

  private final List<String> employeeList;

  public Employees() {
    this.employeeList = new ArrayList<>();
  }

  public Employees(List<String> employeeList) {
    this.employeeList = new ArrayList<>(employeeList);
  }

  public void loadData() {
    // Read all employees from db and put into list
    employeeList.add("John");
    employeeList.add("Paul");
    employeeList.add("Ringo");
    employeeList.add("George");
  }

  public List<String> getEmployeeList() {
    return new ArrayList<>(employeeList);
  }

  @Override
  public Object clone() {
    List<String> temp = new ArrayList<>(this.getEmployeeList());
    return new Employees(temp);
  }
}
