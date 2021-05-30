package sample;

import java.util.LinkedList;
import java.util.List;

public class Director implements Leadership {

    private List<Document> documents=new LinkedList<>();

    Director(){
        documents.add(new Document(""));
    }

    @Override
    public void makeChanges(String type, Employee employee, int index, String name, List<Employee> employees) {
        type=type.toLowerCase();
        System.out.println(type);
        switch (type){
            case ("add employee"):
                Document document=new Document("add");
                document.addEmployee(employee);
                this.documents.add(document);
                break;
            case ("remove employee"):
                Document document2=new Document("remove");
                document2.removeEmployee(employee);
                this.documents.add(document2);
                break;
            case ("change employee data"):
                Document document3=new Document("change employee");
                document3.changeEmployeeData(employee,index);
                this.documents.add(document3);
                break;
            case "change employees list":
                Document document4=new Document("change employees");
                document4.setEmployees(employees);
                this.documents.add(document4);
                break;
            case ("change restaurant name"):
                Document document5=new Document("change name");
                document5.setName(name);
                this.documents.add(document5);
                break;
        }
    }

    String getName(){
        return RestaurantSingleton.getInstance().getName();
    }

    List<Employee> getEmployees(){
        return RestaurantSingleton.getInstance().getEmployees();
    }
}
