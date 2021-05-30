package sample;

import java.io.Serializable;

public class IdCard implements Serializable {
    private int number;
    private double skill;

    public IdCard(int number, double skill) {
        this.number = number;
        this.skill = skill;
    }

    public IdCard(){

    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getSkill() {
        return skill;
    }

    public void setSkill(double skill) {
        this.skill = skill;
    }
}
