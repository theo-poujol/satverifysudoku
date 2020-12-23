package com.company.Exercice3.Sudoku2Sat;


/** Représente la variable présente dans le littéral Literal. **/

public class Variable {

    /** Nom de la variable **/
    private String name;

    /** Numéro **/
    private int num;

    /** Valeur de la variable dans la formule **/
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