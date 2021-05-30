package sample;

public class Janitor extends WorkingEmployee {
    private double coefficient=1;
    private String department="janitor";

    public Janitor(String name, String surname, int age, int number, double skill) {
        super(name, surname, age, number, skill);
    }

    public Janitor(){

    }

    public void setSalary(double salary) {
        super.setSalary(salary/(coefficient+super.getIdCard().getSkill()));
    }

    @Override
    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public double getSalary() {
        return Math.round(super.getSalary()*(this.coefficient+super.getIdCard().getSkill())*Math.pow(10,2))/Math.pow(10,2);
    }

    @Override
    public String getDepartment(){
        return this.department;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    @Override
    public String toString() {
        return "Employee: name-"+getName()+", surname-"+getSurname()+", age-"+getAge()
                +", IDcard-"+getIdCard().getNumber()+", skill-"+getIdCard().getSkill()
                +", coefficient-"+getCoefficient()+", department-"+getDepartment()+", salary-"+getSalary();
    }
}
