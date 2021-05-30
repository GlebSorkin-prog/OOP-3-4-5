package sample;

import java.util.List;

public class Document {

    private String type;
    private String info;

    public Document(String type){
        type=type.toLowerCase();
        switch (type){
            case "add":
                type="Add";
                break;
            case "remove":
                type="Remove";
                break;
            case "change employee":
                type="Change employee data";
                break;
            case "change employees":
                type="Change employees";
                break;
            case "change name":
                type="Change name";
                break;
            default:
                type="Created!";
                info="";
                break;
        }
        this.type=type;
    }

    void addEmployee(Employee employee){
        RestaurantSingleton.getInstance().getEmployees().add(employee);
        this.info=employee.toString();
    }

    void removeEmployee(Employee employee){
        RestaurantSingleton.getInstance().getEmployees().remove(employee);
        this.info=employee.toString();
    }

    void changeEmployeeData(Employee employee, int index){
        RestaurantSingleton.getInstance().getEmployees().set(index,employee);
        this.info=employee.toString();
    }

    void setName(String name){
        RestaurantSingleton.getInstance().setName(name);
        this.info=name;
    }

    void setEmployees(List<Employee> employees){
        RestaurantSingleton.getInstance().setEmployees(employees);
        this.info=employees.size()+" employees";
    }

    @Override
    public String toString() {
        return type.concat(": ").concat(info);
    }
}
