package sample;

public abstract class WorkingEmployee extends Employee {

    private transient String department;

    public WorkingEmployee(String name, String surname, int age, int number, double skill) {
        super(name, surname, age, number, skill);
        this.department="worker";
    }

    public WorkingEmployee(){

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

    @Override
    public String toString() {
        return "Employee: name-"+getName()+", surname-"+getSurname()+", age-"+getAge()
                +", IDcard-"+getIdCard().getNumber()+",salary-"+getSalary()+", department-"+getDepartment();
    }
}
