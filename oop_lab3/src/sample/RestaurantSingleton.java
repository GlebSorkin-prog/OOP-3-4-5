package sample;

import java.util.LinkedList;
import java.util.List;

public class RestaurantSingleton {

    private static RestaurantSingleton instance;

    private String name;
    private List<Employee> employees = new LinkedList<>();

    private RestaurantSingleton(){

    }

    static synchronized RestaurantSingleton getInstance(){
        if(instance == null){
            instance = new RestaurantSingleton();
        }
        return instance;
    }

    public synchronized String getName() {
        return name;
    }

    public synchronized void setName(String name) {
        this.name = name;
    }

    synchronized List<Employee> getEmployees() {
        return employees;
    }

    synchronized void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
