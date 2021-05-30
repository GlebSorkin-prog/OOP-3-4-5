package sample;

public class Manager extends ManagingEmployee {

    private String department="manager";
    private double coefficient=2.5;

    public Manager(String name, String surname, int age, int number, double skill) {
        super(name, surname, age, number, skill);
    }

    public Manager(){

    }

    @Override
    public void setSalary(double salary) {
        super.setSalary(salary/(coefficient+super.getIdCard().getSkill()));
    }

    @Override
    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String getDepartment(){
        return this.department;
    }

    @Override
    public double getSalary() {
        return Math.round(super.getSalary()*(this.coefficient+super.getIdCard().getSkill())*Math.pow(10,2))/Math.pow(10,2);
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
