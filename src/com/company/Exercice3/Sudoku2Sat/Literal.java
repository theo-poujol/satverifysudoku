package com.company.Sudoku2Sat;

/** Représente un littéral d’une clause **/
public class Literal {

    /** Variable du littéral **/
    Variable var;

    /** Signe du littéral false = - **/
    boolean sign;

    public Literal(Variable var, boolean sign) {
        this.var = var;
        this.sign = sign;
    }

    public Literal(String name, int num, boolean sign) {
        this.var = new Variable(name, num);
        this.sign = sign;
    }

    public Variable getVar() { return this.var; }

    public boolean getSign() { return this.sign; }
}