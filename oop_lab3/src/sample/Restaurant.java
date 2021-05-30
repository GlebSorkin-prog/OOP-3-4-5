package sample;

import java.util.LinkedList;
import java.util.List;

public class Restaurant {
    private String name;
    private List<Employee> employees = new LinkedList<>();

    Restaurant(String name, List<Employee> employees){
        this.name=name;
        this.employees=employees;
    }

    public Restaurant() {
    }
}
