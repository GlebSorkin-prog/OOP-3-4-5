package sample;

public abstract class ManagingEmployee extends Employee {

    private transient String department;

    public ManagingEmployee(String name, String surname, int age, int number, double skill) {
        super(name, surname, age, number, skill);
        this.department="controller";
    }

    public ManagingEmployee(){

    }

    @Override
    public void setSalary(double salary) {
        super.setSalary(salary);
    }

    @Override
    public double getSalary() {
        return super.getSalary();
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDepartment(){
        return this.department;
    }
}
