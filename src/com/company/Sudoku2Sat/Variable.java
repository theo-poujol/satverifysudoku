package com.company.Sudoku2Sat;

public class Variable {

    private String name;
    private int num;
    private boolean value;

    public Variable(String name, int num, boolean value) {
        this.name = name;
        this.num = num;
        this.value = value;
    }

    public Variable(String name, int num) {
        this.name = name;
        this.num = num;
    }


    public Variable(Variable var) {
        this.name = var.getName();
        this.num = var.getNum();
        this.value = var.getValue();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}
