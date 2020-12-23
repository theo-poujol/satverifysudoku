package com.company.Exercice1.SatVerifieur;

import java.util.Arrays;

public class SatFormula {
    private int nb_var;
    private int[] formula;
    private boolean[] affects;
    private boolean result;


    public SatFormula(int nb_var, int[] formula, boolean[] affects) {
        this.nb_var = nb_var;
        this.formula = formula;
        this.affects = affects;
    }

    public SatFormula(int nb_var) {
        this.formula = new int[nb_var];
        this.nb_var = nb_var;
    }

    public int getNb_var() {
        return nb_var;
    }

    public void setNb_var(int nb_var) {
        this.nb_var = nb_var;
    }

    public boolean[] getAffects() {
        return affects;
    }

    public void setAffects(boolean[] affects) {
        this.affects = affects;
    }

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public int[] getFormula() { return this.formula; }

    public void setFormula(int[] formula) { this.formula = formula; }

    @Override
    public String toString() {
        return (Arrays.toString(this.formula));
    }
}
