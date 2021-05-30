package sample;

import com.google.gson.annotations.SerializedName;

public abstract class Employee extends Person {

    @SerializedName("type")
    private String typeName;

    private double salary;
    private IdCard idCard;

    public Employee(String name, String surname, int age, IdCard idCard){
        super(name,surname,age);
        salary=700;
        this.idCard=idCard;
        typeName = getClass().getName();
    }

    public Employee(String name, String surname, int age, int number, double skill){
        super(name,surname,age);
        salary=700;
        this.idCard=new IdCard(number,skill);
        typeName = getClass().getName();
    }

    public Employee(){

    }

    public IdCard getIdCard() {
        return idCard;
    }

    public void setIdCard(IdCard idCard) {
        this.idCard = idCard;
    }

    public double getSalary(){
        return salary;
    }

    public void setSalary(double salary){
        this.salary=salary;
    }
}
